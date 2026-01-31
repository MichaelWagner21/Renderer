//x: 0-775
//y: 0-750

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;






public class MainR {
    
    
    final static int XBOUND = 600;
    final static int YBOUND = 405;
    
    

    public static PanelR thisPanel = new PanelR(XBOUND, YBOUND);
    public static CameraR activeCamera;
    



    private static volatile boolean wPressed = false;
    private static volatile boolean sPressed = false;

    private static volatile boolean aPressed = false;
    private static volatile boolean dPressed = false;

    //Following Methods: Copied from stack overflow
    public static boolean isWPressed() {
        synchronized (MainR.class) {
            return wPressed;
        }
    }
    public static boolean isSPressed() {
        synchronized (MainR.class) {
            return sPressed;
        }
    }
    public static boolean isAPressed() {
        synchronized (MainR.class) {
            return aPressed;
        }
    }
    public static boolean isDPressed() {
        synchronized (MainR.class) {
            return dPressed;
        }
    }



    public static void main(String[] args){


        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (MainR.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                            sPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            aPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            dPressed = true;
                        }
                        break;

                    case KeyEvent.KEY_RELEASED:
                        if (ke.getKeyCode() == KeyEvent.VK_W) {
                            wPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_S) {
                            sPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_A) {
                            aPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_D) {
                            dPressed = false;
                        }
                        break;
                    }
                    return false;
                }
            }
        });




        System.out.print("\033[H\033[2J");


        JFrame appFrame = new JFrame("Renderer");


        appFrame.add(thisPanel);
        appFrame.pack();
        appFrame.setVisible(true);
        appFrame.setResizable(false);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
    
        thisPanel.fillCanvas(Color.BLACK);

        activeCamera = new CameraR(XBOUND, YBOUND);
        EnvironmentR myEnv = new EnvironmentR(50, 50, 50);




        for (int x = -5; x <= 5; x++){
            myEnv.drawLine(Color.CYAN, x, 5, 15, x, -5, 15);
        }
        //System.out.print(myEnv);


        //Main Loop
        while (true){
            if ( !(MainR.isWPressed() && MainR.isSPressed())){
                if (MainR.isWPressed()){
                    activeCamera.z += 1;
                }
                else if (MainR.isSPressed()){
                    activeCamera.z -= 1;
                }
            }
            
            if ( !(MainR.isAPressed() && MainR.isDPressed())){
                if (MainR.isAPressed()){
                activeCamera.x -= 1;
                }
                else if (MainR.isDPressed()){
                    activeCamera.x += 1;
                }
            }
            
            activeCamera.updateProjection(myEnv);
            thisPanel.render(activeCamera.projection, XBOUND, YBOUND);
        }
        // camera1.updateProjection(myEnv);
        // thisPanel.render(camera1.projection, XBOUND, YBOUND);
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
    
