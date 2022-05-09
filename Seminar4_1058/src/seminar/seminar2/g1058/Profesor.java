package seminar.seminar2.g1058;

public class Profesor extends Persoana implements Cloneable{
    private String departament;
    private Punctaj punctaj;

    public Profesor() {
    }

    public Profesor(long cnp) {
        super(cnp);
    }

    public Profesor(long cnp, String nume, Adresa adresa, String departament, Punctaj punctaj) {
        super(cnp, nume, adresa);
        this.departament = departament;
        this.punctaj = punctaj;
    }

    public String getDepartament() {
        return departament;
    }

    public void setDepartament(String departament) {
        this.departament = departament;
    }

    public Punctaj getPunctaj() {
        return punctaj;
    }

    public void setPunctaj(Punctaj punctaj) {
        this.punctaj = punctaj;
    }

    @Override
    public String toString() {
        return super.toString()+"{" + departament + "," + punctaj +"}";
    }

    @Override
    public boolean evaluare() {
        return punctaj.getCercetare()>50 && punctaj.getDidactic()>50 && punctaj.getVizibilitate()>50;
    }

    @Override
    public double medie() {
        return (punctaj.getCercetare()+punctaj.getDidactic()+punctaj.getDidactic())/3.0;
    }

    @Override
    public int compareTo(Persoana o) {
        Profesor profesor = (Profesor) o;
        int p1=punctaj.getPunctaj(),p2 = profesor.getPunctaj().getPunctaj();
        if (p1==p2){
            return 0;
        } else {
            if (p1<p2){
                return -1;
            } else {
                return 1;
            }
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Profesor profesor = (Profesor) super.clone();
        profesor.setAdresa( (Adresa) getAdresa().clone());
        profesor.setPunctaj( (Punctaj) punctaj.clone());
        return profesor;
    }
}
