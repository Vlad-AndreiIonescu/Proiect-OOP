import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    List<Punct> puncte = new ArrayList<>();

    public List<Punct> getPuncte() {
        return puncte;
    }

    public Main() {
    }

    public void citire(String numeFisier) throws Exception {
        try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
            puncte.clear();
            String linie;
            while((linie = in.readLine())!=null){
                Punct punct = new Punct();
                String[] t = linie.split(",");
                punct.setEtichetaFigura(t[0]);
                punct.setEtichetaPunct(t[1]);
                punct.setOx(Double.parseDouble(t[2].trim()));
                punct.setOy(Double.parseDouble(t[3].trim()));

                puncte.add(punct);
            }
        }
    }

    public void print(String mesaj){
        System.out.println(mesaj);
        for(Punct punct:puncte){
            System.out.println(punct);
        }
    }

    public void listareDistante(String numeFisier) throws Exception {
        try(PrintWriter out = new PrintWriter(numeFisier)){
            for(Punct punct:puncte){
                out.println(punct.getEtichetaFigura() + ", " + punct.getEtichetaPunct() + ", " + punct.distanta());
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();

        List<Punct> puncte = app.getPuncte();
        try {
            app.citire("puncte.csv");
            app.print("Lista de puncte:");

            //Cerinta 1: Comparabilitate dupa distanta
            for(int i =0; i< puncte.size()-1;i++){
                for(int j =i+1; j< puncte.size();j++){
//                    System.out.println(puncte.get(i).compareTo(puncte.get(j)));
                    if(puncte.get(i).compareTo(puncte.get(j))==0){
                        System.out.println("Elementele egale sunt: " + puncte.get(i) + puncte.get(j));
                    }
                }
            }


            //Cerinta 2: Sa se citeasca in lista si sa se afiseze nr de puncte
            System.out.println("Nr de puncte:");
            System.out.println(puncte.stream().count());

            //Cerinta 3: Sa se afiseze nr de puncte pt fiecare figura
            Map<String, Long> nrDePunctePeFigura = puncte.stream().collect(Collectors.groupingBy(Punct::getEtichetaFigura, Collectors.counting()));
            System.out.println("Nr de puncte pe fiecare grupa:");
            nrDePunctePeFigura.keySet().forEach(etichetaFigura -> {
                System.out.println(etichetaFigura + ":" + nrDePunctePeFigura.get(etichetaFigura));
            });

            //Cerinta 4: Sa se salveze descrescator distantele in fisierul distante.csv
            puncte.sort(Collections.reverseOrder());
            app.listareDistante("distanta.csv");


        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
