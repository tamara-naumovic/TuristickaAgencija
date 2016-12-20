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
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiPartnere extends OpstaSO {

    List<OpstiDomenskiObjekat> partneri;

    public List<OpstiDomenskiObjekat> getPartneri() {
        return partneri;
    }

    
    @Override
    protected void executeSpecificOperation() throws Exception {
        partneri = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new PoslovniPartner());
        for (OpstiDomenskiObjekat odo : partneri) {
            PoslovniPartner pp = (PoslovniPartner) odo;
            Mesto mesto = (Mesto) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Mesto(), pp.getMesto().getMestoID());
            pp.setMesto(mesto);
            
        }
    }
    
}
