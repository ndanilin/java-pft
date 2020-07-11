package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("c15e652abb11a50e8b69ec9d05f9679927240258");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ndanilin", "java-pft")).commits();
        for (RepoCommit commit: commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
