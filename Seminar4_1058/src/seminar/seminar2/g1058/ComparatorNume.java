package seminar.seminar2.g1058;

import java.util.Comparator;

public class ComparatorNume implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getNume().compareTo(o2.getNume());
    }
}
