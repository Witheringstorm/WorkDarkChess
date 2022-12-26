package view;


import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static view.Music.clip;
/*
绘制棋盘
 */

public class Chessboard extends JPanel {
    private int Chessboard_Width = 300;
    private int Chessboard_Height = 600;

    BufferedImage Wooden_Chessboard;
    BufferedImage test;
    BufferedImage advisor;

    public Chessboard() {
        File 中国象棋=new File("中国象棋.wav");
        Music.playMusic(中国象棋);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        try {
            Wooden_Chessboard = ImageIO.read(new File("image/WoodenChessboard.jpg"));
            test = ImageIO.read(new File("image/test.jpg"));
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    @Override
    public void paintComponent(Graphics g) {
        //绘制背景和格子
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(Wooden_Chessboard, 0, 0, this);
        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(3.0f));
        g2.drawRect(100, 50, Chessboard_Width, Chessboard_Height); //在（100,50）处绘制棋盘
        g2.setStroke(new BasicStroke(1.0f));
        for (int i = 1; i < 4; i++) {
            g2.drawLine(100 + 75 * i, 50, 100 + 75 * i, 650);//一个格子大小75*75
        }
        for (int j = 1; j < 8; j++) {
            g2.drawLine(100, 50 + 75 * j, 400, 50 + 75 * j);
        }
    }
}






