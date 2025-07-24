package korzeniowski.mateusz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubApiBranch {
    private String name;
    private Commit commit;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Commit {
        private String sha;
    }

}
