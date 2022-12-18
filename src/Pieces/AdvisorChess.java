package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class AdvisorChess extends Piece {

    //棋子：仕


    ImageIcon b = new ImageIcon("image/b_advisor.png");
    ImageIcon bs = new ImageIcon("image/b_advisor_s.png");
    public AdvisorChess(char side) {
        type = 5;
        points = 10;
        this.side = side;
    }

    public JLabel visible(int i, int j) {
        System.out.println("visible!!!");
        if (this.IsReversal == false) {
            PieceLabel.setIcon(unreversed);
        } else {
            if (this.side == 'r' && !IsSelected)
                PieceLabel.setIcon(red_advisor);

            if (this.side == 'r' && IsSelected) {
                PieceLabel.setIcon(red_advisor_select);
            }
            if (this.side == 'b' && !IsSelected) {
                PieceLabel.setIcon(b);
            }
            if (this.side == 'b' && IsSelected) {
                PieceLabel.setIcon(bs);
            }
        }

        PieceLabel.setSize(75, 75);
        PieceLabel.setLocation(Coordinate.reverse_calculateX(j + 1) -10, Coordinate.reverse_calculateY(i + 1) - 17);
        return PieceLabel;
    }

}


