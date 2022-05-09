import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class NotaContabila implements Comparable {
    private Date dataOperatiunii;
    private int contDebitor;
    private int contCreditor;
    private double suma;

    public NotaContabila(Date dataOperatiunii, int contDebitor, int contCreditor, double suma) {
        this.dataOperatiunii = dataOperatiunii;
        this.contDebitor = contDebitor;
        this.contCreditor = contCreditor;
        this.suma = suma;
    }

    public NotaContabila() {
    }

    public Date getDataOperatiunii() {
        return dataOperatiunii;
    }

    public void setDataOperatiunii(Date dataOperatiunii) {
        this.dataOperatiunii = dataOperatiunii;
    }

    public int getContDebitor() {
        return contDebitor;
    }

    public void setContDebitor(int contDebitor) {
        this.contDebitor = contDebitor;
    }

    public int getContCreditor() {
        return contCreditor;
    }

    public void setContCreditor(int contCreditor) {
        this.contCreditor = contCreditor;
    }

    public double getSuma() {
        return suma;
    }

    public void setSuma(double suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        return "NotaContabila{" +
                "dataOperatiunii=" + dataOperatiunii +
                ", contDebitor=" + contDebitor +
                ", contCreditor=" + contCreditor +
                ", suma=" + suma +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotaContabila that = (NotaContabila) o;
        return contDebitor == that.contDebitor && contCreditor == that.contCreditor && Double.compare(that.suma, suma) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contDebitor, contCreditor, suma);
    }

    @Override
    public int compareTo(Object o) {
        NotaContabila nota = (NotaContabila) o;
//        if(this.dataOperatiunii.compareTo(nota.dataOperatiunii)==0){
//            return 0;
//        } else if(this.dataOperatiunii.before(nota.dataOperatiunii)){
//            return -1;
//        } else{
//            return 1;
//        }
        return this.dataOperatiunii.compareTo(nota.dataOperatiunii);
    }
}
