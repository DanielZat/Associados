package com.boasaude.associados.entity;

import java.time.LocalDateTime;

import lombok.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ConsultaEntity {

    private String id;

    private String idAssociado;

    private String nome;

    private String descricao;

    private String medico;

    private String especialidade;

    private LocalDateTime data;
}
