package EventDealer;

import Pieces.*;

public class ClickPieces {
    static Piece[] interact_click = new Piece[3];
    public static boolean theVeryFirstClick = true;

    public static void click(Piece a, int i, int j) {
        //第一次点击后判定轮到哪方
        if (theVeryFirstClick) {
            if (a.side == 'r') {
                Player.playerTurn = 'b';
            } else {
                Player.playerTurn = 'r';
            }
            theVeryFirstClick = false;
        }
        System.out.println(String.format("%c", Player.whichPlayer()));


        //翻转背扣的棋子
        if (a.IsReversal == false && Player.isFirstClick() == true) {
            a.IsReversal = true;
            System.out.println("reverse!!!");
            a.visible(i, j);
            Player.click_times += 2;
        }// TODO: 2022/12/18 完善条件

        //选中点击的棋子
        else if (a.IsSelected == false && Player.isFirstClick() == true
                && (theVeryFirstClick || a.side == Player.whichPlayer())) {
            a.IsSelected = true;
            System.out.println("Selected!!!");
            Player.click_times += 1;
            a.visible(i, j);
            interact_click[0] = a;

        } else if (a.IsSelected == true) {
            a.IsSelected = false;
            System.out.println("Unselected!!!");
            Player.click_times -= 1;
            a.visible(i, j);
        }

        //一方连续点击两个棋子，两个棋子发生交互
        if (a.IsSelected == false && !Player.isFirstClick()) {
            Interact.interact(interact_click, a);
        }


    }
}
