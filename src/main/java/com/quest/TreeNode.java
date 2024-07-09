package com.quest;

import com.fasterxml.jackson.core.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class TreeNode {
    private final String question;
    private TreeNode yesBranch;
    private TreeNode noBranch;
    private final Boolean isFinal;
    private static final Logger LOGGER = LogManager.getLogger(TreeNode.class);

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

    public static TreeNode treeInitializer() {
        return new TreeBuilder().buildTreeFromJson(Objects.requireNonNull(JsonParser.class.getClassLoader().getResource("questions.json")).getFile());
    }

    public static TreeNode findNodeByQuestion(TreeNode root, String question) {
        if (root == null) {
            LOGGER.warn("root is null");
            return null;
        }
        if (root.getQuestion().equals(question)) {
            return root;
        }
        TreeNode foundNode = findNodeByQuestion(root.getYesBranch(), question);
        if (foundNode == null) {
            foundNode = findNodeByQuestion(root.getNoBranch(), question);
        }
        return foundNode;
    }
}