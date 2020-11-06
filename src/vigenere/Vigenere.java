/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Willy
 */
public final class Vigenere {

    public static class KeyIsLargerThanText extends Exception {

        public KeyIsLargerThanText() {
        }

        public KeyIsLargerThanText(String string) {
            super(string);
        }

        public KeyIsLargerThanText(String string, Throwable thrwbl) {
            super(string, thrwbl);
        }

        public KeyIsLargerThanText(Throwable thrwbl) {
            super(thrwbl);
        }

    }

    public static String codificar(String plano, String clave) throws KeyIsLargerThanText, ABC.NotInABC {
        if (clave.length() > plano.length()) {
            throw new KeyIsLargerThanText("La clave proporcionada es más grande que el texto plano");
        }
        plano = plano.toUpperCase();
        clave = clave.toUpperCase();
        String resultado = "";

        int location = 0;
        for (int i = 0; i < plano.length(); i++) {
            location = (location == clave.length()) ? 0 : location;
            int indexPlano;
            try {
                indexPlano = ABC.indexOf(plano.charAt(i));
            } catch (ABC.NotInABC ex) {
                indexPlano = -1;
            }
            if (indexPlano != -1) {
                final int indexClave = ABC.indexOf(clave.charAt(location));
                resultado = resultado + ABC.charAt(indexPlano + indexClave);
                location++;
            } else {
                resultado = resultado + plano.charAt(i);
            }
        }

        return resultado;
    }

    public static String decodificar(String coded, String clave) throws KeyIsLargerThanText, ABC.NotInABC {
        if (clave.length() > coded.length()) {
            throw new KeyIsLargerThanText("La clave proporcionada es más grande que el texto plano");
        }
        coded = coded.toUpperCase();
        clave = clave.toUpperCase();
        String resultado = "";

        int location = 0;
        for (int i = 0; i < coded.length(); i++) {
            location = (location == clave.length()) ? 0 : location;
            int indexPlano;
            try {
                indexPlano = ABC.indexOf(coded.charAt(i));
            } catch (ABC.NotInABC ex) {
                indexPlano = -1;
            }
            if (indexPlano != -1) {
                final int indexClave = ABC.indexOf(clave.charAt(location));
                resultado = resultado + ABC.charAt(indexPlano - indexClave);
                location++;
            } else {
                resultado = resultado + coded.charAt(i);
            }
        }

        return resultado;
    }

}
