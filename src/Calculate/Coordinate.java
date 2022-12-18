package Calculate;

//将鼠标点击坐标换算成棋盘坐标
public class Coordinate {
    public static int calculateX(int i) {
        return (i - 100 - 20) / 75 + 1;
    }

    public static int calculateY(int j) {
        return (j - 50 - 20) / 75 + 1;
    }
    public static int reverse_calculateY(int j) {
        return (j - 1) *75 +50 +17;
    }
    public static int reverse_calculateX(int i) {

        return (i - 1) * 75 + 100 +17;
    }
}
