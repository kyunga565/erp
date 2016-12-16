package kr.or.dgit.bigdata.erp.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Title;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UiPanel extends JPanel implements ActionListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JButton btnOk;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public UiPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 10));
		add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("번호");
		pMain.add(lblNo);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tfNo = new JTextField();
		tfNo.setEditable(false);
		tfNo.setText(getNextNo());
		pMain.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblName);
		
		tfName = new JTextField();
		pMain.add(tfName);
		tfName.setColumns(10);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		
		btnOk = new JButton("추가");
		btnOk.addActionListener(this);
		pBtn.add(btnOk);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

	}

	private String getNextNo() {
		String no = TitleDao.getInstance().getNextNo() + "";
		return no;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnOk) {
			actionPerformedBtnOk(e);
		}
	}
	protected void actionPerformedBtnOk(ActionEvent e) {
		Title inTitle = getObject(); //입력된 text 겟오브텍트하나만듬
		if(btnOk.getText().equals("추가")){
			TitleDao.getInstance().insertItem(inTitle);
			JOptionPane.showMessageDialog(null, "추가되었습니다.");
		}else{ //수정
			TitleDao.getInstance().updateItem(inTitle);
			JOptionPane.showMessageDialog(null, "수정되었습니다.");
			btnOk.setText("추가"); //btnok를 원상태로 바꿔줌 --"추가"
		}
		clearTf();
	}
	private void clearTf() {
		tfNo.setText(getNextNo());
		tfName.setText("");
	}
	public void setObject(Title title){ //get만들면 set도 만들어줘여ㅑ
		tfNo.setText(title.getNo()+"");
		tfName.setText(title.getTitlename());
		btnOk.setText("수정");
		
	}
	private Title getObject() {
		int no = Integer.parseInt(tfNo.getText().trim());
		String titleName = tfName.getText().trim();
		return new Title(no,titleName);
	}

	protected void actionPerformedBtnCancel(ActionEvent e) {
		clearTf();
	}
	
}






