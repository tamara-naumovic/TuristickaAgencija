/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.NitKlijent;

/**
 *
 * @author Tami
 */
public class Server extends Thread {

    private ServerSocket serverSocket;
    public static List<NitKlijent> klijenti = new LinkedList<>();

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("ServerSocket je kreiran.");
        } catch (IOException ex) {
            System.out.println("ServerSocket nije kreiran.");
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket s = serverSocket.accept();
                NitKlijent clientThread = new NitKlijent(s);
                clientThread.start();
                klijenti.add(clientThread);
                int i = klijenti.size();
                System.out.println("Klijent broj " + i + " je konektovan!");
            } catch (SocketException ex) {
                System.out.println("Server je zatvoren!");
                break;
            } catch (IOException ex) {
                System.out.println("Klijent nije konektovan!");
            }
        }
    }

    public void closeServerSocket() {
        for (NitKlijent ct : klijenti) {
            try {
                ct.getSocket().close();
                System.err.println("Klijent je diskonektovan");
            } catch (IOException ex) {
                System.out.println("Klijent ne moze da se diskonektuje. " + ct.getSocket().getInetAddress());
                ex.printStackTrace();
            }
        }
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Serverski soket ne moze da se zatvori.");
            ex.printStackTrace();
        }
    }
    public static void closeClient(NitKlijent nk){
        try {
            nk.getSocket().close();
            System.out.println("Klijent je crko");
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
