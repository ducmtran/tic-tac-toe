package game;

public class Logic {
	public static final int RUNNING = 0;
	public static final int PLAYER_X_WON = 1;
	public static final int PLAYER_O_WON = -1;
	public static final int TIE = 2;
	public static final int PLAYER_X = 1;
	public static final int PLAYER_O = 2;
	public static final int EMPTY = 0;

	int[] board = new int[] {	EMPTY,EMPTY,EMPTY,
								EMPTY,EMPTY,EMPTY,
								EMPTY,EMPTY,EMPTY
	};
	int state = RUNNING;
	int player = PLAYER_X;
	int turnsPlayed = 0;
	Board b;

	public Logic() {

    }
	
	public Logic(Board b) {
		this.b = b;
	}
	public int getState() {
		return state;
	}
	public int getPlayer() {
		return player;
	}
	public int[] getBoard() {
		return board;
	}
	
	public void clickOnCell(int row, int column) {
		if (state != RUNNING) {
			resetGame();
			b.renderBoard(state);
		} else if (isEmpty(row, column)) {
			addToBoard(row, column);
			state = gameOverCondition();
			switchPlayer();
			b.renderBoard(state);
			someFunc();
		}
	}

	private int someFunc() {
		int a = 5;
		int b = a +5;
		return b;
	}

	public boolean isEmpty(int row, int column) {
		if (board[row*3+column] == EMPTY) {
			return true;
		}
		return false;
	}

	public void switchPlayer() {
		if (player == PLAYER_X) {
			player = PLAYER_O;
		} else {
			player = PLAYER_X;
		}
	}
	
	public int gameOverCondition() {
		if (checkPlayerWon(PLAYER_X)) {
			return PLAYER_X_WON;
		} else if (checkPlayerWon(PLAYER_O)) {
			return PLAYER_O_WON;
		} else if (turnsPlayed == 9) {
			return TIE;
		}
		return RUNNING;
	}

	private boolean checkPlayerWon(int player) {
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
		

	public void addToBoard(int row, int column) {
		// add X or O to board array
		board[row*3+column] = player;
		turnsPlayed += 1;
	}

	public void resetGame() {
		this.state = RUNNING;
		this.player = PLAYER_X;
		for (int i=0; i<9; i++) {
			board[i] = EMPTY;
		}
		turnsPlayed = 0;
	}
}
