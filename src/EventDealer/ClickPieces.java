package EventDealer;

import Pieces.*;
import view.ChessGameFrame;

public class ClickPieces {
    static Piece interact_click;
    public static boolean theVeryFirstClick = true;

    public static boolean PlayerTurnLabelHide = false;
    public static void click(Piece a) {
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
        if (!a.alive && Player.isFirstClick() == true) {
        } else if (a.IsReversal == false && Player.isFirstClick() == true && a.alive) {
            a.IsReversal = true;
            System.out.println("reverse!!!");
            a.visible();
            Player.click_times += 2;
        }// TODO: 2022/12/18 完善条件

        //选中点击的棋子
        else if (a.IsSelected == false && Player.isFirstClick() == true && a.alive
                && (theVeryFirstClick || a.side == Player.whichPlayer())) {
            PlayerTurnLabelHide = true;
            a.IsSelected = true;
            System.out.println("Selected!!!");
            Player.click_times += 1;
            a.visible();
            interact_click = a;

        } else if (a.IsSelected == true) {
            PlayerTurnLabelHide = false;
            a.IsSelected = false;
            System.out.println("Unselected!!!");
            Player.click_times -= 1;
            a.visible();
        }

        //一方连续点击两个棋子，两个棋子发生交互
        if (a.IsSelected == false && !Player.isFirstClick()) {
            if (interact_click.IsSelected) {
                Interact.interact(interact_click, a);
            }
        }


    }
}
