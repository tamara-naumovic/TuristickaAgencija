/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import domen.Rezervacija;
import domen.StavkaAranzmana;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kolekcije.KolekcijaUlogovanih;
import konstante.Konstante;
import kontroler.Kontroler;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Tami
 */
public class NitKlijent extends Thread{
    
    Socket socket;
    private boolean aktivan;

    public NitKlijent(Socket socket) {
        this.socket = socket;
        this.aktivan = true;
        
    }

    public Socket getSocket() {
        return socket;
    }
    
    @Override
    public void run() {
        try {
            while (aktivan  && !isInterrupted()) {                
                KlijentTransfer ct = primi();
                ServerTransfer st = new ServerTransfer();
                System.out.println("O:" +ct.getOperacija());
                boolean success;
                switch(ct.getOperacija()){
                    case Konstante.VRATI_AGENTE:
                            List<OpstiDomenskiObjekat> agenti = Kontroler.getInstance().getAgente();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(agenti);
                        break;
                    case Konstante.VRATI_PARTNERE: 
                            List<OpstiDomenskiObjekat> partneri = Kontroler.getInstance().getPoslovnePartnere();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(partneri);
                        break;
                    case Konstante.VRATI_KLIJENTE:
                            List<OpstiDomenskiObjekat> klijenti = Kontroler.getInstance().getKlijente();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(klijenti);
                        break;
                    case Konstante.VRATI_ARANZMANE:
                            List<OpstiDomenskiObjekat> aranzmani = Kontroler.getInstance().getAranzmane();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(aranzmani);
                        break;
                    case Konstante.VRATI_MESTA:
                            List<OpstiDomenskiObjekat> mesta = Kontroler.getInstance().getMesta();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(mesta);
                        break;
                    case Konstante.VRATI_STAVKE:
                            List<OpstiDomenskiObjekat> stavke = Kontroler.getInstance().getSveStavke();
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(stavke);
                        
                        case Konstante.VRATI_KLIJENTSKE_REZERVACIJE:
                        
                            List<OpstiDomenskiObjekat> Krezervacije = Kontroler.getInstance().getKlijentskeRezervacije((Klijent) ct.getParametar());
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(Krezervacije);
                        
                        break;
                    case Konstante.VRATI_PARTNERSKE_ARANZMANE:
                        
                            List<OpstiDomenskiObjekat> partnerAranzmani = Kontroler.getInstance().getPartnerskeAranzmane((PoslovniPartner) ct.getParametar());
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(partnerAranzmani);
                        
                        break;
                    case Konstante.VRATI_STAVKE_ARANZMANA:
                        
                            List<OpstiDomenskiObjekat> stavkeAr = Kontroler.getInstance().getStavkeAranzmana((Aranzman) ct.getParametar());
                            st.setUspesnost(Konstante.USPEH);
                            st.setPodaci(stavkeAr);
                       
                        break;
                    case Konstante.VRATI_REZERVACIJE:
                        List<OpstiDomenskiObjekat> rezervacije = Kontroler.getInstance().getRezervacije();
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(rezervacije);
                    case Konstante.KREIRAJ_PARTNERA:
                        success = Kontroler.getInstance().kreirajPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_KLIJENTA:
                        success = Kontroler.getInstance().kreirajKlijenta((Klijent) ct.getParametar());
                        //System.out.println(""+ct.getParametar().toString());
                        if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_ARANZMAN:
                        success = Kontroler.getInstance().kreirajAranzman((Aranzman) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_REZERVACIJU:
                        success = Kontroler.getInstance().kreirajRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.KREIRAJ_STAVKU_ARANZMANA:
                        
                            success = Kontroler.getInstance().kreirajStavkuAranzmana((StavkaAranzmana) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.IZMENI_KLIJENTA:
                        
                            success = Kontroler.getInstance().izmeniKlijenta((Klijent) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }
                        
                        break;
                    case Konstante.IZMENI_PARTNERA:
                        success = Kontroler.getInstance().izmeniPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_PARTNERA:
                        success = Kontroler.getInstance().obrisiPartnera((PoslovniPartner) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_KLIJENTA:
                        success = Kontroler.getInstance().obrisiKlijenta((Klijent) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_REZERVACIJU:
                        success = Kontroler.getInstance().obrisiRezervaciju((Rezervacija) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_ARANZMAN:
                        success = Kontroler.getInstance().obrisiAranzman((Aranzman) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.OBRISI_STAVKU_ARANZMANA:
                        success = Kontroler.getInstance().obrisiStavkuAranzmana((StavkaAranzmana) ct.getParametar());
                            if (success) {
                                st.setUspesnost(Konstante.USPEH);
                            } else {
                                st.setUspesnost(Konstante.GRESKA);
                            }

                        break;
                    case Konstante.PRETRAZI_KLIJENTE:
                    
                        List<OpstiDomenskiObjekat> klijentiPretraga = Kontroler.getInstance().pretraziKlijente((String) ct.getParametar());
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(klijentiPretraga);
                        break;
                    case Konstante.PRETRAZI_PARTNERE:
                    
                        List<OpstiDomenskiObjekat> partneriPretraga = Kontroler.getInstance().pretraziPartnere((String) ct.getParametar());
                        st.setUspesnost(Konstante.USPEH);
                        st.setPodaci(partneriPretraga);
                        break;
                    case Konstante.NADJI_AGENTA:
                        
                        OpstiDomenskiObjekat ulogovaniAgent = Kontroler.getInstance().nadjiAgenta((Agent) ct.getParametar());
                        if (ulogovaniAgent!=null) {
                            if (!KolekcijaUlogovanih.getInstance().getListaUlogovanih().contains(ulogovaniAgent)) {
                                KolekcijaUlogovanih.getInstance().dodaj((Agent)ulogovaniAgent);
                                System.out.print("Agent u kolekciji" +KolekcijaUlogovanih.getInstance().getListaUlogovanih().toString());
                                st.setUspesnost(Konstante.USPEH);
                                st.setPodaci(ulogovaniAgent);
                            }else{
                                st.setUspesnost(Konstante.GRESKA);
                                st.setPodaci(ulogovaniAgent);
                            }
                        }else{
                            st.setUspesnost(Konstante.GRESKA);
                        }
                        break;
                    case Konstante.LOGOUT:
                        OpstiDomenskiObjekat ulogovaniAg = (Agent) ct.getParametar();
                        if (ulogovaniAg!=null) {
                            KolekcijaUlogovanih.getInstance().obrisi((Agent)ulogovaniAg);
                            st.setUspesnost(Konstante.USPEH);
                            //server.Server.closeClient(this);
                            
                        }else{
                            st.setUspesnost(Konstante.GRESKA);
                        }
                        break;
                }
                posalji(st);
            }
            
        } catch (Exception e) {
        }
    }

    public void posalji(ServerTransfer st) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(st);
        }catch (IOException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public KlijentTransfer primi() {
        KlijentTransfer kt = new KlijentTransfer();
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            kt = (KlijentTransfer) in.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(KlijentNit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kt;
    }
}
