/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemas;

/**
 *
 * @author gabri
 */
public class LoginDTO {
    private String usuario;
    private String senha;
    private int operacao;
    
    public LoginDTO(){
        
    }
    public LoginDTO(String usuario, String senha){
        this.usuario = usuario;
        this.senha = senha;
    }
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
        
    }
  
    
    public String getUsuario(){
        return usuario;
    }
    
    public String getSenha(){
        return senha;
    }

    /**
     * @return the operacao
     */
    public int getOperacao() {
        return operacao;
    }

    /**
     * @param operacao the operacao to set
     */
    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }
    
  
    
    
    
    
}
