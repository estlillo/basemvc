package cl.barbatos.basemvc.exception;

public class NotFoundException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Record with id %s not found";

    public NotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
    }
}
