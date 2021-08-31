import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
                k.setBonuspunkte(rs.getDouble("Bonuspunkte"));
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

    /*Aufgabe 6c*/
    public void updateRechnung(Rechnung rechnung){
        String updateSQL="Update Rechnungen SET Kdnr=?, Gesamtbetrag=?, Datum =? Where ReNr=? ";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pStmt = conn.prepareStatement(updateSQL);
            ) {
            pStmt.setDouble(1,rechnung.getGesamtbetrag());
            pStmt.setString(2,rechnung.getDatum());
            pStmt.setInt(3,rechnung.getKdnr());
            pStmt.setInt(4,rechnung.getRenr());

            int affectedRows = pStmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }

    public void insertKundeUndRechnungen(ArrayList<Rechnung> neueRechnungen, Kunde neuerKunde){
        int newId = insertKunde(neuerKunde);

        for (int counter = 0; counter < neueRechnungen.size(); counter++) {
            Rechnung r = neueRechnungen.get(counter);
            r.setKdnr(newId);
            insertRechnung(r, neuerKunde);
        }
    }

    public List<Rechnung> getRechnungenByKunde(int kdnr){
        ArrayList<Rechnung> rechnungen = new ArrayList<>();


        String getRechnungByKDNR = "SELECT * FROM Rechnungen WHERE KDNR = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(getRechnungByKDNR)) {
            preparedStatement.setInt(1, kdnr);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Rechnung r=new Rechnung();
                r.setKdnr(rs.getInt("KDNR"));
                r.setDatum(rs.getString("Datum"));
                r.setGesamtbetrag(rs.getDouble("Gesamtbetrag"));
                r.setRenr(rs.getInt("ReNr"));
                rechnungen.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rechnungen;
    }
    public ArrayList<Kunde> getWeiblicheKunden(){
        ArrayList<Kunde> weiblicheKunden=new ArrayList<Kunde>();

        String selectWeiblicheKunden = "SELECT * FROM Kunden WHERE Geschlecht='Frau'";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(selectWeiblicheKunden)) {

             ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
             int kdnr=rs.getInt("Kdnr");
             Kunde k = getKundeByKdnr(kdnr);
             weiblicheKunden.add(k);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return  weiblicheKunden;
    }

    public Kunde getKundeMitDenMeistenBonusPunkten(){
        Kunde k=null;

        String selectKundenOrderByBonuspunkteDesc = "SELECT * FROM Kunden ORDER BY Bonuspunkte desc";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(selectKundenOrderByBonuspunkteDesc)) {

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int kdnr=rs.getInt("Kdnr");
                k = getKundeByKdnr(kdnr);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

      return  k;
    }

    public void loescheAlleRechnungenUndDanachDenKunden(Kunde k){
        String deleteRechnungen="DELETE FROM Rechnungen WHERE KDNR = ?";
        String deleteKunde="DELETE FROM Kunden WHERE KDNR = ?";
        Connection conn = null;
        try{
             conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
        }

        try (
             PreparedStatement pStmtDeleteRechnungen = conn.prepareStatement(deleteRechnungen);
             PreparedStatement pStmtDeleteKunde = conn.prepareStatement(deleteKunde);
        ) {

            conn.setAutoCommit(false);
            pStmtDeleteKunde.setInt(1,k.getKdnr());
            pStmtDeleteRechnungen.setInt(1,k.getKdnr());

            pStmtDeleteRechnungen.executeUpdate();
            pStmtDeleteKunde.executeUpdate();

            conn.commit();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            if (conn!=null){
                try {
                    conn.rollback();
                } catch (SQLException e2) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void printKundenMetadata()
    {
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Kunden")) {
            ResultSet rs = stmt.executeQuery();

            ResultSetMetaData  meta = rs.getMetaData();
            int numerics = 0;

            for ( int i = 1; i <= meta.getColumnCount(); i++ )
            {
                System.out.printf( "%-20s %-20s%n", meta.getColumnLabel( i ),
                        meta.getColumnTypeName( i ) );

                if ( meta.isSigned( i ) )
                    numerics++;
            }

            System.out.println();
            System.out.println( "Spalten: " + meta.getColumnCount() +
                    ", Numerisch: " + numerics );

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


    }



}
