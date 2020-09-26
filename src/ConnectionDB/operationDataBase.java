/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnectionDB;

import applicationmaster.Tools;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.bson.types.ObjectId;
public class operationDataBase {
     public static void FillTo_TableView(JTable table) {
        ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        int intervalClassCountNegative = 0;
        int intervalClassCountMin = 0;
        int intervalClassCountShort1 = 0;
        int intervalClassCountShort2 = 0;
        int intervalClassCountMean1 = 0;
        int intervalClassCountMean2 = 0;
        int intervalClassCountLong = 0;

        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            DBCollection col = (DBCollection) ConnectionDB.MongoDB.db.getCollection("taxi1");
            DBCursor cursor = col.find();
            int i = 0;

            while (cursor.hasNext() && i != 1000000) {
                DBObject dbo = cursor.next();
                ObjectId _ID = (ObjectId) dbo.get("_id");
                int vndID;

                try {
                    vndID = (int) dbo.get("VendorID");
                } catch (Exception e) {
                    double a = (double) dbo.get("VendorID");
                    vndID = (int) a;
                }
                String tpepdate = (String) dbo.get("tpep_pickup_date");
                String tpepofdate = (String) dbo.get("tpep_dropof_date");

                String tf = String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0, 2)) * 3600
                    + (Integer.parseInt(tpepofdate.substring(11, 19).substring(3, 5)) * 60 + (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8))))) - (Integer.parseInt(tpepdate.substring(11, 19).substring(0, 2)) * 3600
                    + (Integer.parseInt(tpepdate.substring(11, 19).substring(3, 5)) * 60 + (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
                int tf2;

                tf2 = Integer.parseInt(tf);

                /* Interval class */
                String intervalClass = LogicTools.calculateIntervalTrip(tf2).toString();

                /* Interval class Count */
                if (intervalClass.equals(LogicTools.TripTimeClass.NEGATIVE.toString()))
                    intervalClassCountNegative += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.SHORT1_TRIP.toString()))
                    intervalClassCountShort1 += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.SHORT2_TRIP.toString()))
                    intervalClassCountShort2 += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.MIN_TRIP.toString()))
                    intervalClassCountMin += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.MEAN1_TRIP.toString()))
                    intervalClassCountMean1 += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.MEAN2_TRIP.toString()))
                    intervalClassCountMean2 += 1;
                if (intervalClass.equals(LogicTools.TripTimeClass.LONG_TRIP.toString()))
                    intervalClassCountLong += 1;

                int pc;
                try {
                    pc = (int) dbo.get("passenger_count");
                } catch (Exception e) {
                    double a = (double) dbo.get("passenger_count");
                    pc = (int) a;
                }
                double td = 1.0;
                try {
                    td = (double) dbo.get("trip_distance");
                } catch (Exception e) {
                    int a = (int) dbo.get("trip_distance");
                    td = td * a;
                }
                int rc;
                try {
                    rc = (int) dbo.get("RatecodeId");
                } catch (Exception e) {
                    double a = (double) dbo.get("RatecodeId");
                    rc = (int) a;
                }
                String fwd = (String) dbo.get("stor_and_fwd");
                int plID;
                try {
                    plID = (int) dbo.get("pulocationId");
                } catch (Exception e) {
                    double a = (double) dbo.get("plID");
                    plID = (int) a;
                }
                int dlID;
                try {
                    dlID = (int) dbo.get("dolocationid");
                } catch (Exception e) {
                    double a = (double) dbo.get("dolocationid");
                    dlID = (int) a;
                }
                int pt;
                try {
                    pt = (int) dbo.get("paymment_type");
                } catch (Exception e) {
                    double a = (double) dbo.get("paymment_type");
                    pt = (int) a;
                }
                int fa;
                try {
                    fa = (int) dbo.get("fare_amount");
                } catch (Exception e) {
                    double a = (double) dbo.get("fare_amount");
                    fa = (int) a;
                }
                double ext = 1.0;
                try {
                    ext = (double) dbo.get("extra");
                } catch (Exception e) {
                    int a = (int) dbo.get("extra");
                    ext = ext * a;
                }
                double mtx = 1.0;
                try {
                    mtx = (double) dbo.get("mtaa_tax");
                } catch (Exception e) {
                    int a = (int) dbo.get("mtaa_tax");
                    mtx = mtx * a;
                }
                double tam = 1.0;
                try {
                    tam = (double) dbo.get("tip_amount");
                } catch (Exception e) {
                    int a = (int) dbo.get("tip_amount");
                    tam *= a;
                }
                int tla;
                try {
                    tla = (int) dbo.get("tools_amount");
                } catch (Exception e) {
                    double a = (double) dbo.get("tools_amount");
                    tla = (int) a;
                }
                double impsur = 1.0;
                try {
                    impsur = (double) dbo.get("improvement_surcharge");
                } catch (Exception e) {
                    int a = (int) dbo.get("improvement_surcharge");
                    impsur *= a;
                }
                double ttam = 1.0;
                try {
                    ttam = (double) dbo.get("total_amount");
                } catch (Exception e) {
                    int a = (int) dbo.get("total_amount");
                    ttam *= a;
                }
                String cngsur = "";

                //rows.add(new Object[]{_ID, vndID, tpepdate, tpepofdate, tf2, intervalClass, pc, td, rc, fwd, plID, dlID, pt, fa, ext, mtx, tam, tla, impsur, ttam, cngsur});
                model.addRow(new Object[]{_ID, vndID, tpepdate, tpepofdate, tf2, intervalClass, 0, pc, td, rc, fwd, plID, dlID, pt, fa, ext, mtx, tam, tla, impsur, ttam, cngsur});

                i += 1;
            }

            int finalIntervalClassCountNegative = intervalClassCountNegative;
            int finalIntervalClassCountShort = intervalClassCountShort1;
            int finalIntervalClassCountShort1 = intervalClassCountShort2;
            int finalIntervalClassCountMin = intervalClassCountMin;
            int finalIntervalClassCountMean = intervalClassCountMean1;
            int finalIntervalClassCountMean1 = intervalClassCountMean2;
            int finalIntervalClassCountLong = intervalClassCountLong;
            model.getDataVector().stream().forEach(row -> {
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.NEGATIVE.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountNegative);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.SHORT1_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountShort);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.SHORT2_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountShort1);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.MIN_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountMin);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.MEAN1_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountMean);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.MEAN2_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountMean1);
                if (((Vector) row).get(5).equals(LogicTools.TripTimeClass.LONG_TRIP.toString()))
                    ((Vector) row).set(6, finalIntervalClassCountLong);

            });
            table.setModel(model);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

 /*public static void FillTo_TableView(JTable table){
        ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        try{
            DefaultTableModel model=(DefaultTableModel) table.getModel();
            DBCollection col=(DBCollection) ConnectionDB.MongoDB.db.getCollection("taxi1");
            DBCursor cursor=col.find();
      int   i=0;     
            while(cursor.hasNext() && i!=1000000 ){
            DBObject dbo=cursor.next();
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID;
            try{
             vndID=(int) dbo.get("VendorID");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("VendorID");
            vndID=(int) a;
            }
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
                String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;
            tf2=Integer.parseInt(tf);     
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            double tam=1.0;
            try{
            tam=(double)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("tip_amount");
            tam*=a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
            String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
        // model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
            }    
       table.setModel(model);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }}*/
 // fonction pour l user 
    public static boolean checkUser(String Password)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection collection =ConnectionDB.MongoDB.db.getCollection("users");
    DBCursor cursor=collection.find();
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    if(dbo.get("Password").equals(Password))
    {
    return true;
    }
    else{
    return false;
    }
    }
    return false;  
    }
    public static void insertUsers(String userName,String Password)
    {
   ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection collection=ConnectionDB.MongoDB.db.getCollection("users");
    BasicDBObject doc=new BasicDBObject();
    doc.append("userName", "admin");
    doc.append("Password", "admin");
    collection.insert(doc);
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //  username 
    public static void fillComboBox(JComboBox combo)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
    DBCollection col=  ConnectionDB.MongoDB.db.getCollection("users");
        DBCursor cursor=col.find(new BasicDBList(),new BasicDBObject("userName",Boolean.TRUE));
        while(cursor.hasNext()){
        combo.addItem((String)cursor.next().get("userName"));
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data  pour dlocation plocation 
     public static void searchData(JTable table,String Column,int val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   int val2=(int)dbo.get(Column);
if(val2<val)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
               String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("there is no erroneous recording ");
    }
    else{
    table.setModel(model);
    }
    }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 2 
     // c pour  ll vindid+ psscount +paymment type
    public static void searchData2(JTable table,String Column,int val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
   
   int val2=(int)dbo.get(Column);
if(val2>val)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
       String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 3 pour 
    // improvment surcharge +Mta_Tax   // double 
     public static void searchData3(JTable table,String Column,double val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2!=val) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
       String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
   if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 4 total amount + trip distance 
    public static void searchData4(JTable table,String Column,double val)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2<val) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
              String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf); 
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }    
    
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // search data 6 pour extra 
     public static void searchData6(JTable table,String Column,double val,double val1,double val3)
    {
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
    double val2=1.0;
   try{
   val2=(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int) dbo.get(Column);
   val2*=a;
   }
  
if(val2!=val && val2!=val1 && val2!=val3) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
    
            String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);        
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
   /*  public static int getNumInteger(JTable table,String Column,int min,int max)
    {
        int i=0;
         ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        DBCollection col= ConnectionDB.MongoDB.db.getCollection("taxi1");
          DBCursor cursor=col.find();
          while(cursor.hasNext()){
            DBObject dbo= cursor.next();
            int vndID=(int) dbo.get(Column);
           if(vndID<min || vndID>max)
           {
               
           i++;
           }     
        }
    return i;
    }*/
     public static int getcharInteger(String Column,String var1,String var2)
    {
        int i=0;
        ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
        DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
          DBCursor cursor=col.find();
          while(cursor.hasNext()){
            DBObject dbo= cursor.next();
            String S_A_F=(String) dbo.get(Column);
           if(!S_A_F.equals(var1) || !S_A_F.equals(var2));
           {
           i++;
           }
              
        }
    return i;
    }
     /////////////////////////////////////////////////////////////////////////////////////////////////////////
    int i=0; 
    public  void fillFautSearch(JTable table,Vector<ObjectId> list)
    {
        
    ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    
  try{
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
  
if(list.get(i).equals(dbo.get("_id"))) // && val!=0.5
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
            String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
  }
  catch(Exception ex){
  Tools.showMessage(i+"");
  }
    if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
    
    
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     public static double getAver(JTable table,int indexColumn)
     {
         double Res=0;
      for(int i=0;i<table.getModel().getRowCount();i++)
      {
          try{
     Res+=(double) table.getModel().getValueAt(i, indexColumn);
          }
          catch(Exception ex)
          {
          int a=(int) table.getModel().getValueAt(i, indexColumn);
          Res+=a;
          }
      }
     
     return  Res/table.getModel().getRowCount();
     }
   /*   public static int DistTime(JTable table,String Column,int max,int min)
   {
       int g=0;
   int[] tab=new int[1000000];
       for(int i=0;i<1000000;i++)
       {
       tab[i]=(int) table.getModel().getValueAt(i, 0);
         //  System.out.println("wait");
       }
       for(int i=0;i<1000000;i++)
       {
       if(tab[i]>max && tab[i]<min)
       {
       g++;
       }
       }
   return g;
   }*/
     public static void istogrameDist(int min,int max,String Column,JTable table)
     {
        
          ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   
   
   int val2=(int)dbo.get(Column);
if( min>(int)dbo.get(Column) || (int)dbo.get(Column)>max)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
              String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
     if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
  
    
     }
      public static void istogrameDistDB(double min,double max,String Column,JTable table)
     {
        
          ConnectionDB.MongoDB.Connect_MongoDB("AppMaster");
     DefaultTableModel model=(DefaultTableModel) table.getModel();
    DBCollection col=ConnectionDB.MongoDB.db.getCollection("taxi1");
    DBCursor cursor=col.find();
    int i=0;
    while(cursor.hasNext())
    {
    DBObject dbo=cursor.next();
    //Object c=(Object) dbo.get(Column);
      //  Tools.showMessage(c+"");
   double val=1.0;
   try{
   val =(double)dbo.get(Column);
   }
   catch(Exception ex)
   {
   int a=(int)dbo.get(Column);
   val*=a;
   }
   
//   int val2=(int)dbo.get(Column);
if( min>val || val>max)
    {   
            ObjectId _ID=(ObjectId) dbo.get("_id");
            int vndID=(int) dbo.get("VendorID");
            String tpepdate=(String) dbo.get("tpep_pickup_date");
            String tpepofdate=(String) dbo.get("tpep_dropof_date");
              String tf=String.valueOf((Integer.parseInt(tpepofdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepofdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepofdate.substring(11, 19).substring(6, 8)))))-(Integer.parseInt(tpepdate.substring(11, 19).substring(0,2))*3600
            +(Integer.parseInt(tpepdate.substring(11, 19).substring(3,5))*60+ (Integer.parseInt(tpepdate.substring(11, 19).substring(6, 8))))));
           int tf2;

            tf2=Integer.parseInt(tf);
            int pc;
            try{
            pc=(int) dbo.get("passenger_count");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("passenger_count");
            pc=(int) a;
            }
            double td=1.0;
            try{
            td=(double) dbo.get("trip_distance");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("trip_distance");
            td=td*a;
            }
            int rc;
            try{
            rc=(int) dbo.get("RatecodeId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("RatecodeId");
            rc=(int) a;
            }
            String fwd=(String) dbo.get("stor_and_fwd");
            int plID;
            try{
            plID=(int) dbo.get("pulocationId");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("plID");
            plID=(int)a;
            }
            int dlID;
            try{
            dlID=(int) dbo.get("dolocationid");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("dolocationid");
            dlID=(int)a;
            }
            int pt;
            try{
            pt=(int) dbo.get("paymment_type");
            }
            catch(Exception e)
            {
            double a=(double) dbo.get("paymment_type");
            pt=(int)a;
            }
            int fa;
            try{
            fa=(int)dbo.get("fare_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("fare_amount");
            fa=(int)a;
            }
            double ext=1.0;
            try
            {
                ext=(double)dbo.get("extra");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("extra");
            ext=ext*a;
            }
            double mtx=1.0;
            try{
            mtx=(double)dbo.get("mtaa_tax");
            }
            catch(Exception e)
            {
                int a=(int)dbo.get("mtaa_tax");
                mtx=mtx*a;
            }
            int tam;
            try{
            tam=(int)dbo.get("tip_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tip_amount");
            tam=(int)a;
            }
            int tla;
            try{
            tla=(int) dbo.get("tools_amount");
            }
            catch(Exception e)
            {
            double a=(double)dbo.get("tools_amount");
            tla=(int)a;
            }
            double impsur=1.0;
            try{
            impsur=(double)dbo.get("improvement_surcharge");
            }
            catch(Exception e)
            {
            int a=(int) dbo.get("improvement_surcharge");
            impsur*=a;
            }
            double ttam=1.0;
            try{
            ttam=(double)dbo.get("total_amount");
            }
            catch(Exception e)
            {
            int a=(int)dbo.get("total_amount");
            ttam*=a;
            }
           String cngsur="";
           
            i+=1;
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf2,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});

    }
    
    }
     if(table.getModel().getRowCount()==0)
    {
    Tools.showMessage("There is no erroneous recording ");
    }
    else{
    table.setModel(model);
    Tools.showMessage("The number of erroeneous records are :"+table.getModel().getRowCount()+"Rows");
    }
  
    
     }
      public static void getObjectID(JTable table1,JTable table2)
     {
         DefaultTableModel model=(DefaultTableModel) table2.getModel();
         Vector<ObjectId> vec=new Vector<ObjectId>();
        
        for(int i=0;i<table1.getModel().getRowCount();i++)
        {
        vec.add((ObjectId) table1.getModel().getValueAt(i, 0));
        }
        for(int i=0;i<table1.getModel().getRowCount();i++)
        {
        model.addRow(new Object[]{vec.get(i)});
        }
        table2.setModel(model);
     }
      public static void loadMatrix(JTable tab1,JTable tab2,JTable tab3,JTable tab4,JTable tab5,JTable tab6,JTable tab7,JTable Matrix)
      {
         ArrayList<JTable> rules;
         rules = new ArrayList<>(Arrays.asList(tab1, tab2, tab3, tab4, tab5, tab6, tab7));
         Hashtable<String , ArrayList<Integer>> matrix = new Hashtable<>();
         
         for (int i = 0; i<rules.size(); ++i){
             JTable currentJtable = rules.get(i);
             for(int k=0 ; k<currentJtable.getRowCount();++k){
                ObjectId currentObjectId =(ObjectId) currentJtable.getModel().getValueAt(k,0);
                String objId = currentObjectId.toString();
                if(! matrix.containsKey(objId)){
                    matrix.put(objId, new ArrayList(Arrays.asList(0,0,0,0,0,0,0)));
                }
                matrix.get(objId).set(i, 1);
             }
         }
        Set<String> keys = matrix.keySet();
        int finish = 100;
        double[] support = new double[7];
        for(int i=0;i<7;i++)
           support[i] =  0.0;
        int counter = 0;
        for(String k : keys ){
            counter++;
            for (int i=0;i<7;++i){
                support[i] += matrix.get(k).get(i);
            }
            //if(--finish == 0){
            //    break;
           // }
            System.out.println(k + ": "+matrix.get(k));
        }
       for(int i=0;i<7;i++){
           System.out.println("Rule"+(i+1) +"sup : "+support[i] + "--  conf : "+support[i]/counter);
         
       }
      double[] s = new double[512];
      for(int i=0;i<512;i++)
           s[i] =  0.0;
      
      
      int z;
      Hashtable<String, Integer> tt = new Hashtable<>();
      
      for(String k : keys ){
          z = 0;
        for(int i=0;i<6;++i){
            for(int j=i+1;j<7;++j){
                // mkach 2 1 , 1 flba9i 
                if(matrix.get(k).get(i) == 1 && matrix.get(k).get(j) == 1){
                    String tmp = Integer.toString(i) + "->" + Integer.toString(j);
                    if (!tt.containsKey(tmp)){
                        tt.put(tmp, 0);
                    }
                    tt.put(tmp, tt.get(tmp)+1);
                }
                z++;
            }
        }
      }
     
      //==========display
      Set<String> ss2 = tt.keySet();
      
      for(String pp : ss2){
           //                                                                                           support[int(pp[0])] suuport
       //   System.out.println(pp + "-> : val1 : "+tt.get(pp)+" -- val2 : "+ ((double)tt.get(pp))/((double)counter));                ((double)support[Integer.parseInt(pp.substring(1))]))
        System.out.println(pp + " : support : "+((double)tt.get(pp))/((double)counter)+" -- confiance : "+ ((double)tt.get(pp))/((double)counter));
      }
    
     
      
      }
      
      public static void searchTime(JTable tableData,JTable tableDelete ,int min,int max)
      {
      DefaultTableModel model=(DefaultTableModel) tableDelete.getModel();
      //DefaultTableModel model1=(DefaultTableModel) tableData.getModel();
      
      for(int i=0;i<tableData.getModel().getRowCount();i++)
      {
      if(((int)min<(int)tableData.getModel().getValueAt(i, 4)) && ((int)tableData.getModel().getValueAt(i, 4)<(int)max) )
      {
         ObjectId _ID=(ObjectId) tableData.getModel().getValueAt(i, 0);
            int vndID=(int) tableData.getModel().getValueAt(i, 1);
            String tpepdate=(String) tableData.getModel().getValueAt(i, 2);
            String tpepofdate=(String) tableData.getModel().getValueAt(i, 3);
       String tf=(String) tableData.getModel().getValueAt(i, 4);

                 
            int pc=(int) tableData.getModel().getValueAt(i, 5);
          
            double td=(double) tableData.getModel().getValueAt(i, 6);
           
            int rc=(int) tableData.getModel().getValueAt(i, 7);
           
            String fwd=(String) tableData.getModel().getValueAt(i, 8);
            int plID=(int) tableData.getModel().getValueAt(i, 9);
            
            int dlID=(int) tableData.getModel().getValueAt(i, 10);
           
            int pt=(int) tableData.getModel().getValueAt(i, 11);
           
            int fa=(int) tableData.getModel().getValueAt(i, 12);
           
            double ext=(double) tableData.getModel().getValueAt(i, 13);
            
            double mtx=(double) tableData.getModel().getValueAt(i, 14);
           
            int tam=(int) tableData.getModel().getValueAt(i, 15);
          
            int tla=(int) tableData.getModel().getValueAt(i, 16);
           
            double impsur=(double) tableData.getModel().getValueAt(i, 17);
            
            double ttam=(double) tableData.getModel().getValueAt(i, 18);
           
           String cngsur="";
           model.addRow(new Object[]{_ID,vndID,tpepdate,tpepofdate,tf,pc,td,rc,fwd,plID,dlID,pt,fa,ext,mtx,tam,tla,impsur,ttam,cngsur});
      }
      }
      tableDelete.setModel(model);
      }
        
}
