package cn.edu.calculation;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import cn.edu.entity.Overview;
import cn.edu.entity.Protein;
import cn.edu.entity.Result;
import cn.edu.entity.ReturnResult;

public class OutputForShow {
	static Cal cal = new Cal();
	
	public static ReturnResult CalForShow(double input) {
		
		
		
		Set oSet = cal.rd.db.overviewMap.keySet();
		Iterator oIt = oSet.iterator();
		int j = 0;
		String[][] gpcSimulation = new String[cal.rd.db.overviewMap.size()][4];
		String[][] tkwResult = new String[cal.rd.db.overviewMap.size()][1];
		String[][] wuwLevel = new String[cal.rd.db.overviewMap.size()][3];
		
		while( oIt.hasNext() ) {
			Object key = oIt.next();
			Overview overview = (Overview) cal.rd.db.overviewMap.get(key);
			double[] h2o = overview.getH2o();
			double[] n = overview.getNitrogen();
			;
			try {
				double z = Cal.CalZ(h2o, n);
				double tkw = Cal.CalTkw(z, input);
				double gs = Cal.CalGS(tkw);
				double wuw = Cal.CalWUW(gs, tkw);
				double fy = Cal.CalFy(wuw);
				Protein protein = (Protein) cal.rd.db.proteinMap.get(key);
				double gpc = Cal.CalGpc(Double.parseDouble(protein.getHwam()), Double.parseDouble(protein.getGnam()));
				String level = cal.JudgeLevel(wuw);
				Result result = new Result(protein.getHwam(),protein.getGnam(),tkw, wuw, String.valueOf(cal.CalFy(wuw)),cal.JudgeLevel(wuw));
				cal.rd.db.resultMap.put(key, result);
				gpcSimulation[j][0] = String.valueOf(gpc);
				gpcSimulation[j][1] = String.valueOf(Cal.CalLys(gpc));
				gpcSimulation[j][2] = String.valueOf(Cal.CalEaa(gpc));
				gpcSimulation[j][3] = String.valueOf(Cal.CalEaai(gpc));
				
				tkwResult[j][0] = String.valueOf(tkw);
				wuwLevel[j][0] = String.valueOf(wuw);
				wuwLevel[j][1] = String.valueOf(fy);
				wuwLevel[j][2] = level;
				j++;
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ReturnResult returnResult = new ReturnResult(gpcSimulation,tkwResult,wuwLevel);
		return returnResult;
		
	}

}
