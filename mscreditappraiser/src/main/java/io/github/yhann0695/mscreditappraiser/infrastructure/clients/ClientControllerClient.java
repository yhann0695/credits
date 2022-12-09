package io.github.yhann0695.mscreditappraiser.infrastructure.clients;

import io.github.yhann0695.mscreditappraiser.domain.model.CustomerData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "msclients", path = "/customers")
public interface ClientControllerClient {

    @GetMapping(params = "cpf")
    ResponseEntity<CustomerData> detailsClient(@RequestParam("cpf") String cpf);
}
