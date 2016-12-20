/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;

import baza.DbBroker;
import domen.Agent;
import domen.OpstiDomenskiObjekat;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class SOVratiAgente extends OpstaSO {

    List<OpstiDomenskiObjekat> agenti;

    public List<OpstiDomenskiObjekat> getAgenti() {
        return agenti;
    }

    @Override
    protected void executeSpecificOperation() throws Exception {
        agenti = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Agent());
        
    }

}
