//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.OperationType;

public class RollbackOperation extends VcsOperation {
    public RollbackOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * executes the command
     */
    public int execute(Vcs vcs) {
        //clears the staging
        vcs.getStaging().clear();
        ArrayList<Commit> list = vcs.getCurrentBranch().getCommits();
        Commit c = list.get(list.size() - 1);

        //sets the filesystem to the version given by the last commit
        vcs.setSnapshot(c.getSnapshot());
        return 0;
    }

}
