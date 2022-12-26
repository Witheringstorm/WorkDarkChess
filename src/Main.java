import SaveAndLoad.Save;
import view.ChessGameFrame;
import Pieces.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Information_of_Location.initialize();
            ChessGameFrame g = new ChessGameFrame();
            g.setVisible(true);
            Save.writeRecord();
        });
    }
}
