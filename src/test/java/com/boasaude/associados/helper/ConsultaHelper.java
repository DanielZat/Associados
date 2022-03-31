package com.boasaude.associados.helper;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import com.boasaude.associados.entity.ConsultaEntity;

public class ConsultaHelper {

    public static List<ConsultaEntity> criarListaHistoricoConsulta() {

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
                        .build());
    }
}
