package seminar.seminar2.g1058;

public enum Disciplina {
    ALGEBRA(5),ANALIZA_MATEMATICA(5),POO(6),PROGRAMARE_MULTIPARADIGMA_JAVA(6),
    PAW(6),CONTABILITATE(4),PROBABILITATI(5);

    private int credite;

    Disciplina(int credite) {
        this.credite = credite;
    }

    public int getCredite() {
        return credite;
    }

    public void setCredite(int credite) {
        this.credite = credite;
    }
}
