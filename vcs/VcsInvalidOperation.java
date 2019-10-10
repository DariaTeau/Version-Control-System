//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class VcsInvalidOperation extends VcsOperation {
    /**
     *
     * @param type
     * @param operationArgs
     */
    public VcsInvalidOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * return the error code
     */
    public int execute(Vcs vcs) {
        return ErrorCodeManager.VCS_BAD_CMD_CODE;
    }
}
