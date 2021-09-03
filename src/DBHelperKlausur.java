import java.sql.*;
import java.util.ArrayList;

public class DBHelperKlausur {

    private String url = "jdbc:sqlite:C://sqlite/db/KlausurGrabner.db";
    public void createTableProjekte(){
        String ddlCreateTableProjekte="CREATE TABLE ";
        ddlCreateTableProjekte += " Projekte (ProjektId INTEGER PRIMARY KEY AUTOINCREMENT, ";
        ddlCreateTableProjekte += " Projektbezeichnung VARCHAR(20), ";
        ddlCreateTableProjekte += " Projekttyp VARCHAR(20), ";
        ddlCreateTableProjekte += " Budget Decimal, ";
        ddlCreateTableProjekte += " Laufzeit int ";
        ddlCreateTableProjekte += ")";
        System.out.println("DDL to create the Kunden-Table " +  ddlCreateTableProjekte);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(ddlCreateTableProjekte);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createTableProjektaufgaben(){
        String ddlCreateTableProjektaufgaben = "CREATE TABLE ";
        ddlCreateTableProjektaufgaben += " Projektaufgaben(ProjektaufgabenId INTEGER PRIMARY KEY AUTOINCREMENT, ";
        ddlCreateTableProjektaufgaben += " ProjektId INTEGER, ";
        ddlCreateTableProjektaufgaben += " Aufgabenbezeichnung varchar(20), ";
        ddlCreateTableProjektaufgaben += " Laufzeit Integer, ";
        ddlCreateTableProjektaufgaben += " FOREIGN KEY (ProjektId) REFERENCES Projekte(ProjektId)";
        ddlCreateTableProjektaufgaben += ")";
        System.out.println("DDL to create the Rechnungen-Table " +  ddlCreateTableProjektaufgaben);

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            stmt.execute(ddlCreateTableProjektaufgaben);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int insertProjekt(Projekte neuesProjekt){

        int lastId=0;

        String insertSQL="INSERT INTO Projekte(Projektbezeichnung, Projekttyp, Budget, Laufzeit) ";
        insertSQL += "Values(?,?,?,?)";
        String sqlText = "SELECT last_insert_rowid() as rowid;";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pStmt = conn.prepareStatement(insertSQL);
             PreparedStatement stmtLastRowId = conn.prepareStatement(sqlText)) {
            pStmt.setString(1,neuesProjekt.getProjektBezeichnung());
            pStmt.setString(2,neuesProjekt.getProjektTyp());
            pStmt.setDouble(3,neuesProjekt.getBudget());
            pStmt.setInt(4,neuesProjekt.getLaufzeit());

            pStmt.executeUpdate();
            /*Insert von einer anderen Connection beeinflusst nicht die row_id*/
            ResultSet rs = null;
            rs = stmtLastRowId.executeQuery();
          rs.next();
            lastId=rs.getInt("rowid");

            rs.close();
            pStmt.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

       neuesProjekt.setProjektId(lastId);
        return lastId;
    }

    public Projekte getProjektById(int projektId){
        Projekte p =new Projekte();
        String getProjektById = "SELECT * FROM Projekte WHERE ProjektId = ?";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(getProjektById)) {
            preparedStatement.setInt(1, projektId);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                p.setProjektId(rs.getInt("ProjektId"));
                p.setProjektBezeichnung(rs.getString("Projektbezeichnung"));
                p.setProjektTyp(rs.getString("Projekttyp"));
                p.setBudget(rs.getDouble("Budget"));

                p.setLaufzeit(rs.getInt("Laufzeit"));
            } else {

                System.out.println("Projekt wurde nicht gefunden");

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return p;
    }

    public ArrayList<Projekte> getAllProjekteOrdrByBudgetDesc(){
        ArrayList<Projekte> meineProjekte =new ArrayList<Projekte>();
        String getAllProjekte = "SELECT * FROM Projekte ORDER BY Budget DESC ";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement preparedStatement = conn.prepareStatement(getAllProjekte)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int projektId=rs.getInt("ProjektId");
                Projekte p=getProjektById(projektId);
                meineProjekte.add(p);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return meineProjekte;
    }

}
