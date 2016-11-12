import java.util.Scanner;


public class Player {
   private String name;
   private Scanner scan;
   private char tecken;

/** Skapar en spelare med namnet name
*/
   public Player(String name, char tecken) {
      
      scan = new Scanner(System.in);
      this.name = name;
      this.tecken = tecken;
   
   }
   
   public String getName() {
      return name;
   }
   public char getTecken() {
      return tecken;
   }


   

/** Gör ett drag från spelplanen board. Läses från tangentbordet
*/
   public void makeMove(PlayBoard board) {
      int row = scan.nextInt();
      int column = scan.nextInt();
      board.setTecken(row, column, tecken);
   }

}