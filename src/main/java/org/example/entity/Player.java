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


    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX= gp.screenWidth/2 - (gp.tileSize/2);
        screenY= gp.screenHeight/2 -(gp.tileSize/2);

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

    public void update(){
        if(keyH.upPRessed==true) {
            direction = "up";
            worldY-=speed;

        }
        else if (keyH.downPRessed==true) {
            direction = "down";
            worldY+= speed;
        }
        else if (keyH.leftPRessed==true) {
            direction = "left";
            worldX-=speed;
        }
        else if (keyH.rightPRessed==true) {
            direction = "right";
            worldX+= speed;
        }

        spriteCounter++;
        if (spriteCounter> 15) {

            if (spriteNum==1){
                spriteNum=2;
            }
            else if (spriteNum==2){
                spriteNum=1;
            }
            spriteCounter=0;
        }

    }
    public void draw (Graphics g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
