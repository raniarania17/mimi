/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import javax.swing.JOptionPane;

/**
 *
 * @author benso
 */
public class MongoDB {
     public static DB db;
    public static void Connect_MongoDB(String DataBase){
        try{
       MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
       db=mongoClient.getDB(DataBase);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
