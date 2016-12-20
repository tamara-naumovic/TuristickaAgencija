/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;

import baza.DbBroker;
import domen.Agent;
import domen.OpstiDomenskiObjekat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tami
 */
public class SONadjiAgenta extends OpstaSO{
    private OpstiDomenskiObjekat agent;
    public OpstiDomenskiObjekat ulogovan;

    public SONadjiAgenta(OpstiDomenskiObjekat agent) {
        this.agent = agent;
        ulogovan=null;
    }
    

    @Override
    protected void executeSpecificOperation() throws Exception {
        try {
            System.out.println("pokrenut je u bazi...");
            List<OpstiDomenskiObjekat> agenti = DbBroker.getInstance().getAllOpstiDomenskiObjekats(agent);
            Agent uneti = (Agent) agent;
            for (OpstiDomenskiObjekat odo : agenti) {
                Agent a = (Agent) odo;
                System.out.println(a.getKorisnickoIme());
                System.out.println(a.getSifra());
                System.out.println(uneti.getKorisnickoIme());
                System.out.println(uneti.getSifra());
                if (a.getKorisnickoIme().equals(uneti.getKorisnickoIme())&& a.getSifra().equals(uneti.getSifra())) {
                    this.ulogovan =a;
                    return;
                }
            }
        } catch (Exception e) {
            Logger.getLogger(SONadjiAgenta.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public OpstiDomenskiObjekat getAgent() {
        return ulogovan;
    }

   
    
    
    
}
