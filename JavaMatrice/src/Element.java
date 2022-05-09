import java.util.Objects;

public class Element implements Comparable{
    private int indexLinie;
    private int indexColoana;
    private double valoareElement;

    public Element(int indexLinie, int indexColoana, double valoareElement) {
        this.indexLinie = indexLinie;
        this.indexColoana = indexColoana;
        this.valoareElement = valoareElement;
    }

    public Element() {
    }

    public int getIndexLinie() {
        return indexLinie;
    }

    public void setIndexLinie(int indexLinie) {
        this.indexLinie = indexLinie;
    }

    public int getIndexColoana() {
        return indexColoana;
    }

    public void setIndexColoana(int indexColoana) {
        this.indexColoana = indexColoana;
    }

    public double getValoareElement() {
        return valoareElement;
    }

    public void setValoareElement(double valoareElement) {
        this.valoareElement = valoareElement;
    }

    @Override
    public String toString() {
        return "Element{" +
                "indexLinie=" + indexLinie +
                ", indexColoana=" + indexColoana +
                ", valoareElement=" + valoareElement +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return indexLinie == element.indexLinie && indexColoana == element.indexColoana && Double.compare(element.valoareElement, valoareElement) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(indexLinie, indexColoana, valoareElement);
    }

    @Override
    public int compareTo(Object o) {
        Element element = (Element) o;
        if(valoareElement == element.valoareElement){
            return 0;
        }
        if(valoareElement<element.valoareElement){
            return -1;
        } else{
            return 1;
        }
    }
}
