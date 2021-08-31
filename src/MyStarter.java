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

        Kunde k1 = dbHelper.getKundeByKdnr(1);
        System.out.println(k1);
        k1.setVorname("Hans");
        dbHelper.updateKunde(k1);
        k1 = dbHelper.getKundeByKdnr(1);
        System.out.println(k1);
        k1 = dbHelper.getKundeByKdnr(7);
        System.out.println(k1);

    }
}
