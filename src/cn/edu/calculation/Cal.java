package cn.edu.calculation;

import java.io.IOException;

public class Cal {
	public ReadData rd;
	public Cal() {
		rd = new ReadData();
		try {
			rd.ReadOverview();
			rd.ReadSummary();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static double CalZ( double[] h2o,double[] n ) throws NumberFormatException, IOException {
		double stressH2o = 0;
		double stressNitrogen = 0;
		double z = 0;
//		for ( int i = 0; i < h2o.length; i++ ) {
//			result += (1-(1-h2o[i])*(1-n[i]));
//		}
		for (int i = 1; i < h2o.length; i++) {
			stressH2o += h2o[i];
			stressNitrogen += n[i];
		}
		z = 1-(1-stressH2o)*(1-stressNitrogen);
		
//		System.out.println("h2o:" + stressH2o + "n:" + stressNitrogen + "z:" + z);
		return z;

	}
	
	public static double CalTkw( double z,double tkwPotential ) throws NumberFormatException, IOException {
		double tkw = (-2.254)*z+tkwPotential;
		return tkw;
	}
	
	public static double CalGS( double tkw ) {
		double gs = (tkw*1.175+4.4134)/1000;
//		System.out.println("gs:" + gs + "\n");
		return gs;
	}
	
	public static double CalWUW(double gs,double tkw) {
		double wuw = (1000.00/gs)*(tkw/1000);
		//System.out.println(wuw);
		return wuw;
	}
	
	public static double CalGpc( double hwam, double nucm ) {
		double gpc = nucm/hwam*5.83;;
		//System.out.println(nucm.toString() + "\t" + hwam.toString());
		
		return gpc;
	}
	
	public static double CalFy( double wuw ) {
		double fy = -0.0006*Math.pow(wuw, 2)+0.9056*wuw-283.77;
		return fy;
	}
	
	public static double CalLys( double gpc ) {
		double lys = 0.0179*gpc+0.1135;
		return lys;
	}
	
	public static double CalEaa( double gpc ) {
		double eaa = 0.2484*gpc+0.2704;
		return eaa;
	}
	
	public static double CalEaai( double gpc ) {
		double eaai = 6.401*gpc+24.15;
		return eaai;
	}
	
	public static String JudgeLevel( double wuw ) {
		if( wuw > 790 ) {
			return "Level 1";
		}
		else if( wuw > 770 ) {
			return "Level 2";
		}
		else if( wuw > 750) {
			return "Level 3";
		}
		else if( wuw > 730) {
			return "Level 4";
		}
		else if( wuw > 710) {
			return "Level 5";
		}
		else {
			return "等外";
		}
	}

}
