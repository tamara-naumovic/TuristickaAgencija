/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemske_operacije;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import baza.DbBroker;

/**
 *
 * @author Tami
 */
public abstract class OpstaSO {

    public final void executeOperation() throws Exception {
        try {
            DbBroker.getInstance().connectToMySqlDatabase();
            executeSpecificOperation();
            DbBroker.getInstance().commit();
            DbBroker.getInstance().closeConnection();
        } catch (Exception e) {
            DbBroker.getInstance().rollback();
            DbBroker.getInstance().closeConnection();
        }
    }

    protected abstract void executeSpecificOperation() throws Exception;
}
