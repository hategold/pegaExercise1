package item1;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class FishGameFrame extends JFrame {
	
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	public FishGameFrame(){
		super("Feed Fish Game");
		setLayout(new BorderLayout());
		label1 = new JLabel("label with text");
		add(label1,BorderLayout.NORTH);
		
		Icon pic = new ImageIcon(getClass().getResource("/img/TN_1607-01.jpg"));
		System.out.println(pic);
		label2 = new JLabel(pic);
		add(label2,BorderLayout.CENTER);
		
		
	}
}