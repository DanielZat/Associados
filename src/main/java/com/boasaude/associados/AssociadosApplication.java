package com.boasaude.associados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.boasaude.associados.messaging.binding.EmissaoCobrancaSegundaViaCarteiraEventChannel;
import com.boasaude.associados.messaging.binding.SolicitacaoImpressaoCarteiraEventChannel;

@SpringBootApplication
@EnableBinding({SolicitacaoImpressaoCarteiraEventChannel.class, EmissaoCobrancaSegundaViaCarteiraEventChannel.class})
public class AssociadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociadosApplication.class, args);
    }

}
