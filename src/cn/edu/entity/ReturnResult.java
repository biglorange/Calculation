package cn.edu.entity;

public class ReturnResult {
	private String[][] gpcSimulation;
	private String[][] tkw;
	private String[][] wuwLevel;
	public ReturnResult(String[][] gpcSimulation, String[][] tkw, String[][] wuwLevel) {
		super();
		this.gpcSimulation = gpcSimulation;
		this.tkw = tkw;
		this.wuwLevel = wuwLevel;
		
	}
	public String[][] getGpcSimulation() {
		return gpcSimulation;
	}
	public void setGpcSimulation(String[][] gpcSimulation) {
		this.gpcSimulation = gpcSimulation;
	}
	public String[][] getTkw() {
		return tkw;
	}
	public void setTkw(String[][] tkw) {
		this.tkw = tkw;
	}
	public String[][] getWuwLevel() {
		return wuwLevel;
	}
	public void setWuwLevel(String[][] wuwLevel) {
		this.wuwLevel = wuwLevel;
	}
	

}
