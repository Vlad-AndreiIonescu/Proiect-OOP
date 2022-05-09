import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public Main() {
    }

    private List<Element> elemente = new ArrayList<>();

    public List<Element> getElemente() {
        return elemente;
    }

    public void citire(String numeFisier) throws Exception {
        try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
            elemente.clear();
            String linie;
            while((linie = in.readLine())!=null){
                Element element = new Element();
                String[] t = linie.split(",");
                element.setIndexLinie(Integer.parseInt(t[0].trim()));
                element.setIndexColoana(Integer.parseInt(t[1].trim()));
                element.setValoareElement(Double.parseDouble(t[2].trim()));

                elemente.add(element);
            }
        }
    }


    public void print(String mesaj){
        System.out.println(mesaj);
        for(Element element:elemente){
            System.out.println(element);
        }
    }

    public void salvare(String numeFisier) throws Exception {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))){
            for(Element element:elemente){
                if(element.getIndexColoana()==element.getIndexLinie()+1){
                    out.writeObject(element.getValoareElement());
                }
            }
        }
    }

    public void restaurare(String numeFisier) throws Exception {
        List<Double> valori = new ArrayList<>();
        try(FileInputStream in1 = new FileInputStream(numeFisier)){
            ObjectInputStream in = new ObjectInputStream(in1);
            valori.clear();
            while(in1.available()!=0){
                valori.add((Double) in.readObject());
            }

            for(Double valoare:valori){
                System.out.println(valoare);
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();

        try {
            //cer 4?
            app.citire("matricerara.csv");
            app.print("Elemente:");

            List<Element> elemente = app.getElemente();
            Element e = new Element(4,4,-10);
            //Cerinta1: Testarea egalitatii
            System.out.println("Testarea egalitatii: ");
            for(Element element: app.elemente){
                if(e.equals(element)){
                    System.out.println(element);
                    break;
                }
            }

            //Cerinta1: Comparabilitate intre elemente dupa valoare - nu merge
            List<Element> lista1 = new ArrayList<>();
            List<Element>lista2 = new ArrayList<>();

            lista1 = app.elemente.subList(0, app.elemente.size()/2);
            lista2 = app.elemente.subList(app.elemente.size()/2+1, app.elemente.size());
            for(Element element: lista1){
                for(Element element1:lista2){
                    if(element.compareTo(element1)==0){
                        System.out.println("Elementul egal este: "+element);
                    }
                }
            }

            //Cerinta 2: Se citesc elementele intr-o lista si se afiseaza la consolo elementele negative

            List<Element> elementeNegative = new ArrayList<>();
            for(Element element:elemente){
                if(element.getValoareElement()<0){
                    elementeNegative.add(element);
                }
            }
            System.out.println("\n");
            System.out.println("Elemente negative:");
            for(Element element:elementeNegative){
                System.out.println(element);
            }

            System.out.println("\n");

            //Cerinta 3: Sa se afiseze mediile pe coloane ale matricei
            Map<Integer, Double> mediilePeColoane = elemente.stream().collect(Collectors.groupingBy(Element::getIndexColoana, Collectors.averagingDouble(Element::getValoareElement)));
            System.out.println("Mediile pe coloane:");
            mediilePeColoane.keySet().forEach(index -> System.out.println(index +":"+mediilePeColoane.get(index)));

            System.out.println("\n");

            //Cerinta4
            app.salvare("diagonala.dat");

            //Cer 5
            System.out.println("Restaurare:");
            app.restaurare("diagonala.dat");



        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
