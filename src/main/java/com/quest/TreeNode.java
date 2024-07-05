package com.quest;

public class TreeNode {
    private String question;
    private TreeNode yesBranch;
    private TreeNode noBranch;

    public TreeNode() {}

    public TreeNode(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
}