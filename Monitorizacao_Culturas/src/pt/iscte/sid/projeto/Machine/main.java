/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pt.iscte.sid.projeto.Machine;

/**
 * Esta classe serve para testar o backend de ES por favor nao apagar
 * @author Sérgio
 */
public class main {
    public static void main(String[] args){
        DatabaseMiddleManForInvestigador d = new DatabaseMiddleManForInvestigador("svbro@iscte-iul.com", "123");
        System.out.println(d.getCulturas());
    }
}
