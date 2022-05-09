package seminar.seminar2.g1058;

import java.io.Serializable;

public abstract class Persoana implements Evaluare,Comparable<Persoana>, Serializable {
    private long cnp;
    private String nume;
    private Adresa adresa;

    public Persoana() {
    }

    public Persoana(long cnp) {
        this.cnp = cnp;
    }

    public Persoana(long cnp, String nume, Adresa adresa) {
        this.cnp = cnp;
        this.nume = nume;
        this.adresa = adresa;
    }

    public long getCnp() {
        return cnp;
    }

    public void setCnp(long cnp) {
        this.cnp = cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "{" + cnp + "," + nume  +"," + adresa + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Persoana persoana = (Persoana) o;

        return cnp == persoana.cnp;
    }

    @Override
    public int hashCode() {
        return (int) (cnp ^ (cnp >>> 32));
    }
}
