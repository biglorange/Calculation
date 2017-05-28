package cn.edu.entity;

public class Result {
	private double hwam;
	private double nucm;
	private double tkw;
	private double wuw;
	public Result(double hwam, double nucm, double tkw, double wuw) {
		super();
		this.hwam = hwam;
		this.nucm = nucm;
		this.tkw = tkw;
		this.wuw = wuw;
	}
	public double getHwam() {
		return hwam;
	}
	public double getNucm() {
		return nucm;
	}
	public double getTkw() {
		return tkw;
	}
	public double getWuw() {
		return wuw;
	}
	
	

}
