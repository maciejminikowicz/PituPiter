package pl.mm.pitupiter.model;

public enum UserAccountType {
    PRIVATE("private"),
    PUBLIC("public");

    private final String displayName;

    UserAccountType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
