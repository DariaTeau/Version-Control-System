package vcs;

import java.util.ArrayList;
import java.util.HashMap;

import filesystem.FileSystemOperation;
import filesystem.FileSystemSnapshot;
import utils.AbstractOperation;
import utils.ErrorCodeManager;
import utils.OutputWriter;
import utils.Visitor;

public final class Vcs implements Visitor {
    private final OutputWriter outputWriter;
    private FileSystemSnapshot activeSnapshot;
    private ArrayList<AbstractOperation> staging = new ArrayList<AbstractOperation>();
    private Branch currentBranch = new Branch();
    private HashMap<String, Branch> branches = new HashMap<String, Branch>();
    private Commit currentCommit;

    /**
     * Vcs constructor.
     *
     * @param outputWriter the output writer
     */
    public Vcs(OutputWriter outputWriter) {
        this.outputWriter = outputWriter;
    }

    /**
     * returns the FileSystemSnapshot.
     */
    public FileSystemSnapshot getSnapshot() {
        return activeSnapshot;
    }

    /**
     * sets the FileSystemSnapshot.
     */
    public void setSnapshot(FileSystemSnapshot f) {
        activeSnapshot = f;
    }

    public Branch getCurrentBranch() {
        return currentBranch;
    }

    public void setCurrentBranch(Branch b) {
        currentBranch = b;
    }

    public OutputWriter getOutputWriter() {
        return outputWriter;
    }

    public ArrayList<AbstractOperation> getStaging() {
        return staging;
    }

    public void addOperation(AbstractOperation op) {
        staging.add(op);
    }

    public HashMap<String, Branch> getBranches() {
        return branches;
    }

    public void addBranch(Branch b) {
        branches.put(b.getName(), b);
    }

    public void setCurrentCommit(Commit c) {
        currentCommit = c;
    }

    /**
     * Does initialisations.
     */
    public void init() {
        this.activeSnapshot = new FileSystemSnapshot(outputWriter);
        //TODO other initialisations 
        //makes branch master and its first commit
        this.currentBranch.setName("master");
        this.currentCommit = new Commit(" First commit", this.currentBranch,
                this.activeSnapshot.cloneFileSystem());
        this.currentBranch.addCommit(this.currentCommit);
        this.branches.put("master", this.currentBranch);
    }

    /**
     * Visits a file system operation.
     *
     * @param fileSystemOperation the file system operation
     * @return the return code
     */
    public int visit(FileSystemOperation fileSystemOperation) {
        return fileSystemOperation.execute(this.activeSnapshot);
    }

    /**
     * Visits a vcs operation.
     *
     * @param vcsOperation the vcs operation
     * @return return code
     */
    @Override
    public int visit(VcsOperation vcsOperation) {
        //TODO
        int exitCode = vcsOperation.execute(this);
        ErrorCodeManager.getInstance().checkExitCode(outputWriter, exitCode);
        return 0;
    }

    //TODO methods through which vcs operations interact with this
}
