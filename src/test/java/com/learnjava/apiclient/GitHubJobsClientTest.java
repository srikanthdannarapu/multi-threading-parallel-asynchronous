package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static com.learnjava.util.LoggerUtil.log;
import static org.junit.jupiter.api.Assertions.*;

class GitHubJobsClientTest {

    WebClient webClient = WebClient.create("https://jobs.github.com/");
    GitHubJobsClient gjhClient = new GitHubJobsClient(webClient);

    @Test
    void invokeGithubJobsAPI_withPageNumber() {
        //given
        int pageNum = 1;
        String description = "JavaScript";

        //when
        List<GitHubPosition> gitHubPositions = gjhClient.invokeGithubJobsAPI_withPageNumber(pageNum, description);
        log("gitHubPositions " + gitHubPositions);

        //then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void invokeGithubJobsAPI_usingMultiplePageNumbers() {
        //given
        List<Integer> pageNumList = List.of(1,2,3);
        String description = "Java";

        //when
        List<GitHubPosition> gitHubPositions = gjhClient.invokeGithubJobsAPI_usingMultiplePageNumbers(pageNumList, description);
        log("gitHubPositions " + gitHubPositions);

        //then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions
                .forEach(Assertions::assertNotNull);
    }

    @Test
    void invokeGithubJobsAPI_usingMultiplePageNumbers_cf() {
        //given
        List<Integer> pageNumList = List.of(1,2,3);
        String description = "Java";

        //when
        List<GitHubPosition> gitHubPositions = gjhClient.invokeGithubJobsAPI_usingMultiplePageNumbers_cf(pageNumList, description);
        log("gitHubPositions " + gitHubPositions);

        //then
        assertTrue(gitHubPositions.size() > 0);
        gitHubPositions
                .forEach(Assertions::assertNotNull);
    }
}