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
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiKlijente extends OpstaSO {
    
    List<OpstiDomenskiObjekat> klijenti;

    public List<OpstiDomenskiObjekat> getKlijenti() {
        return klijenti;
    }

    
    @Override
    protected void executeSpecificOperation() throws Exception {
        klijenti = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Klijent());
        for (OpstiDomenskiObjekat odo : klijenti) {
            Klijent k = (Klijent) odo;
            Mesto mesto = (Mesto) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Mesto(), k.getMesto().getMestoID());
            k.setMesto(mesto);
            
        }
    }
}
