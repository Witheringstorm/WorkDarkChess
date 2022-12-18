package EventDealer;

import Pieces.*;

public class Interact {
    public static void interact(Piece a, int i1, int j1, Piece b, int i2, int j2) {
        if (a.side != b.side) {
            if ((a.type >= b.type || a.type * b.type == 0)) {
                if (a.type != 6 && b.type != 1) {
                    capture(a, b);
                }
            }
        }
    }

    public static void capture(Piece a, Piece b) {

    }

}
