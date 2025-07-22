package korzeniowski.mateusz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GitHubApiResponse {
    private String name;
    private Boolean fork;
    private Owner owner;

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Owner {
        private String login;
    }
}
