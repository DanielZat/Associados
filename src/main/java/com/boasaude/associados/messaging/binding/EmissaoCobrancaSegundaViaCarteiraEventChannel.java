package com.boasaude.associados.messaging.binding;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

import com.boasaude.associados.messaging.rabbitbinding.RabbitBinding;

public interface EmissaoCobrancaSegundaViaCarteiraEventChannel {

    @Output(RabbitBinding.SOLICITAR_COBRANCA_SEGUNDA_VIA_CARTEIRA_OUTPUT)
    MessageChannel emissaoCobrancaSegundaViaCarteiraOutput();
}
