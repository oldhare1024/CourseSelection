package rjgc.selects;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class UpdateSelect extends JFrame implements ActionListener {
    JTextField snoField;
    JTextField nameField;
    JTextField cnoField;
    JTextField cnameField;
    JTextField creditField;
    JTextField scoreField;

    public UpdateSelect(Object stu) {
        setTitle("修改用户");
        setLayout(null);
        setBounds(300, 300, 500, 500);
        //添加文本控件
        JLabel snoJLabel = new JLabel("学号");
        JLabel nameJLabel = new JLabel("姓名");
        JLabel cnoJLabel = new JLabel("课程号");
        JLabel cnameJLabel = new JLabel("课程名");
        JLabel creditJLabel = new JLabel("学分");
        JLabel scoreJLabel = new JLabel("成绩");
        //传输内容到文本框
        if (stu != null) {
            snoField = new JTextField(((Vector) stu).get(0).toString());
            nameField = new JTextField(((Vector) stu).get(1).toString());
            cnoField = new JTextField(((Vector) stu).get(2).toString());
            cnameField = new JTextField(((Vector) stu).get(3).toString());
            creditField = new JTextField(((Vector) stu).get(4).toString());
            scoreField = new JTextField(((Vector) stu).get(5).toString());
        } else {
            snoField = new JTextField();
            nameField = new JTextField();
            cnoField = new JTextField();
            cnameField = new JTextField();
            creditField = new JTextField();
            scoreField = new JTextField();
        }
        //调整控件大小和位置
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        //把输入框变不可更改
        snoField.setEditable(false);

        nameJLabel.setBounds(50, 70, 100, 100);
        nameField.setBounds(100, 100, 200, 30);
        nameField.setEditable(false);

        cnoJLabel.setBounds(50, 120, 100, 100);
        cnoField.setBounds(100, 150, 200, 30);

        cnameJLabel.setBounds(50, 170, 100, 100);
        cnameField.setBounds(100, 200, 200, 30);
        cnameField.setEditable(false);

        creditJLabel.setBounds(50, 220, 100, 100);
        creditField.setBounds(100, 250, 200, 30);
        creditField.setEditable(false);
        //成绩区域
        scoreJLabel.setBounds(50, 270, 100, 100);
        scoreField.setBounds(100, 300, 200, 30);


        JButton yesButton = new JButton("确定");
        yesButton.setBounds(100, 350, 80, 30);
        JButton noButton = new JButton("取消");
        noButton.setBounds(200, 350, 80, 30);
        add(snoJLabel);
        add(snoField);
        add(nameJLabel);
        add(nameField);
        add(cnoJLabel);
        add(cnoField);
        add(cnameJLabel);
        add(cnameField);
        add(creditJLabel);
        add(creditField);
        add(creditJLabel);
        add(creditField);
        add(scoreJLabel);
        add(scoreField);
        add(yesButton);
        add(noButton);
        //给修改的确定取消按钮设置监听事件
        yesButton.setActionCommand("确定");
        yesButton.addActionListener(this);
        noButton.setActionCommand("取消");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        UpdateSelect UpdateSelect = new UpdateSelect(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定")) {
            try {
                DBUtil.initst();
                String sno = snoField.getText();
                String stuname = nameField.getText();
                String cno = cnoField.getText();
                String cname = cnameField.getText();
                String credit = creditField.getText();
                String score = scoreField.getText();
                int n = DBUtil.st.executeUpdate("update sc set cno='" + cno + "',score='" + score + "' where sno='" + sno + "'");
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
            this.dispose();
            SelectsModel.reload(null);
        }
    }
}