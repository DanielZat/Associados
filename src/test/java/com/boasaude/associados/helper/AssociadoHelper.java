package com.boasaude.associados.helper;

import static com.boasaude.associados.enums.SexoEnum.MASCULINO;

import java.time.LocalDateTime;

import com.boasaude.associados.entity.AssociadoEntity;
import com.boasaude.associados.entity.EnderecoEntity;

public class AssociadoHelper {

    public static AssociadoEntity criarAssociadoAtivo() {

        return AssociadoEntity.builder()
                .id("12345678")
                .nome("Darci Oliveira")
                .cpf("60624654801")
                .dataNascimento(LocalDateTime.now())
                .sexo(MASCULINO)
                .ativo(true)
                .endereco(EnderecoEntity.builder()
                        .id("2342356")
                        .rua("Antônio Lourenço Rosa")
                        .numero(180)
                        .complemento("Apartamento 304 / Bloco A")
                        .bairro("Mato Grande")
                        .cidade("Canoas")
                        .estado("RS")
                        .build())
                .build();
    }

    public static AssociadoEntity criarAssociadoInativo() {

        return AssociadoEntity.builder()
                .id("12345678")
                .nome("Darci Oliveira")
                .cpf("60624654801")
                .dataNascimento(LocalDateTime.now())
                .sexo(MASCULINO)
                .ativo(false)
                .endereco(EnderecoEntity.builder()
                        .id("2342356")
                        .rua("Antônio Lourenço Rosa")
                        .numero(180)
                        .complemento("Apartamento 304 / Bloco A")
                        .bairro("Mato Grande")
                        .cidade("Canoas")
                        .estado("RS")
                        .build())
                .build();
    }
}
