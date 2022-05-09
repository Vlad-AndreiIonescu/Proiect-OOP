package seminar.seminar2.g1058;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static SimpleDateFormat formatData = new SimpleDateFormat("yyMMdd");

    public Main() {
    }

    private List<Student> studenti = new ArrayList<>();

    public List<Student> getStudenti() {
        return studenti;
    }

    public void citire(String numeFisier) throws Exception {
        try (BufferedReader in = new BufferedReader(new FileReader(numeFisier))) {
            studenti.clear();
            String linie;
            while ((linie = in.readLine()) != null) {
                Student student = new Student();
                String[] t = linie.split(",");
                student.setCnp(Long.parseLong(t[0].trim()));
                student.setNume(t[1]);
                Adresa adresa = new Adresa();
                adresa.setLocalitate(t[2]);
                adresa.setStrada(t[3]);
                adresa.setNumar(t[4]);
                adresa.setCodPostal(Integer.parseInt(t[5].trim()));
                student.setAdresa(adresa);
                student.setGrupa(Integer.parseInt(t[6].trim()));
                student.setAnul(Integer.parseInt(t[7].trim()));
                t = in.readLine().split(",");
                int n = t.length / 2;
                int[] note = new int[n];
                Disciplina[] discipline = new Disciplina[n];
                for (int i = 0; i < n; i++) {
                    discipline[i] = Disciplina.valueOf(t[i * 2].trim().toUpperCase());
                    note[i] = Integer.parseInt(t[i * 2 + 1].trim());
                }
                student.setDiscipline(discipline);
                student.setNote(note);
                studenti.add(student);
            }
        }
    }

    public void print(String mesaj) {
        System.out.println(mesaj);
        for (Student student : studenti) {
            System.out.println(student);
        }
    }

    public void listareMedii(String numeFisier) throws Exception {
        try (PrintWriter out = new PrintWriter(numeFisier)) {
            for (Student student : studenti) {
//                cnp, nume, anul, grupa, media
                out.println(student.getCnp() + "," + student.getNume() +
                        "," + student.getAnul() + "," + student.getGrupa() +
                        "," + student.medie());
            }
        }
    }

    public void salvare(String numeFisier) throws Exception{
        try(ObjectOutputStream out =
                    new ObjectOutputStream(new FileOutputStream(numeFisier))) {
            for (Student student:studenti){
                out.writeObject(student);
            }
        }
    }

    public void restaurare(String numeFisier) throws Exception{
        try(FileInputStream in1 = new FileInputStream(numeFisier);
            ObjectInputStream in = new ObjectInputStream(in1)){
            studenti.clear();
//            try {
//                while (true) {
//                    studenti.add((Student) in.readObject());
//                }
//            }
//            catch (IOException ex){}
            while (in1.available()!=0){
                studenti.add((Student) in.readObject());
            }
        }
    }

    public static void main(String[] args) {
        try {
            Student student1 = new Student(5020210256987L, "Popescu Diana",
                    new Adresa("Brasov", "Calea Bucuresti", "10", 3897),
                    1058, 2, new Disciplina[]{Disciplina.ALGEBRA, Disciplina.POO}, new int[]{9, 8});
//            System.out.println(student1);
            Student student2 = new Student(5020210256987L);
//            System.out.println(student1.equals(student2));

            Student clona = (Student) student1.clone();
            clona.getNote()[0] = 7;
            clona.getAdresa().setLocalitate("Alta localitate");
            clona.getDiscipline()[1] = Disciplina.CONTABILITATE;
//            System.out.println("Obiect clonat:\n" + student1);
//            System.out.println("Clona:\n" + clona);

            Profesor profesor = new Profesor(1671212458741L, "Popescu Ion",
                    new Adresa("Bucuresti", "Brasov", "16", 7896), "Statistica",
                    new Punctaj(85, 87, 56));
            Profesor clonaProfesor = (Profesor) profesor.clone();
            clonaProfesor.getPunctaj().setVizibilitate(100);
//            System.out.println("\n\n->>\n" + profesor);
//            System.out.println(clonaProfesor);

            Main app = new Main();
            app.citire("studenti.csv");
            app.print("Lista de studenti:");
            List<Student> listaStudenti = app.getStudenti();
            Collections.sort(listaStudenti);
            app.print("Studentii sortati dupa medie:");

            ComparatorNume comparatorNume = new ComparatorNume();

            Collections.sort(listaStudenti, comparatorNume);
            app.print("Studentii sortati alfabetic:");

//            Sortare dupa data nasterii
            listaStudenti.sort(new Comparator<Student>() {
                @Override
                public int compare(Student student, Student t1) {
                    String d1 = String.valueOf(student.getCnp()).substring(1, 7);
                    String d2 = String.valueOf(t1.getCnp()).substring(1, 7);
                    Date data1 = null, data2 = null;
                    try {
                        data1 = formatData.parse(d1);
                        data2 = formatData.parse(d2);
                    } catch (Exception ex) {
                    }
                    return data1.compareTo(data2);
                }
            });
            app.print("Sortare dupa data nasterii:");
//            Calcul medii
            app.listareMedii("medii.csv");

//            Cautare dupa cnp
            long cnp = 6020401587411L;
            Student studentCautat = new Student(cnp);
            int k = listaStudenti.indexOf(studentCautat);
            if (k==-1){
                System.out.println("Nu am gasit studentul cu cnp:"+cnp);
            } else {
                System.out.println("Studentul cautat:\n"+listaStudenti.get(k));
            }
//            Cautare dupa medie
            studentCautat.setNote(new int[]{5,5,5});
            k = Collections.binarySearch(listaStudenti,studentCautat);
            if (k>=0){
                System.out.println("Studentul cautat:\n"+listaStudenti.get(k));
            } else {
                System.out.println("Nu am gasit studentul cu media "+studentCautat.medie());
                System.out.println("Pozitie de inserare:"+(-k-1));
            }

//            Cautare binara dupa nume
            studentCautat.setNume("Ionescu Daniel");
            listaStudenti.sort(comparatorNume);
            k = Collections.binarySearch(listaStudenti,studentCautat,comparatorNume);
            if (k>=0){
                System.out.println("Studentul cautat:\n"+listaStudenti.get(k));
            } else {
                System.out.println("Nu am gasit studentul cu numele "+studentCautat.getNume());
                System.out.println("Pozitie de inserare:"+(-k-1));
            }

//            salvare
            app.salvare("studenti.dat");
//            Restaurare lista
            app.restaurare("studenti.dat");

            app.print("Lista restaurata:");

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
