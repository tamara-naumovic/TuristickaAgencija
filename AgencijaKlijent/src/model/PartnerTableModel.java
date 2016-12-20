/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import domen.PoslovniPartner;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Tami
 */
public class PartnerTableModel extends AbstractTableModel{

    List<PoslovniPartner> listapartnera;
    String[]kolone = new String[]{"Naziv","Tip Poslovnog Partnera", "Ziro racun", "Mesto"};

    public PartnerTableModel(List<PoslovniPartner> listapartnera) {
        this.listapartnera = listapartnera;
    }

    public List<PoslovniPartner> getListapartnera() {
        return listapartnera;
    }

    public void setListapartnera(List<PoslovniPartner> listapartnera) {
        this.listapartnera = listapartnera;
    }
    
    
    @Override
    public int getRowCount() {
        return listapartnera.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PoslovniPartner pp = listapartnera.get(rowIndex);
        switch(columnIndex){
            case 0: return pp.getNaziv();
            case 1: return vratiNazivTipa(pp);
            case 2: return pp.getZiroRacun();
            case 3: return pp.getMesto().getNaziv();
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
    
    public String vratiNazivTipa(PoslovniPartner pp){
        if (pp.getTipPoslovnogPartnera()==1) {
            return "Smestaj";
        }else{
            return "Prevoz";
        }
    }
    
    
}
