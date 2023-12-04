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

        // 课程管理菜单栏

        // 选课管理菜单栏
        JMenu select = new JMenu("  选课管理  ");
        menuBar.add(select);
        JMenuItem addselect = new JMenuItem("添加选课");
        JMenuItem updateselect = new JMenuItem("修改选课");
        JMenuItem deleteselect = new JMenuItem("删除选课");
        select.add(addselect);
        select.add(updateselect);
        select.add(deleteselect);

        // 系统退出
        JLabel exit = new JLabel("    系统退出");
        exit.setForeground(Color.red);
        exit.setFont(new java.awt.Font("宋体", Font.BOLD, 15));
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
        Main2 main2 = new Main2();
    }
}
