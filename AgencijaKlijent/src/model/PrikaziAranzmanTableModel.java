/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Aranzman;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class PrikaziAranzmanTableModel extends AbstractTableModel{
    
    List<Aranzman> lista;
    String[] kolone = new String[]{"Naziv","Destinacija","Cena","Datum polaska","Datum povratka"};

    public PrikaziAranzmanTableModel(List<Aranzman> lista) {
        this.lista = lista;
    }

    public List<Aranzman> getLista() {
        return lista;
    }

    public void setLista(List<Aranzman> lista) {
        this.lista = lista;
    }

    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aranzman ar = lista.get(rowIndex);
        switch(columnIndex){
            case 0: return ar.getNaziv();
            case 1: return ar.getDestinacija();
            case 2: return ar.getCena();
            case 3: return ar.getDatumPolaska();
            case 4: return ar.getDatumPovratka();
            default: return "n/a";
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
}
