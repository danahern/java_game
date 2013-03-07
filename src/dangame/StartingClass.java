package dangame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class StartingClass extends Applet implements Runnable, KeyListener {
	private static Background bg1, bg2;
	private Robot robot;
	private Image image, character, background, characterDown, characterJumped, currentSprite;
	private Graphics second;
	private URL base;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(this);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-bot Alpha");
		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		// Image Setups
		character = getImage(base, "data/character.png");
		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");
		currentSprite = character;
		background = getImage(base, "data/background.png");
	}

	@Override
	public void start() {
		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
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
			robot.update();
			if (robot.isJumped()){
				currentSprite = characterJumped;
			}else if (robot.isJumped() == false && robot.isDucked() == false){
				currentSprite = character;
			}
			bg1.update();
			bg2.update();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(background, bg1.getBgX(),
				bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(),
				bg2.getBgY(), this);
		g.drawImage(character, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("Move up");
			break;
		case KeyEvent.VK_S:
			currentSprite = characterDown;
            if (robot.isJumped() == false){
                robot.setDucked(true);
                robot.setSpeedX(0);
            }
			break;
		case KeyEvent.VK_A:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_D:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
		case KeyEvent.VK_SPACE:
			robot.jump();
			System.out.println("Jump");
			break;
		case KeyEvent.VK_ESCAPE:
			System.exit(ABORT);
			break;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			System.out.println("Stop moving up");
			break;
		case KeyEvent.VK_S:
			currentSprite = character;
			robot.setDucked(false);
			break;
		case KeyEvent.VK_A:
			robot.stopLeft();
			break;
		case KeyEvent.VK_D:
			robot.stopRight();
			break;
		case KeyEvent.VK_SPACE:
			System.out.println("Stop Jumping");
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static Background getBg1() {
        return bg1;
    }

    public static Background getBg2() {
        return bg2;
    }
}
