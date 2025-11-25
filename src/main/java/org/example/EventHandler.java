package org.example;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public EventHandler(GamePanel gp) {
        this.gp = gp;
        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        // Pit tile at 27,16
        if (hit(27, 16, "any")) {
            damagePit();
        }

        // Healing pool tile at 23,12
        if (hit(23, 12, "any")) {
            healingPool();
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect.x = eventCol * gp.tileSize + eventRect.x;
        eventRect.y = eventRow * gp.tileSize + eventRect.y;

        if (gp.player.solidArea.intersects(eventRect)) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    private void damagePit() {
        if (!gp.player.inPit) { // only trigger once per step onto the tile
            gp.player.life -= 1;
            gp.player.inPit = true;
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "You fell into a pit!";

        }
    }

    private void healingPool() {
        if (gp.keyH.enterPressed) {
            gp.player.life = gp.player.maxLife;
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Fountain of Youth drip drip.\nYour life has been restored!";
            gp.keyH.enterPressed = false; // prevent retrigger
        }
    }
}
