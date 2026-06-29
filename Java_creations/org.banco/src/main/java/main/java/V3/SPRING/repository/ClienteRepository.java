package main.java.V3.SPRING.repository;

import main.java.V3.SPRING.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByCpf(String cpf);
    Optional<Cliente> findByLogin(String login);
    Optional<Cliente> findByEmail(String email);
    boolean existsByCpf(String cpf);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}