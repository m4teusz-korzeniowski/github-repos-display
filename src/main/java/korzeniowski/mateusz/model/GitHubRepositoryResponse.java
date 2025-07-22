package korzeniowski.mateusz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubRepositoryResponse {
    private String name;
    private String ownerName;
    private List<Branch> branches;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Branch {
        private String name;
        private String lastCommitSha;
    }
}
