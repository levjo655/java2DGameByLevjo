package org.example.entity;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    String[] dialogues = new String[20];
    int dialogueIndex = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction() {

    }
    public void speak (){
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;

            case "left":
                direction = "right";
                break;

            case "right":
                direction = "left";
                break;


        }}

    public void update() {
        setAction();
        collisionOn = false;
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkPlayer(this);
        gp.cChecker.checkTile(this);
        if (collisionOn == false) {
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
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteCounter == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }


    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Only draw if visible on screen
        if (screenX > -gp.tileSize && screenX < gp.screenWidth &&
                screenY > -gp.tileSize && screenY < gp.screenHeight) {

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

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }


    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return image;

    }
}
