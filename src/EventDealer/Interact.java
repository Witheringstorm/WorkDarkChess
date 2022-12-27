package EventDealer;

import Pieces.Information_of_Location;
import Pieces.Piece;
import view.Music;

import java.io.File;
import java.util.ArrayList;

import static Pieces.Information_of_Location.chessboard;
import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Interact {
    public static ArrayList<Piece> DeadPieces = new ArrayList<>();

    public static Piece DeadPiece = null;

    public static void interact(Piece a, Piece b) {

        //如果是空格，则除 炮 以外都可与之交换位置
        if (!b.alive) {
            if ((abs(a.x - b.x) + abs(a.y - b.y) == 1) && a.type != 0) {
                capture(a, b);
            }
        }
        //炮的特殊移动和吃子判定
        else if (a.type == 0 && (a.x == b.x || a.y == b.y)) {
            int count = 0;
            if (a.x == b.x) {
                for (int i = Math.min(a.y, b.y) + 1; i < max(a.y, b.y); i++) {
                    if (Information_of_Location.chessboard[i][a.x].alive) {
                        count++;
                        System.out.println(a.x + " " + i);
                    }
                }
            } else {
                for (int j = Math.min(a.x, b.x) + 1; j < max(a.x, b.x); j++) {
                    if (Information_of_Location.chessboard[a.y][j].alive) {
                        System.out.println(j + " " + a.y);
                        count++;
                    }
                }
            }
            if (count == 1 && (!b.IsReversal || a.side != b.side)) {
                capture(a, b);
            }
        }
        //其他棋子的吃子判定
        else if (!b.IsReversal) {
        } else if ((abs(a.x - b.x) + abs(a.y - b.y) == 1) && a.side != b.side) {
            if (a.type == 1 && b.type == 6) {
                capture(a, b);
            } else if (a.type == 6 && b.type == 1) {
            } else if (a.type == 1 && b.type == 0) {
            } else if (a.type >= b.type) {
                capture(a, b);
            }
        }
    }

    public static void capture(Piece a, Piece b) {
        File 吃子音效=new File("吃子音效.wav");
        Music.playMusic3(吃子音效);
        //棋子a吃棋子b
        if(b.alive){
            DeadPiece = b;
            DeadPieces.add(b);
        }
        b.alive = false;
        int tmpx = a.x;
        int tmpy = a.y;
        a.x = b.x;
        a.y = b.y;
        b.x = tmpx;
        b.y = tmpy;
        Piece tmp = chessboard[a.y][a.x];
        chessboard[a.y][a.x] = chessboard[b.y][b.x];
        chessboard[b.y][b.x] = tmp;
        Player.click_times++;
        a.IsSelected = false;
        b.IsSelected = false;
        a.visible();
        b.visible();
        ClickPieces.PlayerTurnLabelHide = false;
        System.out.println("Capture!!!");
    }

}
