/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

/**
 *
 * @author Willy
 */
public class ABC {

    public static class NotInABC extends Exception {

        public NotInABC() {
        }

        public NotInABC(String string) {
            super(string);
        }

        public NotInABC(String string, Throwable thrwbl) {
            super(string, thrwbl);
        }

        public NotInABC(Throwable thrwbl) {
            super(thrwbl);
        }

    }

    private static final String ABC = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
    private static final String SPECIAL_CARACTERS = "ÁÉÍÓÚÜ";

    public static int indexOf(final char c) throws NotInABC {
        if (SPECIAL_CARACTERS.indexOf(c) != -1) {
            switch (c) {
                case 'Á':
                    return ABC.indexOf('A');
                case 'É':
                    return ABC.indexOf('E');
                case 'Í':
                    return ABC.indexOf('I');
                case 'Ó':
                    return ABC.indexOf('O');
                case 'Ú':
                case 'Ü':
                    return ABC.indexOf('U');
            }
        }
        final int i = ABC.indexOf(c);
        if (i == -1) {
            throw new NotInABC("\"" + String.valueOf(c) + "\" no está en el abecedario.");
        }
        return ABC.indexOf(c);
    }

    public static char charAt(int i) {
        i = (i < 0) ? (ABC.length() + i) : i;
        return ABC.charAt(i % ABC.length());
    }

}
