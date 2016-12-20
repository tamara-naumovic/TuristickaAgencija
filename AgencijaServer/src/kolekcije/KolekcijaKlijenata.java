/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kolekcije;

import domen.Klijent;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tami
 */
public class KolekcijaKlijenata {
    private static KolekcijaKlijenata instance;
    List<Klijent> listaKlijenata;

    private KolekcijaKlijenata() {
        listaKlijenata = new ArrayList<>();
    }

    public static KolekcijaKlijenata getInstance() {
        if (instance == null) {
            instance = new KolekcijaKlijenata();
        }
        return instance;
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }

    public void dodaj(Klijent a) {
        listaKlijenata.add(a);
    }

    public void obrisi(Klijent a) {
        for (int i = 0; i < listaKlijenata.size(); i++) {
            if (listaKlijenata.get(i).equals(a)) {
                listaKlijenata.remove(i);
            }
        }
    }

    @Override
    public String toString() {
        String string="";
        for (Klijent kli : listaKlijenata) {
            string+=kli.toString();
        }
        return string;
    }
}
