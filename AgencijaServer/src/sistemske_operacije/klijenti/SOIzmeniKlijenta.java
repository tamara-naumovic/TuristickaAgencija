/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.klijenti;

import baza.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOIzmeniKlijenta extends OpstaSO{
    private OpstiDomenskiObjekat klijent;
    private boolean success = false;

    public boolean isUspesno() {
        return success;
    }

    public SOIzmeniKlijenta(OpstiDomenskiObjekat klijent) {
        this.klijent = klijent;
    }

    @Override
    protected void executeSpecificOperation() throws SQLException {
        success = DbBroker.getInstance().updateOpstiDomenskiObjekat(klijent);
    }
}
