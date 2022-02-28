package com.boasaude.associados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import com.boasaude.associados.messaging.binding.EmissaoCobrancaSegundaViaCarteiraEventChannel;
import com.boasaude.associados.messaging.binding.SolicitacaoImpressaoCarteiraEventChannel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Boa Saúde - Associados", version = "1.0", description = "Documentação do microsserviço de associados"))
@EnableBinding({SolicitacaoImpressaoCarteiraEventChannel.class, EmissaoCobrancaSegundaViaCarteiraEventChannel.class})
public class AssociadosApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociadosApplication.class, args);
    }

}
