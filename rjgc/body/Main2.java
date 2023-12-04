package rjgc.body;

import rjgc.course.CourseModel;
import rjgc.selects.AddSelect;
import rjgc.selects.SelectsModel;
import rjgc.stu.UserModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

public class Main2 extends JFrame {
    public static UserModel userModel;
    public static CourseModel coursemodel;
    public static SelectsModel selectsmodel;

    public Main2() {
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

        // �γ̹���˵���

        // ѡ�ι���˵���
        JMenu select = new JMenu("  ѡ�ι���  ");
        menuBar.add(select);
        JMenuItem addselect = new JMenuItem("���ѡ��");
        JMenuItem updateselect = new JMenuItem("�޸�ѡ��");
        JMenuItem deleteselect = new JMenuItem("ɾ��ѡ��");
        select.add(addselect);
        select.add(updateselect);
        select.add(deleteselect);

        // ϵͳ�˳�
        JLabel exit = new JLabel("    ϵͳ�˳�");
        exit.setForeground(Color.red);
        exit.setFont(new java.awt.Font("����", Font.BOLD, 15));
        menuBar.add(exit);

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
        Main2 main2 = new Main2();
    }
}
