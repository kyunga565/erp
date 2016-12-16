package kr.or.dgit.bigdata.erp.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface interfaceDao<T> {
	//생성자 칠요없음
	
	int insertItem(T item);//end of insert

	int updateItem(T item);//end of update
	
	void deleteItemByNo(int no);//end of delete
	
	List<T> selectItemByAll();
	
	T selectItemByNo(int no);
	
	T getObject(ResultSet rs) throws SQLException;
	
	int getNextNo();
	
	
}
