package AddressBook;

/**
 * Created by yilungao on 10/12/16.
 */
public class TooLittleInputException extends Exception{
    public TooLittleInputException() {
        super("Please enter a first name or last name and another field.");
    }
}
