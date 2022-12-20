package view;
/*
游戏主窗体。
 */

import Calculate.*;
import EventDealer.ClickPieces;
import EventDealer.Interact;
import EventDealer.Player;
import Pieces.*;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Pieces.Information_of_Location.chessboard;
import static Pieces.Information_of_Location.initialize;

public class ChessGameFrame extends JFrame {
    private final int GameFrameWidth = 720;

    private final int GameFrameHeight = 720;

    public int Click_x;
    public int Click_y;

    public JLabel PlayerTurnLabel = new JLabel();
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
        getPlayerTurnLabel();
        DeadPiecesVisible();
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
            Information_of_Location.restart();
            System.out.println("Restart");
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    chessboard[i][j].x = j;
                    chessboard[i][j].y = i;
                    chessboard[i][j].IsSelected = false;
                    chessboard[i][j].IsReversal = false;
                    chessboard[i][j].alive = true;
                    chessboard[i][j].visible();
                }
            }
            Player.click_times = 0;
            ClickPieces.theVeryFirstClick = true;
            ClickPieces.PlayerTurnLabelHide = false;
        });
        add(restart);
    }

    //绘制棋子，给每个棋子添加动作监听器。
    public void DrawPieces() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                chessboard[i][j].x = j;
                chessboard[i][j].y = i;
                JLabel label = chessboard[i][j].visible();
                Piece ClickedPiece = chessboard[i][j];
                label.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        // 这里是点击 JLabel 后要执行的代码
                        System.out.println("Click!");
                        ClickPieces.click(ClickedPiece);
                        getPlayerTurnLabel();
                    }
                });
                add(label);
            }
        }
    }
    //轮到哪方，添加标语。
    public void getPlayerTurnLabel() {
        if(ClickPieces.PlayerTurnLabelHide){
            PlayerTurnLabel.setText("");
        }
        else if (Player.whichPlayer() == 'r') {
            PlayerTurnLabel.setText("Red's Turn");
        } else {
            PlayerTurnLabel.setText("Black's Turn");
        }
        PlayerTurnLabel.setSize(200, 50);
        PlayerTurnLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        PlayerTurnLabel.setLocation(450, 500);
        add(PlayerTurnLabel);
    }

    //在棋盘外显示死亡的棋子
    public static void DeadPiecesVisible(){
        int y = 0;
        int count = 0;
        for(Piece i : Interact.DeadPieces){
            if (i.side == 'r') {

            }
        }
    }


}







