/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Tami
 */
public abstract class OpstiDomenskiObjekat implements Serializable{

    public abstract String getNazivTabele();

    public abstract String getParametre();

    public abstract String getNaziveParametara();

    public abstract String getNazivPrimarnogKljuca();

    public abstract Integer getVrednostPrimarnogKljuca();

    public abstract List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs);

    public abstract String getUpdateUpit();

    public abstract String getSlozeniPrimarniKljuc();
}
