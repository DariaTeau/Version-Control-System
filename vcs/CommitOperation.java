//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.AbstractOperation;
import utils.ErrorCodeManager;
import utils.OperationType;

public class CommitOperation extends VcsOperation {

    public CommitOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * executes the command
     */
    public int execute(Vcs vcs) {
        ArrayList<AbstractOperation> s = vcs.getStaging();
        //checks if the staging is empty
        if (s.isEmpty()) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        //builds the message according to the parameters
        StringBuilder b = new StringBuilder();
        for (int i = 2; i < operationArgs.size(); i++) {
            b.append(" ");
            b.append(operationArgs.get(i));
        }
        //makes a new commit
        Commit c = new Commit(b.toString(), vcs.getCurrentBranch(),
                vcs.getSnapshot().cloneFileSystem());

        //adds it to the list of the currentBranch
        vcs.getCurrentBranch().addCommit(c);

        //clars the staging
        s.clear();

        return 0;
    }

}
