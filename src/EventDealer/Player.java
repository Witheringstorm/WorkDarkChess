package EventDealer;

import javax.swing.*;

public class Player {
    public static int click_times = 0;
    public static char playerTurn = 'r';

    public static boolean isFirstClick() {
        if (click_times % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static char whichPlayer() {
        if (click_times % 4 == 0) {
            if (playerTurn == 'r') {
                return 'b';
            } else return 'r';
        } else {
            return playerTurn;
        }
    }
}
