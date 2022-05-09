public class Punct implements Comparable{
    private String etichetaFigura;
    private String etichetaPunct;
    private double ox;
    private double oy;

    public Punct(String etichetaFigura, String etichetaPunct, double ox, double oy) {
        this.etichetaFigura = etichetaFigura;
        this.etichetaPunct = etichetaPunct;
        this.ox = ox;
        this.oy = oy;
    }

    public Punct() {
    }

    public String getEtichetaFigura() {
        return etichetaFigura;
    }

    public void setEtichetaFigura(String etichetaFigura) {
        this.etichetaFigura = etichetaFigura;
    }

    public String getEtichetaPunct() {
        return etichetaPunct;
    }

    public void setEtichetaPunct(String etichetaPunct) {
        this.etichetaPunct = etichetaPunct;
    }

    public double getOx() {
        return ox;
    }

    public void setOx(double ox) {
        this.ox = ox;
    }

    public double getOy() {
        return oy;
    }

    public void setOy(double oy) {
        this.oy = oy;
    }

    @Override
    public String toString() {
        return "Punct{" +
                "etichetaFigura='" + etichetaFigura + '\'' +
                ", etichetaPunct='" + etichetaPunct + '\'' +
                ", ox=" + ox +
                ", oy=" + oy +
                '}';
    }

    public double distanta(){
        return Math.sqrt((ox * ox + oy * oy));
    }


    @Override
    public int compareTo(Object o) {
        Punct punct = (Punct) o;
        if(this.distanta()==punct.distanta()){
            return 0;
        } else if(this.distanta()< punct.distanta()){
            return -1;
        } else {
            return 1;
        }
    }
}
