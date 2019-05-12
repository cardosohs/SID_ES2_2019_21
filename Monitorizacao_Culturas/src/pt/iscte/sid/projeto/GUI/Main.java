
package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;


/**
 * Classe Main simples para inciar a aplicação
 * @author Grupo 21
 */
public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new LoginWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
