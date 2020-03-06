package tests;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGitHub {

   @Test
   public void testCommits() throws IOException {
      Github github = new RtGithub("8e0ed00844f4f1c298676272bb138011edee7cd7 ");
      RepoCommits commits = github.repos().get(new Coordinates.Simple("SergeyLashenco", "TestQAAM")).commits();
      for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String , String>().build())) {
         System.out.println(new RepoCommit.Smart(commit).message());
      }
   }
}
