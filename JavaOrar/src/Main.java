import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private List<Programare> programari = new ArrayList();

    public List<Programare> getProgramari() {
        return programari;
    }

    public Main() {
    }

    public void citire(String numeFisier) throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(numeFisier))) {
            programari.clear();
            String linie;
            while ((linie = in.readLine()) != null) {
                Programare programare = new Programare();
                String[] t = linie.split(",");
                programare.setDisciplina(t[0]);
                programare.setZi(Integer.parseInt(t[1].trim()));
                programare.setInterval(Integer.parseInt(t[2].trim()));
                programare.setTip(Tip.valueOf(t[3]));
                programare.setFormatia(t[4]);

                programari.add(programare);
            }
        }
    }

    public void print(String mesaj) {
        System.out.println(mesaj);
        for (Programare programare : programari) {
            System.out.println(programare);
        }
    }

    public void salvare(String numeFisier) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(numeFisier))) {
            for (Programare programare : programari) {
                out.writeObject(programare);
            }
        }
    }

    public void restaurare(String numeFisier) throws Exception {
        try (FileInputStream in1 = new FileInputStream(numeFisier)) {
            ObjectInputStream in = new ObjectInputStream(in1);
            programari.clear();

            while (in1.available() != 0) {
                programari.add((Programare) in.readObject());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            Main app = new Main();
            app.citire("orar.csv");


            Programare programare = new Programare("Mate", 1, 0, Tip.SEMINAR, "formatie");
            System.out.println(programare);


            //Cerinta2: Sa se citeasca informatiile intr-o lista si sa se afiseze nr total de cursuri si de seminare.. Avem nevoie de: metoda de citire pe care o apelam in main cu app.citire
            List<Programare> programari = app.getProgramari();
            System.out.println("****************************");
            Map<Tip, Long> listaSuma = programari.stream().collect(Collectors.groupingBy(Programare::getTip, Collectors.counting()));
            System.out.println("Nr total cursuri si seminare:");
            System.out.println(listaSuma);

            System.out.println("\n");


            //Cerinta3: Sa se afiseze disciplinele la care orarul este complet (curs+seminar)
            Map<String, List<Programare>> listaOrarComplet = programari.stream().collect(Collectors.groupingBy(Programare::getDisciplina, Collectors.toList()));
            System.out.println("Discipline cu orar complet:");
            listaOrarComplet.keySet().forEach(disciplina -> {
                System.out.println(disciplina);
                listaOrarComplet.get(disciplina).forEach(System.out::println);
            });


            //Cerinta4: Salvare fisier binar lista de programari ordonate dupa zi si interval. Sa se citeasca si sa se afiseze lista citita
            programari.sort(new Comparator<Programare>() {
                @Override
                public int compare(Programare programare, Programare t1) {
                    return programare.compareTo(t1);
                }
            });

            app.salvare("listaProgramariOrdonate.dat");
            app.restaurare("listaProgramariOrdonate.dat");
            app.print("Lista ordonata:");

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
