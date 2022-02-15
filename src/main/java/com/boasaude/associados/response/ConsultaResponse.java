package com.boasaude.associados.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class ConsultaResponse {

    private String id;

    private String nome;

    private String descricao;

    private String medico;

    private String especialidade;

    private LocalDateTime data;
}
