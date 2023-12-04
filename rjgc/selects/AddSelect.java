package rjgc.selects;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddSelect extends JFrame implements ActionListener {
    JTextField snoField;
    JTextField cnoField;

    public AddSelect() {
        setTitle("添加用户");
        setLayout(null);
        setBounds(300, 300, 500, 500);

        JLabel snoJLabel = new JLabel("学号");
        JLabel cnoJLabel = new JLabel("课程号");

        snoField = new JTextField();
        cnoField = new JTextField();

        //设置控件位置和大小
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        cnoJLabel.setBounds(50, 70, 100, 100);
        cnoField.setBounds(100, 100, 200, 30);

        JButton yesButton = new JButton("确定");
        yesButton.setBounds(100, 300, 80, 30);
        JButton noButton = new JButton("取消");
        noButton.setBounds(200, 300, 80, 30);
        add(snoJLabel);
        add(snoField);
        add(cnoJLabel);
        add(cnoField);

        add(yesButton);
        add(noButton);
        //给添加选课设置的确定和取消按钮的监听事件
        yesButton.setActionCommand("确定");
        yesButton.addActionListener(this);
        noButton.setActionCommand("取消");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        AddSelect AddSelect = new AddSelect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("确定")) {
            try {
                DBUtil.initst();
                String sno = snoField.getText();
                String cno = cnoField.getText();

                int n = DBUtil.st.executeUpdate("insert into sc values('" + sno + "','" + cno + "',0)");
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
            SelectsModel.reload(null);
        }
    }
}