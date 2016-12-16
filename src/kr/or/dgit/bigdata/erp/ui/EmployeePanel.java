package kr.or.dgit.bigdata.erp.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class EmployeePanel extends JPanel implements ActionListener {
	private JTextField tfempno;
	private JTextField tfempname;
	private JTextField tfsalary;

	private JButton btnAdd;
	private JButton btncancel;
	private JComboBox<Title> cbtitle;
	private JComboBox<Employee> cbmanager;
	private JComboBox<Department> cbdno;
	/**
	 * Create the panel.
	 */
	public EmployeePanel() {
		setBorder(new EmptyBorder(10, 40, 10, 40));
		setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblempno = new JLabel("사번");
		lblempno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblempno);
		
		tfempno = new JTextField();
		add(tfempno);
		tfempno.setColumns(10);
		
		JLabel lblempname = new JLabel("사원이름");
		lblempname.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblempname);
		
		tfempname = new JTextField();
		tfempname.setColumns(10);
		add(tfempname);
		
		JLabel lbltitle = new JLabel("직책");
		lbltitle.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbltitle);
		
		cbtitle = new JComboBox<>();
		List<Title> listtitle = TitleDao.getInstance().selectItemByAll();
		for(Title t :listtitle){
			cbtitle.addItem(t);
		}
		add(cbtitle);
		
		JLabel lblmanager = new JLabel("매니저");
		lblmanager.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblmanager);
		
		cbmanager = new JComboBox<>();
		List<Employee> emplist = EmpDao.getInstance().selectItemByAll();
		for(Employee e :emplist){
			cbmanager.addItem(e);
		}
		add(cbmanager);
		
		JLabel lblsalary = new JLabel("급여");
		lblsalary.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lblsalary);
		
		tfsalary = new JTextField();
		tfsalary.setColumns(10);
		add(tfsalary);
		
		JLabel lbldno = new JLabel("부서번호");
		lbldno.setHorizontalAlignment(SwingConstants.RIGHT);
		add(lbldno);
		
		cbdno = new JComboBox<>();
		List<Department> deptlist = DeptDao.getInstance().selectItemByAll();
		for(Department d :deptlist){
			cbdno.addItem(d);
		}
		add(cbdno);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		add(btnAdd);
		
		btncancel = new JButton("취소");
		btncancel.addActionListener(this);
		add(btncancel);

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btncancel) {
			actionPerformedBtncancel(e);
		}
		if (e.getSource() == btnAdd) {
			actionPerformedBtnAdd(e);
		}
	}
	private void clearTf() {
		tfempno.setText("");
		tfempname.setText("");
		cbtitle.setSelectedIndex(0);
		cbmanager.setSelectedIndex(0);
		cbdno.setSelectedIndex(0);
	}
	//추가확인
	protected void actionPerformedBtnAdd(ActionEvent e) {
		Employee inemp = getObject();
		if(btnAdd.getText().equals("추가")){
			EmpDao.getInstance().insertItem(inemp);
			JOptionPane.showMessageDialog(null, "추가되었습니다.");
		}else{ //수정
			EmpDao.getInstance().updateItem(inemp);
			JOptionPane.showMessageDialog(null, "수정되었습니다.");
			btnAdd.setText("추가"); //btnok를 원상태로 바꿔줌 --"추가"
		}
		clearTf();
	}
	private Employee getObject() {
		int empno = Integer.parseInt(tfempno.getText().trim());
		String empname = tfempname.getText().trim();
		Title title = (Title) cbtitle.getSelectedItem();
		Employee manager = (Employee) cbmanager.getSelectedItem();
		int salary = Integer.parseInt(tfsalary.getText().trim());
		Department dept= (Department) cbdno.getSelectedItem();
		return new Employee(empno, empname,  title,  manager, salary,  dept);
	}
	public void setObject(Employee emp) {
		tfempno.setText(emp.getEmpno()+"");
		tfempname.setText(emp.getEmpname());
		cbtitle.setSelectedItem(emp.getTitle());
		cbmanager.setSelectedItem(emp.getManager());
		tfsalary.setText(emp.getSalary()+"");
		cbdno.setSelectedItem(emp.getDept());
		btnAdd.setText("수정");
	}
	//취소
	protected void actionPerformedBtncancel(ActionEvent e) {
		clearTf();
	}
}
