package com.boasaude.associados.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class EnderecoEntity {

    private String id;

    private String rua;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;
}
