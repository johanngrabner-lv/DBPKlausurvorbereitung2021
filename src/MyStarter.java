public class MyStarter {

    public static void main(String args[]){
        System.out.println("Klausurvorbereitung");

        DBHelper dbHelper=new DBHelper();
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

    }
}
