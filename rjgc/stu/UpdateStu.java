package rjgc.stu;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class UpdateStu extends JFrame implements ActionListener {
    JTextField snoField;
    JTextField nameField;
    JTextField sexField;
    JTextField ageField;
    JTextField sdeptField;

    public UpdateStu(Object stu) {
        setTitle("修改用户");
        setLayout(null);
        setBounds(300, 300, 500, 500);
        //添加文本控件
        JLabel snoJLabel = new JLabel("学号");
        JLabel nameJLabel = new JLabel("姓名");
        JLabel sexJLabel = new JLabel("性别");
        JLabel ageJLabel = new JLabel("年龄");
        JLabel sdeptJLabel = new JLabel("系别");
        //传输内容到文本框
        if (stu != null) {
            snoField = new JTextField(((Vector) stu).get(0).toString());
            nameField = new JTextField(((Vector) stu).get(1).toString());
            sexField = new JTextField(((Vector) stu).get(2).toString());
            ageField = new JTextField(((Vector) stu).get(3).toString());
            sdeptField = new JTextField(((Vector) stu).get(4).toString());
        } else {
            snoField = new JTextField();
            nameField = new JTextField();
            sexField = new JTextField();
            ageField = new JTextField();
            sdeptField = new JTextField();
        }
        //调整控件大小和位置
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        snoField.setEditable(false);
        nameJLabel.setBounds(50, 70, 100, 100);
        nameField.setBounds(100, 100, 200, 30);
        sexJLabel.setBounds(50, 120, 100, 100);
        sexField.setBounds(100, 150, 200, 30);
        ageJLabel.setBounds(50, 170, 100, 100);
        ageField.setBounds(100, 200, 200, 30);
        sdeptJLabel.setBounds(50, 220, 100, 100);
        sdeptField.setBounds(100, 250, 200, 30);
        JButton yesButton = new JButton("确定");
        yesButton.setBounds(100, 300, 80, 30);
        JButton noButton = new JButton("取消");
        noButton.setBounds(200, 300, 80, 30);
        add(snoJLabel);
        add(snoField);
        add(nameJLabel);
        add(nameField);
        add(sexJLabel);
        add(sexField);
        add(ageJLabel);
        add(ageField);
        add(sdeptJLabel);
        add(sdeptField);
        add(yesButton);
        add(noButton);
        //给修改的确定取消按钮设置监听事件
        yesButton.setActionCommand("确定");
        yesButton.addActionListener(this);
        noButton.setActionCommand("取消");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        UpdateStu updateStu = new UpdateStu(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定")) {
            try {
                DBUtil.initst();
                String sno = snoField.getText();
                String stuname = nameField.getText();
                String sex = sexField.getText();
                String age = ageField.getText();
                String sdept = sdeptField.getText();
                int n = DBUtil.st.executeUpdate("update stu set name='" + stuname + "',sex='" + sex + "',age='" + age + "',sdept='" + sdept + "' where sno='" + sno + "'");
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "修改成功");
                } else {
                    JOptionPane.showMessageDialog(null, "修改失败");
                }
                DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            this.hide();
            UserModel.reload(null);
        }
    }
}