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
public class Rezervacija extends OpstiDomenskiObjekat {

    private int rezervacijaID;
    private double zaduzenje;
    private Date datum;
    private Date rokPlacanja;
    private Agent agent;
    private Klijent klijent;
    private Aranzman aranzaman;
    private double avans;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public double getAvans() {
        return avans;
    }

    public void setAvans(double avans) {
        this.avans = avans;
    }

    
    public Aranzman getAranzaman() {
        return aranzaman;
    }

    public void setAranzaman(Aranzman aranzaman) {
        this.aranzaman = aranzaman;
    }

    public int getRezervacijaID() {
        return rezervacijaID;
    }

    public void setRezervacijaID(int rezervacijaID) {
        this.rezervacijaID = rezervacijaID;
    }

    public double getZaduzenje() {
        return zaduzenje;
    }

    public void setZaduzenje(double zaduzenje) {
        this.zaduzenje = zaduzenje;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getRokPlacanja() {
        return rokPlacanja;
    }

    public void setRokPlacanja(Date rokPlacanja) {
        this.rokPlacanja = rokPlacanja;
    }

    
    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Rezervacija() {
    }

    public Rezervacija(double avans,Agent agent, int rezervacijaID, double zaduzenje, Date datum, Date rokPlacanja, Aranzman aranzman, Klijent klijent) {
        this.rezervacijaID = rezervacijaID;
        this.zaduzenje = zaduzenje;
        this.datum = datum;
        this.rokPlacanja = rokPlacanja;
        this.agent = agent;
        this.klijent = klijent;
        this.aranzaman = aranzman;
        this.avans = avans;
    }
    
    
    @Override
    public String getNazivTabele() {
        return "rezervacija";
        
    }

    @Override
    public String getParametre() {
        return String.format("%s, %s, %s, %s, '%s', '%s', '%s', '%s'", rezervacijaID, aranzaman.getAranzmanID(), klijent.getKlijentID(), agent.getAgentID(), datum, rokPlacanja, zaduzenje, avans);
    }

    @Override
    public String getNaziveParametara() {
        return "RezervacijaID,AranzmanID,KlijentID,AgentID,Datum,RokPlacanja,Zaduzenje,Avans";
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
        List<OpstiDomenskiObjekat> rezervacije = new ArrayList<>();
        
        try {
            while (rs.next()) {                
                Integer id = rs.getInt("RezervacijaID");
                Double dug = rs.getDouble("Zaduzenje");
                Date datu = rs.getDate("Datum");
                Date rok = rs.getDate("RokPlacanja");
                Double avansS = rs.getDouble("Avans");
                
                Integer kid = rs.getInt("KlijentID");
                Integer arid = rs.getInt("AranzmanID");
                Integer agentS = rs.getInt("AgentID");
                
                Aranzman ar = new Aranzman(arid);
                Klijent k = new Klijent();
                k.setKlijentID(kid);
                
                Rezervacija r = new Rezervacija(avansS, new Agent(agentS),id, dug, datu,rok, ar, k);
                rezervacije.add(r);
            }
        } catch (Exception e) {
            System.out.println("Greska u Rezervacije.Class ResultSet");
        }
        return rezervacije;
    }

    @Override
    public String getUpdateUpit() {
        return "RezervacijaID = "+rezervacijaID+", Zaduzenje = "+zaduzenje+", Avans = "+avans+", Datum = "+datum+
                ", RokPlacanja = " + rokPlacanja +", KlijentID = "+klijent.getKlijentID()+", AranzmanID = "+aranzaman.getAranzmanID()+", AgentID = "+agent.getAgentID();
    }

    @Override
    public String getSlozeniPrimarniKljuc() {
        return "WHERE RezervacijaID="+rezervacijaID+" AND AranzmanID="+aranzaman.getAranzmanID()+" AND KlijentID="+klijent.getKlijentID()+" AND AgentID="+agent.getAgentID();
    }

    @Override
    public String toString() {
        return  "Rezervacija{" +"rezervacijaID="+rezervacijaID+", klijentID="+klijent.getKlijentID()+ ", aranzmanID="+aranzaman.getAranzmanID()+ ", agentID=" + agent.getAgentID() +", Zaduzenje = "+zaduzenje+", Avans = "+avans+", Datum = "+datum+", RokPlacanja = " + rokPlacanja + '}';
  
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.rezervacijaID;
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
        final Rezervacija other = (Rezervacija) obj;
        if (this.rezervacijaID != other.rezervacijaID) {
            return false;
        }
        return true;
    }

    
    
    
    
}
