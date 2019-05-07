/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.iscte.sid.projeto.GUI;

/**
 *
 * @author Sérgio
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PopUpWindow {

public static void main(String[] args)
{

String resp = "Hello";

String input = JOptionPane.showInputDialog(null, resp);                         

if (resp.compareTo(input) == 0)
    JOptionPane.showMessageDialog(null, "Correct!");
else
    JOptionPane.showMessageDialog(null, "Incorrect");
}

}
