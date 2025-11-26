package org.example;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect [][] ;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;


    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];

        int col = 0;
        int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow){


            eventRect[col][row] = new EventRect ();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row]. eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row]. eventRectDefaultY = eventRect[col][row].y;
            col++;
            if (col == gp.maxWorldCol){
                col = 0;
                row ++;
            }
        }






    }

    public void checkEvent() {

        // check character positiom is more than one tile away

        int xDistance = Math.abs (gp.player.worldX - previousEventX);
        int yDistance = Math.abs (gp.player.worldY - previousEventY);
        int distance = Math.max (xDistance, yDistance);
        if (distance >gp.tileSize){
            canTouchEvent= true;
        }

        if (canTouchEvent == true){
            if (hit (27, 16, "right")) {
                damagePit (27, 16, gp.dialogueState);
            }
            if (hit (23, 19, "any")) {
                damagePit (22, 19, gp.dialogueState);
            }

            // Healing pool tile at 23,12
            if (hit (23, 12, "up")) {
                healingPool (23, 12, gp.dialogueState);
            }
        }
        // Pit tile at 27,16

    }

    public boolean hit(int col, int row, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRectDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRectDefaultY;

        return hit;
    }

    private void damagePit(int col, int row, int gameState) {
        if (!gp.player.inPit) { // only trigger once per step onto the tile
            gp.player.life -= 1;
            gp.player.inPit = true;
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You fell into a pit!";
//            eventRect[col][row].eventDone= true;
            canTouchEvent= false;



        }
    }

    private void healingPool(int col, int row, int gameState) {
        if (gp.keyH.enterPressed) {
            gp.player.life = gp.player.maxLife;
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Fountain of Youth drip drip.\nYour life has been restored!";
            gp.keyH.enterPressed = false; // prevent retrigger
        }
    }
}
