package korzeniowski.mateusz.service;

import korzeniowski.mateusz.model.GitHubApiRepositoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Service
public class GitHubRepositoryService {
    private final RestTemplate restTemplate;
    private final static String USER_REPOS_URL = "https://api.github.com/users/%s/repos";
    private final static String REPO_BRANCHES_URL = "https://api.github.com/repos/%s/%s/branches";

    public GitHubRepositoryService() {
        this.restTemplate = new RestTemplate();
    }

    public List<GitHubApiRepositoryResponse> getRepositories(String username) {
        String userReposUrl = String.format(USER_REPOS_URL, username);
        //String repoBranchesUrl = String.format(REPO_BRANCHES_URL, owner, repo);
        GitHubApiRepositoryResponse[] repos = restTemplate.getForObject(userReposUrl, GitHubApiRepositoryResponse[].class);
        List<GitHubApiRepositoryResponse> list = Arrays.stream(repos)
                .filter(repo -> !Boolean.TRUE.equals(repo.getFork()))
                .toList();
        return list;
    }
}
