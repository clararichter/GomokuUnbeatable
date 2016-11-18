public class Controller {
   
/* 
Instantiates a PlayBoard object and two players. 
Executes a match.
*/
   public static void main(String[] args) {
      
      //determines the size of the board
      int nbr_rows = 5;
      
      PlayBoard board = new PlayBoard(nbr_rows);
      Player playerX = new Player("Human", 'X');
      ComputerPlayer playerO = new ComputerPlayer("Intelligent Computer", 'O');
      
      Player current = playerO;
      
      do {         
         if (current == playerX) 
         {
            current = playerO; 
         }
         else 
         {
            current = playerX; 
         }
         
         System.out.println(board.getGrid());
         
         if(current == playerX) { System.out.println(current.getName() + "'s turn: enter (row, column)"); }
         else { System.out.println(current.getName() + "'s turn:"); }
         
         if (current == playerO) 
         {
            board = playerO.makeMove(board, 3);
         }
         else 
         {
            current.makeMove(board); 
         }

       
      }
      while (!board.playerWin(current.getTecken()) && !board.draw());
      
      System.out.println(board.getGrid());
      
      if(board.playerWin(current.getTecken())) {System.out.println(current.getName() + " WON!");}
      
      else {System.out.println("IT'S A TIE!");}

   }
}