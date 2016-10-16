package AddressBook;

import java.util.ArrayList;

/**
 * Created by yilungao on 10/12/16.
 */
public class InvalidInputException extends Exception {


    private String message = "";

    public InvalidInputException(ArrayList<InputError> errorList) {

        for (InputError error : errorList) {
            message += error.getMessage() + "\n";
        }

    }

    @Override
    public String getMessage(){
        return message;
    }

}
