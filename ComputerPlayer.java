import java.util.ArrayList;


public class ComputerPlayer extends Player {
   private String name;
   private char token = 'O';
   private char opponentToken;

   public ComputerPlayer(String name, char token) {
      super(name, token);
      if(token == 'O') {opponentToken = 'X'; }
      else {opponentToken = 'O';}
   }
   
   /*
   @override
   The player makes the move that maximizes the minimum value of the position resulting 
   from the opponent's possible following moves.
   */
   public PlayBoard makeMove(PlayBoard board, int depth) {
      
      PlayBoard copy = new PlayBoard(board.getNbrSquares(), board.getState());
      
      int bestValue = Integer.MIN_VALUE;
      PlayBoard newState = new PlayBoard(board.getNbrSquares());
      
      for(PlayBoard childState : board.possibleNewStates(token)){
         
         int heuristic = childState.minimax(depth, false, token, opponentToken);
         if(heuristic > bestValue) 
         {
            bestValue = heuristic;
            newState = childState; 
         }
      }
      
      return newState;
      
      
   }

   
}