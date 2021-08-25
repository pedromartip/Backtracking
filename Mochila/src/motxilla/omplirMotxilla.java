/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package motxilla;

import java.util.ArrayList;

/**
 *
 * @author pedromarti
 */
public class omplirMotxilla {


    public static void omplirMotxilla(Motxilla motxilla, Motxilla motxillaOptima, ArrayList<Objecte> objectes, boolean isPlena) {

        if (!isPlena) {
            /* Si no esta plena, recorrem tots els elements de l'arrayList d'objectes.
            Per a cada un, miram si existeix. 
            
            - CAS NO EXISTEIX: Si no existeix, miram si el pes d'aquest objecte
            que miram, i el total actual de la motxilla, superen el pes màxim 
            establert. Si no el superen, es fica l'objecte actual i feim 
            l'agorisme un altra vegada. Després borram l'objecte que miram de 
            la motxilla normal (la que no es l'òptima).
            
            - CAS EXISTEIX: Si l'objecte que miram (de l'arrayList d'objectes
            pasat per paràmetre), existeix dins la motxilla, tornam a aplicar-li
            l'algorisma*/

            for (int i = 0; i < objectes.size(); i++) {
                if (!motxilla.existeixObjecte(objectes.get(i))) {
                    if (motxilla.getPesMax() >= motxilla.getPes() + objectes.get(i).getPes()) {
                        motxilla.afegirObjecte(objectes.get(i));
                        omplirMotxilla(motxilla, motxillaOptima, objectes, false);
                        motxilla.borrarObjecte(objectes.get(i));
                    } else {
                        omplirMotxilla(motxilla, motxillaOptima, objectes, true);
                    }
                }
            }

        } else if (isPlena) {

            /* Miram si la motxilla optima té menor benefici per que la motxilla base.
            Si es així, ficam els objectes de la motxilla base, a la motxilla
            optimitzada, perquè em trobat un nou benefici major. */
            if (motxilla.getBenefici() > motxillaOptima.getBenefici()) {
                Objecte[] objectesMotxillaBase = motxilla.getObjectes();
                motxillaOptima.buidarMotxilla();
                
                //Ficam els objectes a la motxilla optima
                for (int i = 0; i < objectesMotxillaBase.length; i++) {
                    Objecte e = objectesMotxillaBase[i];
                    if (e != null) {
                        motxillaOptima.afegirObjecte(e);
                    }
                }
            }
        }
    }
}
