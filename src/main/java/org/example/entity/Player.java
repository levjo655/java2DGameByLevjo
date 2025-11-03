package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int hasKey= 0;


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX= gp.screenWidth/2 - (gp.tileSize/2);
        screenY=gp.screenHeight/2 - (gp.tileSize/2);
        solidArea= new Rectangle(8,16,32,32);
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY= solidArea.y;


        setDefaultVaulues();
        getPLayerImage();
    }

    public void setDefaultVaulues (){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed= 4;
        direction="down";
    }
    public void getPLayerImage(){
        try {
            up1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_1.png"));
            up2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_up_2.png"));
            down1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_down_2.png"));
            left1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_1.png"));
            left2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_left_2.png"));
            right1= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_1.png"));
            right2= ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/boy_right_2.png"));

//
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void update() {
        boolean moving = false;

        // --- Check key input ---
        if (keyH.upPRessed) {
            direction = "up";
            moving = true;
        }
        else if (keyH.downPRessed) {
            direction = "down";
            moving = true;
        }
        else if (keyH.leftPRessed) {
            direction = "left";
            moving = true;
        }
        else if (keyH.rightPRessed) {
            direction = "right";
            moving = true;
        }

        // --- Check for collisions ---
        collisionOn = false;
        gp.cChecker.checkTile(this);

        //check obj collision
        int objIndex = gp.cChecker.checkObject(this,true);
        pickUpObject(objIndex);


        // --- Move only if a key was pressed ---
        if (moving && !collisionOn) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
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
   public void pickUpObject (int i){
       if (i != 999){
           String objectName= gp.obj[i].name;
           switch (objectName){
               case "key":
               hasKey ++;
               gp.obj[i] = null;
                   System.out.println("key" + hasKey);
               break;
               case "Door":
               if (hasKey >0){
                   gp.obj[i] = null;
                   hasKey --;
               }
                   System.out.println("key" + hasKey);
               break;
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
