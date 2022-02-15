package com.boasaude.associados.messaging.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.boasaude.associados.messaging.rabbitbinding.RabbitBinding;

public interface SolicitacaoImpressaoCarteiraEventChannel {

    @Output(RabbitBinding.SOLICITAR_IMPRESSAO_CARTEIRA_OUTPUT)
    MessageChannel solicitacaoImpressaoCarteiraOutput();
}
