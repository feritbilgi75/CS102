import java.util.Random;

public class Trap {
    int vertTrap;
    int horTrap;

    int index1;
    int index2;
    int index3;
    int index4;

    int indexOfPlayer;

    public Trap(Board board){
        Random random = new Random();

        int vertTrap = random.nextInt((board.height-1)/3);
        
        int horTrap = random.nextInt((board.width-1)/3);

        //if koy.
        while (true){
            if ((vertTrap == 0 || vertTrap == (board.height-1)/3) && horTrap == 0 || horTrap == (board.width-1)/3){
                vertTrap = random.nextInt((board.height-1)/3);
        
                horTrap = random.nextInt((board.width-1)/3);
        }
            else{
                break;
            }
        
        }
        


        index1 = horTrap * 3 + 1;
        index2 = horTrap * 3 + 2;
        index3 = vertTrap * 3 + 1;
        index4 = vertTrap * 3 + 2;

    }
    public void detectTrap(Player player, Player[] players){
        if ((player.horLoc == index1 || player.horLoc == index2) && (player.vertLoc == index3 || player.vertLoc == index4)){
            
            for (int i = 0; i < players.length; i++){
                if (players[i].playChar == player.playChar){
                    indexOfPlayer = i;
                    break;
                }
            }
            if (indexOfPlayer == 1){
                player.horLoc = 1;
                player.vertLoc = 1;
            }
            else if (indexOfPlayer == 2){
                player.horLoc = 1;
                player.vertLoc = 2;
            }
            else if (indexOfPlayer == 3){
                player.horLoc = 2;
                player.vertLoc = 1;
            }
            else{
                player.horLoc = 2;
                player.vertLoc = 1;
            }
        }
        System.out.println("You moved into a trap!"); 
        System.out.println("You moved back to the closest corner!");
        
    }

    public void showTrap(Board board){
        int trapCell = 0;
        if (vertTrap == 0){
            trapCell = horTrap;
        }
        else{
            if (horTrap == (board.width-1)/3){
                trapCell = horTrap + vertTrap;
            }
            else if (vertTrap == (board.height-1)/3){
                trapCell = (board.height-1)/3 + (board.width-1)/3 + ((board.width-1)/3 - horTrap) - 1;
            }
            else{
                trapCell = (board.height-1)/3 + (board.width-1)/3 - 1 + ((board.width-1)/3 - 1) + ((board.height-1)/3 - vertTrap) - 1;
            }
        }
    }

    
  

    
}
