import java.io.Serializable;

public class Programare implements Comparable, Serializable {
    private String disciplina;
    private int zi;
    private int interval;
    private Tip tip;
    private String formatia;

    public Programare(String disciplina, int zi, int interval, Tip tip, String formatia) throws Exception {

        this.disciplina = disciplina;
        this.zi = zi;
        if (interval >= 1 && interval <= 8) {
            this.interval = interval;
        }else{
            this.interval=1;
        }
        this.tip = tip;
        this.formatia = formatia;
    }

    public Programare() {
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public int getZi() {
        return zi;
    }

    public void setZi(int zi) {
        this.zi = zi;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        if (interval >= 1 && interval <= 8) {
            this.interval = interval;
        }else{
            System.out.println("Intervalul nu e conform parametrilor");
        }
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getFormatia() {
        return formatia;
    }

    public void setFormatia(String formatia) {
        this.formatia = formatia;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "disciplina='" + disciplina + '\'' +
                ", zi=" + zi +
                ", interval=" + interval +
                ", tip=" + tip +
                ", formatia='" + formatia + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Programare programare = (Programare) o;
        if (this.zi == programare.zi) {
            if (this.interval == programare.interval) {
                return 0;
            } else if (this.interval < programare.interval) {
                return -1;
            } else {
                return 1;
            }
        } else if (this.zi < programare.zi) {
            return -1;
        } else {
            return 1;

        }
    }
}
