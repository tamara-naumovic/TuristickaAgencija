/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kontroler;

import domen.Agent;
import domen.Aranzman;
import domen.Klijent;
import domen.OpstiDomenskiObjekat;
import domen.PoslovniPartner;
import domen.Rezervacija;
import domen.StavkaAranzmana;
import forme.*;
import java.io.IOException;
import java.util.List;
import server.Server;
import sistemske_operacije.SONadjiAgenta;
import sistemske_operacije.SOVratiAgente;
import sistemske_operacije.SOVratiMesta;
import sistemske_operacije.aranzmani.SOKreirajAranzman;
import sistemske_operacije.aranzmani.SOObrisiAranzman;
import sistemske_operacije.aranzmani.SOVratiAranzmane;
import sistemske_operacije.aranzmani.SOVratiStavkeAranzmana;
import sistemske_operacije.klijenti.SOIzmeniKlijenta;
import sistemske_operacije.klijenti.SOKreirajKlijenta;
import sistemske_operacije.klijenti.SOObrisiKlijenta;
import sistemske_operacije.klijenti.SOPretraziKlijente;
import sistemske_operacije.klijenti.SOVratiKlijente;
import sistemske_operacije.klijenti.SOVratiKlijentskeRezervacije;
import sistemske_operacije.partneri.SOIzmeniPoslovnogPartnera;
import sistemske_operacije.partneri.SOKreirajPoslovnogPartnera;
import sistemske_operacije.partneri.SOObrisiPoslovnogPartnera;
import sistemske_operacije.partneri.SOPretraziPartnere;
import sistemske_operacije.partneri.SOVratiPartnere;
import sistemske_operacije.partneri.SOVratiPartnerskeAranzmane;
import sistemske_operacije.rezervacije.SOKreirajRezervaciju;
import sistemske_operacije.rezervacije.SOObrisiRezervaciju;
import sistemske_operacije.rezervacije.SOVratiRezervaciju;
import sistemske_operacije.stavkeAranzmana.SOKreirajStavkuAranzmana;
import sistemske_operacije.stavkeAranzmana.SOObrisiStavkuAranzmana;
import sistemske_operacije.stavkeAranzmana.SOVratiSveStavke;

/**
 *
 * @author Tami
 */
public class Kontroler {

