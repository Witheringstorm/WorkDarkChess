package Pieces;


import Calculate.Coordinate;

import javax.swing.*;

public class GeneralChess extends Piece {
        //棋子： 将


    public static ImageIcon r = new ImageIcon("image/r_general.png");
    public static ImageIcon b = new ImageIcon("image/b_general.png");
    ImageIcon rs = new ImageIcon("image/r_general_s.png");
    ImageIcon bs = new ImageIcon("image/b_general_s.png");


    public GeneralChess(char side) {
        type = 6;
        points = 30;
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
