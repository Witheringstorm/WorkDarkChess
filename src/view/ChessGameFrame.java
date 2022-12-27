package view;
/*
游戏主窗体。
 */

import Calculate.Points;
import EventDealer.ClickPieces;
import EventDealer.Player;
import Pieces.Information_of_Location;
import Pieces.Piece;
import SaveAndLoad.Save;
import SaveAndLoad.Undo;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static EventDealer.Interact.DeadPiece;
import static EventDealer.Interact.DeadPieces;
import static Pieces.Information_of_Location.chessboard;
import static view.Music.bg_clip;


//import static view.Music.clip;


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

    public boolean canSave = true;
    public boolean canSave2 = false;
    public static int clickTimes = 0;
    public boolean cheat = false;

//    public static  Clip bg_clip;
//    public static  Clip music1_clip;
//    public static  Clip music2_clip;

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
        addCheatButton();
//        Save.writeRecord();
        addUndoButton();
        addMuteButton();
        File 中国象棋=new File("中国象棋.wav");
        Music.playMusic1(中国象棋);
        bg_clip.loop(Clip.LOOP_CONTINUOUSLY);

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

    //静音按钮
    public void addMuteButton(){
        AtomicInteger c = new AtomicInteger();
        JButton mute = new JButton("Mute");
        mute.setLocation(450, 50);
        mute.setSize(100, 30);
        mute.setVisible(true);
        mute.addActionListener(e -> {
            c.getAndIncrement();
            if (c.get() % 2 == 1) {
                mute.setText("Unmute");
                bg_clip.stop();
                System.out.println("Mute!");
            }
            else {
                mute.setText("Mute");
                bg_clip.start();
                System.out.println("Unmute!");
            }
        });
        add(mute);
    }


    //重新开始按钮
    public void addRestartButton() {
        JButton restart = new JButton("Restart");
        restart.setLocation(500, 100);
        restart.setSize(100, 50);
        restart.setVisible(true);
        restart.addActionListener(e -> {
            Information_of_Location.restart();
            System.out.println("Restart");
            bg_clip.stop();

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
            clickTimes = 0;
            ClickPieces.theVeryFirstClick = true;
            ClickPieces.PlayerTurnLabelHide = false;
            for (int i = 0; i < 32; i++) {
                DeadPiecesLabel[i].setIcon(new ImageIcon("Image/dead.jpg"));
            }
            Points.calculatePoints();
            PointsVisible();
            DeadPiece = null;
            DeadPieces = new ArrayList<>();
            Winner = "";
            GameOver = false;
            WinnerVisible();
            Save.record = new ArrayList<>();
            Save.record2 = new ArrayList<>();
            //Save.writeRecord();
            canSave = true;
            canSave2 = false;
            dispose();
            SwingUtilities.invokeLater(() -> {
                Information_of_Location.initialize();
                ChessGameFrame g = new ChessGameFrame();
                g.setVisible(true);
                Save.writeRecord();
            });


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

                        clickTimes++;
                        canSave2 = true;
                        Save.record.add(String.format("%d %d&", ClickedPiece.x, ClickedPiece.y));
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

                    public void mouseEntered(MouseEvent e) {
                        if (cheat && ClickedPiece.alive) {
                            ClickedPiece.visible(true);
                        }
                        Points.calculatePoints();
                        PointsVisible();
                        DeadPiecesVisible();
                    }

                    public void mouseExited(MouseEvent e) {
                        if (cheat) {
                            ClickedPiece.visible();
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
        PlayerTurnLabel.setLocation(450, 450);
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
                if (60 * count2 < 660) {
                    DeadPiecesLabel[count1 + count2].setLocation(0, 60 * count1);
                } else {
                    DeadPiecesLabel[count1 + count2].setLocation(60, 60 * (count1 - 11));
                }
                DeadPiecesLabel[count1 + count2].setSize(60, 60);
                count1++;
                System.out.println("Dead!!!");
            } else if (deadPiece != null && deadPiece.side == 'b') {
                DeadPiecesLabel[count1 + count2].setIcon(deadPiece.b);
                if (60 * count2 < 660) {
                    DeadPiecesLabel[count1 + count2].setLocation(645, 60 * count2);
                } else {
                    DeadPiecesLabel[count1 + count2].setLocation(585, 60 * (count2 - 11));
                }

                DeadPiecesLabel[count1 + count2].setSize(60, 60);
                count2++;
                System.out.println("Dead!!!");
            }
            //add(DeadPiecesLabel[count1 + count2]);
        }
        for (int j = 0; j < 32; j++) {
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
        JButton save = new JButton("Save");
        save.setLocation(500, 200);
        save.setSize(100, 50);
        save.setVisible(true);
        save.addActionListener(e -> {
            if (/*canSave && */canSave2) {
                Save.record2 = new ArrayList<>();
                Save.writeRecord2();
                Save.record2.add(String.valueOf(Player.whichPlayer()));
                String path = JOptionPane.showInputDialog
                        (null, "请输入存档名称", "default.txt");
                if (path != null) {
                    if (path.split("\\.").length < 2 || !path.split("\\.")[1].equals("txt")) {
                        JOptionPane.showMessageDialog
                                (null, "不支持的文件格式（需要.txt）", "错误编码：101",
                                        JOptionPane.ERROR_MESSAGE);
                    } else {
                        Save.writeFile("file/" + path);
                        canSave = false;
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "游戏未开始时不能存档"
                        , "非法操作", JOptionPane.WARNING_MESSAGE);
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
            AtomicInteger status = new AtomicInteger();
            String path = JOptionPane.showInputDialog
                    (null, "请输入你要读入存档的名称", "default.txt");
            if (path != null) {
                if (path.split("\\.").length < 2 || !path.split("\\.")[1].equals("txt")) {
                    JOptionPane.showMessageDialog(null, "不支持的文件格式（需要.txt）",
                            "错误编码：101", JOptionPane.ERROR_MESSAGE);
                } else {
                    dispose();
                    new Thread(() -> {
                        try {
                            status.set(Save.loadGame("file/" + path));
                        } catch (ArrayIndexOutOfBoundsException a) {
                            status.set(102);
                        }
                        int[] count = new int[7];
                        for (int x = 0; x < 8; x++) {
                            for (int y = 0; y < 4; y++) {
                                count[chessboard[x][y].type]++;
                            }
                        }
                        if (count[0] != 4 || count[1] != 10 || count[2] != 4 || count[3] != 4 ||
                                count[4] != 4 || count[5] != 4 || count[6] != 2) {
                            status.set(103);
                        }
                        if (status.get() == 102) {
                            JOptionPane.showMessageDialog(null, "存档中棋盘错误",
                                    "错误代码：102", JOptionPane.ERROR_MESSAGE);
                            Save.loadGame("default/d.txt");
                        } else if (status.get() == 103) {
                            JOptionPane.showMessageDialog(null, "存档中棋子数目或类型错误",
                                    "错误代码：103", JOptionPane.ERROR_MESSAGE);
                        } else if (status.get() == 104) {
                            JOptionPane.showMessageDialog(null, "存档中缺少行棋方",
                                    "错误代码：104", JOptionPane.ERROR_MESSAGE);
                        } else if (status.get() == 105) {
                            JOptionPane.showMessageDialog(null, "存档中行棋步骤错误",
                                    "错误代码：105", JOptionPane.ERROR_MESSAGE);
                        }

                        getPlayerTurnLabel();
                        DeadPiece = null;
                        DeadPieces = new ArrayList<>();
                        clickTimes = 0;

                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (!chessboard[i][j].alive) {
                                    DeadPieces.add(chessboard[i][j]);
                                }
                            }
                        }
                        DeadPiecesVisible();
                        ClickPieces.PlayerTurnLabelHide = false;
                    }).start();
                    SwingUtilities.invokeLater(() -> {
                        ChessGameFrame g = new ChessGameFrame();
                        g.setVisible(true);
                    });

                }
            }

            Winner = "";
            GameOver = false;
            WinnerVisible();
            canSave = false;

        });
        add(load);
    }

    //作弊模式
    public void addCheatButton() {
        AtomicInteger c = new AtomicInteger();
        JButton Cheat = new JButton("Cheat Mode: OFF");
        Cheat.setLocation(450, 600);
        Cheat.setSize(200, 50);
        Cheat.setVisible(true);
        Cheat.addActionListener(e -> {
            c.getAndIncrement();
            if (c.get() % 2 == 1) {
                cheat = true;
                Cheat.setText("Cheat Mode: ON");
            } else {
                cheat = false;
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        chessboard[i][j].visible();
                    }
                }
                Cheat.setText("Cheat Mode: OFF");
            }
        });
        add(Cheat);
    }



    //悔棋按钮
    public void addUndoButton() {
        JButton undo = new JButton("Undo");
        undo.setLocation(500, 500);
        undo.setSize(100, 50);
        undo.setVisible(true);
        AtomicInteger a = new AtomicInteger(1);
        undo.addActionListener(e -> {
            bg_clip.stop();
            if (clickTimes >= 2) {
                try {
                    Undo.undo(a.getAndIncrement());
                    dispose();
                    SwingUtilities.invokeLater(() -> {
                        ChessGameFrame g = new ChessGameFrame();
                        g.setVisible(true);
                    });
                } catch (IndexOutOfBoundsException q) {
                    a.getAndDecrement();
                    JOptionPane.showMessageDialog(null, "无法进一步回退",
                            "非法操作", JOptionPane.ERROR_MESSAGE);
                }
                getPlayerTurnLabel();
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
            } else {
                JOptionPane.showMessageDialog(null, "无法进一步回退",
                        "非法操作", JOptionPane.ERROR_MESSAGE);
            }
        });
        add(undo);
    }

}












