/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Kontroler;
import domen.PoslovniPartner;
import forme.GlavnaForma;
import forme.aranzman.AranzmanFrm;
import forme.klijent.KlijentForma;
import forme.partner.PartnerFrm;
/**
 *
 * @author Tami
 */
public class GUIKontroler {
    
    public static void invokeKlijentForm(){
        KlijentForma test = new KlijentForma();
        test.setVisible(true);
    }
    
    public static void invokePartnerForm(){
        PartnerFrm test = new PartnerFrm();
        test.setVisible(true);
    }
    
    public static void invokeAranzmanForm(PoslovniPartner pp){
        AranzmanFrm test = new AranzmanFrm(pp);
        test.setVisible(true);
    }
     public static void invokeMainForm(){
         GlavnaForma test = new GlavnaForma();
         test.setVisible(true);
     }
    
    
    
}
