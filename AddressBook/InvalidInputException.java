/**
 * Created by yilungao on 10/12/16.
 */

package AddressBook;

import java.util.ArrayList;

/**
 * If an entry has too few fields input, an InvalidInputException will be thrown.
 */
public class InvalidInputException extends Exception {


    private String message = "";

    /**
     * Creates a new InvalidInputException with details designated by an ArrayList of possible errors.
     *
     * @param errorList an ArrayList of input errors
     */
    public InvalidInputException(ArrayList<InputError> errorList) {

        for (InputError error : errorList) {
            message += error.getMessage() + "\n";
        }

    }

    /**
     * Returns the message of this exception.
     *
     * @return  a String representing this exception.
     */
    @Override
    public String getMessage(){
        return message;
    }

}
