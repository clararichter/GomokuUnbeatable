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
   @param a two dimensional array holding the squares values
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
   @return the value of a specific square
   @param the position of the wanted square
   */      
   public char getTecken(int i, int j) {
      return squares[i][j];            
    }
   
   /*
   Sets the value of a specific square.
   @param i, j determines the position of the square to be set
   @param m the value of the square to be set
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
      
      
      for(int i = 0; i<nbrSquares; i++) {
         diagonals[0][i] =  squares[i][i];
      }
      
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
   @return true if a given row/column/diagonal has squares with the same value
   */
   private boolean checkLine(char[] c) {
      for(int i = 1; i<nbrSquares; i++) {
         if (c[i] != c[i-1] || c[i] == '-') {
            return false; }
         
      }
      return true;
   
   }
   
   /*
   @return true if a given row/column/diagonal has squares with only some certain value
   */
   private boolean checkLine(char[] c, char value) {
      for(int i = 0; i<nbrSquares; i++) {
         if (c[i] != value) {
            return false; }
         
      }
      return true;
   
   }
    
   private boolean stringCheck(String s) {
      for(int i = 1; i<nbrSquares; i++) {
         if (s.charAt(i) != s.charAt(i-1) || s.charAt(i) == '-') {
            return false;
         
         }
      
      
      }
      return true;
      
   }
   
   private static String repeatedChar(char c, int count){
       StringBuilder r = new StringBuilder();
       for (int i = 0; i < count; i++) {
           r.append(c);
       }
       return r.toString();
    }

   public boolean playerWin(char tecken) {
      
      //checking horizontal and vertical directions
      for(int i = 0; i<nbrSquares; i++) {
         String horizontal = "";
         String vertical = "";
         for(int j = 0; j<nbrSquares; j++) {
            horizontal += squares[i][j];
            vertical += squares[j][i];
            if (horizontal.equals(repeatedChar(tecken, nbrSquares)) || vertical.equals(repeatedChar(tecken, nbrSquares))) {
               return true; }    
         }
      }
      // checking diagonals
      String diagonal1 = "";
      String diagonal2 = "";
      
      for(int i = 0; i<nbrSquares; i++) {
         diagonal1 +=  squares[i][i];
      }
      
      int j = nbrSquares;
      for(int i = 0; i<nbrSquares; i++) {
         j -= 1;
         diagonal2 += squares[i][j]; }

      if (diagonal1.equals(repeatedChar(tecken, nbrSquares)) || diagonal2.equals(repeatedChar(tecken, nbrSquares))) {
         return true; }

      return false;
   }

   
   
   public boolean hasWon() {
      
      //checking horizontal and vertical directions
      for(int i = 0; i<nbrSquares; i++) {
         String horizontal = "";
         String vertical = "";
         for(int j = 0; j<nbrSquares; j++) {
            horizontal += squares[i][j];
            vertical += squares[j][i];
            
            
         }
         if (stringCheck(horizontal) || stringCheck(vertical)) {
               return true; }    
         
      }
      // checking diagonals
      String diagonal1 = "";
      String diagonal2 = "";
      
      for(int i = 0; i<nbrSquares; i++) {
         diagonal1 +=  squares[i][i];
      }
      
      int j = nbrSquares;
      for(int i = 0; i<nbrSquares; i++) {
         j -= 1;
         diagonal2 += squares[i][j]; }

      if (stringCheck(diagonal1) || stringCheck(diagonal2)) {
         return true; }

      return false;
   }
  
   public String getGrid() {
      //String grid = String.format("---------------------\n  %s | %s | %s | %s | %s  \n---------------------\n  %s | %s | %s | %s | %s  \n---------------------\n  %s | %s | %s | %s | %s \n---------------------\n  %s | %s | %s | %s | %s \n---------------------\n  %s | %s | %s | %s | %s \n---------------------", squares[0][0], squares[0][1], squares[0][2], squares[0][3], squares[0][4], squares[1][0], squares[1][1], squares[1][2], squares[1][3], squares[1][4], squares[2][0], squares[2][1], squares[2][2], squares[2][3], squares[2][4], squares[3][0], squares[3][1], squares[3][2], squares[3][3], squares[3][4], squares[4][0], squares[4][1], squares[4][2], squares[4][3], squares[4][4]);       
      
      String grid = String.format("-------------\n  %s | %s | %s  \n------------- \n  %s | %s | %s  \n-------------\n  %s | %s | %s \n-------------", squares[0][0], squares[0][1], squares[0][2], squares[1][0], squares[1][1], squares[1][2], squares[2][0], squares[2][1], squares[2][2]);      
            
      return grid;
   
   }
   
   
   public boolean terminal() {
      if (hasWon() || draw()) {
      return true; }
      
      else {
      return false;}
   
   }
   
   public int heuristic() {
            
      if (playerWin('O')) {
         return Integer.MAX_VALUE;
         
      }
      
      else if (draw()) {
         return 0;
      }
      
      else if (playerWin('X')){
         return Integer.MIN_VALUE;
      }
      
      else {return evaluateBoard(); }
            
   }
   
   private int evaluateLine(char[] row){
      int result = 0;
      
      for(int i = 1; i<nbrSquares; i++) {
                  
         if(row[i] != row[i-1] && row[i] != '-' && row[i-1] != '-')
         {return 0;}
         
         else if(row[i] == row[i-1]) {
            if(row[i] == 'O') {result++; }
            
            else {result--; }
         
         
         }
      }
      return result;
   }
   
   //for each row, diagonal and column:
      /*
      if more than one characters is in it, add 0 
      for every one with only O's in it, add the number to result
      for every one with only X's in it, subtract the number from result
      */

      
   public int evaluateBoard() {
      int result = 0;
      
      for(int i = 0; i<nbrSquares; i++) {
         char[] row = squares[i];  
         result += evaluateLine(row);
            
         
      }
      
      for(char[] column : getColumns())
      {
         result += evaluateLine(column);
      }

            
      for(char[] diagonal : getDiagonals())
      {
         result += evaluateLine(diagonal);
      }
        
      return result;
   }
   
   
   public ArrayList<PlayBoard> possibleNewStates(char tecken) {
      
      ArrayList<PlayBoard> newStates = new ArrayList<PlayBoard>();
      
      for(int i = 0; i<nbrSquares; i++) {
         for(int j = 0; j<nbrSquares; j++) {
            if (squares[i][j] == '-') {
               squares[i][j] = tecken;
               
               char[][] updatedSquares = new char[nbrSquares][nbrSquares];
               for(int k = 0; k<nbrSquares; k++) {
                  for(int m = 0; m<nbrSquares; m++) {
                      
                        updatedSquares[k][m] = squares[k][m]; 
                   }
               }
                              
               newStates.add(new PlayBoard(nbrSquares, updatedSquares));
               
               squares[i][j] = '-';
               
            }
         }
      }
                  
      return newStates;
   }
   
   public int minimax(int depth, boolean maximizingPlayer){
      if(depth == 0 || terminal())
      {
         return heuristic();
      }
      
      if(maximizingPlayer) 
      {
         int bestValue = Integer.MIN_VALUE;
         for(PlayBoard childState : possibleNewStates('O')){
            int v = childState.minimax(depth-1, false);
            bestValue = max(bestValue, v);
            
         }
         return bestValue;
      }
      
      else 
      {
         int bestValue = Integer.MAX_VALUE;
         for(PlayBoard childState : possibleNewStates('X')){
            int v = childState.minimax(depth-1, true);
            bestValue = min(bestValue, v);
            
         }
         return bestValue;
      }
      
      
   
   }
   
   public int max(int first, int second){
      if(first >= second) {return first;}
      else {return second;}
   }
   
   public int min(int first, int second){
      if(first <= second) {return first;}
      else {return second;}
   }

   
   
   
}
