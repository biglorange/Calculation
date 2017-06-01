package cn.edu.entity;


public class Result {
	private String hwam;
	private String gnam;
	private double tkw;
	private String fy;
	private double wuw;
	private String level;
	public Result(String hwam, String gnam, double tkw, double wuw, String fy, String level) {
		super();
		this.hwam = hwam;
		this.gnam = gnam;
		this.tkw = tkw;
		this.fy = fy;
		this.wuw = wuw;
		this.level = level;
	}
	public String getHwam() {
		return hwam;
	}
	public String getGnam() {
		return gnam;
	}
	public double getTkw() {
		return tkw;
	}
	public double getWuw() {
		return wuw;
	}
	public String getFy() {
		return fy;
	}
	public String getLevel() {
		return level;
	}
	public void setHwam(String hwam) {
		this.hwam = hwam;
	}
	public void setGnam(String gnam) {
		this.gnam = gnam;
	}
	public void setTkw(double tkw) {
		this.tkw = tkw;
	}
	public void setWuw(double wuw) {
		this.wuw = wuw;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	
	
	

}
