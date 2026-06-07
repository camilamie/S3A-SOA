package br.com.fiap.s3a.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public RecursoNaoEncontradoException(String tipo, Long id) {
        super(tipo + " com ID " + id + " não encontrado(a).");
    }
}