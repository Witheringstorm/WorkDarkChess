package Pieces;


import java.util.ArrayList;

public class Information_of_Location  {
    /*
    用二维数组存储棋子类型和位置信息
     */
    public static Piece[][] chessboard ;
    public static Piece[][] chessboardCopy = new Piece[8][4];


    public static ArrayList<Piece> arr;


    public static void initialize() {
        //初始化棋盘
        //填充指定数量的对应棋子
        chessboard =new Piece[8][4];
        arr = new ArrayList<>();
        arr.add(new GeneralChess('r'));
        arr.add(new GeneralChess('b'));
        for (int i = 0; i < 2; i++) {
            arr.add(new AdvisorChess('r'));
            arr.add(new AdvisorChess('b'));
            arr.add(new MinisterChess('r'));
            arr.add(new MinisterChess('b'));
            arr.add(new ChariotChess('r'));
            arr.add(new ChariotChess('b'));
            arr.add(new HorseChess('r'));
            arr.add(new HorseChess('b'));
            arr.add(new CannonChess('r'));
            arr.add(new CannonChess('b'));
        }
        for (int i = 0; i < 5; i++) {
            arr.add(new SoldierChess('r'));
            arr.add(new SoldierChess('b'));
        }
        int k = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                chessboard[i][j] = arr.get(k);
                k++;
            }
        }
        //对二维数组里的棋子进行洗牌（打乱顺序）
        for (int i = 7; i > 0; i--) {
            for (int j = 3; j > 0; j--) {
                int x = (int) (Math.random() * (i + 1));
                int y = (int) (Math.random() * (j + 1));
                Piece tmp = chessboard[i][j];
                chessboard[i][j] = chessboard[x][y];
                chessboard[x][y] = tmp;
            }
        }

    }

    public static void restart() {
        for (int i = 7; i > 0; i--) {
            for (int j = 3; j > 0; j--) {
                int x = (int) (Math.random() * (i + 1));
                int y = (int) (Math.random() * (j + 1));
                Piece tmp = chessboard[i][j];
                chessboard[i][j] = chessboard[x][y];
                chessboard[x][y] = tmp;
            }
        }
    }


}