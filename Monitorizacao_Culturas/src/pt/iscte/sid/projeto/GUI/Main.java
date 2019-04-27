/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.iscte.sid.projeto.GUI;

import java.awt.EventQueue;


/**
 *
 * @author Sérgio
 */
public class Main {
    public static void main(String[] args) {
         EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginWindow frame = new LoginWindow();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
