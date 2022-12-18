package EventDealer;

import Pieces.*;

public class ClickPieces {
    public static boolean theVeryFirstClick = true;
    public static void click(Piece a, int i, int j) {
        //第一次点击后判定轮到哪方
        if(theVeryFirstClick){
            if(a.side=='r'){
                Player.playerTurn = 'b';
            }
            else{
                Player.playerTurn = 'r';
            }
            theVeryFirstClick = false;
        }
        System.out.println(String.format("%c",Player.whichPlayer()));


        //翻转背扣的棋子
        if (a.IsReversal == false && Player.isFirstClick() == true) {
            a.IsReversal = true;
            System.out.println("reverse!!!");
            a.visible(i, j);
            Player.click_times += 2;
        }// TODO: 2022/12/18 完善条件


        //选中点击的棋子
        else if (a.IsSelected == false && Player.isFirstClick() == true) {
            a.IsSelected = true;
            System.out.println("Selected!!!");
            Player.click_times += 1;
            a.visible(i,j);
        }


        else if(a.IsSelected == true){
            a.IsSelected = false;
            System.out.println("Unselected!!!");
            Player.click_times -= 1;
            a.visible(i,j);
        }

    }
}
