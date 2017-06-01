package cn.edu.calculation;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import cn.edu.data.Database;
import cn.edu.entity.Overview;
import cn.edu.entity.Protein;
import cn.edu.entity.ReturnResult;
import jxl.*;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class ReadData {
	public Database db;
	public ReadData() {
		db = new Database();
	}
	public Database ReadSummary() throws IOException {
		
		FileReader fr = new FileReader("C:/DSSAT45/Wheat/Summary.OUT");
		LineNumberReader lr = new LineNumberReader(fr);
		lr.setLineNumber(0);
		String line = null;
		int count = 0;
		int key = 1;
		while ((line = lr.readLine()) != null) {
			if (lr.getLineNumber() >= 5) {
				String[] data = line.split("\\s+");
				Protein protein = new Protein(data[19],data[44]);
				db.proteinMap.put(String.valueOf(key++),protein);
				
			}
		}
		lr.close();
		fr.close();
		return db;
	}

	public Database ReadOverview() throws NumberFormatException, IOException {
		FileReader fr = new FileReader("C:/DSSAT45/Wheat/OVERVIEW.OUT");
		LineNumberReader lr = new LineNumberReader(fr);
		lr.setLineNumber(0);
		String line = null;
		int count = 0;
		boolean flag = false;
		int key = 1;
		String pattern = ".*kg/ha.*%.*H2O.*N.*";
		LinkedList listN = new LinkedList();
		LinkedList listH2o = new LinkedList();
		while ((line = lr.readLine()) != null) {
			if( flag ) {
				String[] data = line.split("\\s+");
				listN.add(data[data.length-1]);
				listH2o.add(data[data.length-2]);
				count++;
				if( count >= 9 ) {
					String[] n = new String[listN.size()];
					String[] h2o = new String[listH2o.size()];
					int length = listN.size();
					for( int i = 0; i < length; i++ ) {
						n[i] = (String) listN.get(i);
						h2o[i] = (String) listH2o.get(i);
					}

					db.overviewMap.put(String.valueOf(key++),new Overview(h2o,n));
					count = 0;
					flag = false;
					listN.removeAll(listN);
					listH2o.removeAll(listH2o);
				}
			}

			if(Pattern.matches(pattern, line)) {
				flag = true;
				continue;
			}
			
		}
		return db;
	}
	
	public static void OutputToFile(ReturnResult rr) {
		String filepath = "D:/ResultOutput.txt";
		File file = new File(filepath);
		FileWriter fw;
		if ( !file.exists() || !file.isFile() ) {
			try {
				fw = new FileWriter(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "There is no file,build new one:\nD:/ResultOutput.txt.\n");
		}
		try {
			fw = new FileWriter(file);
			StringBuilder sb = new StringBuilder();
			for( int i = 0; i < rr.getGpcSimulation().length; i++ ) {
				String str = rr.getGpcSimulation()[i][0] + "\t" + rr.getGpcSimulation()[i][1] 
						+ "\t" + rr.getTkw()[i][0] + "\t\t\t" + rr.getWuwLevel()[i][0] 
								+ "\t" + rr.getWuwLevel()[i][1];
				sb.append(str + "\n");
				
			}
			fw.write(sb.toString());
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			JOptionPane.showMessageDialog(null, "Save success: \nD:/Resultoutput.txt\n");
	}
	public static void WriteToExcel(ReturnResult rr) {
		
		String excelpath = "D:/resultOutput.xls";
		
		
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(new File(excelpath));
			WritableSheet sheet = workbook.createSheet("输出结果",0);
			String[] colCell = {"Protein","LYS","EAA","EAAI","TKW","WUW","FY","LEVEL"};
			for( int i = 0; i < colCell.length; i++ ) {
				sheet.addCell(new Label(i,0,colCell[i]));
			}
//			sheet.addCell(new Label(0,0,"Protein"));
//			//sheet.addCell(new Label(1,0,"NUCM"));
//			sheet.addCell(new Label(1,0,"TKW"));
//			sheet.addCell(new Label(2,0,"FY"));
//			sheet.addCell(new Label(3,0,"WUW"));
//			sheet.addCell(new Label(4,0,"LEVEL"));
			String[][] hwamGnamS = rr.getGpcSimulation();
			String[][] tkwS = rr.getTkw();
			String[][] wuwLevelS = rr.getWuwLevel();
			
			double[][] hwamGnam = new double[hwamGnamS.length][hwamGnamS[1].length];
			double[][] tkw = new double[tkwS.length][tkwS[1].length];
			double[][] wuwFY = new double[wuwLevelS.length][wuwLevelS[1].length-1];
			String[] level = new String[wuwLevelS.length];
			for( int i = 0; i < hwamGnamS.length; i++ ) {
				
					hwamGnam[i][0] = Double.parseDouble(hwamGnamS[i][0]);
				
			}
			for( int i = 0; i < tkwS.length; i++ ) {
				for ( int j = 0; j < tkwS[1].length; j++ ) {
					tkw[i][j] = Double.parseDouble(tkwS[i][j]);
				}
			}
			for( int i = 0; i < wuwLevelS.length; i++ ) {
					wuwFY[i][0] = Double.parseDouble(wuwLevelS[i][0]);
					wuwFY[i][1] = Double.parseDouble(wuwLevelS[i][1]);
					
					level[i] = wuwLevelS[i][2];
				
			}
			for( int i = 0; i < hwamGnam.length; i++ ) {
//				protein
				jxl.write.Number number0 = new jxl.write.Number(0,i+1,hwamGnam[i][0]);
//				lys
				jxl.write.Number number1 = new jxl.write.Number(1,i+1,hwamGnam[i][1]);
//				eaa
				jxl.write.Number number2 = new jxl.write.Number(2,i+1,hwamGnam[i][1]);
//				eaai
				jxl.write.Number number3 = new jxl.write.Number(3,i+1,hwamGnam[i][1]);
//				tkw
				jxl.write.Number number4 = new jxl.write.Number(4,i+1,tkw[i][0]);
//				wuw
				jxl.write.Number number5 = new jxl.write.Number(5,i+1,wuwFY[i][0]);
//				fy
				jxl.write.Number number6 = new jxl.write.Number(6,i+1,wuwFY[i][1]);
//				level
				Label numberLevel = new Label(4,i+1,level[i]);
				sheet.addCell(number0);
				sheet.addCell(number1);
				sheet.addCell(number2);
				sheet.addCell(number3);
				sheet.addCell(number4);
				sheet.addCell(number5);
				sheet.addCell(number6);
				sheet.addCell(numberLevel);
			}
			workbook.write();
			workbook.close();
			
		} catch (IOException | WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "Save success: \nD:/resultoutput.xls\n");
	}
	

}
