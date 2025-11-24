package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;

    public boolean upPRessed, downPRessed, leftPRessed, rightPRessed, enterPressed;


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

        // title state

        if (gp.gameState == gp.titleState) {
            if (keyCode == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }

            }
            if (keyCode == KeyEvent.VK_S) {

                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.stopMusic();
                    gp.playMusic(0);
                    gp.gameState = gp.playState;

                }
                if(gp.ui.commandNum==1){
                  // add later for load command
                  // }
            } if (gp.ui.commandNum==2){
                    System.exit(0);
                }

                }

        }

        // PLay State

        if (gp.gameState == gp.playState) {
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
                gp.gameState = gp.pauseState;
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                enterPressed = true;
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

        // Pause State
        else if (gp.gameState == gp.pauseState) {
            if (keyCode == KeyEvent.VK_P) {
                gp.gameState = gp.playState;


            }
        }
        // Dialogue State
        else if (gp.gameState == gp.dialogueState) {
            if (keyCode == KeyEvent.VK_ENTER) {
                gp.gameState = gp.playState;

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
