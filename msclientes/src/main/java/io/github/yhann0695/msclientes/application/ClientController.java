package io.github.yhann0695.msclientes.application;

import io.github.yhann0695.msclientes.application.representation.ClientSaveRequest;
import io.github.yhann0695.msclientes.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/customers")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientSaveRequest request) {
        Client client = ClientSaveRequest.of(request);
        clientService.save(client);

        URI hearderLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .query("cpf={cpf}")
                .buildAndExpand(client.getCpf())
                .toUri();
        return ResponseEntity.created(hearderLocation).build();
    }

    @GetMapping
    public ResponseEntity<Optional<Client>> detailsClient(@RequestParam("cpf") String cpf) {
        var client = clientService.findClientByCPF(cpf);
        if (client.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(client);
    }
}
