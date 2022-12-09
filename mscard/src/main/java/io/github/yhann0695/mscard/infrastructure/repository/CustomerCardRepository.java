package io.github.yhann0695.mscard.infrastructure.repository;

import io.github.yhann0695.mscard.domain.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Long> {
    List<CustomerCard> findByCpf(String cpf);
}
