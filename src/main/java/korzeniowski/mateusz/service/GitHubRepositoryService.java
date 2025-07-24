package korzeniowski.mateusz.service;

import korzeniowski.mateusz.excpetion.UserNotFoundException;
import korzeniowski.mateusz.model.GitHubApiBranch;
import korzeniowski.mateusz.model.GitHubApiRepository;
import korzeniowski.mateusz.model.GitHubRepositoryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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

        try {
            GitHubApiRepository[] repos = restTemplate.getForObject(userReposUrl, GitHubApiRepository[].class);

            if (repos == null) {
                return new ArrayList<>();
            }

            List<GitHubApiRepository> reposList = Arrays.stream(repos)
                    .filter(repo -> !Boolean.TRUE.equals(repo.getFork()))
                    .toList();

            List<GitHubRepositoryResponse> response = new ArrayList<>();

            for (GitHubApiRepository repo : reposList) {
                String repoBranchesUrl = String.format(REPO_BRANCHES_URL, username, repo.getName());
                GitHubApiBranch[] branches = restTemplate.getForObject(repoBranchesUrl, GitHubApiBranch[].class);
                if (branches == null) {
                    branches = new GitHubApiBranch[0];
                }
                List<GitHubRepositoryResponse.Branch> branchesList = Arrays.stream(branches)
                        .map(this::map)
                        .toList();
                response.add(new GitHubRepositoryResponse(repo.getName(), username, branchesList));
            }
            return response;
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(String.format("GitHub user %s not exists", username));
        }
    }

    private GitHubRepositoryResponse.Branch map(GitHubApiBranch branch) {
        return new GitHubRepositoryResponse.Branch(branch.getName(), branch.getCommit().getSha());
    }
}
