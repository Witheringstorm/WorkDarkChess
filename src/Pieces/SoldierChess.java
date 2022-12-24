package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class SoldierChess extends Piece{


    ImageIcon rs = new ImageIcon("image/r_soldier_s.png");

    ImageIcon bs= new ImageIcon("image/b_soldier_s.png");
    public SoldierChess(char side) {
        type = 1;
        points = 1;
        this.side = side;
        r = new ImageIcon("image/r_soldier.png");
        b = new ImageIcon("image/b_soldier.png");
    }

    public JLabel visible() {
        System.out.println("visible!!!");
        if(this.alive == false){
            PieceLabel.setIcon(dead);
        }
        else if (this.IsReversal == false) {
            PieceLabel.setIcon(unreversed);
        }else {
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
        PieceLabel.setLocation(Coordinate.reverse_calculateX(x + 1) -10, Coordinate.reverse_calculateY(y + 1) - 17);
        return PieceLabel;
    }
}
