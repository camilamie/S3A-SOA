package br.com.fiap.s3a.repository;

import br.com.fiap.s3a.domain.model.Alerta;
import br.com.fiap.s3a.domain.enums.NivelAlerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
    List<Alerta> findByMiniDroidId(Long miniDroidId);
    List<Alerta> findByResolvidoFalse();
    List<Alerta> findByNivel(NivelAlerta nivel);
    List<Alerta> findByMiniDroidMissaoId(Long missaoId);
}