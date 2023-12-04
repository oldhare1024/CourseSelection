package rjgc.body;

import rjgc.course.AddCourse;
import rjgc.course.CourseModel;
import rjgc.course.UpdateCourse;
import rjgc.selects.AddSelect;
import rjgc.selects.SelectsModel;
import rjgc.stu.AddStu;
import rjgc.stu.UpdateStu;
import rjgc.stu.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

public class Main extends JFrame {
	public static UserModel userModel;
	public static CourseModel coursemodel;
	public static SelectsModel selectsmodel;

	public Main() {
		setTitle("学生选课管理系统");
		setBounds(300, 100, 1000, 800);

		String sql = "select * from stu";
		userModel = new UserModel();
		userModel.init(sql);

		String sql1 = "select * from course";
		coursemodel = new CourseModel();
		coursemodel.init(sql1);

		String sql2 = "SELECT sc.sno,stu.name,sc.cno,course.cname,credit,score FROM sc,stu,course where sc.sno=stu.sno and course.cno=sc.cno";
		selectsmodel = new SelectsModel();
		selectsmodel.init(sql2);

		// 学生表
		JTable jTable = new JTable();

		jTable.setModel(userModel);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable);

		add(jScrollPane, BorderLayout.CENTER);
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// 学生信息菜单栏
		JMenu user = new JMenu("  学生管理  ");
		menuBar.add(user);
		JMenuItem addstu = new JMenuItem("增加学生");
		JMenuItem updatestu = new JMenuItem("修改学生");
		JMenuItem deletestu = new JMenuItem("删除学生");
		user.add(addstu);
		user.add(updatestu);
		user.add(deletestu);

		// 课程管理菜单栏
		JMenu course = new JMenu("  课程管理  ");
		menuBar.add(course);
		JMenuItem addcourse = new JMenuItem("增加课程");
		JMenuItem updatecourse = new JMenuItem("修改课程");
		JMenuItem deletecourse = new JMenuItem("删除课程");
		course.add(addcourse);
		course.add(updatecourse);
		course.add(deletecourse);

		// 选课管理菜单栏
		JMenu select = new JMenu("  选课管理  ");
		menuBar.add(select);
		JMenuItem addselect = new JMenuItem("添加选课");
		JMenuItem updateselect = new JMenuItem("修改选课");
		JMenuItem deleteselect = new JMenuItem("删除选课");
		select.add(addselect);
		select.add(updateselect);
		select.add(deleteselect);

		// 查询管理菜单栏
		JMenu query = new JMenu("  查询管理  ");
		menuBar.add(query);
		JMenuItem querystu = new JMenuItem("查询所有学生");
		JMenuItem querycourse = new JMenuItem("查询所有课程");
		JMenuItem queryselect = new JMenuItem("查询所有选课");
		query.add(querystu);
		query.add(querycourse);
		query.add(queryselect);

		// 系统退出
		JLabel exit = new JLabel("    系统退出");
		exit.setForeground(Color.red);
		exit.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
		menuBar.add(exit);

		// 查询区
		JPanel jPaneln = new JPanel();
		JLabel nameJLabel = new JLabel("学号/课程号：");
		JTextField nameField = new JTextField(20);
		JButton selectbtn = new JButton("查询");
		jPaneln.add(nameJLabel);
		jPaneln.add(nameField);
		jPaneln.add(selectbtn);
		add(jPaneln, BorderLayout.NORTH);
		// 查询学号/课程号按钮
		selectbtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String stu = nameField.getText();
				String sql = "select * from stu where sno='" + stu + "'";
				UserModel.reload(sql);
				String sql1 = "select * from course where cno='" + stu + "'";
				CourseModel.reload(sql1);
				String sql2 = "SELECT sc.sno,stu.name,sc.cno,course.cname,credit,score FROM sc,stu,course where sc.sno=stu.sno and course.cno=sc.cno and sc.sno='"
						+ stu + "'";
				SelectsModel.reload(sql2);

			}
		});

		/*
		 * 学生管理
		 */
		// 增加学生信息
		addstu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				new AddStu().show();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 删除学生信息
		deletestu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object user = userModel.rowData.get(index);
					String sno = ((Vector) user).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from stu where sno='" + sno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "删除成功");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
						UserModel.reload(null);
						DBUtil.closeDB();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 修改学生信息
		updatestu.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object user = userModel.rowData.get(index);
					new UpdateStu(user).show();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		/*
		 * 课程管理
		 */
		// 增加课程信息
		addcourse.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				new AddCourse().show();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 删除课程信息
		deletecourse.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object course = coursemodel.rowData1.get(index);
					String cno = ((Vector) course).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from course where cno='" + cno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "删除成功");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
						CourseModel.reload(null);
						DBUtil.closeDB();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 修改课程信息
		updatecourse.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object course = coursemodel.rowData1.get(index);
					new UpdateCourse(course).show();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		/*
		 * 学生选课管理
		 */
		// 增加学生选课信息
		addselect.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				new AddSelect().show();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 删除学生选课信息
		deleteselect.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object select = selectsmodel.rowData2.get(index);
					String sno = ((Vector) select).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from sc where sno='" + sno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "删除成功");
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
						}
						SelectsModel.reload(null);
						DBUtil.closeDB();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		// 修改学生选课信息
		updateselect.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				int index = jTable.getSelectedRow();
				if (index == -1) {
					JOptionPane.showMessageDialog(null, "未选中");
				} else {
					Object select = selectsmodel.rowData2.get(index);
					new rjgc.selects.UpdateSelect(select).show();
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		/*
		 * 查询管理
		 */
		// 查询所有学生信息
		querystu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 刷新jTable并把jTable显示出来
				UserModel.reload(null);
				jTable.setModel(userModel);
			}
		});
		// 查询所有课程信息
		querycourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 刷新jTable并把jTable显示出来
				CourseModel.reload(null);
//				jTable.show();
				jTable.setModel(coursemodel);
			}
		});
		// 查询所有学生选课信息
		queryselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 刷新jTable并把jTable显示出来
				SelectsModel.reload(null);
//				jTable.show();
				jTable.setModel(selectsmodel);
			}
		});
		/*
		 * 系统退出
		 */
		exit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				// 处理鼠标点击
			}

			public void mouseEntered(MouseEvent e) {
				// 处理鼠标移入
			}

			public void mouseExited(MouseEvent e) {
				// 处理鼠标离开
			}

			public void mousePressed(MouseEvent e) {
				// 处理鼠标按下
			}

			public void mouseReleased(MouseEvent e) {
				// 处理鼠标释放
			}
		});
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main main = new Main();
	}
}
