import java.util.Scanner;


public class Player {
   private String name;
   private Scanner scan;
   private char token;

   public Player(String name, char token) {
      
      scan = new Scanner(System.in);
      this.name = name;
      this.token = token;
   
   }
   
   public String getName() {
      return name;
   }
   public char getTecken() {
      return token;
   }


   /* 
   Makes a move on the playboard board, read from keyboard.
   */
   public void makeMove(PlayBoard board) {
      int row = scan.nextInt();
      int column = scan.nextInt();
      board.setTecken(row, column, token);
   }

}