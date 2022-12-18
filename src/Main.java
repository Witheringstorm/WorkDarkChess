import view.ChessGameFrame;
import Pieces.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Information_of_Location play = new Information_of_Location();
            play.initialize();
            ChessGameFrame g = new ChessGameFrame();
            g.setVisible(true);
        });
    }
}
