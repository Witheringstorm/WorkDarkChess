package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class EmptyChess extends Piece{
    public EmptyChess(char side) {
        type = -1;
        points = 0;
        this.side = side;
    }

    public JLabel visible(int i, int j) {
        System.out.println("visible!!!");
        if (this.IsReversal == false) {
            PieceLabel.setIcon(unreversed);
        } else {
            if (this.side == 'r' && IsSelected == false)
                PieceLabel.setIcon(red_advisor);

            if (this.side == 'r' && IsSelected == true) {
                PieceLabel.setIcon(red_advisor_select);
            }
        }

        PieceLabel.setSize(75, 75);
        PieceLabel.setLocation(Coordinate.reverse_calculateX(j + 1) -10, Coordinate.reverse_calculateY(i + 1) - 17);
        return PieceLabel;
    }
}
