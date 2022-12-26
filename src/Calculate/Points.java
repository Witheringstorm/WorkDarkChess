package Calculate;

import Pieces.*;
import view.*;
import static Pieces.Information_of_Location.chessboard;
import static view.ChessGameFrame.PointsOfBlack;
import static view.ChessGameFrame.PointsOfRed;
import static view.ChessGameFrame.GameOver;
import static view.ChessGameFrame.Winner;

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

        if(PointsOfRed >= 80) {
            GameOver = true;
            Winner = "Red";
        }
        if(PointsOfBlack >= 80){
            GameOver = true;
            Winner = "Black";
        }
    }
}
