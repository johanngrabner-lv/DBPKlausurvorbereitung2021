import java.sql.*;

public class DBHelper {
    private String url = "jdbc:sqlite:C://sqlite/db/Klausurvorbereitung.db";

    public void createKundenTable(){
        String ddlCreateTableKunden="CREATE TABLE ";
        ddlCreateTableKunden += " Kunden (KDNR INTEGER PRIMARY KEY AUTOINCREMENT, ";
        ddlCreateTableKunden += " Vorname VARCHAR(20), ";
        ddlCreateTableKunden += " Nachname VARCHAR(20), ";
        ddlCreateTableKunden += " Geschlecht VARCHAR(5), ";
        ddlCreateTableKunden += " Bonuspunkte decimal(10,2) ";
        ddlCreateTableKunden += ")";
        System.out.println("DDL to create the Kunden-Table " +  ddlCreateTableKunden);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(ddlCreateTableKunden);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void createRechnungenTable(){
        String ddlCreateRechnungenKunden = "CREATE TABLE ";
        ddlCreateRechnungenKunden += " Rechnungen(RENR INTEGER PRIMARY KEY AUTOINCREMENT, ";
        ddlCreateRechnungenKunden += " Kdnr INTEGER, ";
        ddlCreateRechnungenKunden += " Datum varchar(20), ";
        ddlCreateRechnungenKunden += " Gesamtbetrag decimal(10,2), ";
        ddlCreateRechnungenKunden += " FOREIGN KEY (Kdnr) REFERENCES Kunden(Kdnr)";
        ddlCreateRechnungenKunden += ")";
        System.out.println("DDL to create the Rechnungen-Table " +  ddlCreateRechnungenKunden);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(ddlCreateRechnungenKunden);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insertKunde(Kunde neuerKunde){

        int lastId=0;

        String insertSQL="INSERT INTO Kunden(Vorname, Nachname, Geschlecht, Bonuspunkte) ";
        insertSQL += "Values(?,?,?,?)";
        String sqlText = "SELECT last_insert_rowid() as rowid;";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pStmt = conn.prepareStatement(insertSQL);
             PreparedStatement stmtLastRowId = conn.prepareStatement(sqlText)) {
            pStmt.setString(1,neuerKunde.getVorname());
            pStmt.setString(2,neuerKunde.getNachname());
            pStmt.setString(3,neuerKunde.getGeschlecht());
            pStmt.setDouble(4,neuerKunde.getBonuspunkte());

            pStmt.executeUpdate();

            ResultSet rs = null;

            rs = stmtLastRowId.executeQuery();
            rs.next();
            lastId=rs.getInt("rowid");
            rs.close();
            pStmt.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        neuerKunde.setKdnr(lastId);
        return lastId;
    }

    public void updateKunde(Kunde kunde){

        String updateKunde="UPDATE Kunden SET Vorname=?, Nachname=?, Geschlecht=?, Bonuspunkte=? WHERE KDNR=? ";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pStmt = conn.prepareStatement(updateKunde);
             ) {
            pStmt.setString(1,kunde.getVorname());
            pStmt.setString(2,kunde.getNachname());
            pStmt.setString(3,kunde.getGeschlecht());
            pStmt.setDouble(4,kunde.getBonuspunkte());
            pStmt.setInt(5,kunde.getKdnr());

            int affectedRows = pStmt.executeUpdate();

            if (affectedRows==0){
                System.out.println("Kein Kunde gefunden");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public Kunde getKundeByKdnr(int kdnr){
        Kunde k =new Kunde();
        String getKundeByKDNR = "SELECT * FROM Kunden WHERE KDNR = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(getKundeByKDNR)) {
            preparedStatement.setInt(1, kdnr);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                k.setKdnr(rs.getInt("KDNR"));
                k.setVorname(rs.getString("Vorname"));
                k.setNachname(rs.getString("Nachname"));
                k.setGeschlecht(rs.getString("Geschlecht"));
                if (rs.wasNull()){
                    k.setGeschlecht("nicht definiert");
                }
                k.setBonuspunkte(rs.getDouble("Bonsupunkte"));
            } else {
                System.out.println("Kunde wurde nicht gefunden");
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return k;
    }

    public int insertRechnung(Rechnung neueRechnung, Kunde vorhandenerKunde){

        int lastId=0;

        String insertSQL="INSERT INTO Rechnungen(Kdnr, Gesamtbetrag, Datum) ";
        insertSQL += "Values(?,?,?)";
        String sqlText = "SELECT last_insert_rowid() as rowid;";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pStmt = conn.prepareStatement(insertSQL);
             PreparedStatement stmtLastRowId = conn.prepareStatement(sqlText)) {
            pStmt.setInt(1,vorhandenerKunde.getKdnr());
            pStmt.setDouble(2,neueRechnung.getGesamtbetrag());
            pStmt.setString(3,neueRechnung.getDatum());
            pStmt.executeUpdate();

            ResultSet rs = null;

            rs = stmtLastRowId.executeQuery();
            rs.next();
            lastId=rs.getInt("rowid");
            rs.close();
            pStmt.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        neueRechnung.setRenr(lastId);
        return lastId;
    }


}
