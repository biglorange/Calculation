package cn.edu.calculation;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import cn.edu.data.Database;
import cn.edu.entity.Overview;
import cn.edu.entity.Protein;
import cn.edu.entity.Result;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Cal cal = new Cal();
		double input = 44.13;
//		double[] a = new double[2];
//		double[] h2o = new double[10];
//		double[] n = new double[10];
		
		Database db = cal.rd.db;
		Set pSet = cal.rd.db.proteinMap.keySet();
		Iterator pIt = pSet.iterator();
		while( pIt.hasNext() ) {
			Object key = pIt.next();
			Protein obj = (Protein) cal.rd.db.proteinMap.get(key);
			System.out.println(obj.getHwam() + " " + obj.getNucm());
		}

		/*Set oSet = cal.rd.db.overviewMap.keySet();
		Iterator oIt = oSet.iterator();
		int j = 1;
		while( oIt.hasNext() ) {
			System.out.println("overview" + j++);
			Object key = oIt.next();
			Overview overview = (Overview) cal.rd.db.overviewMap.get(key);
			double[] test = overview.getH2o();
			double[] test1 = overview.getNitrogen();
			for(int i = 0; i< test.length; i++ ) {
				System.out.println(test[i] +"  " + test1[i]);
			}
		}*/
		
		Set oSet = cal.rd.db.overviewMap.keySet();
		Iterator oIt = oSet.iterator();
		int j = 1;
		while( oIt.hasNext() ) {
			Object key = oIt.next();
			Overview overview = (Overview) cal.rd.db.overviewMap.get(key);
			double[] h2o = overview.getH2o();
			double[] n = overview.getNitrogen();
			double z= Cal.CalZ(h2o, n);
			double tkw = Cal.CalTkw(z, input);
			double gs = Cal.CalGS(tkw);
			double wuw = Cal.CalWUW(gs, tkw);
			Protein protein = (Protein) cal.rd.db.proteinMap.get(key);
			Result result = new Result(protein.getHwam(),protein.getNucm(),tkw,wuw);
			cal.rd.db.resultMap.put(key, result);
			System.out.println(j++ + ":");
			System.out.println(wuw);
			System.out.println(Cal.JudgeLevel(wuw));
			
		}
		System.exit(0);
	}

}
