//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class BranchOperation extends VcsOperation {

    public BranchOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * executes the command
     */
    public int execute(Vcs vcs) {
        String arg = operationArgs.get(1);
        /*
         * if the branch exists the
         * error code is returned
         */
        if (vcs.getBranches().containsKey(arg)) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }
        //a new branch is made and added to the list
        Branch newBranch = new Branch(arg, vcs.getCurrentBranch());
        vcs.addBranch(newBranch);
        return 0;

    }

}
