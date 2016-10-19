/**
 * Created by yilungao on 10/16/16.
 */

package AddressBook;

/**
 * If unwanted input is detected for any of the fields, an InputError will be thrown.
 */
public class InputError {

    private String message;
    private int fieldNum;

    /**
     * Creates a new InputError with the specified message, for the field specified.
     *
     * @param _message  the message of this Exception
     * @param _fieldNum an int that represents the index of the field containing an error
     */
    public InputError(String _message, int _fieldNum) {
        message = _message;
        fieldNum = _fieldNum;
    }

    /**
     * Returns the message of this Exception
     *
     * @return  this Exception's message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the index of the field containing invalid input
     *
     * @return  an int representing the index of the correct field
     */
    public int getInvalidFieldNum() {
        return fieldNum;
    }

    /**
     * Returns String representation of this Exception.
     *
     * @return  a String representation of this Exception
     */
    @Override
    public String toString() {
        return "error" + fieldNum + "\n";
    }
}
