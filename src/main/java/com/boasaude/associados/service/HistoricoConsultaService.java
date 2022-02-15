package com.boasaude.associados.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.boasaude.associados.mapper.ConsultaMapper;
import com.boasaude.associados.repository.HistoricoConsultaRepository;
import com.boasaude.associados.response.ConsultaResponse;

import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
@Slf4j
public class HistoricoConsultaService {

    private final HistoricoConsultaRepository historicoConsultaRepository;

    public Flux<ConsultaResponse> buscarHistoricoConsultaAssociado(String id) {

        return historicoConsultaRepository.buscarHistoricoConsultaAssociado(id)
                .map(ConsultaMapper::converterEntityEmResponse);
    }
}
