/*
 * For any game, the stateT structure records the current state
 * of the game.  For tic-tac-toe, the main component of the
 * state record is the board, which is an array of characters
 * using 'X' for the first player, 'O' for the second, and ' '
 * for empty squares.  Although the board array is logically
 * a two-dimensional structure, it is stored as a linear array
 * so that its indices match the numbers used by the human
 * player to refer to the squares, as follows:
 *
 *        1 | 2 | 3
 *       ---+---+---
 *        4 | 5 | 6
 *       ---+---+---
 *        7 | 8 | 9
 *
 * Note that element 0 is not used, which requires allocation
 * of an extra element.
 *
 * In addition to the board array, the code stores a player
 * value to indicate whose turn it is.  
 */


public class StateTicTacToe extends State {
	
    public char board[] = new char [(3 * 3) + 1];
    
    public StateTicTacToe() {
    	
        for (int i=0; i<10; i++) 
            board[i] = ' ';
        
        player = 1;
    }
    
    public StateTicTacToe(StateTicTacToe state) {
    	
        for(int i=0; i<10; i++)
            this.board[i] = state.board[i]; 
        
        player = state.player;
    }
    
    public String toString() {
    	
    	String ret = "";
    	
    	for(int i=0; i<3; i++) {
    		for(int j=0; j<3; j++) {
    			ret += board[1 + i*3+j] + ", ";
    		}
    		ret += "\n";
    	}
    	return ret;
    }
}
