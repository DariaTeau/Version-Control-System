//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

import utils.AbstractOperation;
import utils.ErrorCodeManager;
import utils.OperationType;
import utils.OutputWriter;

public class StatusOperation extends VcsOperation {

    public StatusOperation(OperationType type, ArrayList<String> operationArgs) {
        super(type, operationArgs);
    }

    /*
     * executes the command
     */
    public int execute(Vcs vcs) {
        //the case of a bad command
        if (operationArgs.size() != 1) {
            return ErrorCodeManager.VCS_BAD_CMD_CODE;
        }

        OutputWriter writer = vcs.getOutputWriter();
        writer.write("On branch: " + vcs.getCurrentBranch().getName() + "\n");
        writer.write("Staged changes:" + "\n");

        ArrayList<AbstractOperation> s = vcs.getStaging();
        //takes every operation from the staging
        for (AbstractOperation op : s) {
            whichOperation(op, vcs);
        }
        return 0;
    }

    /**
     * writes the correct message according to what filesytem
     * operation has been done.
     * @param op
     * @param vcs
     */
    private static void whichOperation(AbstractOperation op, Vcs vcs) {
        OutputWriter writer = vcs.getOutputWriter();

        if (op.getType().equals(OperationType.MAKEDIR)) {
            ArrayList<String> args = op.getOperationArgs();
            writer.write("Created directory " + args.get(args.size() - 1) + "\n");
        }

        if (op.getType().equals(OperationType.TOUCH)) {
            ArrayList<String> args = op.getOperationArgs();
            writer.write("	" + "Created file " + args.get(args.size() - 1) + "\n");
        }

        if (op.getType().equals(OperationType.WRITETOFILE)) {
            ArrayList<String> args = op.getOperationArgs();
            writer.write("	" + "Added " + "\"" + args.get(args.size() - 1)
            + "\"" + " to file " + args.get(1) + "\n");
        }

        if (op.getType().equals(OperationType.REMOVE)) {
            ArrayList<String> args = op.getOperationArgs();
            if (args.get(0).equals("rm")) {
                writer.write("	" + "Removed file "
                        + args.get(args.size() - 1) + "\n");
            } else {
                writer.write("	" + "Removed directory "
                        + args.get(args.size() - 1) + "\n");
            }
        }

        if (op.getType().equals(OperationType.CHANGEDIR)) {
            ArrayList<String> args = op.getOperationArgs();
            writer.write("Changed directory to " + args.get(args.size() - 1) + "\n");

        }
    }

}
