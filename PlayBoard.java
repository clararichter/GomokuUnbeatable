import java.util.*;

public class PlayBoard {

   private char[][] squares;
   private int nbrSquares; //the square root of the number of squares in board


   /*
   Constructs a new playboard with "empty" squares
   @param the square root of number of squares of the playboard
   */
   public PlayBoard(int nbrSquares) {
      this.nbrSquares = nbrSquares;
      
      squares = new char[nbrSquares][nbrSquares];
      for(int i = 0; i<nbrSquares; i++) {
         for(int j = 0; j<nbrSquares; j++) {
            squares[i][j] = '-';
         }
      }
      
   }
   
   /*
   Constructs a new playboard with pre-determined squares
   @param the square root of number of squares of the playboard
   @param a two dimensional array holding the squares tokens
   */
   public PlayBoard(int nbrSquares, char[][] squares) {
      this.nbrSquares = nbrSquares;
      this.squares = squares;
   
   
   }
   
   /*
   @return the square root of the number of squares in the board
   */
   protected int getNbrSquares() {
      return nbrSquares;
   } 
   
   /*
   @return the state of the game, i.e. the squares.
   */
   public char[][] getState() {
      return squares;
   }
   
   /*
   @return the token of a specific square
   @param the position of the wanted square
   */      
   public char getTecken(int i, int j) {
      return squares[i][j];            
    }
   
   /*
   Sets the token of a specific square.
   @param i, j determines the position of the square to be set
   @param m the token of the square to be set
   */
   public void setTecken(int i, int j, char m) {
      squares[i-1][j-1] = m;
   
   }
   
   /*
   Determines whether the board is full, i.e. if there's been a draw.
   @return true if each square is not empty
   */
   public boolean draw() {
      for(int i = 0; i<nbrSquares; i++) {
         for(int j = 0; j<nbrSquares; j++) {
 
            if (squares[i][j] == '-') {
               return false; }           
         }       
      }

      return true; 
   }
   
   /*
   @return a two dimensional array of characters with two rows, each with one diagonal of the board
   */
   public char[][] getDiagonals() {
      char[][] diagonals = new char[2][nbrSquares];
      
      //first diagonal
      for(int i = 0; i<nbrSquares; i++) {
         diagonals[0][i] =  squares[i][i];
      }
      
      //second diagonal
      int j = nbrSquares;
      for(int i = 0; i<nbrSquares; i++) {
         j -= 1;
         diagonals[1][i] = squares[i][j]; }
      return diagonals;
   }
   
   /*
   @return a two dimensional array of characters, each with a column of the board
   */
   public char[][] getColumns(){
      char[][] columns = new char[nbrSquares][nbrSquares];
      
      for(int i = 0; i<nbrSquares; i++) {
         
         for(int j = 0; j<nbrSquares; j++) {
            
            columns[i][j] = squares[j][i];
         }
      }
      return columns;
   }

      
   /*
   @return true if a given row/column/diagonal is full with only one sort tokens, 
   i.e, a player has one.
   */
   private boolean checkLine(char[] c) {
      for(int i = 1; i<nbrSquares; i++) {
         if (c[i] != c[i-1] || c[i] == '-') {
            return false; }
         
      }
      return true;
   
   }
   
   /*
   @return true if a given row/column/diagonal is full with only some certain token,
   i.e, check if a player has one.
   */
   private boolean checkLine(char[] c, char token) {
      for(int i = 0; i<nbrSquares; i++) {
         if (c[i] != token) {
            return false; }
         
      }
      return true;
   
   }
    
   /*
   Checks to see if a player, given their sign (X or O) has won.
   @return true if the player has won
   */
   public boolean playerWin(char token) {
      
      //checking rows
      for(int i = 0; i<nbrSquares; i++) 
      {
         char[] row = squares[i];
         if(checkLine(row, token)) {return true; }          
      }
      
      //checking columns
      for(char[] column : getColumns())
      {
         if(checkLine(column, token)) {return true; }
      }

      //checking diagonals      
      for(char[] diagonal : getDiagonals())
      {
         if(checkLine(diagonal, token)) {return true; }
      }

      
      return false;
   }

   
   /*
   Checks to see if the board is at the end state where one of the two players has won
   @return true if someone has won
   */
   public boolean hasWon() {
      
      //checking rows
      for(int i = 0; i<nbrSquares; i++) 
      {
         char[] row = squares[i];
         if(checkLine(row)) {return true; }          
      }
      
      //checking columns
      for(char[] column : getColumns())
      {
         if(checkLine(column)) {return true; }
      }

      //checking diagonals      
      for(char[] diagonal : getDiagonals())
      {
         if(checkLine(diagonal)) {return true; }
      }

      
      return false;
   }
      
        
      
   /*
   @return true if board is at end state
   */
   public boolean terminal() {
      if (hasWon() || draw()) {
      return true; }
      
      else {
      return false;}
   
   }
   
