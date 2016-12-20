/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.partneri;

import baza.DbBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOPretraziPartnere extends OpstaSO{
    
    private List<OpstiDomenskiObjekat> partneri;
    private String kriterijumPretrage;

    public List<OpstiDomenskiObjekat> getPartneri() {
        return partneri;
    }

    public SOPretraziPartnere(String kriterijumPretrage) {
        this.kriterijumPretrage = kriterijumPretrage;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        List<OpstiDomenskiObjekat> allPartneri = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new PoslovniPartner());
        System.out.println("allPartneri size: " + allPartneri.size());
        partneri = new LinkedList<>();

        for (OpstiDomenskiObjekat odo : allPartneri) {
            PoslovniPartner pp = (PoslovniPartner) odo;
            if (findStringMatches(pp)) {
                System.out.println("Usao u uslov");
                Mesto mesto = (Mesto) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Mesto(),pp.getMesto().getMestoID());
                pp.setMesto(mesto);
                partneri.add(pp);
            }
        }
    }

    private boolean findStringMatches(PoslovniPartner pp) {
        System.out.println(pp);
        String[] trazeniKriterijumi = kriterijumPretrage.split(" ");
        for (String kriterijum : trazeniKriterijumi) {
            
            if (pp.getNaziv().toLowerCase().matches("(.*)" + kriterijum.toLowerCase() + "(.*)") || pp.getTipPoslovnogPartnera().toString().matches("(.*)" + kriterijum.toLowerCase() + "(.*)") ) {
                return true;
            }
        }
        return false;
    }
    
    
}
