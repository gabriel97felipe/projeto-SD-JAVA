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
public class EquipamentoDTO {
    private int codigo;

    private int potencia;
    private int tempo;

    /**
     * @return the código
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param código the código to set
     */
    public void setCodigo(int código) {
        this.codigo = código;
    }

    /**
     * @return the potencia
     */
    public int getPotencia() {
        return potencia;
    }

    /**
     * @param potencia the potencia to set
     */
    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    /**
     * @return the tempo
     */
    public int getTempo() {
        return tempo;
    }

    /**
     * @param tempo the tempo to set
     */
    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
    
    
    
}
