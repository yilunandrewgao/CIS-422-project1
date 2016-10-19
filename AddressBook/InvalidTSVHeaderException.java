package AddressBook;

/**
 * Created by yilungao on 10/18/16.
 */
public class InvalidTSVHeaderException extends Exception {

    public InvalidTSVHeaderException() {
        super("Selected .tsv file does not have appropriate headers");
    }

}
