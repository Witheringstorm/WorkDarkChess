package EventDealer;

public class Player {
    boolean firstClick = true;
    static int click_times = 0;
    public static char playerTurn;
    public static boolean isFirstClick(){
        if(click_times % 2 == 0){
            return true;
        }
        else{
            return false;
        }
    }
    public static char whichPlayer(){
        if(click_times % 4 == 0){
            return playerTurn;
        }
        else{
            if(playerTurn == 'r'){
                return 'b';
            }else return 'r';
        }
    }
}
