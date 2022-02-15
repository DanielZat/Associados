package com.boasaude.associados.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
public enum SexoEnum {

    MASCULINO("M", "Masculino"), FEMININO("F", "Feminino"), OUTRO("O",
            "Outro");

    private final @Getter String value;

    private final @Getter String externalValue;
}
