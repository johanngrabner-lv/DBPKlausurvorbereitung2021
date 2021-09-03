public class Projektaufgaben {

    private int projektaufgabenId;
    private int projektId;
    private String aufgabenBezeichnung;
    private int aufwandInStunden;

    public int getProjektaufgabenId() {
        return projektaufgabenId;
    }

    @Override
    public String toString() {
        return "Projektaufgaben{" +
                "projektaufgabenId=" + projektaufgabenId +
                ", projektId=" + projektId +
                ", aufgabenBezeichnung='" + aufgabenBezeichnung + '\'' +
                ", aufwandInStunden=" + aufwandInStunden +
                '}';
    }

    public void setProjektaufgabenId(int projektaufgabenId) {
        this.projektaufgabenId = projektaufgabenId;
    }

    public int getProjektId() {
        return projektId;
    }

    public void setProjektId(int projektId) {
        this.projektId = projektId;
    }

    public String getAufgabenBezeichnung() {
        return aufgabenBezeichnung;
    }

    public void setAufgabenBezeichnung(String aufgabenBezeichnung) {
        this.aufgabenBezeichnung = aufgabenBezeichnung;
    }

    public int getAufwandInStunden() {
        return aufwandInStunden;
    }

    public void setAufwandInStunden(int aufwandInStunden) {
        this.aufwandInStunden = aufwandInStunden;
    }
}
