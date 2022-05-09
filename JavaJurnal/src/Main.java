import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    private List<NotaContabila> note = new ArrayList<>();
    public static SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.yyyy");

    public Main() {
    }

    public List<NotaContabila> getNote() {
        return note;
    }

    public void citire(String numeFisier) throws Exception {
        try(BufferedReader in = new BufferedReader(new FileReader(numeFisier))){
            note.clear();
            String linie;
            while((linie = in.readLine())!=null){
                NotaContabila nota = new NotaContabila();
                String[] t = linie.split(",");
                nota.setDataOperatiunii(formatDate.parse(t[0].trim()));
                nota.setContDebitor(Integer.parseInt(t[1].trim()));
                nota.setContCreditor(Integer.parseInt(t[2].trim()));
                nota.setSuma(Double.parseDouble(t[3].trim()));

                note.add(nota);
            }
        }
    }

    public void print(String mesaj){
        System.out.println(mesaj);
        for(NotaContabila nota:note){
            System.out.println(nota);
        }
    }

    public void listareNota(String numeFisier, int cont) throws Exception {
        try(PrintWriter out = new PrintWriter(numeFisier)){
            for(NotaContabila nota:note){
                if(nota.getContCreditor() == cont){
                    out.println(nota.getDataOperatiunii() + ", " + "Creditare" + ", " + nota.getContDebitor() + ", " + nota.getSuma());
                } else if(nota.getContDebitor() == cont){
                    out.println(nota.getDataOperatiunii() + ", " + "Debitare" + ", " + nota.getContCreditor() + ", " + nota.getSuma());
                }
            }
        }
    }

    public static void main(String[] args) {
        Main app = new Main();
        List<NotaContabila> note = app.getNote();
        try {
            app.citire("jurnal.csv");
            app.print("Lista note contabile:");

            //Cerinta 1: Comparabilitate dupa data
            System.out.println("////////////////////////////////////");
            for(int i=0; i<note.size()-1;i++){
                for(int j=i+1; j<note.size();j++){
                    if(note.get(i).compareTo(note.get(j))==0){
                        System.out.println("Notele contabile egale sunt: " + note.get(i) + note.get(j));
                    }
                }
            }

            //Cerinta 2: Sa se afiseze rulajul total = totalizare sume toate operatiunile
            double rulajTotal=0;
            for(NotaContabila nota:note){
                rulajTotal+=nota.getSuma();
            }
            System.out.println("Rulajul total este: " + rulajTotal);

            //Cerinta 3: Sa se afiseze rulajul total pentru fiecare cont:
            Map<Integer, Double> rulajTotalPeFiecareCont = note.stream().collect(Collectors.groupingBy(NotaContabila::getContDebitor, Collectors.summingDouble(NotaContabila::getSuma)));
            System.out.println("Rulajul total pe fiecare cont: ");
            rulajTotalPeFiecareCont.keySet().forEach(contDebitor -> System.out.println(contDebitor + ": " + rulajTotalPeFiecareCont.get(contDebitor)));

            //Cerinta 4: Sa se salveze in fisierul fisa.csv fisa unui cont specificat astfel
            app.listareNota("fisa.csv", 5121);
        } catch (Exception exception) {
            System.err.println(exception);
        }
    }
}
