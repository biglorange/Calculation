package cn.edu.entity;

import java.math.BigDecimal;

public class Overview {
	public double[] h2o;
	public double[] nitrogen;
	
	public Overview(String[] h2o, String[] nitrogen) {
		super();
//		BigDecimal[] bdH2o = new BigDecimal[h2o.length];
//		BigDecimal[] bdNitrogen = new BigDecimal[nitrogen.length];
		
		double[] dH2o = new double[h2o.length];
		double[] dN = new double[nitrogen.length];
		for( int i = 0; i < h2o.length; i++ ) {
			
			dH2o[i] = Double.parseDouble(h2o[i]);
			dN[i] = Double.parseDouble(nitrogen[i]);
		}
		
		this.h2o = dH2o;
		this.nitrogen = dN;
//		this.h2o = bdH2o;
//		this.nitrogen = bdNitrogen;
	}
	public double[] getH2o() {
		return h2o;
	}
	public double[] getNitrogen() {
		return nitrogen;
	}
	
	
}
