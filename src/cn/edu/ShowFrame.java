package cn.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import cn.edu.calculation.Cal;
import cn.edu.calculation.OutputForShow;
import cn.edu.calculation.ReadData;
import cn.edu.entity.ReturnResult;

public class ShowFrame extends JFrame implements ActionListener{
	/**
	 * 
	 */
	//定义组件
	JPanel jProtein,jTkw,jWuw;
	JTabbedPane choose;
	JTable tableProtein ;
	JTable tableWuw ;
	JTable tableTkw ;
	ReturnResult rr;
	
	
	public ShowFrame(double input) {
		
		Cal  cal = new Cal();
		jProtein = new JPanel();
		jTkw = new JPanel();
		jWuw = new JPanel();
		choose = new JTabbedPane();
		JButton buttonOut0 = new JButton("SavetoFile");
		JButton buttonOut1 = new JButton("SavetoFile");
		JButton buttonOut2 = new JButton("SavetoFile");
		rr = OutputForShow.CalForShow(input);
		
		
		//将数据传回来
		String[][] hwamNucm = rr.getGpcSimulation();
		String[][] tkwResult = rr.getTkw();
		String[][] wuwLevel = rr.getWuwLevel();
		
		
		//行号显示的内容
		String[] colProtein = {"GPC","LYS","EAA","EAAI"};
		String[] colTkw = {"TKW"};
		String[] colWuw = {"WUW","FY","Level"};
		
		//对表格赋值
		tableProtein = new JTable(hwamNucm,colProtein);
		tableTkw = new JTable(tkwResult,colTkw);
		tableWuw = new JTable(wuwLevel,colWuw);
		
		
		//为每个标签页中添加带滑动块的表格
		jProtein.add(new JScrollPane(tableProtein));
		jTkw.add(new JScrollPane(tableTkw));
		jWuw.add(new JScrollPane(tableWuw));
		
		//添加导出按钮
		jProtein.add(buttonOut0);
		jTkw.add(buttonOut1);
		jWuw.add(buttonOut2);
		
		buttonOut0.addActionListener(this);
		buttonOut1.addActionListener(this);
		buttonOut2.addActionListener(this);
		
		choose.add("Protein",jProtein);
		choose.add("Tkw", jTkw);
		choose.add("Wuw", jWuw);
		
		
		getContentPane().add(choose);
		
		setTitle("输出");
		setBounds(500,200,500,544);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		//ReadData.OutputToFile(rr);
		ReadData.WriteToExcel(rr);
		
	}

}