   /*
   @return the heuristic token of the board and its current state for player with given token
   */
   public int heuristic(char maxToken, char minToken) {
      
      //heuristic of a board in which the player win is "positive infinity"      
      if (playerWin(maxToken)) {
         return Integer.MAX_VALUE;
         
      }
      
      
      //heuristic of board in which the player lose if "negative infinity"
      else if (playerWin(minToken)){
         return Integer.MIN_VALUE;
      }
      
      //a draw has heuristic 0
      else if (draw()) {
         return 0;
      }
      
      //a board which has not reached an end state is evaluated using the evaluation function
      else {return evaluateBoard(maxToken); }
            
   }
   
   /*
   This method is used with the evaluateBoard() function. It takes an array of chars as
   parameter (a row/column/diagonal) and calculates its favorability from the perspective 
   of a maximizing player. 
   See evaluateBoard() for further information about the evaluation function.
   */
   private int evaluateLine(char[] row, char maxToken){
      int result = 0;
      
      for(int i = 1; i<nbrSquares; i++) {
         //if it contains both players' tokens, the value is zero.         
         if(row[i] != row[i-1] && row[i] != '-' && row[i-1] != '-')
         {return 0;}
         
         else if(row[i] == row[i-1]) {
            //if it contaions maximizing player's token only, value is equal to the number the token appears
            if(row[i] == maxToken) {result++; }
            //if it contains the opponent's token only, the value is the number of these tokens, but negative
            else {result--; }
         
         
         }
      }
      return result;
   }
   
   
   /*
   Looks at each column, row and diagonal in board, calculates its value (see evaluateLine method)
   and adds it the final heuristic of board.
   @return the heuristic value of the board.
   */   
   public int evaluateBoard(char maxToken) {
      int result = 0;
      
      //rows
      for(int i = 0; i<nbrSquares; i++) {
         char[] row = squares[i];  
         result += evaluateLine(row, maxToken);
            
         
      }
      //columns
      for(char[] column : getColumns())
      {
         result += evaluateLine(column, maxToken);
      }

      //diagonals      
      for(char[] diagonal : getDiagonals())
      {
         result += evaluateLine(diagonal, maxToken);
      }
        
      return result;
   }
   
   
   /*
   Looks at the playboard and, for each empty square: sets its value to given token, 
   creates a PlayBoard object with the updated state and adds to list.
   @param the token to be placed on one of the squares
   @return a list of new possible gamestates in the form of PlayBoard objects
   */
   public ArrayList<PlayBoard> possibleNewStates(char token) {
      
      ArrayList<PlayBoard> newStates = new ArrayList<PlayBoard>();
      
      //for each square
      for(int i = 0; i<nbrSquares; i++) {
         for(int j = 0; j<nbrSquares; j++) {
            //set value to be given token
            if (squares[i][j] == '-') {
               squares[i][j] = token;
               
               //instanciate a new 2D array of squares with the updated square
               char[][] updatedSquares = new char[nbrSquares][nbrSquares];
               for(int k = 0; k<nbrSquares; k++) {
                  for(int m = 0; m<nbrSquares; m++) {
                      
                        updatedSquares[k][m] = squares[k][m]; 
                   }
               }
               
               //add a playboard to list with the updated squares               
               newStates.add(new PlayBoard(nbrSquares, updatedSquares));
               
               //reset the the updated square before going to the next empty square
               squares[i][j] = '-';
               
            }
         }
      }
                  
      return newStates;
   }
   
   /*
   The minimax algorithm implemented. Looks at the state of the PlayBoard and determines
   its favorability (from the perspective of given player). 
   This is done through a depth first seach. The method "creates" a game tree to look at the 
   possible states at given depth. For each of these, the heuristic value is returned and 
   each parent node is then determined by taking either the maximum (if maximizing player) 
   or the minimum (if !maximizingPlayer) of the child states.   
   */
   public int minimax(int depth, boolean maximizingPlayer, char maxToken, char minToken){
      if(depth == 0 || terminal())
      {
         return heuristic(maxToken, minToken);
      }
      
      if(maximizingPlayer) 
      {
         int bestValue = Integer.MIN_VALUE;
         for(PlayBoard childState : possibleNewStates(maxToken)){
            int v = childState.minimax(depth-1, false, maxToken, minToken);
            bestValue = Math.max(bestValue, v);
            
         }
         return bestValue;
      }
      
      else 
      {
         int bestValue = Integer.MAX_VALUE;
         for(PlayBoard childState : possibleNewStates(minToken)){
            int v = childState.minimax(depth-1, true, maxToken, minToken);
            bestValue = Math.min(bestValue, v);
            
         }
         return bestValue;
      }

   }
   
   /*
   @return a string representation of the board
   */   
   public String getGrid() {      
      
      char[] line = new char[nbrSquares*4+1];
      for(int i = 0; i<(nbrSquares*4+1) ; i++) {line[i] = '-';}
      String myLine = String.valueOf(line);
      
      String board = myLine + "\n";
      for(int i = 0; i<nbrSquares ; i++) {
         board = board.concat("| ");
         for(int j = 0; j<nbrSquares ; j++) {
            board = board.concat(squares[i][j] + " | ");
         }
         
         board = board.concat("\n" + myLine + "\n");
      }
      
      return board;
 
   }

   
   
   
}
