/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajero.servidor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairo
 */
public class Servidor {

    private static void atenderRecepcion(Socket socket) {
        try {
            Scanner scan = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            while (scan.hasNext()) {
                String msg = scan.nextLine();
                System.out.println("Recibido: " + msg);
            }
            
            out.close();
            socket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {

        try {
            ServerSocket ss = new ServerSocket(7777);
            
            while (true) {
                System.out.println("Aceptando conexiones entrantes...");

                try {
                    Socket socket = ss.accept();
                    System.out.println("Conexión aceptada: " + socket.getInetAddress());
                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            atenderRecepcion(socket); 
                            System.out.println("Fin de la transmisión.");
                        }
                        
                        
                    });
                    t.start();
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
}
