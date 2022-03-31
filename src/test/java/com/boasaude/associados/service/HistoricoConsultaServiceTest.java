package com.boasaude.associados.service;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.boasaude.associados.exception.NotFoundException;
import com.boasaude.associados.helper.ConsultaHelper;
import com.boasaude.associados.repository.HistoricoConsultaRepository;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {"spring.main.webApplicationType=reactive"})
public class HistoricoConsultaServiceTest {

    @Autowired
    private HistoricoConsultaService historicoConsultaService;

    @MockBean
    private HistoricoConsultaRepository historicoConsultaRepository;

    @Test
    public void deveRetornarHistoricoComSucesso() {

        String id = "123456";

        var consultaEntities = Flux.fromIterable(ConsultaHelper.criarListaHistoricoConsulta());

        Mockito.when(historicoConsultaRepository.buscarHistoricoConsultaAssociado(id)).thenReturn(consultaEntities);

        var listaConsultaResponse = historicoConsultaService.buscarHistoricoConsultaAssociado(id).toStream().collect(Collectors.toList());

        Assertions.assertNotNull(listaConsultaResponse);
        Assertions.assertEquals(2, listaConsultaResponse.size());
        Assertions.assertEquals("234533", listaConsultaResponse.get(0).getId());
        Assertions.assertEquals("Consulta", listaConsultaResponse.get(0).getNome());
        Assertions.assertEquals("Consulta de rotina", listaConsultaResponse.get(0).getDescricao());
        Assertions.assertEquals("Laura Renz", listaConsultaResponse.get(0).getMedico());
        Assertions.assertEquals("Ginecologia", listaConsultaResponse.get(0).getEspecialidade());
        Assertions.assertEquals("345632", listaConsultaResponse.get(1).getId());
        Assertions.assertEquals("Exame", listaConsultaResponse.get(1).getNome());
        Assertions.assertEquals("Exame de Mamografia", listaConsultaResponse.get(1).getDescricao());
        Assertions.assertEquals("Pedro Silva", listaConsultaResponse.get(1).getMedico());
        Assertions.assertEquals("Ginecologia", listaConsultaResponse.get(1).getEspecialidade());
    }

    @Test
    public void naoDeveRetornarHistoricoNenhumaConsultaEncontradaParaAssociado() {

        String id = "999999";

        Mockito.when(historicoConsultaRepository.buscarHistoricoConsultaAssociado(id)).thenReturn(Flux.empty());

        StepVerifier.create(historicoConsultaService.buscarHistoricoConsultaAssociado(id))
                .expectErrorMatches(ex -> ex instanceof NotFoundException
                        && ((NotFoundException) ex).getReason().equals("Nenhuma consulta encontrada para este associado."))
                .verify();
    }
}
