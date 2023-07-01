package lab05.exceptions;

public class CadastroException extends RuntimeException {
    public CadastroException(String message) {
        super(message);
    }

    public CadastroException(String format, Object... args) {
        super(String.format(format, args));
    }
}
