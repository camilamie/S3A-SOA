package br.com.fiap.s3a.repository;

import br.com.fiap.s3a.domain.model.Missao;
import br.com.fiap.s3a.domain.enums.StatusMissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MissaoRepository extends JpaRepository<Missao, Long> {
    Optional<Missao> findByNome(String nome);
    List<Missao> findByStatus(StatusMissao status);
}