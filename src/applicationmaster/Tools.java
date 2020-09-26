/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applicationmaster;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.TextField;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author benso
 */
public class Tools {
       public static void showMessage(String Message)
    {
        JOptionPane.showMessageDialog(null, Message);
    }
    
    public static void cleanAllCont(Container con)
    {
    for(Component c:con.getComponents())
    {
     if(c instanceof TextField)
     {
     
     }
     else
     {
         cleanAllCont(con);
     }
    }
    }
    public static void showConfiMessage(String Message)
    {
   
    }
    public  void createFrame(JFrame frame,String Title)
    {
    frame.setTitle(Title);
   // frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Icon_NoSQL_SQL.jpg")));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    }
   
    /*public void createFrameBarChart_PassengerCount(double Num1,double Num2,double Num3,double Num4,double Num5,double Num6,double Num7,double Num8,double Num9,String Title)
    {
     DefaultCategoryDataset cd=new DefaultCategoryDataset();
        cd.setValue(Num1,"","0");
        cd.setValue(Num2, "", "1");
        cd.setValue(Num3, "", "2");
        cd.setValue(Num4, "", "3");
        cd.setValue(Num5, "", "4");
        cd.setValue(Num6, "", "5");
        cd.setValue(Num7, "", "6");
        cd.setValue(Num8, "", "7");
        cd.setValue(Num9, "", "8");
        JFreeChart chart=ChartFactory.createBarChart3D(Title, "Passenger", "Count", cd, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot=chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.WHITE);
        ChartFrame chartframe=new ChartFrame(Title, chart,true);
        chartframe.setSize(1500,900);
         //chartframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Icon_NoSQL_SQL.jpg")));
        chartframe.setLocationRelativeTo(null);
        chartframe.setVisible(true);
    }*/
      public void createFrameBarChart_PassengerCount(double[] Num1,String Title,int tailleTab)
    {
     DefaultCategoryDataset cd=new DefaultCategoryDataset();
     for(int i=0;i<tailleTab;i++){   
     cd.setValue(Num1[i],"",i+1+"");
     }
        JFreeChart chart=ChartFactory.createBarChart3D(Title, "Passenger", "Count", cd, PlotOrientation.VERTICAL, true, true, false);
        CategoryPlot plot=chart.getCategoryPlot();
        plot.setRangeGridlinePaint(Color.WHITE);
        ChartFrame chartframe=new ChartFrame(Title, chart,true);
        chartframe.setSize(1500,900);
         //chartframe.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Images/Icon_NoSQL_SQL.jpg")));
        chartframe.setLocationRelativeTo(null);
        chartframe.setVisible(true);
    }
}
