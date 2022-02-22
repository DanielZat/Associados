package com.boasaude.associados.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boasaude.associados.response.SolicitacaoCarteiraResponse;

import reactor.core.publisher.Mono;

@RequestMapping("/teste")
@RequiredArgsConstructor
@RestController
public class TesteController {

    @GetMapping()
    public Mono<SolicitacaoCarteiraResponse> teste() {
        return Mono.just(SolicitacaoCarteiraResponse.builder().mensagem("teste para uso do kong").build());
    }
}
