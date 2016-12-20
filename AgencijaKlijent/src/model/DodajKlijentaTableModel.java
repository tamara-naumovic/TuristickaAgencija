/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Aranzman;
import domen.Klijent;
import domen.Rezervacija;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class DodajKlijentaTableModel extends AbstractTableModel{

    Klijent k;
    List<Rezervacija> listaRezervacija;
    
    private String[] kolone = new String[]{"Aranzman","Datum rezervisanja","Rok placanja","Avans","Zaduzenje"};

    public DodajKlijentaTableModel() {
        listaRezervacija = new LinkedList<>();
    }

    public List<Rezervacija> getListaRezervacija() {
        return listaRezervacija;
    }
    public void dodajRezvUTabelu(Rezervacija rez){
        listaRezervacija.add(rez);
    }
    public void obrisiRezvIzTabele(int red){
        listaRezervacija.remove(red);
    }

    public void setK(Klijent k) {
        this.k = k;
    }

    public Klijent getK() {
        return k;
    }
    
    
    
    @Override
    public int getRowCount() {
        if (k!=null && k.getListarezervacija()!=null) {
            return k.getListarezervacija().size();
        }
        return -1;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Rezervacija rez = k.getListarezervacija().get(rowIndex);
        
        switch(columnIndex){
            
            case 0: return rez.getAranzaman().getDestinacija();
            case 1: return rez.getDatum();
            case 2: return rez.getRokPlacanja();
            case 3: return rez.getAvans();
            case 4: return rez.getZaduzenje();
            default: return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        int [] niz = new int []{1,2,4};
        for (int i = 0; i < niz.length; i++) {
            if (columnIndex == niz[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Rezervacija r = k.getListarezervacija().get(rowIndex);
        switch(columnIndex){
            case 0: 
                validirajAranzman(rowIndex, (Aranzman) aValue);
                Calendar cal = Calendar.getInstance();
                cal.setTime(r.getAranzaman().getDatumPolaska());
                cal.add(Calendar.DAY_OF_YEAR,-1);
                r.setRokPlacanja(new java.sql.Date((cal.getTime()).getTime()));
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                r.setDatum(new java.sql.Date((new Date()).getTime()));
                fireTableCellUpdated(rowIndex, 4);
                break;
            
            case 3: 
                validirajAvans(rowIndex, Double.parseDouble((String) aValue) );
                r.setZaduzenje(r.getAranzaman().getCena()-r.getAvans());
                fireTableCellUpdated(rowIndex, columnIndex + 1);

                break;
        }
    }
    
    private void validirajAranzman(int rowIndex, Aranzman aranzman) {
        Rezervacija r = k.getListarezervacija().get(rowIndex);
        if (aranzman == null) {
            prikaziGresku("Polje za aranzman nije popunjeno!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        r.setAranzaman(aranzman);
    }
    
    
    
    private void validirajAvans(int rowIndex, Double avans){
        Rezervacija r = k.getListarezervacija().get(rowIndex);
        if (avans==0) {
            prikaziGresku("Polje za avans nije popunjeno!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        r.setAvans(avans);
    }
    
    protected void prikaziGresku(String poruka, int tip){
        JOptionPane.showMessageDialog(null, poruka, "Greska", tip);
    }
    
    
    
    
    
    
    
}
