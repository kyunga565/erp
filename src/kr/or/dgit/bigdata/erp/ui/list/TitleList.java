package kr.or.dgit.bigdata.erp.ui.list;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Title;

import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public class TitleList extends AbstractList {//JPanel {

	public TitleList(String title) {
		super(title);
	} 

	@Override
	protected void tableSetAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0,1);//0,1을 가운데정렬
		tableSetWidth(100,200);
		
	}

	@Override
	protected String[] getColumnData() {
		return new String[]{"번호","직책명"};
	}

	@Override
	protected String[][] getRowData() {
		List<Title> list = TitleDao.getInstance().selectItemByAll();
		String[][] rowDatas = new String[list.size()][];
		for(int i =0;i<list.size();i++){
			Title t = list.get(i);
			String[] ar = list.get(i).toArray();
			rowDatas[i] = ar; 
		}//list.get(i).toArray() = ar
		return rowDatas;
	}

/*	private JPanel contentPane;
	private JTable table;

	public TitleList() {
		setBorder(new TitledBorder(new EmptyBorder(10, 10, 10, 10), "\uC9C1\uCC45\uBAA9\uB85D", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();//getRowData(),getColumnData()
		scrollPane.setViewportView(table);
		reloadData();
	}
	public void reloadData(){
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColumnData());
		table.setModel(model);
		//model바뀌는순강ㅅ로정렬해야ㅐ됨
		tableCellAlignment(SwingConstants.CENTER, 0,1);//0,1을 가운데정렬
		tableSetWidth(100,200);
	}
	private String[] getColumnData() {
		return new String[]{"번호","직책명"};
	}

	private String[][] getRowData() {
		List<Title> list = TitleDao.getInstance().selectTitleByAll();
		String[][] rowDatas = new String[list.size()][];
		for(int i =0;i<list.size();i++){
			Title t = list.get(i);
			String[] ar = list.get(i).toArray();
			rowDatas[i] = ar; 
		}//list.get(i).toArray() = ar
		return rowDatas;
	}
									//swingconstant.left,0,1 0번과1번을왼쪽정렬
	public void tableCellAlignment(int align,int...idx){
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align); //수평정렬하겟
		
		TableColumnModel model = table.getColumnModel();
		for(int i=0;i<idx.length;i++){
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	public void tableSetWidth(int...width){//셀사이즈설정 픽셀단위
		TableColumnModel model = table.getColumnModel();
		for(int i =0;i<width.length;i++){
			model.getColumn(i).setPreferredWidth(width[i]); //0번째컬럼에 처음넘어온사이즈로
		}
	}*/
}








