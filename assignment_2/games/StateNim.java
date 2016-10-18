import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StateNim extends State {

    public char[] board;
    public int boardRows;
    public int coinsLeft;
    public int removedCoinsCount;

    public enum stringOption { HELP, REGULAR }

    public StateNim(int numCoins) {

        coinsLeft = numCoins;
        boardRows = (int) Math.sqrt(numCoins);
        int div = numCoins / boardRows;
        if (div != boardRows) {
            boardRows += 1;
        }

        board = new char[boardRows * boardRows];
        int emptyCoins = board.length - numCoins;
        for (int i = boardRows - 1; i >= 0; i--) {
            for (int j = boardRows - 1; j >= 0; j--) {
                if (emptyCoins != 0) {
                    board[i * boardRows + j] = ' ';
                    emptyCoins--;
                } else {
                    board[i * boardRows + j] = 'O';
                }
            }
        }
        player = 1;
        removedCoinsCount = 0;
    }

    public StateNim(StateNim state) {
        this.coinsLeft = state.coinsLeft;
        this.boardRows = state.boardRows;
        this.removedCoinsCount = state.removedCoinsCount;

        this.board = new char[boardRows * boardRows];
        for(int i=0; i < state.board.length; i++) {
            this.board[i] = state.board[i];
        }
        player = state.player;
    }

    public boolean isValidMove(String [] moves) {
        //check correct input length
        if (moves.length > 3 || moves.length == 0) {
            return false;
        }

        //check for duplicates
        Set<String> uniqueMoves = new HashSet<>(Arrays.asList(moves));
        if (uniqueMoves.size() != moves.length) {
            return false;
        }

        //check for coins at locations
        for (int i = 0; i < moves.length; i++) {
            int index = Integer.parseInt(moves[i]) - 1;
            if (index < 0 || index > this.board.length || this.board[index] == ' ') {
                return false;
            }
        }
        return true;
    }

    public String toHelpString() {
        return toString(stringOption.HELP);
    }

    public String toString() {
        return toString(stringOption.REGULAR);
    }

    private String toString(stringOption option) {
        String ret = "";
        int maxDepth = (int) Math.sqrt(board.length);
        int currDepth = 1;
        int currElement = 0;
        int elementsPerRow = 1 + (2 * maxDepth);

        while (currDepth <= maxDepth) {
            int cointElementStart = (elementsPerRow / 2 + 1) - (1 * currDepth);
            for (int i = 0; i < elementsPerRow; i++) {
                if (i == cointElementStart) {
                    int numElements = 2 * (currDepth - 1) + 1;
                    while (numElements-- > 0) {
                        if (option == stringOption.HELP) {
                            if (board[currElement++] == ' ') {
                                ret += "  ";
                            } else {
                                ret += currElement + " ";
                            }

                        } else  if (option == stringOption.REGULAR) {
                            ret += board[currElement++] + " ";
                        }
                    }
                    break;
                } else {
                    ret += "  ";
                }
            }
            ret += "\n";
            currDepth++;
        }
        return ret;
    }
}
