package io.github.yhann0695.mscard.application.service;

import io.github.yhann0695.mscard.domain.CustomerCard;
import io.github.yhann0695.mscard.infrastructure.repository.CustomerCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerCardService {
    private final CustomerCardRepository customerCardRepository;

    public List<CustomerCard> searchCardsByCpf(String cpf) {
        return customerCardRepository.findByCpf(cpf);
    }
}
