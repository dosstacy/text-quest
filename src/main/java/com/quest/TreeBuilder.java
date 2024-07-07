package com.quest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class TreeBuilder {
    private static final Logger LOGGER = LogManager.getLogger(TreeBuilder.class);

    public TreeNode buildTreeFromJson(String filePath) throws IOException {
        LOGGER.debug("Building tree from file: {}", filePath);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(new File(filePath));
        return buildTree(rootNode);
    }

    private TreeNode buildTree(JsonNode jsonNode) {
        if (jsonNode == null || !jsonNode.has("question")) {
            LOGGER.warn("Invalid JSON node: {}", jsonNode);
            return null;
        }

        TreeNode node = new TreeNode(
                jsonNode.get("question").asText(),
                jsonNode.has("isFinal") && jsonNode.get("isFinal").asBoolean()
        );

        LOGGER.debug("Created TreeNode with question: {}", node.getQuestion());

        if (jsonNode.has("yes")) {
            node.setYesBranch(buildTree(jsonNode.get("yes")));
        }

        if (jsonNode.has("no")) {
            node.setNoBranch(buildTree(jsonNode.get("no")));
        }

        return node;
    }
}

