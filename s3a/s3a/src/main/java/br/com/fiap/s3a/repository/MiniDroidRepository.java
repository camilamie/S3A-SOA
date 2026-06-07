package br.com.fiap.s3a.repository;

import br.com.fiap.s3a.domain.model.MiniDroid;
import br.com.fiap.s3a.domain.enums.StatusDroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MiniDroidRepository extends JpaRepository<MiniDroid, Long> {
    Optional<MiniDroid> findByCodigo(String codigo);
    List<MiniDroid> findByMissaoId(Long missaoId);
    List<MiniDroid> findByStatus(StatusDroid status);

    @Query("SELECT d FROM MiniDroid d WHERE d.bateriaPercentual < :limite")
    List<MiniDroid> findDroidsComBateriaCritica(@Param("limite") double limite);
}