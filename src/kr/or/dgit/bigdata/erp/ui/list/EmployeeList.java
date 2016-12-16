package kr.or.dgit.bigdata.erp.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dto.Employee;

public class EmployeeList extends AbstractList {

	public EmployeeList(String title) {
		super(title);
	}

	@Override
	protected void tableSetAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0,1,2,3,5);//0,1을 가운데정렬
		tableCellAlignment(SwingConstants.RIGHT, 4);
		tableSetWidth(100,200,100,200,200,100);

	}

	@Override
	protected String[] getColumnData() {
		return new String []{"사번","성명","직책","매니져","급여","부서"};
	}

	@Override
	protected String[][] getRowData() {
		List<Employee> list	= EmpDao.getInstance().selectItemByAll();
		String [][] rowDatas = new String[list.size()][];
		for(int i =0;i<list.size();i++){
			rowDatas[i] = list.get(i).toArray();
		}
		return rowDatas;
	}

}
