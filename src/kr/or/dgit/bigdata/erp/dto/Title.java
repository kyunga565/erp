package kr.or.dgit.bigdata.erp.dto;

public class Title {
	private int no;
	private String titlename;
	
	public Title() {}
	
	public Title(int no) {
		this.no = no;
	}
	
	public Title(int no, String titlename) {
		this.no = no;
		this.titlename = titlename;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitlename() {
		return titlename;
	}
	public void setTitlename(String titlename) {
		this.titlename = titlename;
	}
	@Override
	public String toString() {
		return String.format("%s(%s)", no, titlename);
	}
	public String[] toArray(){
		return new String[]{no+"",titlename};
	}
	@Override
	public boolean equals(Object obj) {
		Title t = (Title) obj;
		if (no==t.no){
			return true;
		}else{
			return false;
		}
	}
}
