package view;
/*
游戏主窗体。
 */

import Calculate.*;
import EventDealer.ClickPieces;
import EventDealer.Player;
import Pieces.*;
import SaveAndLoad.Save;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import static EventDealer.Interact.DeadPiece;
import static EventDealer.Interact.DeadPieces;
import static Pieces.Information_of_Location.chessboard;

public class ChessGameFrame extends JFrame {
    private final int GameFrameWidth = 720;

    private final int GameFrameHeight = 720;

    public int Click_x;
    public int Click_y;
//    int count1 = 0;
//    int count2 = 0;

    public static int PointsOfRed = 0;
    public static int PointsOfBlack = 0;

    public JLabel PlayerTurnLabel = new JLabel();
    public JLabel[] DeadPiecesLabel = new JLabel[32];

    public JLabel PointsOfRedLabel = new JLabel();
    public JLabel PointsOfBlackLabel = new JLabel();

    public JLabel WinnerLabel = new JLabel();

    public static boolean GameOver = false;
    public static String Winner;

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

        //显示死亡的棋子
        for (int i = 0; i < 32; i++) {
            DeadPiecesLabel[i] = new JLabel();
        }
        DeadPiecesVisible();


        PointsVisible();
        WinnerVisible();

        addSaveButton();
        addLoadButton();
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
            for (int i = 0; i < 32; i++) {
                DeadPiecesLabel[i].setIcon(new ImageIcon("Image/dead.jpg"));
//                count1 = 0;
//                count2 = 0;
            }
            Points.calculatePoints();
            PointsVisible();
            DeadPiece = null;
            DeadPieces = new ArrayList<>();
            Winner = "";
            GameOver = false;
            WinnerVisible();

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
                int finalI = i;
                int finalJ = j;
                label.addMouseListener(new MouseAdapter() {
                    public void mousePressed(MouseEvent e) {
                        // 这里是点击 JLabel 后要执行的代码
                        //Save.record.add(String.format("%d %d|", ClickedPiece.x, ClickedPiece.y));
                        //Save.convertToList();
                        Piece tmp = DeadPiece;
                        System.out.println("Click!");
                        if (!GameOver) {
                            ClickPieces.click(ClickedPiece);
                        }
                        getPlayerTurnLabel();
                        if (DeadPiece != tmp) {
                            Points.calculatePoints();
                            PointsVisible();
                            WinnerVisible();
                            DeadPiecesVisible();
                        }
                    }
                });
                add(label);
            }
        }
    }

    //轮到哪方，添加标语。
    public void getPlayerTurnLabel() {
        if (ClickPieces.PlayerTurnLabelHide) {
            PlayerTurnLabel.setText("");
        } else if (Player.whichPlayer() == 'r') {
            PlayerTurnLabel.setText("Red's Turn");
        } else {
            PlayerTurnLabel.setText("Black's Turn");
        }
        PlayerTurnLabel.setSize(200, 50);
        PlayerTurnLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        PlayerTurnLabel.setLocation(450, 500);
        PlayerTurnLabel.setForeground(Color.WHITE);
        add(PlayerTurnLabel);
    }

    //在棋盘外显示死亡的棋子
    public void DeadPiecesVisible() {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < DeadPieces.size(); i++) {
            Piece deadPiece = DeadPieces.get(i);
            if (deadPiece != null && deadPiece.side == 'r') {
                DeadPiecesLabel[count1 + count2].setIcon(deadPiece.r);
                DeadPiecesLabel[count1 + count2].setLocation(0, 60 * count1);
                DeadPiecesLabel[count1 + count2].setSize(60, 60);
                count1++;
                System.out.println("Dead!!!");
            } else if (deadPiece != null && deadPiece.side == 'b') {
                DeadPiecesLabel[count1 + count2].setIcon(deadPiece.b);
                DeadPiecesLabel[count1 + count2].setLocation(645, 60 * count2);
                DeadPiecesLabel[count1 + count2].setSize(60, 60);
                count2++;
                System.out.println("Dead!!!");
            }
            //add(DeadPiecesLabel[count1 + count2]);
        }
        for(int j=0;j<32;j++){
            add(DeadPiecesLabel[j]);
        }


    }


    //显示双方得分
    public void PointsVisible() {
        PointsOfRedLabel.setLocation(400, 0);
        PointsOfRedLabel.setSize(320, 50);
        PointsOfRedLabel.setText("Red Points: " + PointsOfRed);
        PointsOfRedLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        PointsOfRedLabel.setForeground(Color.WHITE);
        PointsOfBlackLabel.setLocation(100, 0);
        PointsOfBlackLabel.setSize(320, 50);
        PointsOfBlackLabel.setText("Black Points:" + PointsOfBlack);
        PointsOfBlackLabel.setFont(new Font("Rockwell", Font.BOLD, 30));
        PointsOfBlackLabel.setForeground(Color.WHITE);
        add(PointsOfBlackLabel);
        add(PointsOfRedLabel);
    }

    //显示赢家
    public void WinnerVisible() {
        WinnerLabel.setLocation(400, 300);
        WinnerLabel.setForeground(Color.RED);
        WinnerLabel.setSize(720, 200);
        WinnerLabel.setFont(new Font("Rockwell", Font.BOLD, 60));
        if (!GameOver) {
            WinnerLabel.setText("");
        } else if (Winner.equals("Red")) {
            WinnerLabel.setText("Red Win!");
            System.out.println("Red Win!!!!!!");
        } else if (Winner.equals("Black")) {
            WinnerLabel.setText("Black Win!");
            System.out.println("Black Win!!!!!!");
        }
        add(WinnerLabel);
    }
    //存档按钮
    public void addSaveButton() {
        AtomicBoolean canSave = new AtomicBoolean(true);
        JButton save = new JButton("Save");
        save.setLocation(500, 200);
        save.setSize(100, 50);
        save.setVisible(true);
        save.addActionListener(e -> {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (chessboard[i][j].IsSelected == true) {
                        canSave.set(false);
                    }
                }
            }
            if (canSave.get()) {
                Save.writeFileByFileWriter("data.txt");
            }
        });
        add(save);
    }
    //读档按钮
    public void addLoadButton() {
        JButton load = new JButton("Load");
        load.setLocation(500, 300);
        load.setSize(100, 50);
        load.setVisible(true);
        load.addActionListener(e -> {
            dispose();
            Save.loadGame("data.txt");
            getPlayerTurnLabel();
//            count1 = 0;
//            count2 = 0;
            DeadPiece = null;
            DeadPieces = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!chessboard[i][j].alive) {
                        DeadPieces.add(chessboard[i][j]);
                    }
                }
            }
            DeadPiecesVisible();
            ClickPieces.PlayerTurnLabelHide = false;
        });
        add(load);
    }
}










