import java.util.ArrayList;
import java.util.List;

public class MyStarter {

    public static void main(String args[]){
        /*
        System.out.println("Klausurvorbereitung");

        DBHelper dbHelper=new DBHelper();
        System.out.println(dbHelper.getKundeByKdnr(120));
        System.out.println("Alle Kunden\n");
        System.out.println(dbHelper.getAlleKunden());
        */
        System.out.println("Klausurnachbesprechung");
        DBHelperKlausur dbKL = new DBHelperKlausur();
       /* dbKL.createTableProjekte();
        dbKL.createTableProjektaufgaben();*/

        Projekte p=new Projekte();
        p.setProjektBezeichnung("Klausur V2");
        p.setProjektTyp("Nachbesprechung");
        p.setBudget(5);
        p.setLaufzeit(2);

        //dbKL.insertProjekt(p);

        Projekte projektSuche = dbKL.getProjektById(1);
       // System.out.println(projektSuche);

        //System.out.println(dbKL.getAllProjekteOrdrByBudgetDesc());
        Projektaufgaben aufgabe=new Projektaufgaben();
        aufgabe.setAufwandInStunden(30);
        aufgabe.setAufgabenBezeichnung("Hans");
        //aufgabe.setProjektId(1);
       // dbKL.insertProjektAufgabe(aufgabe,1);
        //System.out.println(dbKL.getProjektMitDenMeistenAufgaben());
       // System.out.println(dbKL.getProjektMitLaengsterLaufzeit());

        System.out.println(dbKL.getSummeAufwandInStundenByProjektId(1));


        /*
        dbHelper.createKundenTable();
        dbHelper.createRechnungenTable();

        Kunde kNeu =new Kunde();
        kNeu.setVorname("Max");
        kNeu.setNachname("Muster");
        kNeu.setGeschlecht("Mann");
        kNeu.setBonuspunkte(250);

        dbHelper.insertKunde(kNeu);

        System.out.println("Neuer Kunden " + kNeu);
        */

        /*Update und GetKunde
        Kunde k1 = dbHelper.getKundeByKdnr(1);
        System.out.println(k1);
        k1.setVorname("Hans");
        dbHelper.updateKunde(k1);
        k1 = dbHelper.getKundeByKdnr(1);
        System.out.println(k1);
        k1 = dbHelper.getKundeByKdnr(7);
        System.out.println(k1);
*/
        /* Aufgabe 6 */
        /*
        Kunde k1 = dbHelper.getKundeByKdnr(1);
        Rechnung r1 =new Rechnung();
        r1.setDatum("1.03.2021");
        r1.setGesamtbetrag(200);
        r1.setKdnr(k1.getKdnr());
        dbHelper.insertRechnung(r1,k1);
        System.out.println("Neue Rechnung " + r1);
        */

        /*Aufgabe 6c */
        /*
        Rechnung r =new Rechnung();
        r.setDatum("1.03.2021");
        r.setGesamtbetrag(400);
        r.setKdnr(1);
        r.setRenr(1);
        dbHelper.updateRechnung(r);
*/
        /*Aufgabe 6b*/
        /*
        Kunde kNeu =new Kunde();
        kNeu.setVorname("Josef");
        kNeu.setNachname("Mayer");
        kNeu.setGeschlecht("Mann");
        kNeu.setBonuspunkte(100);

        Rechnung rNeu =new Rechnung();
        rNeu.setDatum("1.03.2021");
        rNeu.setGesamtbetrag(400);

        ArrayList<Rechnung> rechnungen=new ArrayList<Rechnung>();
        rechnungen.add(rNeu);

        dbHelper.insertKundeUndRechnungen(rechnungen,kNeu);


*/
        /*Aufgabe 6d
        List<Rechnung> rechnungen=dbHelper.getRechnungenByKunde(1);
        System.out.println(rechnungen);
        */

        /* Aufgabe 7
        ArrayList<Kunde> weiblicheKunden =new ArrayList<Kunde>();
        weiblicheKunden = dbHelper.getWeiblicheKunden();
        System.out.println(weiblicheKunden);
        */

        /*Aufgabe 8*/
        /*
        Kunde mostBonusPoints = dbHelper.getKundeMitDenMeistenBonusPunkten();
        System.out.println(mostBonusPoints);
        */

        /*Aufgabe 9
        Kunde kDelte = new Kunde();
        kDelte.setKdnr(1);;
        dbHelper.loescheAlleRechnungenUndDanachDenKunden(kDelte);
*/
        /*Aufgabe 10 */
        //dbHelper.printKundenMetadata();

        //dbHelper.fillSampleKundenDaten();

        //dbHelper.getDatabaseMetaData();
    }
}
