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
public class Agent extends OpstiDomenskiObjekat {

    private int agentID;
    private String imePrezime;
    private String sifra;
    private String korisnickoIme;

    public int getAgentID() {
        return agentID;
    }

    public void setAgentID(int AgentID) {
        this.agentID = AgentID;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String ImePrezime) {
        this.imePrezime = ImePrezime;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String Sifra) {
        this.sifra = Sifra;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String KorisnickoIme) {
        this.korisnickoIme = KorisnickoIme;
    }

    public Agent(int agentID, String imePrezime, String sifra, String korisnickoIme) {
        this.agentID = agentID;
        this.imePrezime = imePrezime;
        this.sifra = sifra;
        this.korisnickoIme = korisnickoIme;
    }

    public Agent() {
    }

    public Agent(Integer agentID) {
        this.agentID = agentID;
    }
    

    @Override
    public String getNazivTabele() {
        return "agent";
    }

    @Override
    public String getParametre() {
        return String.format("%s, '%s', '%s', '%s'", agentID, imePrezime, sifra, korisnickoIme);

    }

    @Override
    public String getNaziveParametara() {
        return "AgentID,ImePrezime,Sifra,KorisnickoIme";
    }

    @Override
    public String getNazivPrimarnogKljuca() {
        return "AgentID";
    }

    @Override
    public Integer getVrednostPrimarnogKljuca() {
        return agentID;
    }

    @Override
    public List<OpstiDomenskiObjekat> konvertujRSUListu(ResultSet rs) {
        List<OpstiDomenskiObjekat> agenti = new ArrayList<>();
        try {
            while (rs.next()) {
                Integer id = rs.getInt("AgentID");
                String ime = rs.getString("ImePrezime");
                String pass = rs.getString("Pass");
                String user = rs.getString("User");

                Agent a = new Agent(id, ime, pass, user);
                agenti.add(a);
            }
        } catch (Exception ex) {
            System.out.println("Greska u Agent.Class ResultSet");
        }
        return agenti;
    }

    @Override
    public String getUpdateUpit() {
        return "AgentID=" + agentID + "ImePrezime='" + imePrezime + "',Sifra='" + sifra + "',KorisnickoIme='" + korisnickoIme + "'";
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return null;
    }

    @Override
    public String toString() {
        return "Agent{" + "agentID=" + agentID + ", imePrezime=" + imePrezime + ", sifra=" + sifra + ", korisnickoIme=" + korisnickoIme + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.agentID;
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
        final Agent other = (Agent) obj;
        if (this.agentID != other.agentID) {
            return false;
        }
        return true;
    }

}
