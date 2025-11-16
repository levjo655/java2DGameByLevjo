package org.example.entity;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey= 0;


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

        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");

    }
    public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image= null;
        try {
        image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/"+ imageName + ".png"));
        image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return  image;

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
               gp.playSe(1);
               hasKey ++;
               gp.obj[i] = null;
               gp.ui.showMessage("You got a key");

               break;
               case "Door":
                   gp.playSe(3);
               if (hasKey >0){
                   gp.obj[i] = null;
                   hasKey --;
                   gp.ui.showMessage("you opened the door");
               } else {
                   gp.ui.showMessage("You need a key");
               }

               break;
               case "Boots":
                   gp.playSe(2);
               speed += 2;
               gp.obj[i] = null;
                   gp.ui.showMessage("Fast af boiiiiiiiii");
                   break;
               case "Chest":
               gp.ui.gameFinished = true;
               gp.stopMusic();
               gp.playSe(4);
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

        g2.drawImage(image, screenX, screenY, null);
    }
}
