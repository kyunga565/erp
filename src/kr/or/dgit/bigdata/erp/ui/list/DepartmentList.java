package kr.or.dgit.bigdata.erp.ui.list;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Title;

import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.util.List;

@SuppressWarnings("serial")
public class DepartmentList extends AbstractList{

	public DepartmentList(String title) {
		super(title);
	}//JPanel {

	@Override
	protected void tableSetAlignWith() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getColumnData() {
		return new String[]{"부서번호","부서명","위치"};
	}

	@Override
	protected String[][] getRowData() {
		List<Department> deptList = DeptDao.getInstance().selectItemByAll();
		String[][] rowDatas = new String[deptList.size()][];
		for(int i =0;i<deptList.size();i++){
			Department d = deptList.get(i);
			String[] ar = deptList.get(i).toArray();
			rowDatas[i] = ar; 
		}//list.get(i).toArray() = ar
		return rowDatas;
	}
	
	/*
	private JTable table;

	public DepartmentList() {
		setBorder(new TitledBorder(new EmptyBorder(10, 10, 10, 10), "\uBD80\uC11C\uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		reloadData();

	}
	private String[] getColumnDate() {
		return new String[]{"부서번호","부서명","위치"};
		
	}

	private String [][] getRowDate() {
		List<Department> deptList = DeptDao.getInstance().selectTitleByAll();
		String[][] rowDatas = new String[deptList.size()][];
		for(int i =0;i<deptList.size();i++){
			Department d = deptList.get(i);
			String[] ar = deptList.get(i).toArray();
			rowDatas[i] = ar; 
		}//list.get(i).toArray() = ar
		return rowDatas;
	}
	public void reloadData() {
		DefaultTableModel model = new DefaultTableModel(getRowDate(), getColumnDate());
		table.setModel(model);
		//model바뀌는순강ㅅ로정렬해야ㅐ됨
		
	}
	*/
	
}

