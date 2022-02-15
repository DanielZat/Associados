package com.boasaude.associados.mapper;

import com.boasaude.associados.entity.ConsultaEntity;
import com.boasaude.associados.response.ConsultaResponse;

public class ConsultaMapper {

    public static ConsultaResponse converterEntityEmResponse(ConsultaEntity consultaEntity) {
        return ConsultaResponse.builder()
                .id(consultaEntity.getId())
                .nome(consultaEntity.getNome())
                .descricao(consultaEntity.getDescricao())
                .especialidade(consultaEntity.getEspecialidade())
                .medico(consultaEntity.getMedico())
                .data(consultaEntity.getData())
                .build();
    }
}
