/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmaster;

import tableFrame.tableOperationMongoDB;
import tableFrame.usersFrame;

/**
 *
 * @author benso
 */
public class ApplicationMaster {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       new Tools().createFrame(new usersFrame(), "Login");
                }
    
}
