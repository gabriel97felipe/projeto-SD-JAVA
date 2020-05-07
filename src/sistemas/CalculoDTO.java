/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemas;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class CalculoDTO {
    
    private int operacao;
    private int qtdPessoas;
    private List<EquipamentoDTO> equipamentos;

    /**
     * @return the qtdPessoas
     */
    public int getQtdPessoas() {
        return qtdPessoas;
    }
    

    /**
     * @param qtdPessoas the qtdPessoas to set
     */
    public void setQtdPessoas(int qtdPessoas) {
        this.qtdPessoas = qtdPessoas;
    }

    /**
     * @return the equipamentos
     */
    public List<EquipamentoDTO> getEquipamentos() {
        return equipamentos;
    }

    /**
     * @param equipamentos the equipamentos to set
     */
    public void setEquipamentos(List<EquipamentoDTO> equipamentos) {
        this.equipamentos = equipamentos;
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
