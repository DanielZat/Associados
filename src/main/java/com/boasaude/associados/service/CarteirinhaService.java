package com.boasaude.associados.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.boasaude.associados.exception.BadRequestException;
import com.boasaude.associados.messaging.binding.EmissaoCobrancaSegundaViaCarteiraEventChannel;
import com.boasaude.associados.messaging.binding.SolicitacaoImpressaoCarteiraEventChannel;
import com.boasaude.associados.messaging.model.SolicitacaoCobrancaSegundaViaPayload;
import com.boasaude.associados.messaging.model.SolicitacaoImpressaoCarteiraPayload;
import com.boasaude.associados.repository.AssociadoRepository;
import com.boasaude.associados.request.SolicitacaoCarteiraRequest;
import com.boasaude.associados.response.SolicitacaoCarteiraResponse;

import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
@Slf4j
public class CarteirinhaService {

    private final AssociadoRepository associadoRepository;

    private final SolicitacaoImpressaoCarteiraEventChannel solicitacaoCarteiraEventChannel;

    private final EmissaoCobrancaSegundaViaCarteiraEventChannel emissaoCobrancaSegundaViaCarteiraEventChannel;

    public Mono<SolicitacaoCarteiraResponse> solicitarSegundaViaCarteirinha(SolicitacaoCarteiraRequest solicitacaoCarteiraRequest) {

        return associadoRepository.buscarAssociadoPorId(solicitacaoCarteiraRequest.getId())
                .filter(associadoEntity -> Boolean.TRUE.equals(associadoEntity.getAtivo()))
                .map(associadoEntity -> {
                    solicitarImpressaoCarteirinha(associadoEntity.getId());
                    emitirCobrancaSegundaViaCarteirinha(associadoEntity.getId());
                    return SolicitacaoCarteiraResponse.builder()
                            .mensagem("Solicitação registrada com sucesso. Você receberá seu novo cartão nos próximos dias")
                            .build();
                })
                .switchIfEmpty(Mono.error(new BadRequestException("Não é possível solicitar segunda via de carteira para este associado")));
    }

    public void solicitarImpressaoCarteirinha(String id) {

        solicitacaoCarteiraEventChannel.solicitacaoImpressaoCarteiraOutput()
                .send(MessageBuilder.withPayload(SolicitacaoImpressaoCarteiraPayload.builder().id(id).build())
                        .build());
    }

    public void emitirCobrancaSegundaViaCarteirinha(String id) {

        emissaoCobrancaSegundaViaCarteiraEventChannel.emissaoCobrancaSegundaViaCarteiraOutput()
                .send(MessageBuilder.withPayload(SolicitacaoCobrancaSegundaViaPayload.builder().id(id).build())
                        .build());
    }
}
