import java.util.ArrayList;


public class ComputerPlayer extends Player {
   private String name;
   private char tecken;// = 'O';

   
   
   /** Skapar en spelare med namnet name
   */
   public ComputerPlayer(String name, char tecken) {
      super(name, tecken);

   }
   
   public PlayBoard makeMove(PlayBoard board, int depth) {
      
      PlayBoard copy = new PlayBoard(board.getNbrSquares(), board.getState());
      
      int bestValue = Integer.MIN_VALUE;
      PlayBoard newState = new PlayBoard(board.getNbrSquares());
      
      for(PlayBoard childState : board.possibleNewStates('O')){
         
         int heuristic = childState.minimax(depth, false);
         if(heuristic > bestValue) 
         {
            bestValue = heuristic;
            newState = childState; 
         }
      }
      
      return newState;
      
      
   }

   
}