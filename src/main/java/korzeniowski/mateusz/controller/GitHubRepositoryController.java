package korzeniowski.mateusz.controller;

import korzeniowski.mateusz.excpetion.UserNotFoundException;
import korzeniowski.mateusz.model.GitHubRepositoryResponse;
import korzeniowski.mateusz.service.GitHubRepositoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@RestController
public class GitHubRepositoryController {
    private final GitHubRepositoryService repositoryService;

    public GitHubRepositoryController(GitHubRepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/{username}/repos")
    public List<GitHubRepositoryResponse> getRepos(@PathVariable String username) {
        return repositoryService.getRepositories(username);
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleException(UserNotFoundException exception) {
        Map<String, Object> error = new LinkedHashMap<>();
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("message", exception.getMessage());
        return error;
    }
}
