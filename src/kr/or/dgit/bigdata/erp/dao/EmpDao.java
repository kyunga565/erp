package kr.or.dgit.bigdata.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.jdbcUtil;

public class EmpDao implements interfaceDao<Employee> {
	private static final EmpDao instance = new EmpDao();

	public static EmpDao getInstance() {
		return instance;
	}
	private EmpDao(){} //외부에서생성할수없ㅇ도록 프라이빗해줌
	
	@Override
	public int insertItem(Employee item) {
		String sql = "insert into employee values(?,?,?,?,?,?)";
		Connection con = DbConnection.getConnection();
		int res = -1;
		//finally안하는방법
		try (PreparedStatement pstmt=DbConnection.getConnection().prepareStatement(sql)){
	//		pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item.getEmpno());
			pstmt.setString(2,item.getEmpname());
			pstmt.setInt(3,item.getTitle().getNo());
			pstmt.setInt(4, item.getManager().getEmpno());
			pstmt.setInt(5, item.getSalary());
			pstmt.setInt(6, item.getDept().getDeptno());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int updateItem(Employee item) {
		String sql = "update employee set empname=?,title=?,manager=?,salart=?,dno=? where empno=?";
		int res = -1;
		PreparedStatement pstmt = null; 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getEmpname());
			pstmt.setInt(2, item.getTitle().getNo());
			pstmt.setInt(3, item.getManager().getEmpno());
			pstmt.setInt(4, item.getSalary());
			pstmt.setInt(5, item.getDept().getDeptno());
			pstmt.setInt(6, item.getEmpno()); //sql 순서대로
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return res;
	}

	@Override
	public void deleteItemByNo(int no) {
		String sql ="delete from employee where empno=?";
		PreparedStatement pstmt = null;
		int res=-1;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1,no);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Employee> selectItemByAll() {
		String sql ="select empno,empname,title,manager,salary,dno from employee";
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		List<Employee> list = new ArrayList<>();
		
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()){
				list.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			jdbcUtil.close(rs,pstmt);
		}
				
		return list;
	}

	@Override
	public Employee selectItemByNo(int no) {
		String sql ="select empno,empname,title,manager,salary,dno from employee where empno=?";
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		Employee emp = null;
//		List<Employee> list = new ArrayList<>();
		
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if(rs.next()){
				emp = (getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		}
				
		return emp;
	}

	@Override
	public Employee getObject(ResultSet rs) throws SQLException {
		int empno = rs.getInt("empno");
		String empname = rs.getString("empname");
		Title title = TitleDao.getInstance().selectItemByNo(rs.getInt("title"));
		Employee manager = EmpDao.getInstance().selectItemByNo(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = DeptDao.getInstance().selectItemByNo(rs.getInt("dno"));
		
		return new Employee(empno,empname,title,manager,salary,dept);
	}

	@Override
	public int getNextNo() {
		/*String sql = "select max(no)+1 as nextNo from employee";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nextNo = -1;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				nextNo = rs.getInt("nextNo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		}*/
		return 0;
	}
}
