import java.util.Random;

/**
 * Player
 */
public class Player {

    String name;
    char playChar;
    int move = 0;
    int trap = 0;
    int totalMove;
    int dice;
    int vertLoc;
    int horLoc;

    Random random = new Random();

    public Player(String playerName, char playChar) {
        this.name = name;
        this.playChar = playChar;

    }

    public void rollDice(){
        this.dice = random.nextInt(6)+1;

    }
 /* 
    public void moveVert(){
        board.deletePlayers(gameBoard, this);
        this.horLoc = this.move + this.horLoc;
        board.movePlayers(gameBoard, this);
    }
*/
    
}