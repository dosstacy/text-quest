package com.quest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;
class TreeBuilderTest {

    @Test
    public void testBuildTreeFromJson() throws IOException {
        TreeBuilder builder = new TreeBuilder();
        TreeNode root = builder.buildTreeFromJson(Paths.get("src", "main", "resources", "questions.json").toString());

        assertNotNull(root);
        assertEquals("Do you want to start a journey to another world?", root.getQuestion());

        TreeNode yesBranch = root.getYesBranch();
        assertNotNull(yesBranch);
        assertEquals("You wake up in an unknown place. Around you is a strange forest with unfamiliar plants. You don't remember how you got here. Will you explore the forest?", yesBranch.getQuestion());

        TreeNode noBranch = root.getNoBranch();
        assertNotNull(noBranch);
        assertEquals("Okay, then do something useful ;)", noBranch.getQuestion());
    }
  
}