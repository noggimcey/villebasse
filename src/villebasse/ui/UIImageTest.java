package villebasse.ui;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;


public class UIImageTest implements UI
{
	private Image image;
	private JFrame mainwin;


	public UIImageTest()
	{
	}


	public boolean initialize(String args[])
	{
		if (args.length < 1)
			return false;

		String path = args[0];
		File f = new File(path);

		try {
			this.image = ImageIO.read(f);
		} catch (Exception e) {
			System.err.println(path + ": " + e.getMessage());
			return false;
		}

		return true;
	}


	public void run()
	{
		this.mainwin = new MainWindow(this.image);
		//this.mainwin.setJMenuBar(new TopMenu());

		System.err.println(getClass().getName() + ".run() done");
	}


	private class MainWindow extends JFrame implements ComponentListener
	{
		public MainWindow(Image img)
		{
			this.setLayout(new GridLayout(4, 4, 0, 0));
			this.addComponentListener(this);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			for (int i = 0; i < 4 * 4; ++i)
				this.add(new ImagePanel(img));

			this.pack();
			this.setVisible(true);
		}


		public void componentResized(ComponentEvent e)
		{
			//if (e.getSource() != this)
			this.setSize(this.getPreferredSize());
			this.repaint();
		}

		public void componentHidden(ComponentEvent e) {}
		public void componentMoved(ComponentEvent e) {}
		public void componentShown(ComponentEvent e) {}


		public Dimension getPreferredSize()
		{
			Dimension d = this.getSize();
			int size = Math.min(d.width, d.height);
			return new Dimension(size, size);
		}
	}


	private class ImagePanel extends JButton implements MouseListener
	{
		private boolean state;
		private Image image;
		private int rotation;


		public ImagePanel(Image i)
		{
			this.image = i;
			this.rotation = 0;
			this.addMouseListener(this);
		}


		public void paint(Graphics g)
		{
			if (this.state) {
				Graphics2D g2d = (Graphics2D) g;
				int size = Math.min(this.getWidth(), this.getHeight());

				//this.getRotationTransform();
				g2d.setTransform(this.getRotationTransform(size));
				//g2d.rotate(this.rotation);
				g.drawImage(
					this.image.getScaledInstance(size, size, Image.SCALE_SMOOTH),
					0, 0, Color.gray, null);
			} else {
				g.setColor(Color.gray);
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
			}
		}


		private AffineTransform getRotationTransform(int size)
		{
			AffineTransform tx = new AffineTransform();
			tx.translate(size / 2, size / 2);

			// AffineTransform(cos(theta), sin(theta), -sin(theta), cos(theta), 0, 0)
			if (this.rotation == 0) // theta = 0
				tx.concatenate(new AffineTransform(1, 0, 0, 1, 0, 0));
			else if (this.rotation == 1) // theta = 3PI/2
				tx.concatenate(new AffineTransform(0, -1, 1, 0, 0, 0));
			else if (this.rotation == 2) // theta = PI
				tx.concatenate(new AffineTransform(-1, 0, 0, -1, 0, 0));
			else if (this.rotation == 3) // theta = PI/2
				tx.concatenate(new AffineTransform(0, 1, -1, 0, 0, 0));

			tx.translate(-size / 2, -size / 2);
			return tx;

			/*
			AffineTransform tx = new AffineTransform();
			tx.quadrantRotate(this.rotation, size / 2, size / 2);
			return tx;
			*/
		}


		public void mouseReleased(MouseEvent e)
		{
			int button = e.getButton();

			if (button == MouseEvent.BUTTON1) {
				this.state = !this.state;
			} else if (button == MouseEvent.BUTTON3 && this.state) {
				this.rotation = (this.rotation + 1) % 4;
				this.repaint();
			}
		}

		public void mousePressed(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mouseClicked(MouseEvent e) {}
	}
}
