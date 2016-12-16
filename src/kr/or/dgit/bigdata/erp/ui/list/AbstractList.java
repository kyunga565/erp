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
public abstract class AbstractList extends JPanel {

	private JTable table;

	public AbstractList(String title) {
		setBorder(new TitledBorder(new EmptyBorder(10, 10, 10, 10), title, TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		tableSetAlignWith(); //메소드하나만들어서 정렬부분넣어놓음-->그래서리로드데이터는 전부같아짐
		
	}
	protected abstract void tableSetAlignWith();
	protected abstract String[] getColumnData();
	protected abstract String[][] getRowData();
	//다른부분만찾아내서 추상메소드로 구현
	
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
	}
}








