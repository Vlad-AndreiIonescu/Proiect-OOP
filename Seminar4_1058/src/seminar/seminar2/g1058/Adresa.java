package seminar.seminar2.g1058;

import java.io.Serializable;

public class Adresa implements Cloneable, Serializable {
    private String localitate,strada,numar;
    private int codPostal;

    public Adresa() {
    }

    public Adresa(String localitate, String strada, String numar, int codPostal) {
        this.localitate = localitate;
        this.strada = strada;
        this.numar = numar;
        this.codPostal = codPostal;
    }

    public String getLocalitate() {
        return localitate;
    }

    public void setLocalitate(String localitate) {
        this.localitate = localitate;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public String getNumar() {
        return numar;
    }

    public void setNumar(String numar) {
        this.numar = numar;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    @Override
    public String toString() {
        return "["+localitate+","+strada+","+numar+","+codPostal + "]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
