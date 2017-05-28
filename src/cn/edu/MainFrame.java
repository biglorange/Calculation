package cn.edu;

import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import cn.edu.calculation.Cal;
import cn.edu.entity.Overview;

public class MainFrame extends JFrame implements ActionListener {

		JPanel jpanel = null;
		JLabel JProtein = null;
		JLabel JTkw = null;
		JLabel JWuw = null;
		
		Cal cal = new Cal();
		
		public MainFrame() {
			getContentPane().setLayout(null);
			jpanel = new JPanel();
			JProtein = new JLabel("Protein");
			JTkw = new JLabel("TKW");
			JWuw = new JLabel("Wuw");
			
			JTextField input = new JTextField();
			input.setBounds(85, 64, 136, 23);
			getContentPane().add(input);
			input.setColumns(10);
			
			JLabel lbltkwprotein = new JLabel("请输入TKWprotein");
			lbltkwprotein.setBounds(85, 24, 136, 23);
			getContentPane().add(lbltkwprotein);
			
			JButton button = new JButton("计算");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					new ShowFrame(Double.parseDouble(input.getText()));
				}
			});
			button.setBounds(85, 132, 93, 23);
			getContentPane().add(button);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

	}

	

