//x: 0-775
//y: 0-750

import java.awt.Color;

import javax.swing.JFrame;


import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;






public class MainR {
    
    
    final static int XBOUND = 600;
    final static int YBOUND = 405;

    final static double MOVEMENT_SPEED = 1.0;
    final static double CAMERA_SPEED = 0.122173;
    
    

    public static PanelR thisPanel = new PanelR(XBOUND, YBOUND);
    public static CameraR activeCamera;
    



    private static volatile boolean wPressed = false;
    private static volatile boolean sPressed = false;

    private static volatile boolean aPressed = false;
    private static volatile boolean dPressed = false;


    private static volatile boolean upPressed = false;
    private static volatile boolean downPressed = false;

    private static volatile boolean rightPressed = false;
    private static volatile boolean leftPressed = false;


    private static volatile boolean spacePressed = false;
    private static volatile boolean shiftPressed = false;

    private static volatile boolean enterPressed = false;


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


    public static boolean isUpPressed() {
        synchronized (MainR.class) {
            return upPressed;
        }
    }
    public static boolean isDownPressed() {
        synchronized (MainR.class) {
            return downPressed;
        }
    }
    public static boolean isRightPressed() {
        synchronized (MainR.class) {
            return rightPressed;
        }
    }
    public static boolean isLeftPressed() {
        synchronized (MainR.class) {
            return leftPressed;
        }
    }


    public static boolean isSpacePressed() {
        synchronized (MainR.class) {
            return spacePressed;
        }
    }
    public static boolean isShiftPressed() {
        synchronized (MainR.class) {
            return shiftPressed;
        }
    }

    public static boolean isEnterPressed(){
        synchronized (MainR.class) {
            return enterPressed;
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


                        if (ke.getKeyCode() == KeyEvent.VK_UP) {
                            upPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                            downPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                            rightPressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                            leftPressed = true;
                        }


                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = true;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
                            shiftPressed = true;
                        }

                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = true;
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


                        if (ke.getKeyCode() == KeyEvent.VK_UP) {
                            upPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                            downPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                            rightPressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
                            leftPressed = false;
                        }


                        if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                            spacePressed = false;
                        }
                        if (ke.getKeyCode() == KeyEvent.VK_SHIFT) {
                            shiftPressed = false;
                        }
                        
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                            enterPressed = false;
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


        //Top Face
        for (int x = -5; x <= 5; x++){
            myEnv.drawLine(Color.GREEN, x, 5, 15, x, 5, 25);
        }
        //Front Face
        for (int x = -5; x <= 5; x++){
            myEnv.drawLine(Color.CYAN, x, 5, 15, x, -5, 15);
        }
        //Right Face
        for (int y = -5; y <= 5; y++){
            myEnv.drawLine(Color.MAGENTA, 5, y, 15, 5, y, 25);
        }
        
        //Bottom Face
        for (int x = -5; x <= 5; x++){
            myEnv.drawLine(Color.YELLOW, x, -5, 15, x, -5, 25);
        }
        //Back Face
        for (int x = -5; x <= 5; x++){
            myEnv.drawLine(Color.RED, x, 5, 25, x, -5, 25);
        }
        
        //Left Face
        for (int y = -5; y <= 5; y++){
            myEnv.drawLine(Color.PINK, -5, y, 15, -5, y, 25);
        }
        //Black Borders
        myEnv.drawLine(Color.WHITE, -5, 5, 15, 5, 5, 15);
        myEnv.drawLine(Color.WHITE, -5, -5, 15, 5, -5, 15);
        myEnv.drawLine(Color.WHITE, -5, 5, 25, 5, 5, 25);
        myEnv.drawLine(Color.WHITE, -5, -5, 25, 5, -5, 25);
        
        myEnv.drawLine(Color.WHITE, -5, 5, 15, -5, 5, 25);
        myEnv.drawLine(Color.WHITE, 5, 5, 15, 5, 5, 25);
        myEnv.drawLine(Color.WHITE, -5, -5, 15, -5, -5, 25);
        myEnv.drawLine(Color.WHITE, 5, -5, 15, 5, -5, 25);

        myEnv.drawLine(Color.WHITE, 5, -5, 15, 5, 5, 15);
        myEnv.drawLine(Color.WHITE, -5, -5, 15, -5, 5, 15);
        myEnv.drawLine(Color.WHITE, 5, -5, 25, 5, 5, 25);
        myEnv.drawLine(Color.WHITE, -5, -5, 25, -5, 5, 25);
        
        //System.out.print(myEnv);

        //myEnv.drawTetrahedron(Color.LIGHT_GRAY, );


        //Main Loop
        while (true){
            if ( !(MainR.isWPressed() && MainR.isSPressed())){
                if (MainR.isWPressed()){
                    activeCamera.moveForward(MOVEMENT_SPEED);
                }
                else if (MainR.isSPressed()){
                    activeCamera.moveBackward(MOVEMENT_SPEED);
                }
            }
            
            if ( !(MainR.isAPressed() && MainR.isDPressed())){
                if (MainR.isAPressed()){
                    activeCamera.moveLeft(MOVEMENT_SPEED);
                }
                else if (MainR.isDPressed()){
                    activeCamera.moveRight(MOVEMENT_SPEED);
                }
            }

            if ( !(MainR.isUpPressed() && MainR.isDownPressed())){
                if (MainR.isUpPressed()){
                    activeCamera.pitch += CAMERA_SPEED;
                }
                else if (MainR.isDownPressed()){
                    activeCamera.pitch -= CAMERA_SPEED;
                }
            }

            if ( !(MainR.isLeftPressed() && MainR.isRightPressed())){
                if (MainR.isLeftPressed()){
                    activeCamera.yaw -= CAMERA_SPEED;
                }
                else if (MainR.isRightPressed()){
                    activeCamera.yaw += CAMERA_SPEED;
                }
            }

            if ( !(MainR.isSpacePressed() && MainR.isShiftPressed())){
                if (MainR.isSpacePressed()){
                    activeCamera.y += MOVEMENT_SPEED;
                }
                else if (MainR.isShiftPressed()){
                    activeCamera.y -= MOVEMENT_SPEED;
                }
            }

            if (MainR.isEnterPressed()){
                activeCamera.shoot(myEnv);
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

    /*public static void update(PanelR w){
        SwingUtilities.updateComponentTreeUI(w);
    }

    public static int randNum(double min, double max){
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static double randNumDouble(double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }*/

}
    
