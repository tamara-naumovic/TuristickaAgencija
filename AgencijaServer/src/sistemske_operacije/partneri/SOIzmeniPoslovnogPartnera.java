/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.partneri;

import baza.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOIzmeniPoslovnogPartnera extends OpstaSO{
    private OpstiDomenskiObjekat partner;
    private boolean success = false;

    public boolean isUspesno() {
        return success;
    }

    public SOIzmeniPoslovnogPartnera(OpstiDomenskiObjekat partner) {
        this.partner = partner;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        success = DbBroker.getInstance().updateOpstiDomenskiObjekat(partner);
    }
}
