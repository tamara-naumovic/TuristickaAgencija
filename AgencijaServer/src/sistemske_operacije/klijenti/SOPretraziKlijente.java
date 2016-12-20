/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.klijenti;

import baza.DbBroker;
import domen.Klijent;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOPretraziKlijente extends OpstaSO{
    private List<OpstiDomenskiObjekat> klijenti;
    private String kriterijumPretrage;

    public List<OpstiDomenskiObjekat> getKlijenti() {
        return klijenti;
    }

    public SOPretraziKlijente(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        List<OpstiDomenskiObjekat> allKlijenti = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Klijent());
        System.out.println("allKlijenti size: " + allKlijenti.size());
        klijenti = new LinkedList<>();

        for (OpstiDomenskiObjekat odo : allKlijenti) {
            Klijent k = (Klijent) odo;
            if (findStringMatches(k)) {
                System.out.println("Usao u uslov");
                Mesto mesto = (Mesto) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Mesto(),k.getMesto().getMestoID());
                k.setMesto(mesto);
                klijenti.add(k);
                System.out.println("Nasao klijenta: "+k.toString());
            }
        }
        
    }

    private boolean findStringMatches(Klijent k) {
        System.out.println(k);
        String[] trazeniKriterijumi = kriterijumPretrage.split(" ");
        for (String kriterijum : trazeniKriterijumi) {
            
            if (k.getIme().toLowerCase().matches("(.*)" + kriterijum.toLowerCase() + "(.*)") || k.getPrezime().toLowerCase().matches("(.*)" + kriterijum.toLowerCase() + "(.*)") ) {
                return true;
            }
        }
        return false;
    }
}
