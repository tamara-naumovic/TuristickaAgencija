/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.partneri;

import baza.DbBroker;
import domen.Aranzman;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiPartnerskeAranzmane extends OpstaSO{

    private List<OpstiDomenskiObjekat> aranzmani;
    private PoslovniPartner partner;

    public SOVratiPartnerskeAranzmane(PoslovniPartner partner) {
        aranzmani = new LinkedList<>();
        this.partner = partner;
    }

    public List<OpstiDomenskiObjekat> getPartnerskiAranzmani() {
        return aranzmani;
    }
    
    @Override
    protected void executeSpecificOperation() throws Exception {
        List<OpstiDomenskiObjekat> partnerAranzmani = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Aranzman());
        for (OpstiDomenskiObjekat odo : partnerAranzmani) {
            Aranzman a = (Aranzman) odo;
            if (Objects.equals(this.partner.getPoslovniPartnerID(), a.getPoslovniPartner().getPoslovniPartnerID())) {
                PoslovniPartner pp =(PoslovniPartner) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new PoslovniPartner(), a.getPoslovniPartner().getPoslovniPartnerID());
                a.setPoslovniPartner(pp);
                aranzmani.add(a);
            }
        }
    }
    
    
}
