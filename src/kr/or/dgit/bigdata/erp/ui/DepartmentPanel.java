package kr.or.dgit.bigdata.erp.ui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Title;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DepartmentPanel extends JPanel implements ActionListener {
	private JTextField tfdeptno;
	private JTextField tfdeptname;
	private JTextField tffloor;
	private JButton btnOk;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public DepartmentPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(20, 20, 20, 20));
		add(panel);
		panel.setLayout(new GridLayout(0, 2, 20, 20));
		
		JLabel deptno = new JLabel("부서번호");
		panel.add(deptno);
		
		tfdeptno = new JTextField();
		tfdeptno.setEditable(false);
		panel.add(tfdeptno);
		tfdeptno.setColumns(10);
		tfdeptno.setText(getNextNo());
		
		JLabel deptname = new JLabel("부서명");
		panel.add(deptname);
		
		tfdeptname = new JTextField();
		panel.add(tfdeptname);
		tfdeptname.setColumns(10);
		
		JLabel floor = new JLabel("위치");
		panel.add(floor);
		
		tffloor = new JTextField();
		panel.add(tffloor);
		tffloor.setColumns(10);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		panel_1.add(btnOk);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		panel_1.add(btnCancel);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnOk) {
			actionPerformedBtnOk(e);
		}
	}

	private String getNextNo() {
		String deptno = DeptDao.getInstance().getNextNo() + "";
		return deptno;
	}
	protected void actionPerformedBtnOk(ActionEvent e) {
		Department indept = getObject(); //입력된 text 겟오브텍트하나만듬
		if(btnOk.getText().equals("추가")){
			DeptDao.getInstance().insertItem(indept);
			JOptionPane.showMessageDialog(null, "추가되었습니다.");
		}else{ //수정
			DeptDao.getInstance().updateItem(indept);
			JOptionPane.showMessageDialog(null, "수정되었습니다.");
			btnOk.setText("추가"); //btnok를 원상태로 바꿔줌 --"추가"
		}
		clearTf();
	}
	private Department getObject() {
		int deptno = Integer.parseInt(tfdeptno.getText().trim());
		String deptname = tfdeptname.getText().trim();
		int floor = Integer.parseInt(tffloor.getText().trim());
		return new Department(deptno,deptname,floor);
	}
	public void setObject(Department dept){ 
		tfdeptno.setText(dept.getDeptno()+"");
		tfdeptname.setText(dept.getDeptname());
		tffloor.setText(dept.getFloor()+"");
		btnOk.setText("수정");
		
	}
	private void clearTf() {
		tfdeptno.setText(getNextNo());
		tfdeptname.setText("");
		tffloor.setText("");
	}
	protected void actionPerformedBtnCancel(ActionEvent e) {
		clearTf();
	}
}
