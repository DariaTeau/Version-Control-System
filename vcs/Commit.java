//Teau Daria-Elena 321CD
package vcs;

import filesystem.FileSystemSnapshot;
import utils.IDGenerator;

public class Commit {
    private int id = IDGenerator.generateCommitID();
    private String message;
    private Branch parent;
    private FileSystemSnapshot fileSystemSnapshot;

    public Commit() {

    }

    public Commit(String m, Branch p, FileSystemSnapshot f) {
        message = m;
        parent = p;
        fileSystemSnapshot = f;
    }

    /**
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return FileSystemSnapshot
     */
    public FileSystemSnapshot getSnapshot() {
        return fileSystemSnapshot;
    }

}
