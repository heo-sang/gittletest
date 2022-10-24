package com.solgit.gittle;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class GitHubApiTest {

    GitHub github;
    @Test
    void test() throws Exception{
         github = new GitHubBuilder().withPassword("heo-sang","hs051135^^").build();
         github.checkApiUrlValidity();
//        System.out.println("Test");
        //System.out.println();
        //GHRepository repo = github.getRepository("heo-sang/kpc-k8s");
        //System.out.println(repo.getDefaultBranch());
//        List<GHIssue> issues = repo.getIssues(GHIssueState.ALL);
//        for (GHIssue issue : issues) {
//            // get participants name
//            System.out.println(issue.getComments().get(0).getUser().getName());
//        }


    }
}
