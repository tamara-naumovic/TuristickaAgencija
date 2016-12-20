/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class PoslovniPartner extends OpstiDomenskiObjekat {

    private int poslovniPartnerID;
    private int pib;
    private int maticniBroj;
    private String naziv;
    private String ziroRacun;
    private int tipPoslovnogPartnera;
    private String ulica;
    private String broj;
    private Mesto mesto;
    private List<Aranzman> listaAranzmana;

    public List<Aranzman> getListaAranzmana() {
        return listaAranzmana;
    }

    public void setListaAranzmana(List<Aranzman> listaAranzmana) {
        this.listaAranzmana = listaAranzmana;
    }

    
    public Integer getPoslovniPartnerID() {
        return poslovniPartnerID;
    }

    public void setPoslovniPartnerID(int poslovniPartnerID) {
        this.poslovniPartnerID = poslovniPartnerID;
    }

    public Integer getPib() {
        return pib;
    }

    public void setPib(int pib) {
        this.pib = pib;
    }

    public Integer getMaticniBroj() {
        return maticniBroj;
    }

    public void setMaticniBroj(int maticniBroj) {
        this.maticniBroj = maticniBroj;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getZiroRacun() {
        return ziroRacun;
    }

    public void setZiroRacun(String ziroRacun) {
        this.ziroRacun = ziroRacun;
    }

    public Integer getTipPoslovnogPartnera() {
        return tipPoslovnogPartnera;
    }

    public void setTipPoslovnogPartnera(int tipPoslovnogPartnera) {
        this.tipPoslovnogPartnera = tipPoslovnogPartnera;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String Ulica) {
        this.ulica = Ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    public PoslovniPartner() {
        listaAranzmana = new LinkedList<>();
    }

    public PoslovniPartner(int poslovniPartnerID, int pib, int maticniBroj, String naziv, String ziroRacun, int tipPoslovnogPartnera, String Ulica, String broj, Mesto mesto) {
        this.poslovniPartnerID = poslovniPartnerID;
        this.pib = pib;
        this.maticniBroj = maticniBroj;
        this.naziv = naziv;
        this.ziroRacun = ziroRacun;
        this.tipPoslovnogPartnera = tipPoslovnogPartnera;
        this.ulica = Ulica;
        this.broj = broj;
        this.mesto = mesto;
    }

    public PoslovniPartner(Integer poslovniPartnerID) {
        this.poslovniPartnerID = poslovniPartnerID;
    }

    
    @Override
    public String toString() {
        return "PoslovniPartner{" + "poslovniPartnerID=" + poslovniPartnerID + ", pib=" + pib + ", maticniBroj=" + maticniBroj + ", naziv=" + naziv + ", ziroRacun=" + ziroRacun + ", tipPoslovnogPartnera=" + tipPoslovnogPartnera + ", Ulica=" + ulica + ", broj=" + broj + ", mesto=" + mesto.toString() + '}';
    }
    
    
    @Override
    public String getNazivTabele() {
        return "poslovnipartner";
    }

    @Override
    public String getParametre() {
        return String.format("%s, %s, %s, '%s', '%s', %s, '%s', '%s', %s", poslovniPartnerID, pib, maticniBroj, naziv, ziroRacun, tipPoslovnogPartnera, ulica, broj, mesto.getMestoID());
    }

    @Override
    public String getNaziveParametara() {
        return "PoslovniPartnerID,PIB,MaticniBroj,Naziv,ZiroRacun,TipPoslovnogPartnera,Ulica,Broj,MestoID";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "PoslovniPartnerID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return poslovniPartnerID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> partneri = new ArrayList<>();
        
        try {
            while (rs.next()) {                
            Integer partnerID = rs.getInt("PoslovniPartnerID");
            Integer piB = rs.getInt("PIB");
            Integer matBr = rs.getInt("MaticniBroj");
            String naziV = rs.getString("Naziv");
            String ziro = rs.getString("ZiroRacun");
            Integer tip = rs.getInt("TipPoslovnogPartnera");
            String ulicA = rs.getString("Ulica");
            String broJ = rs.getString("Broj");
            Integer mestoid = rs.getInt("MestoID");
            
            PoslovniPartner pp = new PoslovniPartner(partnerID, piB, matBr, naziV, ziro, tip, ulicA, broJ, new Mesto(mestoid, 0, null));
            partneri.add(pp);
            }
        } catch (Exception e) {
            System.out.println("Greska u PoslovniPartner.Class ResultSet");
        }
        return partneri;
    }

    @Override
    public String getUpdateUpit() {
        return "PoslovniPartnerID = "+poslovniPartnerID+", PIB = "+pib+", MaticniBroj = "+maticniBroj+", Naziv = '"+naziv+
                "', ZiroRacun = '"+ ziroRacun +"',TipPoslovnogPartnera = "+tipPoslovnogPartnera+", Ulica = '"+ ulica +
                "', Broj = '"+broj+"', MestoID = "+mesto.getMestoID();
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.poslovniPartnerID;
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
        final PoslovniPartner other = (PoslovniPartner) obj;
        if (this.poslovniPartnerID != other.poslovniPartnerID) {
            return false;
        }
        return true;
    }
    
}
