package game;

public class CompLogic extends Logic {

    int recurCount = 0;

    int nextMove;

    public CompLogic(Board b) {
        this.b = b;
    }

    @Override
    public void clickOnCell(int row, int column) {
        if (state != RUNNING) {
            resetGame();
            b.renderBoard(state);
        } else if (isEmpty(row, column)) {
            addToBoard(row, column);
            state = gameOverCondition();
            b.renderBoard(state);
            if (state == RUNNING) {
                switchToComp();
            }
        }
        System.out.println(recurCount);
    }

    private void switchToComp() {
        switchPlayer();
        this.nextMove = minimax(0, player, board).getNextMove();
        int row = nextMove/3;
        int col = nextMove - row*3;
        addToBoard(row, col);
        state = gameOverCondition();
        b.renderBoard(state);
        switchPlayer();
    }


    // minimax algorithm here
    private Node minimax(int depth, int player, int[] board) {
        recurCount += 1;
        int gameState = checkGameState(board);
        if (gameState == TIE) {
            return new Node(0);
        } else if (gameState != RUNNING) {
            return new Node(gameState);
        }
        if (player == PLAYER_X) {
            Node node = new Node(Integer.MIN_VALUE);
            // for all available move: play move and add child
            for (int i=0; i<9; i++) {
                if (board[i] == EMPTY) {
                    int[] newBoard = new int[9];
                    System.arraycopy(board, 0, newBoard, 0, 9);
                    newBoard[i] = player;
                    Node returnNode = minimax(depth +1, PLAYER_O, newBoard);
                    int returnScore = returnNode.getScore();
                    if (node.getScore() <= returnScore) {
                        node.setScore(returnScore);
                        node.setNextMove(i);
                    }
                }
            }
            return node;
        } else {
            Node node = new Node(Integer.MAX_VALUE);
            // for all available move: play move and add child
            for (int i=0; i<9; i++) {
                if (board[i] == EMPTY) {
                    int[] newBoard = new int[9];
                    System.arraycopy(board, 0, newBoard, 0, 9);
                    newBoard[i] = player;
                    Node returnNode = minimax(depth +1, PLAYER_X, newBoard);
                    int returnScore = returnNode.getScore();
                    if(node.getScore() >= returnScore) {
                        node.setScore(returnScore);
                        node.setNextMove(i);
                    }
                }
            }
            return node;
        }
    }

    private int checkGameState(int[] board) {
        if (checkPlayerWon(PLAYER_X, board)) {
            return PLAYER_X_WON;
        } else if (checkPlayerWon(PLAYER_O, board)) {
            return PLAYER_O_WON;
        } else if (noMove(board)) {
            return TIE;
        }
        return RUNNING;
    }

    private boolean noMove(int[] board) {
        for (int pos : board) {
            if (pos == EMPTY) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPlayerWon(int player, int[] board) {
        // TODO Auto-generated method stub
        int countRow = 0, countCol = 0;
        int diag1 = 0, diag2 = 0;
        for (int row=0; row<3; row++) {
            for (int col=0; col<3; col++) {
                if (board[row*3+col] == player) {
                    countRow += 1;
                }
                if (board[col*3+row] == player) {
                    countCol += 1;
                }
            }
            if (countRow == 3 || countCol == 3) {
                return true;
            } else {
                countRow = 0;
                countCol = 0;
            }
            if (board[row*4] == player) {
                diag1 += 1;
            }
            if (board[(row+1)*2] == player) {
                diag2 += 1;
            }
        }
        if (diag1 == 3 || diag2 == 3) {
            return true;
        }
        return false;
    }
}
