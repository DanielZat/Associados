package com.boasaude.associados.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import com.boasaude.associados.response.ConsultaResponse;
import com.boasaude.associados.service.HistoricoConsultaService;

import reactor.core.publisher.Flux;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RequestMapping("/historico")
@RequiredArgsConstructor
@RestController
public class HistoricoConsultaController {

    private final HistoricoConsultaService historicoConsultaService;

    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Histórico localizado com sucesso.", content = @Content(schema = @Schema(
                    implementation = ConsultaResponse.class))),
            @ApiResponse(responseCode = "404", description = "Nenhuma consulta encontrada para este associado.")
    })
    public Flux<ConsultaResponse> buscarHistoricoConsultaAssociado(@PathVariable("id") String id) {
        return historicoConsultaService.buscarHistoricoConsultaAssociado(id);
    }
}
