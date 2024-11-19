import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Arena {
    Scanner scanner = new Scanner(System.in);
    ArrayList<Unit> playerUnits = new ArrayList<Unit>();
    ArrayList<Unit> computerUnits = new ArrayList<Unit>();
    public Arena(){
        


        playerUnits.add(new Warrior());

        Random random = new Random();
        for (int i = 0; i < 6; i++){
            int randNumber = random.nextInt(7);
            if (randNumber == 0){
                playerUnits.add(new Warrior());
            }
            else if (randNumber == 1){
                playerUnits.add(new Archer());
            }

            else if (randNumber == 2){
                playerUnits.add(new Healer());
            }

            else if (randNumber == 3){
                playerUnits.add(new Rogue());
            }

            else if (randNumber == 4){
                playerUnits.add(new Wizard());
            }

            else if (randNumber == 5){
                playerUnits.add(new Bard());
            }

            else if (randNumber == 6){
                playerUnits.add(new Necromancer());
            }
        }

        computerUnits.add(new Warrior());
        for (int i = 0; i < 6; i++){
            int randNumber = random.nextInt(7);
            if (randNumber == 0){
                computerUnits.add(new Warrior());
            }
            else if (randNumber == 1){
                computerUnits.add(new Archer());
            }

            else if (randNumber == 2){
                computerUnits.add(new Healer());
            }

            else if (randNumber == 3){
                computerUnits.add(new Rogue());
            }

            else if (randNumber == 4){
                computerUnits.add(new Wizard());
            }

            else if (randNumber == 5){
                computerUnits.add(new Bard());
            }

            else if (randNumber == 6){
                computerUnits.add(new Necromancer());
            }
        }


        System.out.println();
    }
    public void battle(int playerIndex, int computerIndex){
        playerUnits.get(playerIndex).firstPhase(computerUnits.get(computerIndex), playerUnits, computerUnits);
        System.out.println("Player: " + playerUnits.get(playerIndex).actionString);
        computerUnits.get(computerIndex).firstPhase(playerUnits.get(playerIndex), computerUnits, playerUnits);
        System.out.println("Computer: " + computerUnits.get(computerIndex).actionString);
        if (playerUnits.get(playerIndex).isDead){
            
            System.out.println("Battle ended after phase 1.");
            System.out.println();
        }
        else if (computerUnits.get(computerIndex).isDead){
            
            System.out.println("Battle ended after phase 1.");
            System.out.println();
        }
        else{
            playerUnits.get(playerIndex).secondPhase(computerUnits.get(computerIndex), playerUnits, computerUnits);
            System.out.println("Player: " + playerUnits.get(playerIndex).actionString);
            computerUnits.get(computerIndex).secondPhase(playerUnits.get(playerIndex), computerUnits, playerUnits);
            System.out.println("Computer: " + computerUnits.get(computerIndex).actionString);
            if (playerUnits.get(playerIndex).isDead){
                System.out.println(playerUnits.get(playerIndex).name + " is dead now.");
                System.out.println("Battle ended after phase 2.");
                System.out.println();
            }
            else if (computerUnits.get(computerIndex).isDead){
                
                System.out.println("Battle ended after phase 2.");
                System.out.println();
            }
            else{
                playerUnits.get(playerIndex).thirdPhase(computerUnits.get(computerIndex), playerUnits, computerUnits);
                System.out.println("Player: " + playerUnits.get(playerIndex).actionString);
                computerUnits.get(computerIndex).thirdPhase(playerUnits.get(playerIndex), computerUnits, playerUnits);
                System.out.println("Computer: " + computerUnits.get(computerIndex).actionString);
                if (playerUnits.get(playerIndex).isDead){
                    
                    System.out.println("Battle ended after phase 3.");
                    System.out.println();
                }
                else if (computerUnits.get(computerIndex).isDead){
                    
                    System.out.println("Battle ended after phase 3.");
                    System.out.println();
                }
                else{
                    System.out.println("Battle ended after phase 3.");
                    System.out.println();
                }
            }
        }
    }
    public void printEnemies(){
        for (int i = 0; i < this.computerUnits.size(); i++){
            System.out.println(i + 1 + ". " + computerUnits.get(i).getInfo());
        }
    }
    public void printAllies(){
        for (int i = 0; i < playerUnits.size(); i++){
            System.out.println(i + 1 + ". " + playerUnits.get(i).getInfo());
        }
    }
}
