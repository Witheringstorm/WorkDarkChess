package SaveAndLoad;

import Calculate.Points;
import Pieces.*;
import view.ChessGameFrame;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import EventDealer.*;

import static Pieces.Information_of_Location.chessboard;

public class Save {
    //读取和存档
    public static List<String> record = new ArrayList<>();
    public static List<String> convertToList() {
        StringBuilder sb = new StringBuilder();
        for (Piece[] pieces : chessboard) {
            sb.append(",");
            sb.setLength(0);
            for (Piece piece : pieces) {
                sb.append(piece.toString() + ",");
            }
            //sb.setLength(sb.length() - 1);
            record.add(sb.toString());
        }
        record.add(",ClickInfo" + " " + Player.click_times + " " + Player.playerTurn + " " + ClickPieces.theVeryFirstClick +"|");
        return record;
    }

    public static void writeFileByFileWriter(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = convertToList();
            for (String line : lines
            ) {
                writer.write(line);
                System.out.println(line);
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFileByFileReader(String path) {
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;
            line = reader.readLine();
            reader.close();
            fileReader.close();
            return line;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void loadGame(String path) {
        String input = readFileByFileReader("data.txt");
        String[] pieces = input.split(",");
        for (String piece : pieces) {
            //String[] round
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
                chessboard[x][y].visible();
                Points.calculatePoints();

            } else if (data[0].equals("ClickInfo")) {
                Player.click_times = Integer.parseInt(data[1]);
                Player.playerTurn = data[2].charAt(0);
                ClickPieces.theVeryFirstClick = Boolean.parseBoolean(data[3]);
                System.out.println("Signal!!!!!!");
            }
        }
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame g = new ChessGameFrame();
            g.setVisible(true);
        });

    }
}

