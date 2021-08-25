# Backtracking

· Back 0/1: Try to find optimal solutions to the problem of back 0/1.

· Knight Tour: is a sequence of moves of a knight on a chessboard such that 
the knight visits every square exactly once. If the knight ends on a square 
that is one knight's move from the beginning square (so that it could tour the 
board again immediately, following the same path), the tour is closed; otherwise, it is open.

*ALGORISM BACKTRACKING BACK 0/1*

BEGIN

	omplirMotxilla(motxilla, motxillaOptima: Motxilla ; objectes: ArrayList<Objecte>; isPlena: boolean) {
	
	BEGIN

        if isPlena == false then BEGIN

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

            for int i := 0 to objectes.size do BEGIN 

                if motxilla.existeixObjecte(objectes.get(i)) == false then BEGIN 

                    if motxilla.getPesMax() >= (motxilla.getPes() + objectes.get(i).getPes()) then BEGIN

                    	motxilla.afegirObjecte(objectes.get(i));
                        omplirMotxilla(motxilla, motxillaOptima, objectes, false);
                        motxilla.borrarObjecte(objectes.get(i));
                    END
                    else BEGIN

                        omplirMotxilla(motxilla, motxillaOptima, objectes, true);
                    END
                END
            END
        END
        else if isPlena == true BEGIN 

            /* Miram si la motxilla optima té menor benefici per que la motxilla base.
            Si es així, ficam els objectes de la motxilla base, a la motxilla
            optimitzada, perquè em trobat un nou benefici major. */

            if motxilla.getBenefici() > motxillaOptima.getBenefici() then BEGIN

                Objecte[] objectesMotxillaBase := motxilla.getObjectes();
                motxillaOptima.buidarMotxilla();
                
                //Ficam els objectes a la motxilla optima
                for int i := 0 to objectesMotxillaBase.length do BEGIN

               		e : Objecte
                    objectesMotxillaBase[i] := e;

                    if e != null then BEGIN

                        motxillaOptima.afegirObjecte(e);
                    END
                END
            END
        END
    END
END

*ALGORISM BACKTRACKING KNIGHT TOUR*

BEGIN

	colocarReina(posiciony, reinas, valorborrado, columnaoriginal : Integer) 
    BEGIN

        ValorAntesDeReina : Integer;
        ValorAntesDeReina := 0;

        colocado : Boolean;
        colocado := false;

        if isAcabado() == false && !isAcabado2() == false then BEGIN 

            for int i := valorborrado to < Matriz.length && colocado == false do BEGIN

                if lugarValido(i, posiciony) == true then BEGIN 

                    colocado := true;
                    ValorAntesDeReina := Matriz[i][posiciony].getValor();
                    Matriz[i][posiciony].setValor() := 9;
                    reinas := reinas - 1;

                    if posiciony < (Matriz.length - 1) then BEGIN

                        posiciony = posiciony + 1;
                        if reinas != 0 then BEGIN

                            colocarReina(posiciony, reinas, 0, columnaoriginal);
                        END
                    END
                    else BEGIN

                    	//al encontrar la primera solucion la pintamos y acabaremos
                        if reinas == 0 then BEGIN 

                            for int j = 0 to Matriz.length do BEGIN

                                for int k = 0 to  Matriz.length do BEGIN 

                                    pintarCasilla(j, k);
                                    repaint();
                                END
                            END
                        END
                    END
                END
            END
            if colocado == false then BEGIN

                if (posiciony - 1) == columnaoriginal then BEGIN

                    posiciony := posiciony--;
                    reinas := reinas++;
                END

                enc : boolean;
                enc := false;
                m : Integer

                for m := 0 to  Matriz.length && enc = false do BEGIN 

                    if posiciony >= 1 then BEGIN

                        if Matriz[m][posiciony - 1].getValor() == 9 then BEGIN 

                            enc := true;
                            Matriz[m][posiciony - 1].setValor(0);
                        END
                    END
                    if m == (Matriz.length - 1) && posiciony == 0) then BEGIN

                        setAcabado2(true);
                    END
                END
                for int j := 0 to Matriz.length do BEGIN

                    for int k := 0 to Matriz.length do BEGIN 

                        pintarCasilla(j, k);
                    END
                END

                valorborrado := m;
                colocarReina(posiciony - 1, reinas + 1, valorborrado, columnaoriginal);
            END
        END
    END
END


