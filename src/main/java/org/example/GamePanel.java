package org.example;

import org.example.entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    // screen settings
    final int originalTileSize= 16;
    final int scale= 3;



   public  final int tileSize= originalTileSize * scale;
   public final int maxScreenCol= 16;
   public final int maxScreenRow= 12;
   public final int screenWidth= tileSize * maxScreenCol;
   public final int screenHeight= tileSize * maxScreenRow;


    public final int maxWorldCol= 50;
    public final int maxWorldRow= 50;;
    public final int worldWidth= tileSize * maxWorldCol;
    public final int worldHeight= tileSize * maxWorldRow;




    int FPS= 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH= new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player  player = new Player (this, keyH);


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {

        double drawInterval= 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime= System.nanoTime() + drawInterval;


        while (gameThread!= null){
            long currentTime= System.nanoTime();


            update();

            repaint();



            try {
                double remainingTime= nextDrawTime - System.nanoTime();
                remainingTime= remainingTime /1000000;

                if( remainingTime < 0){
                    remainingTime= 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
