package requests;

public class RequestTypeException extends Exception {
    public RequestTypeException() {
        super("Тип запроса не поддерживает данный метод!");
    }
}
