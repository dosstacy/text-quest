package com.quest;

public class TreeNode {
    private String question;
    private TreeNode yesBranch;
    private TreeNode noBranch;
    private String answer;

    public TreeNode(String question, String answer) {
        this.question = question;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}