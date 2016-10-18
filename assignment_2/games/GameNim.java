import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class GameNim  extends Game{

    private int WinningScore = 10;
    private int LosingScore = -10;
    private int NeutralScore = 0;

    public GameNim(int numCoins) {
        currentState = new StateNim(numCoins);
        StateNim state = (StateNim) currentState;
        System.out.println(state.toString());
    }

    public boolean isWinState(State state) {
        StateNim tState = (StateNim) state;
        if (tState.coinsLeft == 1) {
           return true;
        }
        return false;
    }

    public boolean isStuckState(State state) {

        if (isWinState(state))
            return false;

        StateNim tState = (StateNim) state;

        for (int i = 0; i < tState.board.length; i++)
            if (tState.board[i] == 'O')
                return false;

        return true;
    }

    public Set<State> getSuccessors(State state) {
        if(isWinState(state) || isStuckState(state))
            return null;

        Set<State> successors = new HashSet<>();
        StateNim tstate = (StateNim) state;

        //States include: take away 1, 2, or 3 coins
        int numCoins = 1;
        int nextCoins[] = findNextCoinIndices(tstate.board);
        while (numCoins <= 3)
        {
            StateNim successor_state = new StateNim(tstate);
            for (int i = 0; i < numCoins; i++){
                successor_state.board[nextCoins[i]] = ' ';
                successor_state.coinsLeft -= 1;
                successor_state.player = (state.player == 0 ? 1 : 0);
            }
            successor_state.removedCoinsCount = numCoins;
            successors.add(successor_state);
            numCoins++;
        }
        return successors;
    }

    public double eval(State state) {
        if(isWinState(state)) {
            //player who made last move
            int previous_player = (state.player == 0 ? 1 : 0);

            if (previous_player == 0) {
                //computer wins
                return WinningScore;
            } else {
                //human wins
                return LosingScore;
            }
        }
        return NeutralScore;
    }

    private int[] findNextCoinIndices(char [] board) {
        int curr_index = 0;
        int indices[] = new int[3];
        indices[0] = -1;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == 'O') {
                indices[curr_index++] = i;
                if(curr_index == 3){
                    return indices;
                }
            }
        }
        if (indices[0] >= 0) {
            return indices;
        }
        return null;
    }

    private static boolean isNumeric(String s) {
        return s.matches("\\+?\\d+");
    }

    public static void main(String [] args) throws IOException {

        System.out.println("*==========================================*\n" +
                "|             Welcome To Nim!              |\n" +
                "*==========================================*");
        System.out.println("\nFor current valid moves enter: 'h'");
        System.out.print("\nEnter the number of coins to play with > ");

        //needed to get players input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        //Get the number of coins to play Nim with
        String numCoins = in.readLine();
        while (!isNumeric(numCoins) || Integer.parseInt(numCoins) < 9) {
            System.out.println("Invalid number of coins, at least 9 coins are needed to play:");
            System.out.print("\nEnter the number of coins to play with > ");
            numCoins = in.readLine();
        }

        //Initialise the game
        GameNim game = new GameNim(Integer.parseInt(numCoins));
        Search search = new Search(game);
        int depth = 12;


        //Time to Play
        while (true) {
            StateNim nextState = null;
            switch ( game.currentState.player ) {
                case 0: //computers turn
                    nextState = (StateNim) search.bestSuccessorState(depth);
                    nextState.player = 0;
                    System.out.println("Computer:");
                    System.out.println("Removed: " + nextState.removedCoinsCount + " coins");
                    System.out.println("Coins left: " + nextState.coinsLeft + "\n" + nextState + "\n");
                    break;
                case 1: //Humans turn
                    System.out.print("Enter your move (help > h) > ");

                    //Verify correct move input
                    String[] input;
                    do {
                        input = in.readLine().split(" ");
                        StateNim tstate = (StateNim) game.currentState;
                        if (input[0].equals("h")){
                            System.out.println(tstate.toHelpString());
                            System.out.print("Valid next move parameters. Pick 1, 2, or 3 indices > ");
                            continue;
                        } else if (!tstate.isValidMove(input)) {
                            System.out.print("Invalid move. Pick 1, 2, or 3 indices > ");
                            input[0] = "h";
                            continue;
                        }
                    } while(input[0].equals("h"));

                    //update next state
                    nextState = new StateNim((StateNim) game.currentState);
                    nextState.player = 1;
                    nextState.removedCoinsCount = input.length;
                    for (int i = 0; i < input.length; i++) {
                        nextState.board[Integer.parseInt(input[i]) - 1] = ' ';
                        nextState.coinsLeft -= 1;
                    }
                    System.out.println("\nHuman:");
                    System.out.println("Removed: " + nextState.removedCoinsCount + " coins");
                    System.out.println("Coins left: " + nextState.coinsLeft + "\n" + nextState + "\n");
                    break;
            }

            //set current state
            game.currentState = nextState;
            //change player
            game.currentState.player = (game.currentState.player == 0 ? 1 : 0);

            //Who wins?
            if (game.isWinState(game.currentState) ) {

                if (game.currentState.player == 1) //i.e. last move was by the computer
                    System.out.println("Human Player is forced to take the last coin.\n\nComputer wins!");
                else
                    System.out.println("Computer is forced to take the last coin.\n\nYou win!");
                break;
            }

            if ( game.isStuckState(game.currentState) ) {
                System.out.println("Game failed.");
                break;
            }
        }
    }
}
