/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.aranzmani;

import baza.DbBroker;
import domen.Aranzman;
import domen.OpstiDomenskiObjekat;
import domen.StavkaAranzmana;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiStavkeAranzmana extends OpstaSO {
    private List<OpstiDomenskiObjekat> stavke;
    private Aranzman aranzman;

    public SOVratiStavkeAranzmana(Aranzman aranzman) {
        stavke = new LinkedList<>();
        this.aranzman = aranzman;
    }

    public List<OpstiDomenskiObjekat> getStavke() {
        return stavke;
    }
    
    @Override
    protected void executeSpecificOperation() throws Exception {
        List<OpstiDomenskiObjekat> stavkeAranzmana = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new StavkaAranzmana());
        for (OpstiDomenskiObjekat odo : stavkeAranzmana) {
            StavkaAranzmana sa = (StavkaAranzmana) odo;
            if (Objects.equals(this.aranzman.getAranzmanID(), sa.getAranzman().getAranzmanID())) {
                Aranzman a =(Aranzman) DbBroker.getInstance().getOpstiDomenskiObjekatByPrimaryKey(new Aranzman(), sa.getAranzman().getAranzmanID());
                sa.setAranzman(a);
                stavke.add(a);
            }
        }
    }
}
