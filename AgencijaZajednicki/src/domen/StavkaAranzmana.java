/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Tami
 */
public class StavkaAranzmana extends OpstiDomenskiObjekat {

    private int rbStavke;
    private Aranzman aranzman;
    private String naziv;
    private String opis;

    public int getRbStavke() {
        return rbStavke;
    }

    public void setRbStavke(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public Aranzman getAranzman() {
        return aranzman;
    }

    public void setAranzman(Aranzman aranzman) {
        this.aranzman = aranzman;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public StavkaAranzmana() {
    }
    

    
    public StavkaAranzmana(int rbStavke, Aranzman aranzman) {
        this.rbStavke = rbStavke;
        this.aranzman = aranzman;
    }

    public StavkaAranzmana(int rbStavke) {
        this.rbStavke = rbStavke;
    }

    public StavkaAranzmana(int rbStavke, Aranzman aranzman, String naziv, String opis) {
        this.rbStavke = rbStavke;
        this.aranzman = aranzman;
        this.naziv = naziv;
        this.opis = opis;
    }
    

    @Override
    public String getNazivTabele() {
        return "stavkaaranzmana";
    }
    
    @Override
    public String getParametre() {
        return String.format("%s, %s, '%s', '%s' ", rbStavke, aranzman.getAranzmanID(), naziv, opis);
    }

    @Override
    public String getNaziveParametara() {
        return "RbStavke,AranzmanID,Naziv,Opis";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return null;
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return null;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> stavke = new ArrayList<>();
        try {
            while (rs.next()) {                
                Integer rbSTavke = rs.getInt("RbStavke");
                Integer aranzmanID = rs.getInt("AranzmanID");
                String nazivS = rs.getString("Naziv");
                String opisS = rs.getString("Opis");
                
                StavkaAranzmana sa = new StavkaAranzmana(rbSTavke, new Aranzman(aranzmanID), nazivS, opisS);
                stavke.add(sa);
            }
        } catch (Exception e) {
            System.out.println("Greska u StavkaAranzmana.Class ResultSet"); 
                 
        }
        return stavke;
    }

    @Override
    public String getUpdateUpit() {
        return "AranzmanID = "+aranzman.getAranzmanID()+", RbStavke = "+rbStavke+", Naziv = "+naziv+", Opis = "+opis;

    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return "WHERE RbStavke="+rbStavke+" AND AranzmanID="+aranzman.getAranzmanID();
    }
    
}
