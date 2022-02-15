package com.boasaude.associados.entity;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.boasaude.associados.enums.SexoEnum;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
public class AssociadoEntity {

    private String id;

    private String nome;

    private String cpf;

    private SexoEnum sexo;

    private LocalDateTime dataNascimento;

    private EnderecoEntity endereco;

    private Boolean ativo;
}
