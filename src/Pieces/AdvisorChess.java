package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class AdvisorChess extends Piece {

    //棋子：仕


    public static ImageIcon b = new ImageIcon("image/b_advisor.png");
    ImageIcon bs = new ImageIcon("image/b_advisor_s.png");

    public static ImageIcon r = new ImageIcon("image/r_advisor.png");
    public static ImageIcon rs = new ImageIcon("image/r_advisor_s.png");
    public AdvisorChess(char side) {
        type = 5;
        points = 10;
        this.side = side;
    }

    public JLabel visible() {
        System.out.println("visible!!!");
        if(this.alive == false){
            PieceLabel.setIcon(dead);
        }
        else if (this.IsReversal == false) {
            PieceLabel.setIcon(unreversed);
        } else {
            if (this.side == 'r' && !IsSelected)
                PieceLabel.setIcon(r);

            if (this.side == 'r' && IsSelected) {
                PieceLabel.setIcon(rs);
            }
            if (this.side == 'b' && !IsSelected) {
                PieceLabel.setIcon(b);
            }
            if (this.side == 'b' && IsSelected) {
                PieceLabel.setIcon(bs);
            }
        }

        PieceLabel.setSize(75, 75);
        PieceLabel.setLocation(Coordinate.reverse_calculateX(x + 1) -10, Coordinate.reverse_calculateY(y+ 1) - 17);
        return PieceLabel;
    }

}


