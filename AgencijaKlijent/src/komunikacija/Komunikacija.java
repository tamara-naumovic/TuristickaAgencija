/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import transfer.KlijentTransfer;
import transfer.ServerTransfer;

/**
 *
 * @author Tami
 */
public class Komunikacija {

    private Socket socket;
    private static Komunikacija instance;

    public static Komunikacija getInstance() {
        if (instance == null) {
            instance = new Komunikacija();
        }
        return instance;
    }

    public void setSocket(Socket soket) throws IOException {
        this.socket = soket;
    }

    public void sendRequest(KlijentTransfer ct) throws SocketException, IOException {

        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(ct);
    }

    public ServerTransfer readResponse() throws SocketException, ClassNotFoundException, IOException {
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ServerTransfer sto = (ServerTransfer) in.readObject();

        return sto;
    }

}
