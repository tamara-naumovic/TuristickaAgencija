/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.klijenti;

import baza.DbBroker;
import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import domen.Rezervacija;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiKlijentskeRezervacije extends OpstaSO{
    private List<OpstiDomenskiObjekat> rezervacije;
    private Klijent klijent;

    public SOVratiKlijentskeRezervacije(Klijent klijent) {
        rezervacije = new LinkedList<>();
        this.klijent = klijent;
    }

    public List<OpstiDomenskiObjekat> getKlijentskeRezervacije() {
        return rezervacije;
    }
    
    @Override
    protected void executeSpecificOperation() throws Exception {
        List<OpstiDomenskiObjekat> klijentRezervacije = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Rezervacija());
        for (OpstiDomenskiObjekat odo : klijentRezervacije) {
            Rezervacija r = (Rezervacija) odo;
            if (Objects.equals(this.klijent.getKlijentID(), r.getKlijent().getKlijentID())) {
                Klijent k =(Klijent) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Klijent(), r.getKlijent().getKlijentID());
                Aranzman ar = (Aranzman) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Aranzman(), r.getAranzaman().getAranzmanID());
                Agent ag = (Agent) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Agent(), r.getAgent().getAgentID());
                r.setKlijent(k);
                r.setAgent(ag);
                r.setAranzaman(ar);
                rezervacije.add(r);
            }
        }
    }
}
