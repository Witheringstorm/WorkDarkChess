package SaveAndLoad;

import EventDealer.ClickPieces;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static EventDealer.Interact.DeadPiece;
import static EventDealer.Interact.DeadPieces;
import static Pieces.Information_of_Location.chessboard;
import static SaveAndLoad.Save.record;
import static view.ChessGameFrame.clickTimes;

public class Undo {
    //撤销上一步操作（悔棋）
    String copy;

    public static void undo(int a) {
        clickTimes--;
        record.remove(record.size() - a);
        StringBuffer sb = new StringBuffer();
        for (String i : record) {
            sb.append(i);
        }
        Save.loadUndo(sb.toString());
    }
}


