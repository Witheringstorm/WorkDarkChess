package Pieces;

import javax.swing.*;

public abstract class Piece extends JComponent {
    //所有棋子和空棋子的父类
    public JLabel PieceLabel = new JLabel();
    public int x = 0;
    public int y = 0;
    public boolean exist = true;
    public boolean IsReversal = false;
    public boolean IsSelected = false;
    public int type;
    public char side;
    public boolean alive = true;
    public int points;
    ImageIcon unreversed = new ImageIcon("image/unreversed.png");
    ImageIcon dead = new ImageIcon("image/dead.png");
    public static ImageIcon b;
    public static ImageIcon r;




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

    public abstract JLabel visible();

    public void capture(int i,int j){

    }




}


