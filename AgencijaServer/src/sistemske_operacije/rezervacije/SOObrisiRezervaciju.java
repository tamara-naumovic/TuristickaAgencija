/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije.rezervacije;

import baza.DbBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
import sistemske_operacije.OpstaSO;

/**
 *
 * @author Tami
 */
public class SOObrisiRezervaciju extends OpstaSO{
    private OpstiDomenskiObjekat rezervacija;
    private boolean uspesno = false;
    
    public boolean isUspesno(){
        return uspesno;
    }
    
    public SOObrisiRezervaciju(OpstiDomenskiObjekat rezervacija){
        this.rezervacija = rezervacija;
    }
    @Override
    protected void executeSpecificOperation() throws SQLException {
        uspesno = DbBroker.getInstance().deleteOpstiDomenskiObjekat(rezervacija);
                
    }
}
