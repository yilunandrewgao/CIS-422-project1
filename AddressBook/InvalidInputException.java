package AddressBook;

/**
 * Created by yilungao on 10/12/16.
 */
public class InvalidInputException extends Exception {

    private int invalidFieldNum;

    public InvalidInputException(String message,int number) {
        super(message);

        this.invalidFieldNum = number;
    }

    public int getInvalidFieldNum() {
        return this.invalidFieldNum;
    }
}
