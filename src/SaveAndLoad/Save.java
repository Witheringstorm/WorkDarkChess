package SaveAndLoad;

import Calculate.Points;
import Pieces.*;
import view.ChessGameFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import EventDealer.*;

import static Pieces.Information_of_Location.chessboard;
import static Pieces.Information_of_Location.chessboardCopy;

public class Save {
    //读取和存档
    public static List<String> record = new ArrayList<>();
    public static List<String> record2 = new ArrayList<>();

    //记录棋盘初始状态
    public static void writeRecord() {
        StringBuilder sb = new StringBuilder();
        sb.append("!");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(chessboard[i][j].toString());
                sb.append(",");
            }
        }
        record.add(sb.toString());
        record.add(",ClickInfo" + " " + Player.click_times + " " + Player.playerTurn + " " + ClickPieces.theVeryFirstClick + "!");

    }

    //记录棋盘末状态
    public static void writeRecord2() {
        StringBuilder sb = new StringBuilder();
        sb.append("!");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append(chessboard[i][j].toString());
                sb.append(",");
            }
        }
        record2.add(sb.toString());
        record2.add(",ClickInfo" + " " + Player.click_times + " " + Player.playerTurn + " " + ClickPieces.theVeryFirstClick + "!");
    }

    //将记录写入用户指定的文件
    public static void writeFile(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = record;
            for (String line : lines
            ) {
                writer.write(line);
                System.out.println(line);
                writer.flush();
            }
            lines = record2;
            for (String line : lines
            ) {
                writer.write(line);
                System.out.println(line);
                writer.flush();
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String fileReader(String path) {
        File file = new File(path);
        try {
            FileReader fr = new FileReader(file);
            char ch[] = new char[4096];
            int len = fr.read(ch);
            return new String(ch, 0, len);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "你输入的存档不存在",
                    "找不到存档", JOptionPane.ERROR_MESSAGE);
            Save.loadGame("default/d.txt");
        }
        return null;
    }


    public static int loadGame(String path) {
        AtomicBoolean moveCorrect = new AtomicBoolean(true);
        ClickPieces.interact_click = null;
        ClickPieces.theVeryFirstClick = true;
        String input = fileReader(path);
        System.out.println(input);
        String[] part = input.split("!");
        String[] pieces = part[1].split(",");
        for (String piece : pieces) {
            String[] data = piece.split(" ");
            if (!data[0].equals("") && !data[0].equals("ClickInfo")) {
                int type = Integer.parseInt(data[0]);
                char side = data[1].charAt(0);
                boolean alive = Boolean.parseBoolean(data[2]);
                int y = Integer.parseInt(data[3]);
                int x = Integer.parseInt(data[4]);
                boolean IsReversal = Boolean.parseBoolean(data[5]);
                if (type == 0) {
                    chessboard[x][y] = new CannonChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 1) {
                    chessboard[x][y] = new SoldierChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 2) {
                    chessboard[x][y] = new HorseChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 3) {
                    chessboard[x][y] = new ChariotChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 4) {
                    chessboard[x][y] = new MinisterChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 5) {
                    chessboard[x][y] = new AdvisorChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 6) {
                    chessboard[x][y] = new GeneralChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                }


            } else if (data[0].equals("ClickInfo")) {
                Player.click_times = Integer.parseInt(data[1]);
                Player.playerTurn = data[2].charAt(0);
                ClickPieces.theVeryFirstClick = Boolean.parseBoolean(data[3]);
                System.out.println("Signal!!!!!!");
            }
        }

            String[] action = part[2].split("&");
            for (String a : action) {
                String[] data = a.split(" ");
                if (data[0] != "") {
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 4; j++) {
                            chessboard[i][j].x = j;
                            chessboard[i][j].y = i;
                        }
                    }
                    int click_x = Integer.parseInt(data[0]);
                    int click_y = Integer.parseInt(data[1]);
                    Piece click = chessboard[click_y][click_x];
                    ClickPieces.click(click);

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            Points.calculatePoints();
            record = new ArrayList<>();
            record2 = new ArrayList<>();
            writeRecord();


            String[] pieces2 = part[3].split(",");
            for (String piece : pieces2) {
                String[] data = piece.split(" ");
                if (!data[0].equals("") && !data[0].equals("ClickInfo")) {
                    int type = Integer.parseInt(data[0]);
                    char side = data[1].charAt(0);
                    boolean alive = Boolean.parseBoolean(data[2]);
                    int y = Integer.parseInt(data[3]);
                    int x = Integer.parseInt(data[4]);
                    boolean IsReversal = Boolean.parseBoolean(data[5]);
                    if (type == 0) {
                        chessboardCopy[x][y] = new CannonChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 1) {
                        chessboardCopy[x][y] = new SoldierChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 2) {
                        chessboardCopy[x][y] = new HorseChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 3) {
                        chessboardCopy[x][y] = new ChariotChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 4) {
                        chessboardCopy[x][y] = new MinisterChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 5) {
                        chessboardCopy[x][y] = new AdvisorChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    } else if (type == 6) {
                        chessboardCopy[x][y] = new GeneralChess(side);
                        chessboardCopy[x][y].alive = alive;
                        chessboardCopy[x][y].IsReversal = IsReversal;
                    }
                }
            }
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    chessboardCopy[i][j].x = j;
                    chessboardCopy[i][j].y = i;
                }
            }

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 4; j++) {
                    if (!chessboardCopy[i][j].toString().equals(chessboard[i][j].toString())) {
                        moveCorrect.set(false);
                    }
                }
            }
