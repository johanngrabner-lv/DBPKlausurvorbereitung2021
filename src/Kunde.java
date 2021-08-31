import java.util.List;

public class Kunde {
    private int kdnr;
    private String vorname;
    private String nachname;
    private String geschlecht;

    @Override
    public String toString() {
        return "Kunde{" +
                "kdnr=" + kdnr +
                ", vorname='" + vorname + '\'' +
                ", nachname='" + nachname + '\'' +
                ", geschlecht='" + geschlecht + '\'' +
                ", bonuspunkte=" + bonuspunkte +
                ", rechnungen=" + rechnungen +
                '}';
    }

    private double bonuspunkte;
    //Composition / Aggregation
    private List<Rechnung> rechnungen;

    public int getKdnr() {
        return kdnr;
    }

    public void setKdnr(int kdnr) {
        this.kdnr = kdnr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    public double getBonuspunkte() {
        return bonuspunkte;
    }

    public void setBonuspunkte(double bonuspunkte) {
        this.bonuspunkte = bonuspunkte;
    }
}