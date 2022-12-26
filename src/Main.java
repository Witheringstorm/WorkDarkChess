import Pieces.Information_of_Location;
import SaveAndLoad.Save;
import view.ChessGameFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Information_of_Location.initialize();
            ChessGameFrame g = new ChessGameFrame();
            g.setVisible(true);
            Save.writeRecord();


            //背景音乐启动
//            Music audioPlayWave = new Music("中国象棋.wav");// 开音乐 音樂名
//            audioPlayWave.start();

        });
    }
}
