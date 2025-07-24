package korzeniowski.mateusz.controller;

import korzeniowski.mateusz.model.GitHubApiRepositoryResponse;
import korzeniowski.mateusz.service.GitHubRepositoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class GitHubRepositoryController {
    private final GitHubRepositoryService repositoryService;

    public GitHubRepositoryController(GitHubRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/{username}/repos")
    public List<GitHubApiRepositoryResponse> getRepos(@PathVariable String username) {
        return repositoryService.getRepositories(username);
    }
}
