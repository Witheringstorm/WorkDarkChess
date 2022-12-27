package Calculate;

import view.Music;

import java.io.File;

import static Pieces.Information_of_Location.chessboard;
import static view.ChessGameFrame.*;

public class Points {
    public static void calculatePoints() {
        PointsOfBlack = 0;
        PointsOfRed = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                if(chessboard[i][j].side == 'r' && !chessboard[i][j].alive){
                    PointsOfBlack += chessboard[i][j].points;
                }
                else if(chessboard[i][j].side == 'b' && !chessboard[i][j].alive){
                    PointsOfRed += chessboard[i][j].points;
                }
            }
        }

        if(PointsOfRed >= 60) {
            GameOver = true;
            Winner = "Red";
            File 获胜音效=new File("获胜音效.wav");
            Music.playMusic4(获胜音效);
        }
        if(PointsOfBlack >= 60){
            GameOver = true;
            Winner = "Black";
            File 获胜音效=new File("获胜音效.wav");
            Music.playMusic4(获胜音效);
        }
    }
}
