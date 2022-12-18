package view;
/*
游戏主窗体。
 */

import Calculate.*;
import EventDealer.ClickPieces;
import EventDealer.Player;
import Pieces.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Pieces.Information_of_Location.chessboard;

public class ChessGameFrame extends JFrame {
    private int GameFrameWidth = 720;

    private int GameFrameHeight = 720;

    public int Click_x;
    public int Click_y;

    public ChessGameFrame() {

        setTitle("Dark Chess");
        setSize(GameFrameWidth, GameFrameHeight);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new Chessboard());
        setLayout(null);
        setLocationRelativeTo(null);
        //addMouseListener();

        addRestartButton();
        DrawPieces();
       // add(PlayerTurnLabel());

    }


    //获取鼠标点击坐标
   /*public void addMouseListener() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getID() == MouseEvent.MOUSE_CLICKED) {
                    Click_x = Coordinate.calculateX(e.getX());
                    Click_y = Coordinate.calculateY(e.getY());
                    System.out.println(Click_x + "," + Click_y);
                }
            }
        });
    }*/

    //重新开始按钮
    public void addRestartButton() {
        JButton restart = new JButton("Restart");
        restart.setLocation(500, 100);
        restart.setSize(100, 50);
        restart.setVisible(true);
        restart.addActionListener(e -> {
            // TODO: 2022/12/17  
            System.out.println("Restart");

        });

        add(restart);
    }

    //绘制棋子，给每个棋子添加动作监听器。
    public void DrawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                JLabel label = chessboard[i][j].visible(i, j);
                int finalI = i;
                int finalJ = j;
                label.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        // 这里是点击 JLabel 后要执行的代码
                        System.out.println("Click!" + (finalI + 1) + "," + (finalJ + 1));
                        ClickPieces.click(chessboard[finalI][finalJ], finalI, finalJ);
                    }
                });
                add(label);
            }
        }
    }

    public static JLabel PlayerTurnLabel(){
        JLabel label = new JLabel(String.format("%c",Player.whichPlayer()));
        label.setText(String.format("%c",Player.whichPlayer()));
        label.setSize(100,50);
        label.setFont(new Font("Rockwell", Font.BOLD, 50));
        label.setLocation(500,500);
        return label;
    }
}







