package com.quest;

public class TreeNode {
    private String question;
    private TreeNode yesBranch;
    private TreeNode noBranch;
    private Boolean isFinal;

    public TreeNode(String question, Boolean isFinal) {
        this.question = question;
        this.isFinal = isFinal;
    }

    public String getQuestion() {
        return question;
    }

    public TreeNode getYesBranch() {
        return yesBranch;
    }

    public void setYesBranch(TreeNode yesBranch) {
        this.yesBranch = yesBranch;
    }

    public TreeNode getNoBranch() {
        return noBranch;
    }

    public void setNoBranch(TreeNode noBranch) {
        this.noBranch = noBranch;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }
}