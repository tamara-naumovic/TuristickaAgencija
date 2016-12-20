/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class Aranzman extends OpstiDomenskiObjekat{

    private int aranzmanID;
    private double cena;
    private Date datumPolaska;
    private Date datumPovratka;
    private PoslovniPartner poslovniPartner;
    private String naziv;
    private String destinacija;
    private List<StavkaAranzmana> listaStavki;

    public List<StavkaAranzmana> getListaStavki() {
        return listaStavki;
    }

    public void setListaStavki(List<StavkaAranzmana> listaStavki) {
        this.listaStavki = listaStavki;
    }

    
    public Integer getAranzmanID() {
        return aranzmanID;
    }

    public void setAranzmanID(int aranzmanID) {
        this.aranzmanID = aranzmanID;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public Date getDatumPolaska() {
        return datumPolaska;
    }

    public void setDatumPolaska(Date datumPolaska) {
        this.datumPolaska = datumPolaska;
    }

    public Date getDatumPovratka() {
        return datumPovratka;
    }

    public void setDatumPovratka(Date datumPovratka) {
        this.datumPovratka = datumPovratka;
    }

    public PoslovniPartner getPoslovniPartner() {
        return poslovniPartner;
    }

    public void setPoslovniPartner(PoslovniPartner poslovniPartnerID) {
        this.poslovniPartner = poslovniPartnerID;
    }

    

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDestinacija() {
        return destinacija;
    }

    public void setDestinacija(String destinacija) {
        this.destinacija = destinacija;
    }

    public Aranzman(int aranzmanID, double cena, Date datumPolaska, Date datumPovratka, PoslovniPartner poslovniPartner, String naziv, String destinacija) {
        this.aranzmanID = aranzmanID;
        this.cena = cena;
        this.datumPolaska = datumPolaska;
        this.datumPovratka = datumPovratka;
        this.poslovniPartner = poslovniPartner;
        this.naziv = naziv;
        this.destinacija = destinacija;
    }
    
    
    public Aranzman() {
        listaStavki = new LinkedList<>();
    }

    public Aranzman(int aranzmanID) {
        this.aranzmanID = aranzmanID;
    }

    
    
    @Override
    public String getNazivTabele() {
        return "aranzman";
    }

    @Override
    public String getParametre() {
        return String.format("%s, '%s', '%s', '%s', %s, '%s', '%s'", aranzmanID, cena, datumPolaska, datumPovratka, poslovniPartner.getPoslovniPartnerID(), naziv, destinacija);
    }

    @Override
    public String getNaziveParametara() {
        return "AranzmanID,Cena,DatumPolaska,DatumPovratka,PoslovniPartnerID,Naziv,Destinacija";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "AranzmanID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return aranzmanID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> aranzmani = new ArrayList<>();
        try {
            while (rs.next()) {                
                Integer id = rs.getInt("AranzmanID");
                Double cenA = rs.getDouble("Cena");
                Date povratak = rs.getDate("DatumPovratka");
                Date polaska = rs.getDate("DatumPolaska");
                Integer partnerID = rs.getInt("PoslovniPartnerID");
                String nazivs = rs.getString("Naziv");
                String destinacijas = rs.getString("Destinacija");
                
                Aranzman ara = new Aranzman(id, cenA, povratak, polaska, new PoslovniPartner(partnerID), nazivs, destinacijas);
                aranzmani.add(ara);
            }
        } catch (Exception e) {
            System.out.println("Greska u Aranzman.Class ResultSet"); 
                 
        }
        return aranzmani;
    }

    @Override
    public String getUpdateUpit() {
        return "AranzmanID = "+aranzmanID+", Cena = "+cena+", DatumPovratka = "+datumPovratka+", DatumPolaska = "+datumPolaska+
                ", PoslovniPartnerID = "+poslovniPartner.getPoslovniPartnerID() +", Naziv = "+naziv+", Destinacija = "+destinacija;
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public String toString() {
        return destinacija+", "+datumPolaska;
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.aranzmanID;
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
        final Aranzman other = (Aranzman) obj;
        if (this.aranzmanID != other.aranzmanID) {
            return false;
        }
        return true;
    }

    public void dodajStavku(StavkaAranzmana nova) {
        nova.setAranzman(this);
        listaStavki.add(nova);
    }
    
    
}
