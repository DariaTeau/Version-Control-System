//Teau Daria-Elena 321CD
package vcs;

import java.util.ArrayList;

public class Branch {
    private String name;
    private Branch parentBranch = null;
    private ArrayList<Commit> commits = new ArrayList<Commit>();

    public Branch() {

    }

    public Branch(String n, Branch pBranch) {
        name = n;
        parentBranch = pBranch;
        Commit c = new Commit();
        commits.add(c);
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param s
     */
    public void setName(String s) {
        name = s;
    }

    /**
     *
     * @return parent branch
     */
    public Branch getParentBranch() {
        return parentBranch;
    }

    /**
     *
     * @param b
     */
    public void setParentBranch(Branch b) {
        parentBranch = b;
    }

    /**
     *
     * @return the commits
     */
    public ArrayList<Commit> getCommits() {
        return commits;
    }

    /**
     *
     * @param c
     */
    public void addCommit(Commit c) {
        commits.add(c);
    }

    /**
     * returns the commit with the given id.
     */
    public Commit findCommit(int id) {
        for (Commit c : commits) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }

    /**
     * delets the commits from the given position
     * to the end.
     * @param position
     */
    public void deleteCommits(int position) {
        for (int i = 0; i < commits.size(); i++) {
            if (i > position) {
                commits.remove(i);
            }
        }
    }

}
