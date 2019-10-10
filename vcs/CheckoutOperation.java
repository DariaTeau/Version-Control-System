//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.ErrorCodeManager;
import utils.OperationType;

public class CheckoutOperation extends VcsOperation {

    public CheckoutOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * executes the command
     */
    public int execute(Vcs vcs) {
        // in case the staging isn't empty
        if (!vcs.getStaging().isEmpty()) {
            return ErrorCodeManager.VCS_STAGED_OP_CODE;
        }

        String arg = operationArgs.get(1);
        // in case it moves to a ceratin commit
        if (arg.equals("-c")) {
            String idString = operationArgs.get(2);
            int id = Integer.parseInt(idString);
            Branch currentBranch = vcs.getCurrentBranch();
            Commit newCommit = currentBranch.findCommit(id);

            // if the commit doesn't exist
            if (newCommit == null) {
                return ErrorCodeManager.VCS_BAD_PATH_CODE;
            } else {
                /*
                 * else the current commit set to the new one
                 * and the commits done after the new one are deleted
                 */
                vcs.setCurrentCommit(newCommit);
                int pos = currentBranch.getCommits().indexOf(newCommit);
                currentBranch.deleteCommits(pos);
                //the current file snapshot is the one of the new commit
                vcs.setSnapshot(newCommit.getSnapshot());
            }
        } else {
            if (vcs.getBranches().containsKey(arg)) {
                Branch newCurrentBranch = vcs.getBranches().get(arg);
                ArrayList<Commit> cList = newCurrentBranch.getCommits();
                vcs.setCurrentBranch(newCurrentBranch);
                /*
                 * sets the current commit to the last commit
                 * of the new current branch
                 */
                vcs.setCurrentCommit(cList.get(cList.size() - 1));
            } else {
                //if the branch doesn't exist
                return ErrorCodeManager.VCS_BAD_CMD_CODE;
            }
        }
        return 0;
    }

}
