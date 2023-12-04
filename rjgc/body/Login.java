package rjgc.body;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JTextField nameField;
    JPasswordField passwordField;

    public Login() {
        setTitle("系统登录123");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,200,440,270);
        //添加账号密码文本及文本框
        JLabel nameJLabel = new JLabel("账号：");
        nameField = new JTextField();
        JLabel passJLabel = new JLabel("密码：");
        passwordField = new JPasswordField();
        nameJLabel.setBounds(60, 15, 100, 100);
        nameField.setBounds(120, 50, 200, 30);
        passJLabel.setBounds(60, 70, 100, 100);
        passwordField.setBounds(120, 100, 200, 30);
        //登录取消按钮
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");
        JButton changepwdButton = new JButton("修改密码");
        JButton cancelButton = new JButton("取消");
        loginButton.setBounds(10, 150, 80, 30);
        registerButton.setBounds(110, 150, 80, 30);
        changepwdButton.setBounds(210, 150, 100, 30);
        cancelButton.setBounds(330, 150, 80, 30);
        //添加到面板
        add(nameJLabel);
        add(nameField);
        add(passJLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
        add(changepwdButton);
        add(cancelButton);
        //给登录取消按钮添加控件
        loginButton.setActionCommand("登录");
        registerButton.setActionCommand("注册");
        changepwdButton.setActionCommand("修改密码");
        cancelButton.setActionCommand("取消");
        loginButton.addActionListener(this);
        registerButton.addActionListener(this);
        changepwdButton.addActionListener(this);
        cancelButton.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        Login login = new Login();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("登录")) {
            try {
                rjgc.body.DBUtil.initst();
                String stuname = nameField.getText();
                String stupwd = passwordField.getText();
                //根据数据库表中的用户名查密码进行匹配
                rjgc.body.DBUtil.rs = rjgc.body.DBUtil.st.executeQuery("select password,level from user where name='" + stuname + "'");
                if (rjgc.body.DBUtil.rs.next()) {
                    if (rjgc.body.DBUtil.rs.getString(1).equals(stupwd)) {
                        //如果密码正确就显示主页面
                        this.dispose();
                        String level1 = "1";
                        String level = rjgc.body.DBUtil.rs.getString(2);
                        if (level.equals(level1))
                            new Main().show();
                        else
                            new Main2().show();
                    } else {
                        //如果密码错误弹出框
                        JOptionPane.showMessageDialog(null, "密码错误，请联系管理员");
                    }
                } else {
                    //姓名不对弹出提示框
                    JOptionPane.showMessageDialog(null, "用户不存在");
                }
                rjgc.body.DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("注册")) {
            try {
                rjgc.body.DBUtil.initst();
                String stuname = nameField.getText();
                String stupwd = passwordField.getText();
                String stulv = "2";
                //根据数据库表中的用户名查密码进行匹配
                rjgc.body.DBUtil.rs = rjgc.body.DBUtil.st.executeQuery("select password from user where name='" + stuname + "'");
                if (rjgc.body.DBUtil.rs.next()) {
                    //如果用户名已存在弹出提示框
                    JOptionPane.showMessageDialog(null, "用户名已存在");
                } else {
                    //如果用户名不存在则插入新用户信息
                    rjgc.body.DBUtil.st.executeUpdate("insert into user(name,password,level) values('" + stuname + "','" + stupwd + "','" + stulv + "')");
                    JOptionPane.showMessageDialog(null, "注册成功");
                }
                rjgc.body.DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("修改密码")) {
            ChangePwd cp = new ChangePwd();
        } else {
            System.exit(0);
        }
    }
}
