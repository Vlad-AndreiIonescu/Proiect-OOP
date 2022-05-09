package seminar.seminar2.g1058;

public class Punctaj implements Cloneable{
    private int didactic, cercetare, vizibilitate;

    public Punctaj() {
    }

    public Punctaj(int didactic, int cercetare, int vizibilitate) {
        this.didactic = didactic;
        this.cercetare = cercetare;
        this.vizibilitate = vizibilitate;
    }

    public int getDidactic() {
        return didactic;
    }

    public void setDidactic(int didactic) {
        this.didactic = didactic;
    }

    public int getCercetare() {
        return cercetare;
    }

    public void setCercetare(int cercetare) {
        this.cercetare = cercetare;
    }

    public int getVizibilitate() {
        return vizibilitate;
    }

    public void setVizibilitate(int vizibilitate) {
        this.vizibilitate = vizibilitate;
    }

    public int getPunctaj(){
        return didactic+cercetare+vizibilitate;
    }

    @Override
    public String toString() {
        return "["+didactic+","+cercetare+","+vizibilitate+"]";
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
