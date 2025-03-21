package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPRessed, downPRessed, leftPRessed, rightPRessed;
    @Override
    public void keyTyped(KeyEvent e) {

    }


    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            upPRessed = true;

        }
        if (keyCode == KeyEvent.VK_S) {
            downPRessed = true;

        }
        if (keyCode == KeyEvent.VK_A) {
            leftPRessed = true;

        }
        if (keyCode == KeyEvent.VK_D) {
            rightPRessed = true;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W) {
            upPRessed = false;

        }
        if (keyCode == KeyEvent.VK_S) {
            downPRessed = false;

        }
        if (keyCode == KeyEvent.VK_A) {
            leftPRessed = false;

        }
        if (keyCode == KeyEvent.VK_D) {
            rightPRessed = false;

        }

    }
}
