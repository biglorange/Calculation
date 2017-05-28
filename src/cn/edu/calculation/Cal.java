package cn.edu.calculation;

import java.io.IOException;

import cn.edu.data.Database;

public class Cal {
	public ReadData rd;
	public Cal() {
		rd = new ReadData();
		try {
			Database db1 = rd.ReadSummary();
			Database db2 = rd.ReadOverview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static double CalZ(double[] h2o,double[] n) throws NumberFormatException, IOException {
//		double[] h2o = new double[9];
//		double[] n = new double[9];
		double stressH2o = 0;
		double stressNitrogen = 0;
		for (int i = 1; i < 9; i++) {
			stressH2o += h2o[i];
			stressNitrogen += n[i];
		}
		double z;
		z = 1 - (1 - stressH2o) * (1 - stressNitrogen);
		return z;

	}
	
	public static double CalTkw(double z,double tkwPotential) throws NumberFormatException, IOException {
		double tkw = (-2.254)*z+tkwPotential;
		return tkw;
	}
	
	public static double CalGS(double tkw) {
		double gs = (tkw*1.175+4.4134)/1000;
		return gs;
	}
	
	public static double CalWUW(double gs,double tkw) {
		double wuw = (1000/gs)*(tkw/1000);
		return wuw;
	}
	
	public static String JudgeLevel( double wuw ) {
		int level = 0;
		if( wuw >= 790 ) {
			return "1";
		}
		else if( wuw >= 770 ) {
			return "2";
		}
		else if( wuw >= 750 ) {
			return "3";
		}
		else if( wuw >= 730 ) {
			return "4";
		}
		else if( wuw >= 710 ) {
			return "5";
		}
		else {
			return "等外";
		}
	}

}
