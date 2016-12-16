package kr.or.dgit.bigdata.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.jdbcUtil;

public class TitleDao implements interfaceDao<Title>{
	private static final TitleDao instance = new TitleDao();

	public static TitleDao getInstance() {
		return instance;
	}
	private TitleDao(){}
	@Override
	public int insertItem(Title item) {
		String sql = "insert into title values(?,?)";
		//db가 인식할수있는언어로바꿔야됨
		Connection con = DbConnection.getConnection(); //1db연결
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			pstmt = con.prepareStatement(sql);//2
			pstmt.setInt(1, item.getNo());
			pstmt.setString(2, item.getTitlename());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()); //1062중복ㅇㅣ라는뜻
			if(e.getErrorCode() == 1062){
				System.out.println("중복입니다");
			};
			e.printStackTrace();
		} finally{
			jdbcUtil.close(pstmt);
			/*try {
				pstmt.close(); //3닫아줘야도
			} catch (SQLException e) {
				e.printStackTrace();
			}*/
		}
		return res;
	}

	@Override
	public int updateItem(Title item) {
		String sql="update title set titlename=? where no=?";
		int res = -1;
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, item.getTitlename());
			pstmt.setInt(2, item.getNo());
			System.out.println(pstmt);
			res = pstmt.executeUpdate(); //추가하는식 db에
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
		String sql = "delete from title where no=?";//1
		PreparedStatement pstmt = null; //3
		try { //4trycatch
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no); //5
			System.out.println(pstmt);//6
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}//2
		finally{ //7
			jdbcUtil.close(pstmt);
		}
		
	}

	@Override
	public List<Title> selectItemByAll() {
		List<Title> titleList =  new ArrayList<Title>();
		String sql = "select no,titlename from title";
		PreparedStatement pstmt = null;
		ResultSet rs = null;//결과를참조한다는뜻 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				titleList.add(getObject(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		}
		return titleList;
	}

	@Override
	public Title selectItemByNo(int no) {
		String sql = "select no, titlename from title where no = ?";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		Title title = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				title = getObject(rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jdbcUtil.close(rs,pstmt);
		
	}
		return title;
	}

	@Override
	public Title getObject(ResultSet rs) throws SQLException {
		return new Title(rs.getInt(1),rs.getString("titlename"));
	}

	@Override
	public int getNextNo() {
		String sql = "select max(no)+1 as nextNo from title";
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
	
	
	/*private static final TitleDao instance = new TitleDao();
	//get
	public static TitleDao getInstance() {
		return instance;
	}
	//생성자
	private TitleDao(){}
	
	public int insertTitle(Title title){
		String sql = "insert into title values(?,?)";
		//db가 인식할수있는언어로바꿔야됨
		Connection con = DbConnection.getConnection(); //1db연결
		PreparedStatement pstmt = null;
		int res = -1;
		try {
			pstmt = con.prepareStatement(sql);//2
			pstmt.setInt(1, title.getNo());
			pstmt.setString(2, title.getTitlename());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getErrorCode()); //1062중복ㅇㅣ라는뜻
			if(e.getErrorCode() == 1062){
				System.out.println("중복입니다");
			};
			e.printStackTrace();
		} finally{
			try {
				pstmt.close(); //3닫아줘야도
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}//end of insert

	public int updateTitle(Title title){ //업데이트
		String sql="update title set titlename=? where no=?";
		int res = -1;
		PreparedStatement pstmt = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, title.getTitlename());
			pstmt.setInt(2, title.getNo());
			System.out.println(pstmt);
			res = pstmt.executeUpdate(); //추가하는식 db에
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
	
	public void deleteTitle(int no){
		String sql = "delete from title where no=?";//1
		PreparedStatement pstmt = null; //3
		try { //4trycatch
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no); //5
			System.out.println(pstmt);//6
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}//2
		finally{ //7
		try {
			pstmt.close(); 
		} catch (SQLException e) {
			e.printStackTrace();
			}
		}

	}//end of delete
	
	public List<Title> selectTitleByAll(){
		List<Title> titleList =  new ArrayList<Title>();
		String sql = "select no,titlename from title";
		PreparedStatement pstmt = null;
		ResultSet rs = null;//결과를참조한다는뜻 
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				titleList.add(getTitle(rs));
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
		return titleList;
	}
	public Title selectTitleByNo(int no){
		String sql = "select no, titlename from title where no = ?";
		PreparedStatement pstmt =  null;
		ResultSet rs = null;
		Title title = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()){
				title = getTitle(rs);
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
		return title;
	}
	private Title getTitle(ResultSet rs) throws SQLException {
		return new Title(rs.getInt(1),rs.getString("titlename"));
		
	}
	public int getNextNo(){
		String sql = "select max(no)+1 as nextNo from title";
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
		return nextNo;*/
	}
	
	
