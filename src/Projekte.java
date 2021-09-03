public class Projekte {

    private int projektId;

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    @Override
    public String toString() {
        return "Projekte{" +
                "projektId=" + projektId +
                ", projektBezeichnung='" + projektBezeichnung + '\'' +
                ", projektTyp='" + projektTyp + '\'' +
                ", Budget=" + Budget +
                ", laufzeit=" + laufzeit +
                '}';
    }

    public String getProjektBezeichnung() {
        return projektBezeichnung;
    }

    public void setProjektBezeichnung(String projektBezeichnung) {
        this.projektBezeichnung = projektBezeichnung;
    }

    public String getProjektTyp() {
        return projektTyp;
    }

    public void setProjektTyp(String projektTyp) {
        this.projektTyp = projektTyp;
    }

    public double getBudget() {
        return Budget;
    }

    public void setBudget(double budget) {
        Budget = budget;
    }

    public int getLaufzeit() {
        return laufzeit;
    }

    public void setLaufzeit(int laufzeit) {
        this.laufzeit = laufzeit;
    }

    private String projektBezeichnung;
    private String projektTyp;
    private double Budget;
    private int laufzeit;
}
