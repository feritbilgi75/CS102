import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean work = true;
        int turnInt = 1;
        Arena arena = new Arena();
        while (work){
            System.out.println("Turn " + turnInt);
            System.out.println("Computer's units:");
            arena.printEnemies();
            System.out.println("");
            System.out.println("Player's units:");
            arena.printAllies();
            System.out.print("Which unit you chose: ");
            int playerChose = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            Random random = new Random();
            int computerChoose = 0;
            while (true){
                computerChoose = random.nextInt(arena.computerUnits.size());
                if (!arena.computerUnits.get(computerChoose).isDead){
                    break;
                }
            }
                if (arena.playerUnits.get(playerChose-1).isDead){
                    System.out.println("Player that you chose is dead.");
                    
                }
            
                else if (playerChose > arena.playerUnits.size() || playerChose < 0){
                     System.out.println("Please enter a variable number");
                }
            
                    else{
                        System.out.println("You choose " + arena.playerUnits.get(playerChose-1).name);
                        System.out.println("Computer choses " + arena.computerUnits.get(computerChoose).name);
                        arena.battle(playerChose-1, computerChoose);
                        int deathCountAlly = 0;
                        int deathCountComputer = 0;
                        for (int i = 0; i < arena.playerUnits.size(); i++){
                            if (arena.playerUnits.get(i).isDead){
                                deathCountAlly++;
                            }
                        }
                        for (int i = 0; i < arena.playerUnits.size(); i++){
                            if (arena.computerUnits.get(i).isDead){
                                deathCountComputer++;
                             }
                         }
                        if (deathCountAlly == arena.playerUnits.size()){
                            System.out.println("The computer wins the game.");
                            work = false;
                        }
                        else if (deathCountComputer == arena.computerUnits.size()){
                            System.out.println("The player wins the game.");
                            work = false;
                        }

                        else {
                            turnInt++;
                        }
            }


        }
    }
}