//            SwingUtilities.invokeLater(() -> {
//            ChessGameFrame g = new ChessGameFrame();
//            g.canSave = false;
//            g.setVisible(true);
//        });
        if (!part[4].equals("r") && !part[4].equals("b")) {
            return 104;
        }
        if (!moveCorrect.get()) {
            return 105;
        } else return 0;
    }

    public static void loadUndo(String string) {
        ClickPieces.interact_click = null;
        ClickPieces.theVeryFirstClick = true;
        String input = string;
        System.out.println(input);
        String[] part = input.split("!");
        String[] pieces = part[1].split(",");
        for (String piece : pieces) {
            String[] data = piece.split(" ");
            if (!data[0].equals("") && !data[0].equals("ClickInfo")) {
                int type = Integer.parseInt(data[0]);
                char side = data[1].charAt(0);
                boolean alive = Boolean.parseBoolean(data[2]);
                int y = Integer.parseInt(data[3]);
                int x = Integer.parseInt(data[4]);
                boolean IsReversal = Boolean.parseBoolean(data[5]);
                if (type == 0) {
                    chessboard[x][y] = new CannonChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 1) {
                    chessboard[x][y] = new SoldierChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 2) {
                    chessboard[x][y] = new HorseChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 3) {
                    chessboard[x][y] = new ChariotChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 4) {
                    chessboard[x][y] = new MinisterChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 5) {
                    chessboard[x][y] = new AdvisorChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                } else if (type == 6) {
                    chessboard[x][y] = new GeneralChess(side);
                    chessboard[x][y].alive = alive;
                    chessboard[x][y].IsReversal = IsReversal;
                }


            } else if (data[0].equals("ClickInfo")) {
                Player.click_times = Integer.parseInt(data[1]);
                Player.playerTurn = data[2].charAt(0);
                ClickPieces.theVeryFirstClick = Boolean.parseBoolean(data[3]);
                System.out.println("Signal!!!!!!");
            }
        }
        String[] action = part[2].split("&");
        for (String a : action) {
            String[] data2 = a.split(" ");
            if (data2[0] != "") {
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 4; j++) {
                        chessboard[i][j].x = j;
                        chessboard[i][j].y = i;
                    }
                }
                int click_x = Integer.parseInt(data2[0]);
                int click_y = Integer.parseInt(data2[1]);
                Piece click = chessboard[click_y][click_x];
                ClickPieces.click(click);
            }
        }
        Points.calculatePoints();

    }
}

