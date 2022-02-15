package com.boasaude.associados.repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import com.boasaude.associados.entity.ConsultaEntity;

import reactor.core.publisher.Flux;

@Repository
@RequiredArgsConstructor
public class HistoricoConsultaRepository {

    public Flux<ConsultaEntity> buscarHistoricoConsultaAssociado(String id) {

        return Flux.fromIterable(criarListaHistoricoConsulta()
                .stream()
                .filter(consultaEntity -> id.equals(consultaEntity.getIdAssociado()))
                .collect(Collectors.toList()));
    }

    private List<ConsultaEntity> criarListaHistoricoConsulta() {

        return Arrays.asList(ConsultaEntity
                .builder()
                .id("234533")
                .idAssociado("123456")
                .nome("Consulta")
                .descricao("Consulta de rotina")
                .especialidade("Ginecologia")
                .medico("Laura Renz")
                .data(LocalDateTime.now())
                .build(), ConsultaEntity
                        .builder()
                        .id("345632")
                        .idAssociado("123456")
                        .nome("Exame")
                        .descricao("Exame de Mamografia")
                        .especialidade("Ginecologia")
                        .medico("Pedro Silva")
                        .data(LocalDateTime.now())
                        .build(), ConsultaEntity
                                .builder()
                                .id("342432")
                                .idAssociado("654321")
                                .nome("Consulta")
                                .descricao("Avaliação pediatrica")
                                .especialidade("Pediatra")
                                .medico("Carla Silva")
                                .data(LocalDateTime.now())
                                .build());
    }
}
