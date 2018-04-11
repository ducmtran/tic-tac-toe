/**
 * 
 */
package game;
import java.awt.Color;

/**
 * @author MinhDuc
 *
 */
// import java.awt.*;
import javax.swing.*;
import game.Board;

public class Main {

	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// Create window
		JFrame f = new JFrame();
		f.setSize(Board.WIDTH, Board.HEIGHT+50);
		
		// Draw black grid
		Board d = new Board();
		Logic l = new Logic(d);
		CompLogic c = new CompLogic(d);

		d.renderBoard(c.getState());
		d.logicHandler(c);

//        d.renderBoard(l.getState());
//        d.logicHandler(l);
		d.setBackground(Color.BLACK);
		
		
		f.add(d);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
