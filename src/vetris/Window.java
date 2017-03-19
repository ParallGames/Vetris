package vetris;

import javax.swing.JFrame;

import vetris.Panel;

public class Window{

	private Panel panel = new Panel();
	private JFrame jFrame = new JFrame();

	Window(){
		jFrame.setContentPane(panel);
		jFrame.setResizable(false);
		jFrame.pack();
		jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jFrame.setTitle("Vetris");
		jFrame.setVisible(true);
	}

	public void repaint(){
		try{
			Thread.sleep(10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		jFrame.revalidate();
		jFrame.repaint();
	}

	public boolean isVisible(){
		return jFrame.isVisible();
	}

	public void close(){
		jFrame.dispose();
	}
}