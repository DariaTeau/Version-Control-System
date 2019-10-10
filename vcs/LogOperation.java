//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.OperationType;

public class LogOperation extends VcsOperation {

    public LogOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * lists the commits and their messages from the
     * current branch
     */
    public int execute(Vcs vcs) {
        ArrayList<Commit> list = vcs.getCurrentBranch().getCommits();
        int pos = 0;
        int size = list.size() - 1;
        for (Commit c : list) {
            vcs.getOutputWriter().write("Commit id: " + c.getId() + "\n");
            vcs.getOutputWriter().write("Message:" + c.getMessage() + "\n");
            if (pos != size) {
                vcs.getOutputWriter().write("\n");
            }
            pos++;
        }
        return 0;
    }

}