    private static Server ss;
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
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ServerGUI gui = new ServerGUI();
                gui.setVisible(true);
                gui.setLocationRelativeTo(null);
            }
        });
    }
    

    public void startServer(int port) {
                
        ss = new Server(port);
        ss.start();
        
        
    }

    public void stopServer() throws IOException {
        ss.closeServerSocket();
    }

    public List<OpstiDomenskiObjekat> getAgente() throws Exception {
        SOVratiAgente so = new SOVratiAgente();
        so.executeOperation();
        return so.getAgenti();
    }

    public List<OpstiDomenskiObjekat> getPoslovnePartnere() throws Exception{
        SOVratiPartnere so = new SOVratiPartnere();
        so.executeOperation();
        return so.getPartneri();
    }

    public List<OpstiDomenskiObjekat> getKlijente()throws Exception {
        SOVratiKlijente so = new SOVratiKlijente();
        so.executeOperation();
        return so.getKlijenti();
    }

    public List<OpstiDomenskiObjekat> getAranzmane()throws Exception {
        SOVratiAranzmane so = new SOVratiAranzmane();
        so.executeOperation();
        return so.getAranzmani();
    }

    public List<OpstiDomenskiObjekat> getMesta() throws Exception{
        SOVratiMesta so = new SOVratiMesta();
        so.executeOperation();
        return so.getMesta();
    }

    public List<OpstiDomenskiObjekat> getKlijentskeRezervacije(Klijent klijent)throws Exception {
        SOVratiKlijentskeRezervacije so = new SOVratiKlijentskeRezervacije(klijent);
        so.executeOperation();
        return so.getKlijentskeRezervacije();
    }

    public List<OpstiDomenskiObjekat> getPartnerskeAranzmane(PoslovniPartner poslovniPartner) throws Exception{
        SOVratiPartnerskeAranzmane so = new SOVratiPartnerskeAranzmane(poslovniPartner);
        so.executeOperation();
        return so.getPartnerskiAranzmani();
    }

    public List<OpstiDomenskiObjekat> getStavkeAranzmana(Aranzman aranzman) throws Exception{
        SOVratiStavkeAranzmana so = new SOVratiStavkeAranzmana(aranzman);
        so.executeOperation();
        return so.getStavke();
    }

    public boolean kreirajPartnera(PoslovniPartner poslovniPartner) throws Exception{
        SOKreirajPoslovnogPartnera so = new SOKreirajPoslovnogPartnera(poslovniPartner);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean kreirajKlijenta(Klijent klijent) throws Exception{
        SOKreirajKlijenta so = new SOKreirajKlijenta(klijent);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean kreirajAranzman(Aranzman aranzman) throws Exception{
        SOKreirajAranzman so = new SOKreirajAranzman(aranzman);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean kreirajRezervaciju(Rezervacija rezervacija) throws Exception{
        SOKreirajRezervaciju so = new SOKreirajRezervaciju(rezervacija);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean kreirajStavkuAranzmana(StavkaAranzmana stavkaAranzmana)throws Exception {
        SOKreirajStavkuAranzmana so = new SOKreirajStavkuAranzmana(stavkaAranzmana);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean izmeniKlijenta(Klijent klijent)throws Exception {
        SOIzmeniKlijenta so = new SOIzmeniKlijenta(klijent);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean izmeniPartnera(PoslovniPartner poslovniPartner)throws Exception {
        SOIzmeniPoslovnogPartnera so = new SOIzmeniPoslovnogPartnera(poslovniPartner);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean obrisiKlijenta(Klijent klijent)throws Exception {
        SOObrisiKlijenta so = new SOObrisiKlijenta(klijent);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean obrisiPartnera(PoslovniPartner poslovniPartner)throws Exception {
        SOObrisiPoslovnogPartnera so = new SOObrisiPoslovnogPartnera(poslovniPartner);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean obrisiRezervaciju(Rezervacija rezervacija) throws Exception{
        SOObrisiRezervaciju so = new SOObrisiRezervaciju(rezervacija);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean obrisiAranzman(Aranzman aranzman) throws Exception{
        SOObrisiAranzman so = new SOObrisiAranzman(aranzman);
        so.executeOperation();
        return so.isUspesno();
    }

    public boolean obrisiStavkuAranzmana(StavkaAranzmana stavkaAranzmana)throws Exception {
        SOObrisiStavkuAranzmana so = new SOObrisiStavkuAranzmana(stavkaAranzmana);
        so.executeOperation();
        return so.isUspesno();
    }

    public List<OpstiDomenskiObjekat> pretraziKlijente(String string)throws Exception {
        SOPretraziKlijente so = new SOPretraziKlijente(string);
        so.executeOperation();
        return so.getKlijenti();
    }

    public List<OpstiDomenskiObjekat> pretraziPartnere (String string)throws Exception {
        SOPretraziPartnere so = new SOPretraziPartnere(string);
        so.executeOperation();
        return so.getPartneri();
    }

    public OpstiDomenskiObjekat nadjiAgenta(Agent agent) throws Exception {
        SONadjiAgenta so = new SONadjiAgenta(agent);
        so.executeOperation();
        return so.getAgent();
    }

    public List<OpstiDomenskiObjekat> getRezervacije() throws Exception {
        SOVratiRezervaciju so = new SOVratiRezervaciju();
        so.executeOperation();
        return so.getRezervacije();
    }

    public List<OpstiDomenskiObjekat> getSveStavke() throws Exception {
        SOVratiSveStavke so = new SOVratiSveStavke();
        so.executeOperation();
        return so.getStavke();
    }

    

    

    
   
}