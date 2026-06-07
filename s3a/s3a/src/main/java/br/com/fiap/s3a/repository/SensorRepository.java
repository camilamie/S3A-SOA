package br.com.fiap.s3a.repository;

import br.com.fiap.s3a.domain.model.Sensor;
import br.com.fiap.s3a.domain.enums.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByMiniDroidId(Long miniDroidId);
    List<Sensor> findByMiniDroidIdAndAtivo(Long miniDroidId, boolean ativo);
    List<Sensor> findByTipo(TipoSensor tipo);
}