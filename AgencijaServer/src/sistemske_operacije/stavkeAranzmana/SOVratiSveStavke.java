/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.stavkeAranzmana;

import baza.DbBroker;
import domen.OpstiDomenskiObjekat;
import domen.StavkaAranzmana;
import java.util.List;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOVratiSveStavke extends OpstaSO {
    
    List<OpstiDomenskiObjekat> stavke;

    public List<OpstiDomenskiObjekat> getStavke() {
        return stavke;
    }

    
    @Override
    protected void executeSpecificOperation() throws Exception {
        stavke = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new StavkaAranzmana());
    }
    
}
