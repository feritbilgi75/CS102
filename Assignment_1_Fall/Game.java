import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        boolean continueGame = true;
        Scanner scanner = new Scanner(System.in);
        int boardWidth;
        int boardHeight;

        Random random = new Random();

        while (continueGame){



            System.out.println("");
            System.out.println("Welcome to the board game.");
            System.out.println("Please enter board width: ");
           
            boardWidth = scanner.nextInt();

            System.out.println("Please enter board height: ");

            boardHeight = scanner.nextInt();
            //Board için class yaz.

            Board board = new Board(boardWidth, boardHeight);
            char[][] gameBoard = board.createBoard();
            

            System.out.println("How many players (2-4): ");
            int playNumb = scanner.nextInt();
            Player players[] = new Player[playNumb];
            scanner.nextLine();

            for (int i = 0; i < playNumb; i++){
                int playerNumber = i;
                String playerName = "player" + playerNumber++;
                System.out.println("For player " + playerNumber++ + ": ");
                char playerChar = scanner.nextLine().charAt(0);
                Player changePlayer = new Player(playerName,playerChar);
                players[i] = changePlayer;
            }
            System.out.println("Players are rolling dice");
            int playerNumber = 0;
            
            for (int i = 0; i < players.length; i++){
                players[i].rollDice();                
            }
                
            for (int i = 0; i < players.length; i++){
                
                if (i == players.length-1){
                    System.out.print(players[i].playChar + ": " + players[i].dice);

                }
                else{
                    System.out.print(players[i].playChar + ": " + players[i].dice + ", ");
                }
            }
            System.out.println("");
            //Breaking tie kodu gelecek.

            for (int i = 0; i < players.length; i++){
                for (int j = i; j < players.length; j++){
                    if (players[i].dice == players[j].dice && i != j){
                        System.out.println("Breaking tie for: " + players[i].playChar + " " + players[j].playChar);
                    }
                }
            }




            for (int i = 0; i < players.length; i++){
                for (int j = 0; j < players.length; j++){
                    //Bubble Sort algorithm 
                    if (players[i].dice < players[j].dice){
                        Player changePlayer = new Player(players[j].name,players[j].playChar);
                        players[j] = players[i];
                        players[i] = changePlayer;
                        //Sort according to dice.
                        //Write breaking tie code later.
                    }

                }
            }
            
            
            


            
            System.out.print("Playing order is: ");
            for (int i = 0; i < players.length; i++){
                System.out.print(players[i].playChar + " ");
            }
            System.out.println(" ");
            System.out.println(" ");

            
            board.addPlayers(gameBoard, players);
            board.drawBoard(gameBoard);

            while (true){
                Trap trap = new Trap(board);
                for (int i = 0; i < players.length; i++){
                    System.out.println(" ");
                    players[i].rollDice();
                    System.out.print("Player " + players[i].playChar + " rolls " + players[i].dice + ", how many cells you move? (0-" + players[i].dice + "): ");
                    players[i].move = scanner.nextInt();
                    players[i].totalMove += players[i].move;
                    scanner.nextLine();
                    players[i].move = players[i].move *3;

                    players[i].totalMove += players[i].move;

                    //Konuma göre gidiliyor.
                    //İlk satırda olması




                    if (players[i].move + players[i].horLoc < board.width-3 && players[i].vertLoc < 3){
                        board.deletePlayers(gameBoard, players[i]);
                        players[i].horLoc = players[i].move + players[i].horLoc;
                        trap.detectTrap(players[i], players);
                        board.movePlayers(gameBoard, players[i]);
                        
                    }
                    //Konuma göre hareketin genişlikten büyük olması
                    else if (players[i].move + players[i].horLoc >= board.width-3){
                        //Look there.
                        int moved = players[i].horLoc;
                        int move = players[i].move;
                        int moveVert = players[i].move + players[i].horLoc - board.width - 3;

                        //Genişlikten büyük yükseklikten küçük.
                        if (players[i].vertLoc < board.height-3 && (players[i].vertLoc + move - (board.width - 3 - players[i].horLoc)) < (board.width -3)){
                            

                            board.deletePlayers(gameBoard, players[i]);
                            //Düzeltme gerekli
                            
                            players[i].horLoc = board.width - 4 + (players[i].horLoc%3);
                            moved = players[i].horLoc - moved;

                            
                            move = move - moved;
                           
                            players[i].vertLoc = move + players[i].vertLoc;
                            trap.detectTrap(players[i], players);
                            board.movePlayers(gameBoard, players[i]);
                    }
                        else{

                            
                            if (players[i].move > board.width-3 && players[i].horLoc > 2){
                                board.deletePlayers(gameBoard, players[i]);

                                players[i].horLoc = (players[i].horLoc%3);
                                moved = moved - players[i].horLoc  ;
                                move = move - moved;

                                players[i].vertLoc -= (board.height - 4);
                                moved = players[i].vertLoc - moved;

                            
                                move = move - moved;


                                players[i].horLoc = players[i].horLoc - move;
                                trap.detectTrap(players[i], players);
                                board.movePlayers(gameBoard, players[i]);
                            }
                            else{

                                //Köşesel hareketler bu ve benzelerine giriyor.
                                board.deletePlayers(gameBoard, players[i]);
                                players[i].horLoc = players[i].horLoc - players[i].move;
                                trap.detectTrap(players[i], players);
                                board.movePlayers(gameBoard, players[i]);
                            }

                            
                        }
                    }
                    else{
                        board.deletePlayers(gameBoard, players[i]);
                        players[i].vertLoc = players[i].vertLoc - players[i].move;
                        //Bu ve bunun gibi yerler için board'a move fonksiyonu yaz.
                        trap.detectTrap(players[i], players);
                        board.movePlayers(gameBoard, players[i]);
                        
                    }



                    //Önceden nereleri ne kadar gitmesini gerektiğini hesaplat sonra oynat.

                    board.drawBoard(gameBoard);
                    






                    if (players[i].totalMove == (boardWidth*2) + (boardHeight-2)*2){
                        System.out.println("Winner is " + players[i].playChar + ", congratulations!");
                        System.out.println(" ");
                        //Player move trap grafiği yazılacak.



                        System.out.println(" ");
                        System.out.println("Play again? (Y/N): ");
                        char gameCont = scanner.nextLine().charAt(0);
                        if (gameCont == 'n' || gameCont == 'N'){
                            continueGame = false;
                            break;
                        }
                        else if (gameCont == 'y' || gameCont == 'Y'){
                            break;
                        }

                    }
                }
            }


            
            




        }
    }
}
