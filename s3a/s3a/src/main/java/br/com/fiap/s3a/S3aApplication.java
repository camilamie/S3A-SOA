package br.com.fiap.s3a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class S3aApplication {

	public static void main(String[] args) {
		SpringApplication.run(S3aApplication.class, args);
		System.out.println("""
                ╔══════════════════════════════════════════════════════╗
                ║        S³A — Sondas Sísmicas Autônomas               ║
                ║              FIAP Global Solution                    ║
                ╠══════════════════════════════════════════════════════╣
                ║  H2 Console  → http://localhost:8080/h2-console      ║
                ╚══════════════════════════════════════════════════════╝
                """);
	}
}