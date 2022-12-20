package Pieces;

import Calculate.Coordinate;

import javax.swing.*;

public class HorseChess extends Piece{

    public static ImageIcon r = new ImageIcon("image/r_horse.png");
    ImageIcon rs = new ImageIcon("image/r_horse_s.png");
    public static ImageIcon b = new ImageIcon("image/b_horse.png");
    ImageIcon bs = new ImageIcon("image/b_horse_s.png");
    public HorseChess(char side) {
        type = 2;
        points = 5;
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
