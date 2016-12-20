/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Aranzman;
import domen.StavkaAranzmana;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class StavkeAranzmanaTableModel extends AbstractTableModel {

    Aranzman araz;
    List<StavkaAranzmana> lista;
    boolean menjaj;
    String[] kolone = new String[]{"Naziv stavke","Opis"};

    public boolean isMenjaj() {
        return menjaj;
    }

    public void setMenjaj(boolean menjaj) {
        this.menjaj = menjaj;
    }

    public Aranzman getAraz() {
        return araz;
    }

    public void setAraz(Aranzman araz) {
        this.araz = araz;
    }

    
    public StavkeAranzmanaTableModel() {
        lista= new LinkedList<>();
    }
    
    @Override
    public int getRowCount() {
        if (araz != null && araz.getListaStavki()!= null) {
            return araz.getListaStavki().size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }
    public void dodajStavkuUtabelu(StavkaAranzmana sa){
        lista.add(sa);
    }
    public void obrisiStavku(StavkaAranzmana sa){
        lista.remove(sa);
        
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaAranzmana sa = araz.getListaStavki().get(rowIndex);
        switch(columnIndex){
            case 0: return sa.getNaziv();
            case 1: return sa.getOpis();
            default : return "n/a";
        }
    }
    
    

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        StavkaAranzmana sa = araz.getListaStavki().get(rowIndex);
        switch(columnIndex){
            case 0: if (validirajCeliju(rowIndex, (String)aValue)) {
                    sa.setNaziv((String)aValue);
                    fireTableDataChanged();
                }
                break;
            case 1: if (validirajCeliju(rowIndex, (String)aValue)) {
                    sa.setOpis((String) aValue);
                    fireTableDataChanged();
                }
                break;
        }

    }
    
    public boolean validirajCeliju(int rowIndex, String unos){
        if (unos.isEmpty()) {
            prikaziGresku("Polje je prazno!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void prikaziGresku(String poruka, int tip) {
        JOptionPane.showMessageDialog(null, poruka, "Greska", tip);

    }
    
}
