package com.boasaude.associados.service;

import static org.mockito.Mockito.when;

import java.util.concurrent.BlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.test.binder.MessageCollector;
import org.springframework.messaging.Message;

import com.boasaude.associados.exception.BadRequestException;
import com.boasaude.associados.exception.NotFoundException;
import com.boasaude.associados.helper.AssociadoHelper;
import com.boasaude.associados.messaging.binding.EmissaoCobrancaSegundaViaCarteiraEventChannel;
import com.boasaude.associados.messaging.binding.SolicitacaoImpressaoCarteiraEventChannel;
import com.boasaude.associados.repository.AssociadoRepository;
import com.boasaude.associados.request.SolicitacaoCarteiraRequest;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class CarteirinhaServiceTest {

    @Autowired
    private CarteirinhaService carteirinhaService;

    @MockBean
    private AssociadoRepository associadoRepository;

    @Autowired
    protected MessageCollector collector;

    @Autowired
    private EmissaoCobrancaSegundaViaCarteiraEventChannel emissaoCobrancaSegundaViaCarteiraEventChannel;

    @Autowired
    private SolicitacaoImpressaoCarteiraEventChannel solicitacaoImpressaoCarteiraEventChannel;

    @Test
    public void naoDeveEmitirCarteiraUsuarioNaoEncontrado() {

        var solicitacaoCarteiraRequest = SolicitacaoCarteiraRequest.builder().id("9999999").build();

        when(associadoRepository.buscarAssociadoPorId(solicitacaoCarteiraRequest.getId()))
                .thenReturn(Mono.empty());

        StepVerifier.create(carteirinhaService.solicitarSegundaViaCarteirinha(solicitacaoCarteiraRequest))
                .expectErrorMatches(ex -> ex instanceof NotFoundException
                        && ((NotFoundException) ex).getReason().equals("Associado não encontrado."))
                .verify();
    }

    @Test
    public void naoDeveEmitirCarteiraUsuarioInativo() {

        var solicitacaoCarteiraRequest = SolicitacaoCarteiraRequest.builder().id("12345678").build();

        var associadoEntity = AssociadoHelper.criarAssociadoInativo();

        when(associadoRepository.buscarAssociadoPorId(solicitacaoCarteiraRequest.getId()))
                .thenReturn(Mono.just(associadoEntity));

        StepVerifier.create(carteirinhaService.solicitarSegundaViaCarteirinha(solicitacaoCarteiraRequest))
                .expectErrorMatches(ex -> ex instanceof BadRequestException
                        && ((BadRequestException) ex).getReason().equals("Não é possível solicitar segunda via de carteira para este associado."))
                .verify();
    }

    @Test
    public void deveEmitirCarteiraComSucesso() {

        final BlockingQueue<Message<?>> channelImpressaoCarteira = collector.forChannel(solicitacaoImpressaoCarteiraEventChannel
                .solicitacaoImpressaoCarteiraOutput());
        channelImpressaoCarteira.clear();

        final BlockingQueue<Message<?>> channelEmissaoCobranca = collector.forChannel(emissaoCobrancaSegundaViaCarteiraEventChannel
                .emissaoCobrancaSegundaViaCarteiraOutput());
        channelEmissaoCobranca.clear();

        var solicitacaoCarteiraRequest = SolicitacaoCarteiraRequest.builder().id("12345678").build();

        var associadoEntity = AssociadoHelper.criarAssociadoAtivo();

        when(associadoRepository.buscarAssociadoPorId(solicitacaoCarteiraRequest.getId()))
                .thenReturn(Mono.just(associadoEntity));

        var solicitacaoCarteiraResponse = carteirinhaService.solicitarSegundaViaCarteirinha(solicitacaoCarteiraRequest).block();

        Assertions.assertNotNull(solicitacaoCarteiraResponse);
        Assertions.assertEquals("Solicitação registrada com sucesso. Você receberá seu novo cartão nos próximos dias.", solicitacaoCarteiraResponse
                .getMensagem());
        Assertions.assertEquals(1, channelImpressaoCarteira.size());
        Assertions.assertEquals(1, channelEmissaoCobranca.size());
    }
}
