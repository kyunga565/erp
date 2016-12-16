package kr.or.dgit.bigdata.erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Employee;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.ui.DepartmentPanel;
import kr.or.dgit.bigdata.erp.ui.EmployeePanel;
import kr.or.dgit.bigdata.erp.ui.UiPanel;
import kr.or.dgit.bigdata.erp.ui.list.AbstractList;
import kr.or.dgit.bigdata.erp.ui.list.DepartmentList;
import kr.or.dgit.bigdata.erp.ui.list.EmployeeList;
import kr.or.dgit.bigdata.erp.ui.list.TitleList;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ErpMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mnTitleList;
	private JMenuItem mnTitleAdd;
	private JMenuItem mnTitleUpdate;
	private JMenuItem mnTitleDel;
	private TitleList titleList;
	private JMenu mnDept;
	private JMenuItem mnDeptAdd;
	private JMenuItem mnDeptUpdate;
	private JMenuItem mnDeptDel;
	private JMenuItem mnDeptList;
	private JMenu mnEmp;
	private JMenuItem mnEmpAdd;
	private JMenuItem mnEmpUpdate;
	private JMenuItem mnEmpDel;
	private JMenuItem mnEmpList;
	private DepartmentList deptList;
	private EmployeeList empList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpMain frame = new ErpMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ErpMain() {
		setTitle("Erp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTittle = new JMenu("직책관리");
		menuBar.add(mnTittle);
		
		mnTitleAdd = new JMenuItem("직책추가");
		mnTitleAdd.addActionListener(this);
		mnTittle.add(mnTitleAdd);
		
		mnTitleUpdate = new JMenuItem("직책수정");
		mnTitleUpdate.addActionListener(this);
		mnTittle.add(mnTitleUpdate);
		
		mnTitleDel = new JMenuItem("직책삭제");
		mnTitleDel.addActionListener(this);
		mnTittle.add(mnTitleDel);
		
		mnTitleList = new JMenuItem("직책리스트");
		mnTitleList.addActionListener(this);
		mnTittle.add(mnTitleList);
		
		mnDept = new JMenu("부서관리");
		mnDept.addActionListener(this);
		menuBar.add(mnDept);
		
		mnDeptAdd = new JMenuItem("부서추가");
		mnDeptAdd.addActionListener(this);
		mnDept.add(mnDeptAdd);
		
		mnDeptUpdate = new JMenuItem("부서수정");
		mnDeptUpdate.addActionListener(this);
		mnDept.add(mnDeptUpdate);
		
		mnDeptDel = new JMenuItem("부서삭제");
		mnDeptDel.addActionListener(this);
		mnDept.add(mnDeptDel);
		
		mnDeptList = new JMenuItem("부서리스트");
		mnDeptList.addActionListener(this);
		mnDept.add(mnDeptList);
		
		mnEmp = new JMenu("사원관리");
		menuBar.add(mnEmp);
		
		mnEmpAdd = new JMenuItem("사원추가");
		mnEmpAdd.addActionListener(this);
		mnEmp.add(mnEmpAdd);
		
		mnEmpUpdate = new JMenuItem("사원수정");
		mnEmpUpdate.addActionListener(this);
		mnEmp.add(mnEmpUpdate);
		
		mnEmpDel = new JMenuItem("사원삭제");
		mnEmpDel.addActionListener(this);
		mnEmp.add(mnEmpDel);
		
		mnEmpList = new JMenuItem("사원리스트");
		mnEmpList.addActionListener(this);
		mnEmp.add(mnEmpList);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnEmpDel) {
			actionPerformedMnEmpDel(e);
		}
		if (e.getSource() == mnEmpUpdate) {
			actionPerformedMnEmpUpdate(e);
		}
		if (e.getSource() == mnEmpAdd) {
			actionPerformedMnEmpAdd(e);
		}
		if (e.getSource() == mnEmpList) {
			actionPerformedMnEmpList(e);
		}
		if (e.getSource() == mnDeptDel) {
			actionPerformedMnDeptDel(e);
		}
		if (e.getSource() == mnDeptUpdate) {
			actionPerformedMnDeptUpdate(e);
		}
		if (e.getSource() == mnDeptAdd) {
			actionPerformedMnDeptAdd(e);
		}
		if (e.getSource() == mnDept) {
			actionPerformedMnDept(e);
		}
		if (e.getSource() == mnDeptList) {
			actionPerformedMnDeptList(e);
		}
		if (e.getSource() == mnTitleDel) {
			actionPerformedMnTitleDel(e);
		}
		if (e.getSource() == mnTitleUpdate) {
			actionPerformedMnTitleUpdate(e);
		}
		if (e.getSource() == mnTitleAdd) {
			actionPerformedMnTitleAdd(e);
		}
		if (e.getSource() == mnTitleList) {
			actionPerformedMnTitleList(e);
		}
	}
	
	protected void actionPerformedMnTitleList(ActionEvent e) {
		titleList = new TitleList("직책");
		setContentPane(titleList);
		revalidate();
	}
	//직책추가버튼
	protected void actionPerformedMnTitleAdd(ActionEvent e) {
		UiPanel titleUi =  new UiPanel();
		setContentPane(titleUi);
		revalidate();
	}
	//직책수정
	protected void actionPerformedMnTitleUpdate(ActionEvent e) {
		List<Title> list = TitleDao.getInstance().selectItemByAll();
		Title selectedTitle = (Title) JOptionPane.showInputDialog(null, 
									"수정할 직책을 선택하세요", 
									"직책수정", 
									JOptionPane.INFORMATION_MESSAGE, 
									null, 
									list.toArray(),
									list.get(0));
		
		UiPanel updatePanel = new UiPanel();
		updatePanel.setObject(selectedTitle);
		setContentPane(updatePanel);
		revalidate();
	}
	//직책삭제
	protected void actionPerformedMnTitleDel(ActionEvent e) {
		List<Title> list = TitleDao.getInstance().selectItemByAll();
		Title selectedTitle = (Title) JOptionPane.showInputDialog(null, 
									"삭제할 직책을 선택하세요", 
									"직책삭제", 
									JOptionPane.INFORMATION_MESSAGE, 
									null, 
									list.toArray(),
									list.get(0));
		TitleDao.getInstance().deleteItemByNo(selectedTitle.getNo());
		JOptionPane.showMessageDialog(null, selectedTitle.toString()+"삭제되었습니다.");
		
		if(titleList != null){//보이는상태라면
			titleList.reloadData(); //리로드데이터를 다시호출
		}
	}
	//부서관ㄹㅣ
	private void actionPerformedMnDept(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	//부서목록
	protected void actionPerformedMnDeptList(ActionEvent e) {
		deptList = new DepartmentList("부서");
		setContentPane(deptList);
		revalidate();
	}
	//부서추가
	protected void actionPerformedMnDeptAdd(ActionEvent e) {
		DepartmentPanel deptui =  new DepartmentPanel();
		setContentPane(deptui);
		revalidate();
	}
	//부서수정
	protected void actionPerformedMnDeptUpdate(ActionEvent e) {
		List<Department> deptlist = DeptDao.getInstance().selectItemByAll();
		Department selecteddept =  (Department) JOptionPane.showInputDialog(null, 
									"수정할 직책을 선택하세요", 
									"직책수정", 
									JOptionPane.INFORMATION_MESSAGE, 
									null, 
									deptlist.toArray(),
									deptlist.get(0));
		
		DepartmentPanel updatePanel = new DepartmentPanel();
		updatePanel.setObject(selecteddept);
		setContentPane(updatePanel);
		revalidate();
		}
	//부서삭제
	protected void actionPerformedMnDeptDel(ActionEvent e) {
		List<Department> List = DeptDao.getInstance().selectItemByAll();
			Department selecteddept = (Department) JOptionPane.showInputDialog(null, 
										"삭제할 직책을 선택하세요", 
										"직책삭제", 
										JOptionPane.INFORMATION_MESSAGE, 
										null, 
										List.toArray(),
										List.get(0));
			DeptDao.getInstance().deleteItemByNo(selecteddept.getDeptno());
			JOptionPane.showMessageDialog(null, selecteddept.toString()+"삭제되었습니다.");
			
			if(deptList != null){//보이는상태라면
				((DepartmentList) deptList).reloadData(); //리로드데이터를 다시호출
			}
	}
	//사원리스트불러오기
	protected void actionPerformedMnEmpList(ActionEvent e) {
		EmployeeList empList= new EmployeeList("사원");
		setContentPane(empList);
		revalidate();
	}
	//추가
	protected void actionPerformedMnEmpAdd(ActionEvent e) {
		EmployeePanel empui =  new EmployeePanel();
		setContentPane(empui);
		revalidate();
	}
	//사원수정
	protected void actionPerformedMnEmpUpdate(ActionEvent e) {
		List<Employee> list = EmpDao.getInstance().selectItemByAll();

		Employee selectedemp = (Employee) JOptionPane.showInputDialog(null, "수정할 사원을 선택하세요", "사원 수정",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(3));

		
		EmployeePanel empPanel = new EmployeePanel();
		empPanel.setObject(selectedemp);
		setContentPane(empPanel);
		revalidate(); 
	}
	protected void actionPerformedMnEmpDel(ActionEvent e) {
		List<Employee> list = EmpDao.getInstance().selectItemByAll();

		Employee selectedEmp = (Employee) JOptionPane.showInputDialog(null, "삭제할 사원을 선택하세요", "사원 삭제",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(3));

		EmpDao.getInstance().deleteItemByNo(selectedEmp.getEmpno());
		JOptionPane.showMessageDialog(null, selectedEmp + "삭제 되었습니다.");

		if(empList != null){//보이는상태라면
			((EmployeeList) empList).reloadData(); //리로드데이터를 다시호출
		}
	}
}

