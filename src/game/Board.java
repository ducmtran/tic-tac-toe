package game;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
// import javax.swing.*;
// import game.Constant;


public class Board extends JPanel {
	private static final long serialVersionUID = 1111115453L;
	
	public static final int WIDTH = 900;
	public static final int HEIGHT = 900;
	public static final int BOX_WIDTH = WIDTH/3;
	public static final int BOX_HEIGHT = HEIGHT/3;
	
	Color color;
	int x;
	int y;
	Logic l;
	int state;
	int player;

	
	public Board() {
		
	}
	
	public void logicHandler(Logic logic) {
		this.l = logic;
		this.state = l.getState();
		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				l.clickOnCell((int) e.getY()/BOX_HEIGHT, (int) e.getX()/BOX_WIDTH);
			}
		});
	}
	
	public void renderXY(Graphics g) {
		// draw X
		int[] board = l.getBoard();		
		int row, column;
		for (int i=0; i<9; i++) {
			row = (int) i/3;
			column = i - row*3;
			if (board[i] == Logic.PLAYER_X) {
				g.setColor(Color.RED);
				g.fillPolygon(rightX(row, column));
				g.fillPolygon(leftX(row, column));
			} else if (board[i] == Logic.PLAYER_O) {
				g.setColor(Color.BLUE);
				renderY(g, row, column);
			}
		}
		
	}
	
	public void renderY(Graphics g, int row, int column) {
		// draw O
		g.fillOval((int) (BOX_WIDTH*(column+0.2)), (int) (BOX_HEIGHT*(row+0.2)), 180, 180);
		g.setColor(Color.BLACK);
		g.fillOval((int) (BOX_WIDTH*(column+0.3)), (int) (BOX_HEIGHT*(row+0.3)), 120, 120);
	}
	
	public Polygon rightX(int row, int column) {
		int[] xPoint = new int[]{ 	(int) (BOX_WIDTH*(column+0.25)-10),
									(int) (BOX_WIDTH*(column+0.25)+10),
									(int) (BOX_WIDTH*(column+0.75)+10),
									(int) (BOX_WIDTH*(column+0.75)-10)
		};
		int[] yPoint = new int[]{	(int) (BOX_HEIGHT*(row+0.25)+10),
									(int) (BOX_HEIGHT*(row+0.25)-10),
									(int) (BOX_HEIGHT*(row+0.75)-10),
									(int) (BOX_HEIGHT*(row+0.75)+10)
		};
		return new Polygon(xPoint, yPoint, 4);
	}
	
	public Polygon leftX(int row, int column) {
		int[] xPoint = new int[]{ 	(int) (BOX_WIDTH*(column+0.75)-10),
									(int) (BOX_WIDTH*(column+0.75)+10),
									(int) (BOX_WIDTH*(column+0.25)+10),
									(int) (BOX_WIDTH*(column+0.25)-10)
		};
		int[] yPoint = new int[]{	(int) (BOX_HEIGHT*(row+0.25)-10),
									(int) (BOX_HEIGHT*(row+0.25)+10),
									(int) (BOX_HEIGHT*(row+0.75)+10),
									(int) (BOX_HEIGHT*(row+0.75)-10)
		};
		return new Polygon(xPoint, yPoint, 4);
	}
	
	public void renderBoard(int state) {
		if (state == Logic.RUNNING) {
			this.color = Color.WHITE;
		} else if (state == Logic.PLAYER_X_WON) {
			this.color = Color.RED;
		} else if (state == Logic.PLAYER_O_WON ) {
			this.color = Color.BLUE;
		} else if (state == Logic.TIE) {
			this.color = Color.GRAY;
		}
		repaint();
	}
	
	public void renderGrid(Graphics g) {
		g.setColor(color);
		g.drawLine(BOX_WIDTH, 0, BOX_WIDTH, HEIGHT);
		g.drawLine(BOX_WIDTH*2, 0, BOX_WIDTH*2, HEIGHT);
		g.drawLine(0, BOX_HEIGHT, WIDTH, BOX_HEIGHT);
		g.drawLine(0, BOX_HEIGHT*2, WIDTH, BOX_HEIGHT*2);
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		renderGrid(g);
		renderXY(g);
	}
	
	

}
