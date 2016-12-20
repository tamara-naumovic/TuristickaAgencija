/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;

import baza.DbBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tami
 */
public class SOVratiMesta extends OpstaSO {

    private List<OpstiDomenskiObjekat> mesta;

    public List<OpstiDomenskiObjekat> getMesta() {
        return mesta;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        mesta = DbBroker.getInstance().getAllOpstiDomenskiObjekats(new Mesto());
    }
    
}
