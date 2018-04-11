package game;

import java.util.ArrayList;

public class Node {
    private Integer score;
    private int nextMove;

    public Node(int value) {
        this.score = value;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public int getNextMove() {
        return nextMove;
    }

    public void setNextMove(int nextMove) {
        this.nextMove = nextMove;
    }
}
