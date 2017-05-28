package cn.edu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import cn.edu.calculation.Cal;
import cn.edu.entity.Overview;
import cn.edu.entity.Protein;
import cn.edu.entity.Result;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class ShowFrame  extends JFrame implements ActionListener {
	JPanel jProtein,jTkw,jWuw;
	JTabbedPane choose;
	JTable tableProtein = null;
	JList listTkw = null;
	JList listWuw = null;
	
	Cal cal = new Cal();
	
	public ShowFrame(double input) {
		jProtein = new JPanel();
		jTkw = new JPanel();
		jWuw = new JPanel();
		choose = new JTabbedPane();
		choose.add("Protein", jProtein);
		
		jProtein.setLayout(null);
		choose.add("TKW", jTkw);
		choose.add("WUW", jWuw);
		
		
		Set oSet = cal.rd.db.overviewMap.keySet();
		Iterator oIt = oSet.iterator();
		int j = 0;
		double[] hwam = new double[cal.rd.db.resultMap.size()];
		double[] nucm = new double[cal.rd.db.resultMap.size()];
		double[] tkwResult = new double[cal.rd.db.resultMap.size()];
		double[] wuwResult = new double[cal.rd.db.resultMap.size()];
		while( oIt.hasNext() ) {
			Object key = oIt.next();
			Overview overview = (Overview) cal.rd.db.overviewMap.get(key);
			double[] h2o = overview.getH2o();
			double[] n = overview.getNitrogen();
			double z;
			try {
				z = Cal.CalZ(h2o, n);
				double tkw = Cal.CalTkw(z, input);
				double gs = Cal.CalGS(tkw);
				double wuw = Cal.CalWUW(gs, tkw);
				Protein protein = (Protein) cal.rd.db.proteinMap.get(key);
				Result result = new Result(protein.getHwam(),protein.getNucm(),tkw,wuw);
				cal.rd.db.resultMap.put(key, result);
				hwam[j] = protein.getHwam();
				nucm[j] = protein.getNucm();
				tkwResult[j] = tkw;
				wuwResult[j] = wuw;
				
				System.out.println(j++ + ":");
				System.out.println(wuw);
				System.out.println(Cal.JudgeLevel(wuw));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
