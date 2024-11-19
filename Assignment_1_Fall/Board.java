public class Board {
    int width;
    int height;

    public Board(int widthA, int heightA) {
        this.width = (widthA * 3) + 1;
        this.height = (heightA * 3) + 1;
    }

    public char[][] createBoard() {
        char[][] board = new char[this.height][this.width];

        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                // Check if it's a border cell or a space inside
                if (((double)i / 3 > 1 && j < this.width-4) && ((double)j / 3 > 1 && i < this.height-4)){
                    board[i][j] = ' ';
                }
                else if (i % 3 == 0 || j % 3 == 0) {
                    board[i][j] = '#';
                } else {
                    board[i][j] = ' ';
                }
            }
        }

        return board;
    }

    public void drawBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println(); // Newline after each row
        }
    }

    public void addPlayers(char[][] board, Player[] players) {
        int playerCount = Math.min(players.length, 4); // Limit to 4 players
        
        //Check here.
        int k = 0;
        
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    if (k < playerCount){
                        board[i + 1][j + 1] = players[k].playChar;
                        players[k].horLoc = j+1;
                        players[k].vertLoc = i+1;

                        k++;
                    }
                    
                    
                }
            }

            
            
        
    }

    public void deletePlayers(char[][] board, Player player){
        //Before movement it deletes player's char on board.
        board[player.vertLoc][player.horLoc] = ' ';
    }

    public void movePlayers(char[][] board, Player player){
        //Hareket ettirme kodu.
        board[player.vertLoc][player.horLoc] = player.playChar;
    }
}
