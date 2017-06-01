package cn.edu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame {

	private JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 200, 336, 254);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(102, 81, 105, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JButton button = new JButton("Calculation");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double input = Double.parseDouble(textField.getText());
				frame.dispose();
				ShowFrame showFrame = new ShowFrame(input);
				
			}
		});
		button.setBounds(102, 132, 105, 23);
		frame.getContentPane().add(button);
		
		JLabel lbltkwprotein = new JLabel("please input: TKWprotein");
		lbltkwprotein.setBounds(78, 44, 150, 15);
		frame.getContentPane().add(lbltkwprotein);
	}
}
