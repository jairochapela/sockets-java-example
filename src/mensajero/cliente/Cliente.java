/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajero.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jairo
 */
public class Cliente {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            
            Socket s = new Socket("localhost", 7777);
            PrintWriter p = new PrintWriter(s.getOutputStream());
            System.out.println("Conectado al servidor. Escriba sus mensajes (FIN para terminar).");
            
            while (scan.hasNext()) {
                String msg = scan.nextLine();
                if ("FIN".equals(msg)) break;
                p.println(msg);
                p.flush();
            }
            
            p.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
