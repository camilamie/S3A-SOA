package br.com.fiap.s3a;

import br.com.fiap.s3a.domain.enums.StatusDroid;
import br.com.fiap.s3a.domain.model.MiniDroid;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class S3aApplicationTests {

	@Test
	@DisplayName("Contexto Spring carrega sem erros")
	void contextLoads() { }

	@Test
	@DisplayName("Droid detecta bateria crítica corretamente")
	void testBateriaCritica() {
		MiniDroid droid = MiniDroid.builder()
				.codigo("S3A-M-TEST").status(StatusDroid.ATIVO)
				.bateriaPercentual(10.0).build();

		assertTrue(droid.bateriaEmNivelCritico());
		assertFalse(droid.isOperacional());
	}

	@Test
	@DisplayName("Droid muda para FALHA ao esgotar bateria")
	void testConsumoBateria() {
		MiniDroid droid = MiniDroid.builder()
				.codigo("S3A-M-TEST2").status(StatusDroid.ATIVO)
				.bateriaPercentual(5.0).build();
		droid.consumirBateria(10.0);

		assertEquals(0.0, droid.getBateriaPercentual());
		assertEquals(StatusDroid.FALHA, droid.getStatus());
	}
}