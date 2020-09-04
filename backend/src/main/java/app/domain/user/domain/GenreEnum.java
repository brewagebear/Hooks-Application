package app.domain.user.domain;

public enum GenreEnum {
    KPOP("K-POP"),
    HIPHOP("HIP-HOP"),
    POP("Pop"),
    ROCK("Rock");

    String value;

    GenreEnum(String value) {
        this.value = value;
    }
    public String value() {
        return value;
    }
}
