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
        DatabaseMiddleManForAdministrador d = new DatabaseMiddleManForAdministrador("root","");
        
        if(d.Failed())
            System.out.println("nao deu para fazer login");
        else{
            d.DeleteInvestigador(2);
           System.out.println(d.getInvestigador());
        }

    }
}
