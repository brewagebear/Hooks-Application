package app.domain.user.domain;

public enum JobEnum {
    VOCALIST("Vocalist"),
    KEYBOARDIST("Keyboardist"),
    DRUMMER("DRUMMER"),
    BASEPLAYER("Bass Player"),
    DJBEATEMAKER("DJ/BeatMaker"),
    RAPPER("Rapper"),
    SONGWRITER("Songwriter"),
    OTHER("Other");

    String value;

    JobEnum(String value) {
        this.value = value;
    }
}
