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
public class Klijent extends OpstiDomenskiObjekat {

    private int klijentID;
    private String prezime;
    private String ime;
    private String email;
    private String telefon;
    private Mesto mesto;
    private List<Rezervacija> listarezervacija;
    private List<Rezervacija> aktivne;
    
    public List<Rezervacija> getListarezervacija() {
        return listarezervacija;
    }
    
    

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
    

    
    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String Ime) {
        this.ime = Ime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
    
    public void dodajRezervaciju(Rezervacija rez){
        
        rez.setKlijent(this);
        listarezervacija.add(rez);
    }
    
    public void obrisiRezervaciju(Rezervacija r){
        listarezervacija.remove(r);
    }

    public void setListarezervacija(List<Rezervacija> listarezervacija) {
        this.listarezervacija = listarezervacija;
    }
    

    public Klijent(int klijentID, String Prezime, String Ime, String email, String telefon, Mesto mesto) {
        this.klijentID = klijentID;
        this.prezime = Prezime;
        this.ime = Ime;
        this.email = email;
        this.telefon = telefon;
        this.mesto = mesto;
        listarezervacija = new LinkedList<>();
        aktivne = new LinkedList<>();
    }

    

    public Klijent() {
        listarezervacija = new LinkedList<>();
    }
    
        
    public List<Rezervacija> getAktivne(){
        if (listarezervacija.isEmpty()) {
            return null;
        }else{
            for (Rezervacija rez : listarezervacija) {
                if (rez.getAranzaman().getDatumPolaska().after(new Date())) {
                    aktivne.add(rez);
                }
            }
        }
        return aktivne;
    }
    
    
    
    
    @Override
    public String getNazivTabele() {
        return "klijent";
    }

    @Override
    public String getParametre() {
        return String.format("%s, '%s', '%s', '%s', %s, '%s'", klijentID, ime, telefon, email, mesto.getVrednostPrimarnogKljuca(),prezime);
    }

    @Override
    public String getNaziveParametara() {
        return "KlijentID,Ime,BrojTelefona,EmailAdresa,MestoID,Prezime";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "KlijentID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return klijentID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> klijenti = new ArrayList<>();
        try {
            while(rs.next()){
                Integer id = rs.getInt("KlijentID");
                String imeS = rs.getString("Prezime");
                String prezimeS = rs.getString("Ime");
                String mail = rs.getString("EmailAdresa");
                String broj = rs.getString("BrojTelefona");
                Integer mestoid = rs.getInt("MestoID");
                                
               Klijent k = new Klijent(id,prezimeS, imeS, mail, broj, new Mesto(mestoid));
               klijenti.add(k);
            }
                
        } catch (Exception ex) {
            System.out.println("Greska u Klijent.Class ResultSet");
        }
        return klijenti;
    }

    @Override
    public String getUpdateUpit() {
        return "KlijentID ="+klijentID+", Ime = '"+ime+"', Prezime = '"+prezime+"', EmailAdresa = '"+email+"', BrojTelefona = '"+telefon+"', MestoID = "+mesto.getVrednostPrimarnogKljuca();
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public String toString() {
        return "Klijent{" + "klijentID=" + klijentID + ", ime=" + ime+", prezime="+prezime + ", email=" + email + ", telefon=" + telefon + ", mesto=" + mesto.toString() + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.klijentID;
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
        final Klijent other = (Klijent) obj;
        if (this.klijentID != other.klijentID) {
            return false;
        }
        return true;
    }

    public void obrisiRezervaciju(int red) {
        listarezervacija.remove(red);
    }

    

    
    
    
}
