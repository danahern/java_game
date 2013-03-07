package dangame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;

public class StartingClass extends Applet implements Runnable, KeyListener {
	private Robot robot;

	@Override
	public void init() {
		setSize(800, 600);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-bot Alpha");
	}

	@Override
	public void start() {
		robot = new Robot();
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		super.stop();
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_A:
			break;
		case KeyEvent.VK_D:
			break;
		case KeyEvent.VK_SPACE:
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			break;
		case KeyEvent.VK_S:
			break;
		case KeyEvent.VK_A:
			break;
		case KeyEvent.VK_D:
			break;
		case KeyEvent.VK_SPACE:
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
