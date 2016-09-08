package md.convertit.hydraulicsystem.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	private static final long serialVersionUID = 2527920853697561010L;
	private ImageIcon imageIcon;

//    public ImagePanel(String img) {
//        this(new ImageIcon(img).getImage());
//    }

    public ImagePanel(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
        
        Dimension size = new Dimension(imageIcon.getImage().getWidth(null), imageIcon.getImage().getHeight(null));
        //setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(new Dimension(100, 100));
        //setSize(100, 200);
        //setLayout(null);
    }

	@Override
	protected void paintComponent(Graphics g) {
      // g.drawImage(img, 0, 0, null);
      System.out.println("repaint la "+imageIcon.getImage());
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;
      g2d.drawImage(imageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
	}

	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
		this.repaint();
	}
	
}
