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
public class Motxilla {

    private int pesMax; //Pes màxim que li volem posar a la motxilla
    private Objecte[] Objecte;

    private int pes;
    private int benefici;

    public Motxilla(int numObjectes, int pesMax) {

        this.pesMax = pesMax;
        this.Objecte = new Objecte[numObjectes];
        this.benefici = 0;
        this.pes = 0;
    }

    //Afegim un element a la motxilla
    public void afegirObjecte(Objecte objecte) {

        for (int i = 0; i < this.Objecte.length; i++) {
            if (this.Objecte[i] == null) {
                this.Objecte[i] = objecte;
                this.benefici += objecte.getBenefici(); 
                this.pes += objecte.getPes(); 
                break;
            }
        }

    }

    //Buidam la motxilla
    public void buidarMotxilla() {
        this.pes = 0;
        this.benefici = 0;
        for (int i = 0; i < this.Objecte.length; i++) {
            this.Objecte[i] = null;
        }
    }

    public Objecte[] getObjectes() {
        return Objecte;
    }

    public int getPes() {
        return pes;
    }

    public void setPes(int pes) {
        this.pes = pes;
    }

    public int getBenefici() {
        return benefici;
    }

    public void setBenefici(int benefici) {
        this.benefici = benefici;
    }

    public int getPesMax() {
        return pesMax;
    }

    public void setPesMax(int pesoMaximo) {
        this.pesMax = pesMax;
    }

    public boolean compararMotxilla(Motxilla m){

        if (this.pes != m.getPes() || this.benefici != m.getBenefici()) {
            return false;
        }
        
        return true;
    }
    
    //Eliminam l'objecte pasat per paràmetre (si existeix) de la motxilla
    public void borrarObjecte(Objecte objecte) {
        for (int i = 0; i < this.Objecte.length; i++) {
            if (this.Objecte[i].comparaObjecte(objecte)) {
                this.Objecte[i] = null; //el elemento fuera
                this.pes -= objecte.getPes(); //Reduce el peso
                this.benefici -= objecte.getBenefici(); // reduce el beneficio
                break;
            }
        }
    }

    public int printObjectesMotxilla(){
        int n = 0;
        for (int i = 0; i < this.Objecte.length; i++) {
            if(Objecte[i] != null){
                n++;
            System.out.println("Objecte "+i+" de la motxilla: "+Objecte[i].toString());
            }
        }
        return n;
    }
    public boolean existeixObjecte(Objecte e) {
        for (int i = 0; i < this.Objecte.length; i++) {
            if (this.Objecte[i] != null && this.Objecte[i].comparaObjecte(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Motxilla optima --> Pes: " + getPes() + ", benefici: " + getBenefici();
    }
}
