package br.com.fiap.s3a.exception;

public class DroidInoperanteException extends RuntimeException {
    public DroidInoperanteException(String codigo) {
        super("MiniDroid " + codigo + " está inoperante. Operação bloqueada.");
    }
}