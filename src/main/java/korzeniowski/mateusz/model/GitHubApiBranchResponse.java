package korzeniowski.mateusz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubApiBranchResponse {
    private String name;
    private Commit commit;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Commit {
        private String lastCommitSha;
    }

}
