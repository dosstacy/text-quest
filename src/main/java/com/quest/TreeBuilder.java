package com.quest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TreeBuilder {
    public TreeNode buildTreeFromJson(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        return buildTree(rootNode);
    }

    private TreeNode buildTree(JsonNode jsonNode) {
        if (jsonNode == null || !jsonNode.has("question")) {
            return null;
        }

        TreeNode node = new TreeNode(
                jsonNode.get("question").asText(),
                jsonNode.has("isFinal") && jsonNode.get("isFinal").asBoolean()
        );

        if (jsonNode.has("yes")) {
            node.setYesBranch(buildTree(jsonNode.get("yes")));
        }

        if (jsonNode.has("no")) {
            node.setNoBranch(buildTree(jsonNode.get("no")));
        }

        return node;
    }
}

