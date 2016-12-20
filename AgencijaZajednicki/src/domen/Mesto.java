/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class Mesto extends OpstiDomenskiObjekat {
    
    private int mestoID;
    private int ptt;
    private String naziv;

    public int getMestoID() {
        return mestoID;
    }

    public void setMestoID(int mestoID) {
        this.mestoID = mestoID;
    }

    public int getPtt() {
        return ptt;
    }

    public void setPtt(int ptt) {
        this.ptt = ptt;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Mesto() {
    }

    public Mesto(int mestoID, int ptt, String naziv) {
        this.mestoID = mestoID;
        this.ptt = ptt;
        this.naziv = naziv;
    }

    public Mesto(Integer mestoID) {
        this.mestoID = mestoID;
    }

    

    
    @Override
    public String getNazivTabele() {
        return "mesto";
    }

    @Override
    public String getParametre() {
        return String.format("%s, '%s', '%s'", mestoID, ptt, naziv);
    }

    @Override
    public String getNaziveParametara() {
        return "MestoID,Ptt,Naziv"; 
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "MestoID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return mestoID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> mesta = new ArrayList<>();
        try {
            while(rs.next()){
                Integer id = rs.getInt("MestoID");
                Integer ptt = rs.getInt("Ptt");
                String naziv = rs.getString("Naziv");
                
                Mesto m = new Mesto(id, ptt, naziv);
                mesta.add(m);
            }
        } catch (Exception ex) {
            System.out.println("Greska u Mesto.Class resultSet");
        }
        return mesta;
    }

    @Override
    public String getUpdateUpit() {
        return "MestoID = "+ mestoID+", PTT = "+ptt+", Naziv= '"+naziv+"'";
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public String toString() {
        return naziv + ", "+ ptt;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.mestoID;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mesto other = (Mesto) obj;
        if (this.mestoID != other.mestoID) {
            return false;
        }
        return true;
    }
    
    
}
