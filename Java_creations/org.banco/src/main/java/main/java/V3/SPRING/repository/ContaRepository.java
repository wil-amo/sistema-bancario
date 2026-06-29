package main.java.V3.SPRING.repository;

import main.java.V3.SPRING.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumero(Integer numero);
    Optional<Conta> findByClienteCpf(String cpf);
    boolean existsByNumero(Integer numero);
}