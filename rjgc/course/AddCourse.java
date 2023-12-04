package rjgc.course;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCourse extends JFrame implements ActionListener {
    JTextField cnoField;
    JTextField cnameField;
    JTextField creditField;
    JTextField ctimeField;
    JTextField cteacherField;
    JTextField cbookField;

    public AddCourse() {
        setTitle("添加课程");
        setLayout(null);
        setBounds(300, 350, 500, 500);
        //添加文本控件
        JLabel cnoJLabel = new JLabel("课程号");
        JLabel cnameJLabel = new JLabel("课程名");
        JLabel creditJLabel = new JLabel("学分");
        JLabel cbookJLabel = new JLabel("书籍");
        JLabel cteacherJLabel = new JLabel("教师");
        JLabel ctimeJLabel = new JLabel("学时");

        cnoField = new JTextField();
        cnameField = new JTextField();
        creditField = new JTextField();
        cbookField = new JTextField();
        cteacherField = new JTextField();
        ctimeField = new JTextField();

        //设置控件位置和大小
        cnoJLabel.setBounds(50, 20, 100, 100);
        cnoField.setBounds(100, 50, 200, 30);

        cnameJLabel.setBounds(50, 70, 100, 100);
        cnameField.setBounds(100, 100, 200, 30);

        creditJLabel.setBounds(50, 120, 100, 100);
        creditField.setBounds(100, 150, 200, 30);

        cbookJLabel.setBounds(50, 170, 100, 100);
        cbookField.setBounds(100, 200, 200, 30);

        cteacherJLabel.setBounds(50, 220, 100, 100);
        cteacherField.setBounds(100, 250, 200, 30);

        ctimeJLabel.setBounds(50, 270, 100, 100);
        ctimeField.setBounds(100, 300, 200, 30);

        JButton yesButton = new JButton("确定");
        yesButton.setBounds(100, 350, 80, 30);
        JButton noButton = new JButton("取消");
        noButton.setBounds(200, 350, 80, 30);

        add(cnoJLabel);
        add(cnoField);
        add(cnameJLabel);
        add(cnameField);
        add(creditJLabel);
        add(creditField);
        add(cbookJLabel);
        add(cbookField);
        add(cteacherJLabel);
        add(cteacherField);
        add(ctimeJLabel);
        add(ctimeField);

        add(yesButton);
        add(noButton);
        //给添加用户设置的确定和取消按钮的监听事件
        yesButton.setActionCommand("确定");
        yesButton.addActionListener(this);
        noButton.setActionCommand("取消");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        AddCourse AddCourse = new AddCourse();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定")) {
            try {
                DBUtil.initst();
                String cno = cnoField.getText();
                String cname = cnameField.getText();
                String credit = creditField.getText();
                String cbook = cbookField.getText();
                String cteacher = cteacherField.getText();
                String ctime = ctimeField.getText();

                int n = DBUtil.st.executeUpdate("insert into course values('" + cno + "','" + cname + "','" + credit + "','" + cbook + "','" + cteacher + "','" + ctime + "')");
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "添加成功");
                } else {
                    JOptionPane.showMessageDialog(null, "添加失败");
                }
                DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DBUtil.closeDB();
        } else {
            this.dispose();
            CourseModel.reload(null);
        }
    }
}