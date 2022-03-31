package com.boasaude.associados.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.boasaude.associados.request.SolicitacaoCarteiraRequest;
import com.boasaude.associados.response.SolicitacaoCarteiraResponse;
import com.boasaude.associados.service.CarteirinhaService;

import reactor.core.publisher.Mono;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/carteirinha")
@RequiredArgsConstructor
@RestController
public class CarteirinhaController {

    private final CarteirinhaService carteirinhaService;

    @PostMapping()
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitação de segunda via de carteirinha criada com sucesso.", content = @Content(
                    schema = @Schema(
                            implementation = SolicitacaoCarteiraResponse.class))),
            @ApiResponse(responseCode = "400", description = "Não é possível solicitar segunda via de carteira para este associado."),
            @ApiResponse(responseCode = "404", description = "Associado não encontrado.")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<SolicitacaoCarteiraResponse> solicitarSegundaViaCarteirinha(@RequestBody SolicitacaoCarteiraRequest solicitacaoCarteiraRequest) {
        return carteirinhaService.solicitarSegundaViaCarteirinha(solicitacaoCarteiraRequest);
    }
}
