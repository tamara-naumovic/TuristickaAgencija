/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kontroler;

import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.Mesto;
import domen.PoslovniPartner;
import domen.Rezervacija;
import domen.StavkaAranzmana;
import forme.*;
import forme.login.LoginForma;
import java.io.IOException;
import java.net.SocketException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import konstante.Konstante;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;


/**
 *
 * @author Tami
 */
public class Kontroler {

    
    private static Kontroler instance;

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginForma log = new LoginForma();
                log.setVisible(true);
                log.setLocationRelativeTo(null);
            }
        });
    }
    
    private void closeProgramOnSocketException() {
        JOptionPane.showMessageDialog(null, "Server closed the connection!\n Program will now exit!", "Error!", JOptionPane.ERROR_MESSAGE);
        System.exit(0);
    }

    

    public List<Agent> getAgente() throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_AGENTE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Agent>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<PoslovniPartner> getPoslovnePartnere() throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_PARTNERE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<PoslovniPartner>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<Klijent> getKlijente()throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_KLIJENTE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Klijent>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<Aranzman> getAranzmane()throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_ARANZMANE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Aranzman>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }
    
    public List<StavkaAranzmana> getSveStavke() throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_STAVKE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<StavkaAranzmana>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }
    
    public List<Rezervacija> getSveRezervacije() throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_REZERVACIJE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Rezervacija>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }


    public List<Mesto> getMesta() throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setOperacija(Konstante.VRATI_MESTA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Mesto>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<Rezervacija> getKlijentskeRezervacije(Klijent klijent)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(klijent);
        kt.setOperacija(Konstante.VRATI_KLIJENTSKE_REZERVACIJE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Rezervacija>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<Aranzman> getPartnerskeAranzmane(PoslovniPartner poslovniPartner) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(poslovniPartner);
        kt.setOperacija(Konstante.VRATI_PARTNERSKE_ARANZMANE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Aranzman>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<StavkaAranzmana> getStavkeAranzmana(Aranzman aranzman) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(aranzman);
        kt.setOperacija(Konstante.VRATI_STAVKE_ARANZMANA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<StavkaAranzmana>) st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public boolean kreirajPartnera(PoslovniPartner poslovniPartner) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(poslovniPartner);
        kt.setOperacija(Konstante.KREIRAJ_PARTNERA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean kreirajKlijenta(Klijent klijent) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(klijent);
        kt.setOperacija(Konstante.KREIRAJ_KLIJENTA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean kreirajAranzman(Aranzman aranzman) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(aranzman);
        kt.setOperacija(Konstante.KREIRAJ_ARANZMAN);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean kreirajRezervaciju(Rezervacija rezervacija) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(rezervacija);
        kt.setOperacija(Konstante.KREIRAJ_REZERVACIJU);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean kreirajStavkuAranzmana(StavkaAranzmana stavkaAranzmana)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(stavkaAranzmana);
        kt.setOperacija(Konstante.KREIRAJ_STAVKU_ARANZMANA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean izmeniKlijenta(Klijent klijent)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(klijent);
        kt.setOperacija(Konstante.IZMENI_KLIJENTA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean izmeniPartnera(PoslovniPartner poslovniPartner)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(poslovniPartner);
        kt.setOperacija(Konstante.IZMENI_PARTNERA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean obrisiKlijenta(Klijent klijent)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(klijent);
        kt.setOperacija(Konstante.OBRISI_KLIJENTA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean obrisiPartnera(PoslovniPartner poslovniPartner)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(poslovniPartner);
        kt.setOperacija(Konstante.OBRISI_PARTNERA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean obrisiRezervaciju(Rezervacija rezervacija) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(rezervacija);
        kt.setOperacija(Konstante.OBRISI_REZERVACIJU);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean obrisiAranzman(Aranzman aranzman) throws Exception{
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(aranzman);
        kt.setOperacija(Konstante.OBRISI_ARANZMAN);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public boolean obrisiStavkuAranzmana(StavkaAranzmana stavkaAranzmana)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(stavkaAranzmana);
        kt.setOperacija(Konstante.OBRISI_STAVKU_ARANZMANA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return true;
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return false;
        }
    }

    public List<Klijent> pretraziKlijente(String string)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(string);
        kt.setOperacija(Konstante.PRETRAZI_KLIJENTE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<Klijent>)st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public List<PoslovniPartner> pretraziPartnere (String string)throws Exception {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(string);
        kt.setOperacija(Konstante.PRETRAZI_PARTNERE);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (List<PoslovniPartner>)st.getPodaci();
            } else {
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }

    public Agent nadjiAgenta(Agent agent) throws Exception {
        
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(agent);
        kt.setOperacija(Konstante.NADJI_AGENTA);
        try {
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            if (st.getUspesnost()== Konstante.USPEH) {
                return (Agent)st.getPodaci();
            } else {
                if (st.getPodaci()!=null) {
                   throw new Exception("Agent je vec ulogovan");
                }
                Exception ex = st.getException();
                throw ex;
            }
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }
    
    public Agent izlogujSe(Agent ag) throws Exception{
        
        try {
        KlijentTransfer kt = new KlijentTransfer();
        kt.setParametar(ag);
        kt.setOperacija(Konstante.LOGOUT);
        
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            ag = (Agent) st.getPodaci();
            if (st.getUspesnost()== Konstante.USPEH) {
                System.out.println("Uspesno izlogovan");
                
            } else {
                throw new Exception(st.getException());
            }
            return ag;
        } catch (SocketException ex) {
            closeProgramOnSocketException();
            return null;
        }
    }
    
    public Klijent nadjiKlijenta(int klijentID) throws Exception {
        try {
            KlijentTransfer kt = new KlijentTransfer();
            kt.setParametar(klijentID);
            kt.setOperacija(Konstante.VRATI_KLIJENTA);
            Komunikacija.getInstance().sendRequest(kt);
            ServerTransfer st = Komunikacija.getInstance().readResponse();
            Klijent k = (Klijent) st.getPodaci();
            if (k!=null) {
                System.out.println("Vracen klijent: "+k.toString());
                return k;
            }else{
                Exception ex = st.getException();
                throw ex;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    
    
   
}