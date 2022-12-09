package io.github.yhann0695.msclientes.application;

import io.github.yhann0695.msclientes.domain.Client;
import io.github.yhann0695.msclientes.infrastructure.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClientByCPF(String cpf) {
        return clientRepository.findByCpf(cpf);
    }
}
