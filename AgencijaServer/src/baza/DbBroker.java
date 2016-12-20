/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.OpstiDomenskiObjekat;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import config.Config;

/**
 *
 * @author Tami
 */
public class DbBroker {

    private Connection connection;
    private static DbBroker instance;

    public DbBroker() {
    }

    public static DbBroker getInstance() {
        if (instance == null) {
            instance = new DbBroker();
        }
        return instance;
    }

    public boolean connectToMySqlDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Drajver ucitan");
            String url = Config.getInstance().getDbUrl();
            String user = Config.getInstance().getUsername();
            String pass = Config.getInstance().getPassword();
            connection = DriverManager.getConnection(url, user, pass);
            connection.setAutoCommit(false);
            System.out.println("Konektovanje na bazu uspesno!");
            return true;
        } catch (ClassNotFoundException ex) {
            System.out.println("Drajver nije nadjen");
            return false;
        } catch (SQLException ex) {
            System.out.println("Neuspesno konektovanje na bazu!");
            return false;
        }
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println("Konekcija ne moze da se zatvori!");
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            System.out.println("Commit ne moze da se uradi!");
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback ne moze da se uradi!");
        }
    }

    public synchronized List<OpstiDomenskiObjekat> getAllOpstiDomenskiObjekats(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query = "SELECT * FROM " + o.getNazivTabele();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(query);
            List<OpstiDomenskiObjekat> list = o.konvertujRSUListu(rs);
            s.close();
            System.out.println("ResultSet uspesno postavljen!");
            return list;
        } catch (SQLException ex) {
            System.out.println("Greska u postavljanju ResultSet-a na klasu " + o.getNazivTabele());
            ex.printStackTrace();
            throw ex;
        }
    }

    public synchronized OpstiDomenskiObjekat getOpstiDomenskiObjekatByPrimaryKey(OpstiDomenskiObjekat o, int id) throws SQLException {
        String query;

        if (o.getSlozeniPrimarniKljuc() == null) {
            query = "SELECT * FROM " + o.getNazivTabele() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + id;
        } else {
            query = "SELECT * FROM " + o.getNazivTabele() + " WHERE " + o.getSlozeniPrimarniKljuc();
        }
        Statement s = (Statement) connection.createStatement();
        ResultSet rs = s.executeQuery(query);
        List<OpstiDomenskiObjekat> list = o.konvertujRSUListu(rs);
        s.close();
        return list.get(0);
    }
    

    public synchronized boolean deleteOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query;
            
            if (o.getSlozeniPrimarniKljuc() == null) {
                query = "DELETE FROM " + o.getNazivTabele() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            } else {
                query = "DELETE FROM " + o.getNazivTabele() + " WHERE " + o.getSlozeniPrimarniKljuc();
            }
            System.out.println(query);
            Statement s = (Statement) connection.createStatement();
            s.executeUpdate(query);
            commit();
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se obrise objekat: " + o.getNazivTabele());
            throw ex;
        }
    }

    public synchronized void deleteOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException {
        for (OpstiDomenskiObjekat o : list) {
            deleteOpstiDomenskiObjekat(o);
        }
    }

    public synchronized boolean saveOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query = "";
            query = "INSERT INTO " + o.getNazivTabele() + "(" + o.getNaziveParametara() + ")" + " VALUES (" + o.getParametre() + ")";
            System.out.println(query);
            Statement s = (Statement) connection.createStatement();
            int i = s.executeUpdate(query);
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se sacuva objekat: " + o.getNazivTabele());
            throw ex;
        }
    }

    public synchronized void saveOpstiDomenskiObjekats(List<OpstiDomenskiObjekat> list) throws SQLException {
        for (OpstiDomenskiObjekat o : list) {
            saveOpstiDomenskiObjekat(o);
        }
    }

    public synchronized boolean updateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) throws SQLException {
        try {
            String query = "";
            if (o.getSlozeniPrimarniKljuc() == null) {
                query = "UPDATE " + o.getNazivTabele() + " SET " + o.getUpdateUpit() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getVrednostPrimarnogKljuca();
            } else {
                query = "UPDATE " + o.getNazivTabele() + " SET " + o.getUpdateUpit() + " WHERE " + o.getSlozeniPrimarniKljuc();
            }
            System.out.println(query);
            Statement s = (Statement) connection.createStatement();
            int i = s.executeUpdate(query);
            s.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Ne moze da se azurira objekat: " + o.getNazivTabele());
            throw ex;
        }
    }
    
    

//    public synchronized boolean saveOrUpdateOpstiDomenskiObjekat(OpstiDomenskiObjekat o) {
//        try {
//            List<OpstiDomenskiObjekat> list = getAllOpstiDomenskiObjekats(o);
//
//            String query = "";
//            if (!list.contains(o)) {
//                query = "INSERT INTO " + o.getNazivTabele() + "(" + o.getNaziveParametara() + ")" + " VALUES (" + o.getParameters() + ")";
//            } else if (o.getNazivPrimarnogKljuca() != null) {
//                query = "UPDATE " + o.getNazivTabele() + " SET " + o.getUpdateQuery() + " WHERE " + o.getNazivPrimarnogKljuca() + "=" + o.getPrimaryKeyValue();
//            }
//            Statement s = (Statement) connection.createStatement();
//            int i = s.executeUpdate(query);
//            s.close();
//            return true;
//        } catch (SQLException ex) {
//            return false;
//        }
//    }

    
}
