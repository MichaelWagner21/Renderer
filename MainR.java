//x: 0-775
//y: 0-750

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class MainR {
    
    
    final static int XBOUND = 775;
    final static int YBOUND = 750;
    
    

    public static PanelR thisPanel = new PanelR(XBOUND, YBOUND);



    public static void main(String[] args){

        System.out.print("\033[H\033[2J");


        JFrame appFrame = new JFrame("Renderer");


        appFrame.add(thisPanel);
        appFrame.pack();
        appFrame.setVisible(true);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    
        thisPanel.fillCanvas(Color.BLACK);
        thisPanel.setPixel(50, 50, Color.RED);
        thisPanel.drawLine(Color.WHITE, 40, 50, 100,200);

        CameraR camera1 = new CameraR(XBOUND, YBOUND);
        EnvironmentR myEnv = new EnvironmentR(2, 2, 2);
        myEnv.drawSimpleCube(Color.RED, -2,-2,-2,2,2,2);
        System.out.println(myEnv);
        //System.out.print(myEnv.getColor(0,0,0));

    }






    public static void wait(int t){
        try {
            Thread.sleep(t);
          } 
        catch (InterruptedException ex) {
    
            Thread.currentThread().interrupt();
          }
    }

    public static void update(PanelR w){
        SwingUtilities.updateComponentTreeUI(w);
    }

    public static int randNum(double min, double max){
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static double randNumDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

}
    
