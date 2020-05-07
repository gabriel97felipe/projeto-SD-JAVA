/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import sistemas.CalculoRetornoDTO;
import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import sistemas.CalculoDTO;
import sistemas.ChatDTO;
import sistemas.EquipamentoDTO;
import sistemas.LoginDTO;
import sistemas.MensagemDTO;
import sistemas.UsuarioDTO;

/**
 *
 * @author gabri
 */
public class ServidorThread extends Thread {

    private static ServerSocket socket = null;
    private Socket cliente = null;
    public DataInputStream in;
    public DataOutputStream out;
    public Scanner scan = new Scanner(System.in);
    private static ServidorGui myGui;
    private static ArrayList<UsuarioDTO> usuarioLogin = new ArrayList();

    public ServidorThread(Socket cliente) {
        this.cliente = cliente;

        this.start();

    }

    public static void main(String[] args) throws IOException {
        int porta = Integer.parseInt(JOptionPane.showInputDialog("Insira a porta do servidor:"));
        System.out.println(porta);
        socket = new ServerSocket(porta);
        myGui = new ServidorGui(porta, InetAddress.getLocalHost().getHostAddress());
        System.out.println("Aguardando conexao");
        while (true) {
            new ServidorThread(socket.accept());
        }
    }

    @Override
    public void run() {
        try {
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
            String linha = null;
            UsuarioDTO usuarioAtivo = null;
            String msg = "";
            while (true) {
                try {
                    Gson gson = new Gson();
                    msg = in.readUTF();
                    System.out.println(msg);
                    UsuarioDTO usuarioDTO = gson.fromJson(msg, UsuarioDTO.class
                    );

                    CalculoDTO calculoDTO = gson.fromJson(msg, CalculoDTO.class
                    );
                    MensagemDTO mensagemDTO = new MensagemDTO();
                    if (usuarioDTO.getOperacao() == 1) {
                        for (int i = 0; i < usuarioSize(); i++) {
                            if (usuarioDTO.getUsuario().equalsIgnoreCase(this.getUsuarioLista(i)) && usuarioDTO.getSenha().equals(this.getSenhaLista(i))) {
                                usuarioLogin.add(usuarioDTO);
                                usuarioAtivo = usuarioDTO;
                                mensagemDTO.setData(new Date());
                                mensagemDTO.setMensagem("Login efetuado com sucesso!");
                                mensagemDTO.setUsuario(usuarioDTO.getUsuario());
                                mensagemDTO.setStatus(true);

                                //
                                break;
                            }
                        }
                        if (usuarioAtivo == null) {
                            mensagemDTO.setData(new Date());
                            mensagemDTO.setMensagem("Login/senha incorreto!");
                            mensagemDTO.setUsuario(usuarioDTO.getUsuario());
                            mensagemDTO.setStatus(false);
                        }
                    }

                    if (usuarioDTO.getOperacao() == 2) {
                        usuarioLogin.remove(usuarioAtivo);
                        usuarioAtivo = null;
                        mensagemDTO.setData(new Date());
                        mensagemDTO.setMensagem("Logout efetuado com sucesso!");
                        mensagemDTO.setUsuario(usuarioDTO.getUsuario());
                        mensagemDTO.setStatus(true);

                    }
                    myGui.atualizaUsersLogados(usuarioLogin);
                    if (calculoDTO.getOperacao() == 3) {
                        System.out.println(calculoDTO);
                        CalculoRetornoDTO crDTO = fazerCalculo(calculoDTO);
                        System.out.println("Mensagem enviada ao cliente.");
                        out.writeUTF(gson.toJson(crDTO));
                        System.out.println(gson.toJson(crDTO));
                    } else {
                        System.out.println(mensagemDTO.getMensagem());
                        out.writeUTF(gson.toJson(mensagemDTO));
                        System.out.println(gson.toJson(mensagemDTO));
                    }
                    if (usuarioDTO.getOperacao() == 2) {
                        cliente.close();
                        break;

                    }
                } catch (IOException ex) {
                    Logger.getLogger(ServidorGui.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int usuarioSize() {
        return myGui.getListaUsuarios().size();
    }

    public String getUsuarioLista(int i) {
        return myGui.getListaUsuarios().get(i).getUsuario();
    }

    public String getSenhaLista(int i) {
        return myGui.getListaUsuarios().get(i).getSenha();
    }

    public CalculoRetornoDTO fazerCalculo(CalculoDTO calculoDTO) {
        CalculoRetornoDTO crDTO = new CalculoRetornoDTO();
        crDTO.setStatus(true);
        List<String> mensagens = new ArrayList<String>();

        for (int i = 0; i < calculoDTO.getEquipamentos().size(); i++) {
            EquipamentoDTO equipamento = calculoDTO.getEquipamentos().get(i);
            int cdEquip = equipamento.getCodigo();
            double gasto = (equipamento.getPotencia() * equipamento.getTempo()) / calculoDTO.getQtdPessoas();
            boolean gastou = false;
            switch (cdEquip) {
                case 1: // chuveiro
                    if (gasto > 3500 * 40) {
                        mensagens.add("Gasto com o chuveiro está maior que a média.");
                    } else {
                        mensagens.add("Chuveiro está ok.");
                    }
                    break;
                case 2: // microondas
                    if (gasto > 1300 * 20) {
                        mensagens.add("Gasto com o microondas está maior que a média.");
                    } else {
                        mensagens.add("Microondas está ok.");
                    }
                    break;
                case 3: // ferro eletrico
                    if (gasto > 1000 * 60) {
                        mensagens.add("Gasto com o ferro está maior que a média.");
                    } else {
                        mensagens.add("Ferro está ok.");
                    }
                    break;
                case 4: // geladeira
                    if (gasto > 200 * 600) {
                        mensagens.add("Gasto com a geladeira está maior que a média.");
                    } else {
                        mensagens.add("Geladeira está ok.");
                    }
                    break;
                case 5: // ar condicionado
                    if (gasto > 3500 * 480) {
                        mensagens.add("Gasto com o ar condicionado está maior que a média.");
                    } else {
                        mensagens.add("Ar condicionado está ok.");
                    }
                    break;
            }
        }
        crDTO.setMsgs(mensagens);
        
        return crDTO;
    }

}
