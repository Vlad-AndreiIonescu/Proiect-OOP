package seminar.seminar5.g1058;

import seminar.seminar2.g1058.Adresa;
import seminar.seminar2.g1058.Student;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        try {
            seminar.seminar2.g1058.Main mainS2 = new seminar.seminar2.g1058.Main();
            mainS2.citire("studenti.csv");
//            mainS2.print("Lista studenti:");

            List<Student> studenti = mainS2.getStudenti();
            System.out.println("Lista initiala:");
//            studenti.forEach(new Consumer<Student>() {
//                @Override
//                public void accept(Student student) {
//                    System.out.println(student);
//                }
//            });
//            studenti.forEach( student -> System.out.println(student) );
            studenti.forEach(System.out::println);

//            Cerinta 1
            List<Student> grupa1058 = studenti.stream().filter(new Predicate<Student>() {
                @Override
                public boolean test(Student student) {
                    return student.getGrupa() == 1058;
                }
            }).collect(Collectors.toList());
            System.out.println("Grupa 1058:");
            grupa1058.forEach(System.out::println);

//            Cerinta 2
            List<Student> listaMedii_5_7 = studenti.stream()
                    .filter(student -> student.medie() >= 5 && student.medie() <= 7)
                    .collect(Collectors.toList());
            System.out.println("Studenti cu medii intre 5 si 7:");
            listaMedii_5_7.forEach(System.out::println);

//            Cerinta 3
            List<Student> promovati = studenti.stream()
                    .filter(Student::evaluare).collect(Collectors.toList());
            System.out.println("Studenti promovati");
            promovati.forEach(System.out::println);

//            Cerinta 4
            List<Student> studentiSort = studenti.stream()
                    .sorted((student1, student2) -> Double.compare(student1.medie(), student2.medie()))
                    .collect(Collectors.toList());
            System.out.println("Lista studentilor sortati dupa medie:");
            studentiSort.forEach(System.out::println);

//            Cerinta 5
            Set<Adresa> adrese = studenti.stream()
                    .map(new Function<Student, Adresa>() {
                        @Override
                        public Adresa apply(Student student) {
                            return student.getAdresa();
                        }
                    }).collect(Collectors.toSet());

            Set<Adresa> adrese_ = studenti.stream()
                    .map(student -> student.getAdresa()).collect(Collectors.toSet());

            Set<Adresa> adrese__ = studenti.stream()
                    .map(Student::getAdresa).collect(Collectors.toSet());

            System.out.println("Lista de adrese:");
            adrese.forEach(System.out::println);

//            Cerinta 6
            Map<Long, Adresa> mapCnpAdrese = studenti.stream()
                    .collect(Collectors.toMap(Student::getCnp, Student::getAdresa));
            System.out.println("Lista adrese relativ la cnp-uri:");
            mapCnpAdrese.keySet().
                    forEach(cnp -> System.out.println(cnp + ":" + mapCnpAdrese.get(cnp)));

//            Cerinta 7
            Map<Integer, List<Student>> mapGrupeStudenti = studenti.stream()
                    .collect(Collectors.groupingBy(Student::getGrupa));
            System.out.println("Studenti pe grupe:");
            mapGrupeStudenti.keySet().forEach(grupa -> {
                System.out.println(grupa + ":");
                mapGrupeStudenti.get(grupa).forEach(System.out::println);
            });

//Cerinta 8
            Map<Integer, List<String>> mapGrupeNumeStudenti = studenti.stream()
                    .collect(Collectors.groupingBy(
                            Student::getGrupa,
                            Collectors.mapping(student -> student.getNume(), Collectors.toList())
                    ));
            System.out.println("Nume studenti pe grupe:");
            mapGrupeNumeStudenti.keySet().forEach(
                    grupa -> System.out.println(grupa + ":" + mapGrupeNumeStudenti.get(grupa)));

//            Cerinta 9
            Map<Integer, Double> mediiPeGrupe = studenti.stream()
                    .collect(Collectors.groupingBy(
                            Student::getGrupa,
                            Collectors.averagingDouble(Student::medie)
                    ));
            System.out.println("Mediile pe grupe:");
            mediiPeGrupe.keySet().forEach(
                    grupa -> System.out.println(grupa + ":" + mediiPeGrupe.get(grupa)));

//            Cerinta 10
            Map<Long, ?> mapCerinta10 = studenti.stream()
                    .collect(Collectors.toMap(
                            Student::getCnp,
                            student -> new Object() {
                                @Override
                                public String toString() {
                                    return student.getNume() + "," + student.getGrupa() + "," + student.medie();
                                }
                            }));
            System.out.println("Colectare nume,grupa si medie pe cnp-uri:");
            mapCerinta10.keySet().forEach(cnp-> System.out.println(cnp+":"+mapCerinta10.get(cnp)));

//            Cerinta 11
            Map<Integer,List<Long>> mapCerinta11 = studenti.stream()
                    .collect(new Supplier<Map<Integer, List<Long>>>() {
                                 @Override
                                 public Map<Integer, List<Long>> get() {
                                     return new HashMap<>();
                                 }
                             }, new BiConsumer<Map<Integer, List<Long>>, Student>() {
                                 @Override
                                 public void accept(Map<Integer, List<Long>> map,
                                                    Student student) {
                                     List<Long> cnpuri = map.get(student.getGrupa());
                                     if (cnpuri == null) {
                                         cnpuri = new ArrayList<>();
                                         map.put(student.getGrupa(), cnpuri);
                                     }
                                     cnpuri.add(student.getCnp());
                                 }
                             }, new BiConsumer<Map<Integer, List<Long>>, Map<Integer, List<Long>>>() {
                                 @Override
                                 public void accept(Map<Integer, List<Long>> map, Map<Integer, List<Long>> map2) {
                                     map2.putAll(map);
                                 }
                             }
                    );
            System.out.println("Lista cnp-urilor pe grupe:");
            mapCerinta11.keySet().forEach(
                    grupa-> System.out.println(grupa+":"+mapCerinta11.get(grupa)));



        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
