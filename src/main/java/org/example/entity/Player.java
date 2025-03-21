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

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultVaulues();
        getPLayerImage();
    }

    public void setDefaultVaulues (){
        x = 100;
        y = 100;
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
            y-=speed;

        }
        else if (keyH.downPRessed==true) {
            direction = "down";
            y+= speed;
        }
        else if (keyH.leftPRessed==true) {
            direction = "left";
            x-=speed;
        }
        else if (keyH.rightPRessed==true) {
            direction = "right";
            x+= speed;
        }

    }
    public void draw (Graphics g2) {
//        g2.setColor(Color.WHITE);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up1;
                break;
                case "down":
                image = down1;
                break;
                case "left":
                image = left1;
                break;
                case "right":
                image = right1;
                break;
                default:
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }
}
