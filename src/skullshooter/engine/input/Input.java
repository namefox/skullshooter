package skullshooter.engine.input;

import java.awt.event.*;

public class Input implements KeyListener, MouseListener, MouseMotionListener {
    
    private static boolean[] keys;
    private static boolean[] mouse;
    private static boolean dragging;
    private static boolean inWindow;
    private static int mX, mY;
    
    public Input() {
        keys = new boolean[65489];
        mouse = new boolean[5];
    }
    
    public static int getMouseX() {
        return mX;
    }
    
    public static int getMouseY() {
        return mY;
    }
    
    public static boolean isMouseDragging() {
        return dragging;
    }
    
    public static boolean isMouseInWindow() {
        return inWindow;
    }
    
    public static boolean getKeyDown(int keyCode) {
        return keys[keyCode];
    }

    public static boolean getKeyUp(int keyCode) {
        return !keys[keyCode];
    }

    public static boolean getMouseDown(int button) {
        return mouse[button];
    }

    public static boolean getMouseUp(int button) {
        return !mouse[button];
    }

    @Override public void keyTyped(KeyEvent e){}
    @Override public void mouseClicked(MouseEvent e){}
    
    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouse[e.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouse[e.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        inWindow = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        inWindow = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dragging = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mX = e.getX();
        mY = e.getY();
    }
}