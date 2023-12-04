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
		setTitle("ѧ��ѡ�ι���ϵͳ");
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

		// ѧ����
		JTable jTable = new JTable();

		jTable.setModel(userModel);
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(jTable);

		add(jScrollPane, BorderLayout.CENTER);
		// �˵���
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		// ѧ����Ϣ�˵���
		JMenu user = new JMenu("  ѧ������  ");
		menuBar.add(user);
		JMenuItem addstu = new JMenuItem("����ѧ��");
		JMenuItem updatestu = new JMenuItem("�޸�ѧ��");
		JMenuItem deletestu = new JMenuItem("ɾ��ѧ��");
		user.add(addstu);
		user.add(updatestu);
		user.add(deletestu);

		// �γ̹���˵���
		JMenu course = new JMenu("  �γ̹���  ");
		menuBar.add(course);
		JMenuItem addcourse = new JMenuItem("���ӿγ�");
		JMenuItem updatecourse = new JMenuItem("�޸Ŀγ�");
		JMenuItem deletecourse = new JMenuItem("ɾ���γ�");
		course.add(addcourse);
		course.add(updatecourse);
		course.add(deletecourse);

		// ѡ�ι���˵���
		JMenu select = new JMenu("  ѡ�ι���  ");
		menuBar.add(select);
		JMenuItem addselect = new JMenuItem("���ѡ��");
		JMenuItem updateselect = new JMenuItem("�޸�ѡ��");
		JMenuItem deleteselect = new JMenuItem("ɾ��ѡ��");
		select.add(addselect);
		select.add(updateselect);
		select.add(deleteselect);

		// ��ѯ����˵���
		JMenu query = new JMenu("  ��ѯ����  ");
		menuBar.add(query);
		JMenuItem querystu = new JMenuItem("��ѯ����ѧ��");
		JMenuItem querycourse = new JMenuItem("��ѯ���пγ�");
		JMenuItem queryselect = new JMenuItem("��ѯ����ѡ��");
		query.add(querystu);
		query.add(querycourse);
		query.add(queryselect);

		// ϵͳ�˳�
		JLabel exit = new JLabel("    ϵͳ�˳�");
		exit.setForeground(Color.red);
		exit.setFont(new java.awt.Font("����", Font.BOLD, 15));
		menuBar.add(exit);

		// ��ѯ��
		JPanel jPaneln = new JPanel();
		JLabel nameJLabel = new JLabel("ѧ��/�γ̺ţ�");
		JTextField nameField = new JTextField(20);
		JButton selectbtn = new JButton("��ѯ");
		jPaneln.add(nameJLabel);
		jPaneln.add(nameField);
		jPaneln.add(selectbtn);
		add(jPaneln, BorderLayout.NORTH);
		// ��ѯѧ��/�γ̺Ű�ť
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
		 * ѧ������
		 */
		// ����ѧ����Ϣ
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
		// ɾ��ѧ����Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
				} else {
					Object user = userModel.rowData.get(index);
					String sno = ((Vector) user).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from stu where sno='" + sno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
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
		// �޸�ѧ����Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
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
		 * �γ̹���
		 */
		// ���ӿγ���Ϣ
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
		// ɾ���γ���Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
				} else {
					Object course = coursemodel.rowData1.get(index);
					String cno = ((Vector) course).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from course where cno='" + cno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
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
		// �޸Ŀγ���Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
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
		 * ѧ��ѡ�ι���
		 */
		// ����ѧ��ѡ����Ϣ
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
		// ɾ��ѧ��ѡ����Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
				} else {
					Object select = selectsmodel.rowData2.get(index);
					String sno = ((Vector) select).get(0).toString();
					try {
						DBUtil.initst();
						int n = DBUtil.st.executeUpdate("delete from sc where sno='" + sno + "'");
						if (n > 0) {
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						} else {
							JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
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
		// �޸�ѧ��ѡ����Ϣ
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
					JOptionPane.showMessageDialog(null, "δѡ��");
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
		 * ��ѯ����
		 */
		// ��ѯ����ѧ����Ϣ
		querystu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ˢ��jTable����jTable��ʾ����
				UserModel.reload(null);
				jTable.setModel(userModel);
			}
		});
		// ��ѯ���пγ���Ϣ
		querycourse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ˢ��jTable����jTable��ʾ����
				CourseModel.reload(null);
//				jTable.show();
				jTable.setModel(coursemodel);
			}
		});
		// ��ѯ����ѧ��ѡ����Ϣ
		queryselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ˢ��jTable����jTable��ʾ����
				SelectsModel.reload(null);
//				jTable.show();
				jTable.setModel(selectsmodel);
			}
		});
		/*
		 * ϵͳ�˳�
		 */
		exit.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				// ���������
			}

			public void mouseEntered(MouseEvent e) {
				// �����������
			}

			public void mouseExited(MouseEvent e) {
				// ��������뿪
			}

			public void mousePressed(MouseEvent e) {
				// ������갴��
			}

			public void mouseReleased(MouseEvent e) {
				// ��������ͷ�
			}
		});
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Main main = new Main();
	}
}
