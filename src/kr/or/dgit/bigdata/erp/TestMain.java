package kr.or.dgit.bigdata.erp;

import java.sql.Connection;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;

public class TestMain {
	public static void main(String[] args) {
		//db연결테스트
		Connection con = DbConnection.getConnection();
		System.out.println(con);
		//자르파일연결해줌:jre system libraries에서 빌드패스-콘피규어빌드패스-다운받은자르파일 연결
		//comfig.java만듬
		Title title = new Title(6,"인턴");
/*		int res = TitleDao.getInstance().insertTitle(title);
		
		if(res ==1) JOptionPane.showMessageDialog(null, "삽입성공");
		else JOptionPane.showMessageDialog(null, "삽입실패");
*/	
/*		title.setNo(6);
		title.setTitlename("막내");
		TitleDao.getInstance().updateTitle(title);*/
		

//		TitleDao.getInstance().deleteTitle(title.getNo());
		
		List<Title> list = TitleDao.getInstance().selectItemByAll();
//		System.out.println(list); for문돌릴꺼여
		
		for(Title t :list){
			System.out.println(t);
		}
		Title searchTitle = new Title(3);
		Title res = TitleDao.getInstance().selectItemByNo(searchTitle.getNo());
		System.out.println(res);
		
		
		//과제 department dto, dao,testmain
		Department dept= new Department(5,"dd",20);
	}
}
