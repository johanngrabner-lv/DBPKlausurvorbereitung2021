public class Rechnung {
    private String datum;
    private double gesamtbetrag;
    private int kdnr; //Foreign Key
    public Kunde kunde; //oder Object

    @Override
    public String toString() {
        return "Rechnung{" +
                "datum='" + datum + '\'' +
                ", gesamtbetrag=" + gesamtbetrag +
                ", kdnr=" + kdnr +
                ", kunde=" + kunde +
                ", renr=" + renr +
                '}';
    }

    private int renr;

    public void setRenr(int renr) {
        this.renr = renr;
    }

    public int getRenr() {
        return renr;
    }

    public String getDatum() {
        return datum;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public double getGesamtbetrag() {
        return gesamtbetrag;
    }

    public void setGesamtbetrag(double gesamtbetrag) {
        this.gesamtbetrag = gesamtbetrag;
    }

    public int getKdnr() {
        return kdnr;
    }

    public void setKdnr(int kdnr) {
        this.kdnr = kdnr;
    }
}