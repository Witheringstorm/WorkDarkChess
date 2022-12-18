package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class CannonChess extends Piece{

    ImageIcon r= new ImageIcon("image/r_cannon.png");
    ImageIcon rs = new ImageIcon("image/r_cannon_s.png");
    ImageIcon b = new ImageIcon("image/b_cannon.png");
    ImageIcon bs = new ImageIcon("image/b_cannon_s.png");
    public CannonChess(char side) {
        type = 0;
        points = 5;
        this.side = side;
    }

    public JLabel visible(int i, int j) {
        System.out.println("visible!!!");
        if (this.IsReversal == false) {
            PieceLabel.setIcon(unreversed);
        } else {
            if (this.side == 'r' && IsSelected == false)
                PieceLabel.setIcon(r);

            if (this.side == 'r' && IsSelected == true) {
                PieceLabel.setIcon(rs);
            }
            if (this.side == 'b' && IsSelected == true){
                PieceLabel.setIcon(bs);
            }
            if (this.side == 'b' && IsSelected == false){
                PieceLabel.setIcon(b);
            }
        }

        PieceLabel.setSize(75, 75);
        PieceLabel.setLocation(Coordinate.reverse_calculateX(j + 1) -10, Coordinate.reverse_calculateY(i + 1) - 17);
        return PieceLabel;
    }
}
