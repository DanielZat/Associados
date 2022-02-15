package com.boasaude.associados.messaging.rabbitbinding;

public final class RabbitBinding {

    private RabbitBinding() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static final String SOLICITAR_IMPRESSAO_CARTEIRA_OUTPUT = "solicitar-impressao-carteira-event";

    public static final String SOLICITAR_COBRANCA_SEGUNDA_VIA_CARTEIRA_OUTPUT = "solicitar-cobranca-segunda-via-carteira-event";
}
