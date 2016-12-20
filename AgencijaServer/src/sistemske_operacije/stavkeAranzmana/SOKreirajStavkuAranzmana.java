/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.stavkeAranzmana;

import baza.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOKreirajStavkuAranzmana extends OpstaSO {
    private OpstiDomenskiObjekat stavka;
    private boolean uspesno = false;
    
    public boolean isUspesno(){
        return uspesno;
    }
    
    public SOKreirajStavkuAranzmana(OpstiDomenskiObjekat stavka){
        this.stavka = stavka;
    }
    @Override
    protected void executeSpecificOperation() throws SQLException {
        uspesno = DbBroker.getInstance().saveOpstiDomenskiObjekat(stavka);
                
    }
}
