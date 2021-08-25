/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motxilla;

/**
 *
 * @author pedromarti
 */
public class Objecte {
    
    private int pes;
    private int valor;
 
    public Objecte(int pes, int valor) {
        this.pes = pes;
        this.valor = valor;
    }
 
    public int getPes() {
        return pes;
    }
 
    public void setPes(int pes) {
        this.pes = pes;
    }
 
    public int getBenefici() {
        return valor;
    }
 
    public void setBenefici(int benefici) {
        this.valor = benefici;
    }
 
    //Comparam dos objectes per saber si son iguals
    public boolean comparaObjecte(Objecte objecteComparar) {

        if (this.pes != objecteComparar.pes || this.valor != objecteComparar.valor) {
            return false;
        }
        
        return true;
    }
 
    @Override
    public String toString(){
        return "Pes: "+pes+","+" benefici: "+valor;
    }
}
