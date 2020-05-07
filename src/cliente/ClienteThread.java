/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import static cliente.ClienteGui.cliente;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemas.UsuarioDTO;

/**
 *
 * @author gabri
 */
public class ClienteThread extends Thread {

    UsuarioDTO usuarioDTO = new UsuarioDTO();
    Gson gson = new Gson();
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private ClienteGui myGui;
    private static String ip;
    private static int portaNum;

    public ClienteThread(String ip, int portaNum) throws IOException {
        cliente = new Socket(ip, portaNum);
        this.start();

    }

    public static void main(String[] args) throws IOException {
        ip = JOptionPane.showInputDialog("Insira o ip do servidor:");
        portaNum = Integer.parseInt(JOptionPane.showInputDialog("Insira a porta do servidor:"));
        new ClienteThread(ip, portaNum);
    }

    public void run() {

        try {
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
            ClienteGui myGui = new ClienteGui(cliente, ip, portaNum);
        } catch (IOException ex) {
            Logger.getLogger(ClienteThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
