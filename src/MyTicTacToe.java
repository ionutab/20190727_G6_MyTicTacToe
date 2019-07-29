import java.util.Scanner;

public class MyTicTacToe {

    public static final char SYMBOL_X = 'X';
    public static final char SYMBOL_0 = '0';

    public static final int SIZE = 3;

    char[][] game;

    Player player1;
    Player player2;

    public MyTicTacToe(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.game = new char[SIZE][SIZE];
    }

    void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                Integer boardValue = SIZE * i + j + 1;
                game[i][j] = boardValue.toString().charAt(0);
            }
        }
    }

    void showBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(game[i][j] + " ");
            }
            System.out.println();
        }
    }

    Move readMove(Player player) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(player.name + " moves:");
        int moveIndex = scanner.nextInt();
        int line = (moveIndex - 1) / SIZE;
        int col = (moveIndex - 1) % SIZE;

        return new Move(line, col);
    }

    void makeMove(Move move, char symbol) {
        game[move.line][move.col] = symbol;
    }

    boolean isWinLine(Move move, char symbol) {
        boolean isWinLine = true;
        int j = 0;

        while (j < SIZE && isWinLine) {
            if (game[move.line][j] != symbol) {
                isWinLine = false;
            }
            j++;
        }

        return isWinLine;
    }

    boolean isWinCol(Move move, char symbol) {
        boolean isWinCol = true;
        int i = 0;
        while (i < SIZE && isWinCol) {
            if (game[i][move.col] != symbol) {
                isWinCol = false;
            }
            i++;
        }
        return isWinCol;
    }

    boolean testIsWin(Move move, char symbol) {
        boolean isWin = false;
        // test line
        isWin = isWinLine(move, symbol);
        // test col
        if (!isWin) {
            isWin = isWinCol(move, symbol);
        }
        if (!isWin && move.line == move.col) {
            // test diag1 if required
        }
        if(!isWin && (move.line + move.col + 1 == SIZE)){
            // test diag2 if required
        }
        return isWin;
    }

    public void playGame() {

        // initBoard()
        initBoard();
        showBoard();
        // currentPlayer = player1
        Player currentPlayer = player1;
        // testIsWin = false
        boolean isWin = false;
        // areMoves = true
        boolean areMoves = true;
        // nrMoves = 0
        int nrMoves = 0;
        // while not win and are moves
        while (!isWin && areMoves) {
            // read move
            Move move = null;
            boolean isValid = false;
            while(!isValid){
                move = readMove(currentPlayer);
                // validate move
                isValid = true;
//              isValid = validateMove(move);
            }
            // make move
            makeMove(move, currentPlayer.symbol);
            showBoard();
            // nrMoves++
            nrMoves++;
            // test number of moves
            if (nrMoves == SIZE * SIZE) {
                areMoves = false;
            }
            // test if it is win
            // - min 5 in case of size 3
            // - min 7 in case of size 4
            // - min 9 in case of size 5
            if (nrMoves >= SIZE + SIZE - 1) {
                isWin = testIsWin(move, currentPlayer.symbol);
            }

            // switch player ?
            if (!isWin) {
                if (currentPlayer == player1) {
                    currentPlayer = player2;
                } else {
                    currentPlayer = player1;
                }
            }
        }

        if (isWin) {
            System.out.println(currentPlayer.name + " IS WINNER !");
        } else {
            System.out.println("draw game");
        }

    }
}
