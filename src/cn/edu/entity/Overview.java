package cn.edu.entity;

public class Overview {
	public double[] h2o;
	public double[] nitrogen;
	public Overview(double[] h2o, double[] nitrogen) {
		super();
		this.h2o = h2o;
		this.nitrogen = nitrogen;
	}
	public double[] getH2o() {
		return h2o;
	}
	public double[] getNitrogen() {
		return nitrogen;
	}
	
	
}
