package AddressBook;

/**
 * Created by yilungao on 10/16/16.
 */
public class InputError {

    private String message;
    private int fieldNum;

    public InputError(String _message, int _fieldNum) {
        message = _message;
        fieldNum = _fieldNum;
    }

    public String getMessage() {
        return message;
    }

    public int getInvalidFieldNum() {
        return fieldNum;
    }

    @Override
    public String toString() {
        return "error" + fieldNum + "\n";
    }
}
