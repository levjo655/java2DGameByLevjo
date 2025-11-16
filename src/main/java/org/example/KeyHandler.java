package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPRessed, downPRessed, leftPRessed, rightPRessed;

    //DEBUGG
    boolean checkDrawTime = false;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

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
        if (keyCode == KeyEvent.VK_P) {
            if(gp.gameState== gp.playState ){
            gp.gameState=gp.pauseState;
            }
            else if (gp.gameState== gp.pauseState){
                gp.gameState= gp.playState;
            }

        }

        //DEBUG
        if (keyCode == KeyEvent.VK_T) {
            if (checkDrawTime == false) {
                checkDrawTime = true;
            } else if (checkDrawTime == true) {
                checkDrawTime = false;

            }

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
