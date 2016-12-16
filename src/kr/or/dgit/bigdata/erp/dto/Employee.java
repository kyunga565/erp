package kr.or.dgit.bigdata.erp.dto;

public class Employee {
	private int empno;
	private String empname;
	private Title title; //int title이 아니라
	private Employee manager;
	private int salary;
	private Department dept;//dno
	
	
	public Employee(int empno) {
		this.empno = empno;
	}


	public Employee(int empno, String empname, Title title, Employee manager, int salary, Department dept) {
		this.empno = empno;
		this.empname = empname;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dept = dept;
	}


	public int getEmpno() {
		return empno;
	}


	public void setEmpno(int empno) {
		this.empno = empno;
	}


	public String getEmpname() {
		return empname;
	}


	public void setEmpname(String empname) {
		this.empname = empname;
	}


	public Title getTitle() {
		return title;
	}


	public void setTitle(Title title) {
		this.title = title;
	}


	public Employee getManager() {
		return manager;
	}


	public void setManager(Employee manager) {
		this.manager = manager;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	@Override
	public String toString() {
		return String.format("%s(%s)",
				 empname, title.getTitlename());
	}
	
	public String[] toArray(){
		return new String[]{empno+"", empname, title.getTitlename(), manager==null?"":manager.getEmpname(), String.format("%,d", salary), dept.getDeptname()};
	}
	@Override
	public boolean equals(Object obj) {
		Employee e = (Employee) obj;
		return empno==e.empno?true:false;
	}
}
