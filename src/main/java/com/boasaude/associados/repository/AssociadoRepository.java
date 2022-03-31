package com.boasaude.associados.repository;

import static com.boasaude.associados.enums.SexoEnum.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;

import com.boasaude.associados.entity.AssociadoEntity;
import com.boasaude.associados.entity.EnderecoEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
@RequiredArgsConstructor
public class AssociadoRepository {

    public Mono<AssociadoEntity> buscarAssociadoPorId(String id) {

        return criarListaAssociados()
                .filter(associadoEntity -> id.equals(associadoEntity.getId()))
                .single();
    }

    private Flux<AssociadoEntity> criarListaAssociados() {

        return Flux.fromIterable(Arrays.asList(AssociadoEntity.builder()
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
                .build(), AssociadoEntity.builder()
                        .id("87654321")
                        .nome("Jose Carlos Silva")
                        .cpf("03747734002")
                        .dataNascimento(LocalDateTime.now())
                        .sexo(MASCULINO)
                        .ativo(true)
                        .endereco(EnderecoEntity.builder()
                                .id("56346323")
                                .rua("Clóvis Bevilaqua")
                                .numero(2170)
                                .complemento("Casa")
                                .bairro("Harmonia")
                                .cidade("Canoas")
                                .estado("RS")
                                .build())
                        .build()));
    }
}
