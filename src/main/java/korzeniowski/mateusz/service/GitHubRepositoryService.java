package korzeniowski.mateusz.service;

import korzeniowski.mateusz.model.GitHubApiBranch;
import korzeniowski.mateusz.model.GitHubApiRepository;
import korzeniowski.mateusz.model.GitHubRepositoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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

    public List<GitHubRepositoryResponse> getRepositories(String username) {
        String userReposUrl = String.format(USER_REPOS_URL, username);

        GitHubApiRepository[] repos = restTemplate.getForObject(userReposUrl, GitHubApiRepository[].class);
        List<GitHubApiRepository> reposList = Arrays.stream(repos)
                .filter(repo -> !Boolean.TRUE.equals(repo.getFork()))
                .toList();

        List<GitHubRepositoryResponse> response = new ArrayList<>();

        for (GitHubApiRepository repo : reposList) {
            String repoBranchesUrl = String.format(REPO_BRANCHES_URL, username, repo.getName());
            GitHubApiBranch[] branches = restTemplate.getForObject(repoBranchesUrl, GitHubApiBranch[].class);
            List<GitHubRepositoryResponse.Branch> branchesList = Arrays.stream(branches)
                    .map(this::map)
                    .toList();
            response.add(new GitHubRepositoryResponse(repo.getName(), username, branchesList));
        }
        return response;
    }

    private GitHubRepositoryResponse.Branch map(GitHubApiBranch branch) {
        return new GitHubRepositoryResponse.Branch(branch.getName(), branch.getCommit().getSha());
    }
}
