package com.learnjava.apiclient;

import com.learnjava.domain.github.GitHubPosition;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static com.learnjava.util.LoggerUtil.log;


public class GitHubJobsClient {

    private WebClient webClient;

    public GitHubJobsClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_withPageNumber(int pageNum, String description){

        String uri = UriComponentsBuilder.fromUriString("/positions.json")
                .queryParam("description", description)
                .queryParam("page", pageNum)
                .buildAndExpand()
                .toUriString();
        log("uri : "+ uri);
         List<GitHubPosition> gitHubPositions = webClient.get().uri(uri)
                .retrieve()
                 .bodyToFlux(GitHubPosition.class)
                 .collectList()
                 .block();

        return gitHubPositions;

    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers(List<Integer> pageNumbers,String description){

        startTimer();

        List<GitHubPosition> gitHubPositions =  pageNumbers.stream()
                .map(pageNumber ->invokeGithubJobsAPI_withPageNumber(pageNumber, description))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        timeTaken();
        return gitHubPositions;
    }

    public List<GitHubPosition> invokeGithubJobsAPI_usingMultiplePageNumbers_cf(List<Integer> pageNumbers,String description){

        startTimer();

        List<CompletableFuture<List<GitHubPosition>>> gitHubPositions =  pageNumbers.stream()
                .map(pageNumber -> CompletableFuture.supplyAsync(()-> invokeGithubJobsAPI_withPageNumber(pageNumber, description)))
                .collect(Collectors.toList());

        List<GitHubPosition> gitHubPositionsList = gitHubPositions.stream()
                .map(CompletableFuture::join)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());


        timeTaken();
        return gitHubPositionsList;
    }
}
