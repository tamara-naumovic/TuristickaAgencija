/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.aranzmani;

import baza.DbBroker;
import domen.Aranzman;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiAranzmane extends OpstaSO{
    
    List<OpstiDomenskiObjekat> aranzmani;

    public List<OpstiDomenskiObjekat> getAranzmani() {
        return aranzmani;
    }

    
    @Override
    protected void executeSpecificOperation() throws Exception {
        aranzmani = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Aranzman());
        for (OpstiDomenskiObjekat odo : aranzmani) {
            Aranzman a = (Aranzman) odo;
            PoslovniPartner pp = (PoslovniPartner) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new PoslovniPartner(), a.getPoslovniPartner().getPoslovniPartnerID());
            a.setPoslovniPartner(pp);
            
        }
    }
}
