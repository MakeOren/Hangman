package io.github.MakeOren;

import java.util.LinkedHashSet;
import java.util.Set;

public class GameState {
    private final Set<Character> wrongGuesses;
    private final String hint;
    private final String picture;
    private final int failedAttemptCount;

    public GameState(Set<Character> wrongGuesses, String hint, String picture, int failedAttemptCount) {
        this.wrongGuesses = new LinkedHashSet<>(wrongGuesses);
        this.hint = hint;
        this.picture = picture;
        this.failedAttemptCount = failedAttemptCount;
    }

    public Set<Character> getWrongGuesses() {
        return new LinkedHashSet<>(wrongGuesses);
    }

    public String getHint() {
        return hint;
    }

    public String getPicture() {
        return picture;
    }

    public int getFailedAttemptCount() {
        return failedAttemptCount;
    }
}
