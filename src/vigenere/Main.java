/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vigenere;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author Willy
 */
public class Main extends Ventana implements Runnable, WindowConstants, SwingConstants {

    private final JLabel choro = new JLabel("En este programa podrás codificar y decodificar mensajes con Vigenere", CENTER);
    private final JLabel plainL = new JLabel("Ingresa el texto a codificar:", LEFT);
    private final JLabel codeL = new JLabel("Ingresa el código para la codificación:", LEFT);
    private final JLabel resL = new JLabel("Este es el resultado", LEFT);

    private final JTextField plain = new JTextField();
    private final JTextField code = new JTextField();
    private final JTextField res = new JTextField();
    private final JButton encode = new JButton("Codificar");
    private final JButton decode = new JButton("Decodificar");
    private final JButton resToPlain = new JButton("Copiar el resultado al texto a codificar");
    private final KeyListener plainLis = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent ke) {
            resLis.value = "";
            res.setText("");
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            resLis.value = "";
            res.setText("");
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            resLis.value = "";
            res.setText("");
        }
    };
    private final ResLis resLis = new ResLis() {
        @Override
        public void keyTyped(KeyEvent ke) {
            res.setText(value);
        }

        @Override
        public void keyPressed(KeyEvent ke) {
            if (ke.getKeyCode() != 39 && ke.getKeyCode() != 37) {
                res.setText(value);
            }
        }

        @Override
        public void keyReleased(KeyEvent ke) {
            if (ke.getKeyCode() != 39 && ke.getKeyCode() != 37) {
                res.setText(value);
            }
        }
    };

    public Main(String title, int w, int h, boolean resizable) {
        super(title, w, h, resizable);
        super.getContentPane().setLayout(null);
    }

    @Override
    public void setComp() {

        choro.setBounds(10, 10, 480, 30);
        super.addComp(choro);

        plainL.setBounds(10, 40, 250, 20);
        plain.setBounds(260, 40, 230, 20);
        plain.addKeyListener(plainLis);
        super.addComp(plainL);
        super.addComp(plain);

        codeL.setBounds(10, 70, 250, 20);
        code.setBounds(260, 70, 230, 20);
        code.addKeyListener(plainLis);
        super.addComp(codeL);
        super.addComp(code);

        resL.setBounds(10, 100, 250, 20);
        res.setBounds(260, 100, 230, 20);
        res.addKeyListener(resLis);
        super.addComp(resL);
        super.addComp(res);

        encode.setBounds(10, 130, 100, 20);
        encode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!plain.getText().isEmpty() && !code.getText().isEmpty()) {
                        final String r = Vigenere.codificar(plain.getText(), code.getText());
                        resLis.value = r;
                        res.setText(r);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Faltan argumentos", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Vigenere.KeyIsLargerThanText | ABC.NotInABC e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Un error ha ocurrido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        super.addComp(encode);
        decode.setBounds(120, 130, 100, 20);
        decode.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if (!plain.getText().isEmpty() && !code.getText().isEmpty()) {
                        final String r = Vigenere.decodificar(plain.getText(), code.getText());
                        resLis.value = r;
                        res.setText(r);
                    } else {
                        JOptionPane.showMessageDialog(null, "Por favor ingrese todos los datos", "Faltan argumentos", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Vigenere.KeyIsLargerThanText | ABC.NotInABC e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Un error ha ocurrido", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        super.addComp(decode);

        resToPlain.setBounds(230, 130, 260, 20);
        resToPlain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (!resLis.value.isEmpty()) {
                    plain.setText(resLis.value);
                    resLis.value = "";
                    res.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Success", "Satisfactoriamente copiado nada", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        super.addComp(resToPlain);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Main main = new Main("Código Vigenere", 500, 160, false);
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                main.mostrar();
            }
        });
        t.start();
    }

    @Override
    public void run() {

    }

}
