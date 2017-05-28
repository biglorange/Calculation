package cn.edu.calculation;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

import cn.edu.data.Database;
import cn.edu.entity.Overview;
import cn.edu.entity.Protein;

public class ReadData {
	public Database db;
	public ReadData() {
		db = new Database();
	}
	public Database ReadSummary() throws IOException {
		
		FileReader fr = new FileReader("./Summary.OUT");
		LineNumberReader lr = new LineNumberReader(fr);
		lr.setLineNumber(0);
		String line = null;
		int count = 0;
		int key = 1;
		while ((line = lr.readLine()) != null) {
			if (lr.getLineNumber() >= 5) {
				String[] data = line.split("\\s+");
				Protein protein = new Protein(Double.parseDouble(data[19]),Double.parseDouble(data[40]));
				db.proteinMap.put(String.valueOf(key++),protein);
				
			}
		}
		return db;
	}

	public Database ReadOverview() throws NumberFormatException, IOException {
		FileReader fr = new FileReader("./OVERVIEW.OUT");
		LineNumberReader lr = new LineNumberReader(fr);
		lr.setLineNumber(0);
		String line = null;
		int count = 0;
		boolean flag = false;
		int key = 1;
		String pattern = ".*kg/ha.*%.*H2O.*N.*";
		Database db1 = new Database();
		LinkedList listN = new LinkedList();
		LinkedList listH2o = new LinkedList();
		while ((line = lr.readLine()) != null) {
			if( flag ) {
				String[] data = line.split("\\s+");
				listN.add(Double.parseDouble(data[data.length-1]));
				listH2o.add(Double.parseDouble(data[data.length-2]));
				count++;
				if( count >= 9 ) {
					double[] n = new double[listN.size()];
					double[] h2o = new double[listH2o.size()];
					int length = listN.size();
					for( int i = 0; i < length; i++ ) {
						n[i] = (Double) listN.get(i);
						h2o[i] = (Double) listH2o.get(i);
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
		return db1;
	}

}
