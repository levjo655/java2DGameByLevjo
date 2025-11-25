package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;


        setDefaultVaulues();
        getPLayerImage();
    }

    public void setDefaultVaulues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // Player status
        maxLife = 6;
        life = maxLife;
    }

    public void getPLayerImage() {

        up1 = setup("player/boy_up_1");
        up2 = setup("player/boy_up_2");
        down1 = setup("player/boy_down_1");
        down2 = setup("player/boy_down_2");
        left1 = setup("player/boy_left_1");
        left2 = setup("player/boy_left_2");
        right1 = setup("player/boy_right_1");
        right2 = setup("player/boy_right_2");

    }

    public void update() {
        boolean moving = false;

        // --- Check key input ---
        if (keyH.upPRessed) {
            direction = "up";
            moving = true;
        } else if (keyH.downPRessed) {
            direction = "down";
            moving = true;
        } else if (keyH.leftPRessed) {
            direction = "left";
            moving = true;
        } else if (keyH.rightPRessed) {
            direction = "right";
            moving = true;
        }
        // After movement code
        if (!(gp.eHandler.hit (27, 16, "any"))) {
            inPit = false; // reset once player leaves the pit
        }


        // --- Check for collisions ---
        collisionOn = false;
        gp.cChecker.checkTile(this);

        // Check NPC collision

        int npcIndex = gp.cChecker.checkEntity(this,gp.npc);
        interactNPC (npcIndex);

        //check obj collision
        int objIndex = gp.cChecker.checkObject(this, true);
        pickUpObject(objIndex);

        // Check Event
        gp.eHandler.checkEvent ();
        gp.keyH.enterPressed = false;


        // --- Move only if a key was pressed ---
        if (moving && !collisionOn) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        // --- Handle walking animation only when moving ---
        if (moving) {
            spriteCounter++;
            if (spriteCounter > 15) {
                spriteNum = (spriteNum == 1) ? 2 : 1;
                spriteCounter = 0;
            }
        } else {
            spriteNum = 1; // idle frame
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) {

        }
    }
    public void interactNPC(int i){
        if (i != 999) {
        if(gp.keyH.enterPressed == true){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
        }





        }
    }

    public void draw (Graphics g2) {
        //  g2.setColor(Color.WHITE);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = (spriteNum == 1) ? up1 : up2;
                break;
            case "down":
                image = (spriteNum == 1) ? down1 : down2;
                break;
            case "left":
                image = (spriteNum == 1) ? left1 : left2;
                break;
            case "right":
                image = (spriteNum == 1) ? right1 : right2;
                break;
            default:
                image = down1;
        }

        g2.drawImage(image, screenX, screenY, null);
    }

    // inside Player class
    public boolean inPit = false;

}
