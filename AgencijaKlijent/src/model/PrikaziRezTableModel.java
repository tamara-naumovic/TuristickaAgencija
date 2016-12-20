/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.Rezervacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class PrikaziRezTableModel extends AbstractTableModel{
    List<Rezervacija> lista;
    private String[] kolone = new String[]{"Aranzman","Datum rezervisanja","Rok placanja","Avans","Zaduzenje"};

    public PrikaziRezTableModel(List<Rezervacija> lista) {
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
        Rezervacija rez = lista.get(rowIndex);
        
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    
}
