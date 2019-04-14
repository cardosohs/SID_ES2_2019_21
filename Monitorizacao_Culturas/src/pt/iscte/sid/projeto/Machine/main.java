/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.iscte.sid.projeto.Machine;

/**
 *
 * @author Sérgio
 */
public class main {
      public static void main(String[] args){
           DatabaseMiddleMan d = new DatabaseMiddleMan("alexandre","alex");
            
            if(d.getFailed())
                System.out.println("nao deu para fazer login");
            else
                System.out.println(d.ReadFromDatabase("cultura"));
           /* System.out.println(d.ReadFromDatabase("Investigador"));
           if( !d.CloseConnection())
               System.out.println("Falha no fecho da ligacao");*/
        }
}
