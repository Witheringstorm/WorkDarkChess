package Pieces;

import javax.swing.*;

public abstract class Piece extends JComponent {
    //所有棋子和空棋子的父类
    public boolean exist = true;
    public boolean IsReversal = false;
    public boolean IsSelected = false;
    public int type;
    public char side;
    public int points;
    ImageIcon unreversed = new ImageIcon("image/unreversed.png");
    ImageIcon red_advisor = new ImageIcon("image/r_advisor.png");
    ImageIcon red_advisor_select = new ImageIcon("image/r_advisor_s.png");


    JLabel PieceLabel = new JLabel();

    public void reverse() {
        IsReversal = true;
    }

    public void select() {
        if (IsReversal && exist && !IsSelected) {
            IsSelected = true;
        }
        if (IsSelected) {
            IsSelected = false;
        }
    }

    public abstract JLabel visible(int i, int j);


}


