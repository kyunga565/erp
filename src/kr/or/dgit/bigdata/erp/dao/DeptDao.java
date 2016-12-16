package kr.or.dgit.bigdata.erp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

import com.mysql.jdbc.Connection;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.jdbcUtil;


public class DeptDao implements interfaceDao<Department>{
	private static final DeptDao instance = new DeptDao();

	public static DeptDao getInstance() {
		return instance;
	}
	private DeptDao(){}
	@Override
	public int insertItem(Department item) {
		String sql = "insert department values(?,?,?)";
		Connection con = (Connection) DbConnection.getConnection();
		PreparedStatement pstmt = null;
		int res = -1;
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, item.getDeptno());
			pstmt.setString(2, item.getDeptname());
			pstmt.setInt(3, item.getFloor());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				pstmt.close(); //3닫아줘야도
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
	@Override
	public int updateItem(Department item) {
		String sql = "update department set deptname=? ,floor=? where deptno=?";
		int res=-1;
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, item.getDeptno());
			pstmt.setString(2, item.getDeptname());
			pstmt.setInt(1, item.getFloor());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		return res;
	}
	@Override
	public void deleteItemByNo(int no) {
		String sql = "delete from department where deptno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(pstmt);
		}
		
	}
	@Override
	public List<Department> selectItemByAll() {
		List<Department> deptList =  new ArrayList<Department>();
		String sql = "select deptno,deptname,floor from department";
		PreparedStatement pstmt = null;
		ResultSet rs = null;//결과를참조한다는뜻 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptList.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		}
		return deptList;
	}
	@Override
	public Department selectItemByNo(int no) {
		String sql = "select deptno,deptname,floor from department where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Department dept = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dept=getObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		
	}
		return dept;
	}
	@Override
	public Department getObject(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"),rs.getString("deptname"),rs.getInt("floor"));
	}
	@Override
	public int getNextNo() {
		String sql = "select max(deptno)+1 as nextNo from Department";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
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
		}
		return nextNo;
	}

	
/*	private static final DeptDao instance = new DeptDao();

	public static DeptDao getInstance() {
		return instance;
	}
	private DeptDao(){}
	
	public int insertDept(Department dept){
		String sql = "insert department values(?,?,?)";
		Connection con = (Connection) DbConnection.getConnection();
		PreparedStatement pstmt = null;
		int res = -1;
	
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDeptname());
			pstmt.setInt(3, dept.getFloor());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				pstmt.close(); //3닫아줘야도
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	} //end of insert
	public int updateDept(Department dept){
		String sql = "update department set deptname=? ,floor=? where deptno=?";
		int res=-1;
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDeptname());
			pstmt.setInt(1, dept.getFloor());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}//end of update
	public void deleteDept(int deptno){
		String sql = "delete from department where deptno=?";
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, deptno);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pstmt.close(); 
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//end of delete
	
	public List<Department> selectTitleByAll(){
		List<Department> deptList =  new ArrayList<Department>();
		String sql = "select deptno,deptname,floor from department";
		PreparedStatement pstmt = null;
		ResultSet rs = null;//결과를참조한다는뜻 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				deptList.add(getDepartment(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				pstmt.close(); 
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return deptList;
	}
	
	public Department selectDepartmentByNo(int no){
		String sql = "select deptno,deptname,floor from department where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		Department dept = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				dept=getDepartment(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			pstmt.close(); 
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		return dept;
		
	}
	
	private Department getDepartment(ResultSet rs) throws SQLException {
		return new Department(rs.getInt("deptno"),rs.getString("deptname"),rs.getInt("floor"));
			}
		
	public int getNextNo(){
		String sql = "select max(deptno)+1 as nextNo from Department";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
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
			try {
				pstmt.close(); 
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return nextNo;
	}*/
}
