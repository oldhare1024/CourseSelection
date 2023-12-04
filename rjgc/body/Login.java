package rjgc.body;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {
    JTextField nameField;
    JPasswordField passwordField;

    public Login() {
        setTitle("ϵͳ��¼123");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,200,440,270);
        //����˺������ı����ı���
        JLabel nameJLabel = new JLabel("�˺ţ�");
        nameField = new JTextField();
        JLabel passJLabel = new JLabel("���룺");
        passwordField = new JPasswordField();
        nameJLabel.setBounds(60, 15, 100, 100);
        nameField.setBounds(120, 50, 200, 30);
        passJLabel.setBounds(60, 70, 100, 100);
        passwordField.setBounds(120, 100, 200, 30);
        //��¼ȡ����ť
        JButton loginButton = new JButton("��¼");
        JButton registerButton = new JButton("ע��");
        JButton changepwdButton = new JButton("�޸�����");
        JButton cancelButton = new JButton("ȡ��");
        loginButton.setBounds(10, 150, 80, 30);
        registerButton.setBounds(110, 150, 80, 30);
        changepwdButton.setBounds(210, 150, 100, 30);
        cancelButton.setBounds(330, 150, 80, 30);
        //��ӵ����
        add(nameJLabel);
        add(nameField);
        add(passJLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
        add(changepwdButton);
        add(cancelButton);
        //����¼ȡ����ť��ӿؼ�
        loginButton.setActionCommand("��¼");
        registerButton.setActionCommand("ע��");
        changepwdButton.setActionCommand("�޸�����");
        cancelButton.setActionCommand("ȡ��");
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
        if (e.getActionCommand().equals("��¼")) {
            try {
                rjgc.body.DBUtil.initst();
                String stuname = nameField.getText();
                String stupwd = passwordField.getText();
                //�������ݿ���е��û������������ƥ��
                rjgc.body.DBUtil.rs = rjgc.body.DBUtil.st.executeQuery("select password,level from user where name='" + stuname + "'");
                if (rjgc.body.DBUtil.rs.next()) {
                    if (rjgc.body.DBUtil.rs.getString(1).equals(stupwd)) {
                        //���������ȷ����ʾ��ҳ��
                        this.dispose();
                        String level1 = "1";
                        String level = rjgc.body.DBUtil.rs.getString(2);
                        if (level.equals(level1))
                            new Main().show();
                        else
                            new Main2().show();
                    } else {
                        //���������󵯳���
                        JOptionPane.showMessageDialog(null, "�����������ϵ����Ա");
                    }
                } else {
                    //�������Ե�����ʾ��
                    JOptionPane.showMessageDialog(null, "�û�������");
                }
                rjgc.body.DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else if (e.getActionCommand().equals("ע��")) {
            try {
                rjgc.body.DBUtil.initst();
                String stuname = nameField.getText();
                String stupwd = passwordField.getText();
                String stulv = "2";
                //�������ݿ���е��û������������ƥ��
                rjgc.body.DBUtil.rs = rjgc.body.DBUtil.st.executeQuery("select password from user where name='" + stuname + "'");
                if (rjgc.body.DBUtil.rs.next()) {
                    //����û����Ѵ��ڵ�����ʾ��
                    JOptionPane.showMessageDialog(null, "�û����Ѵ���");
                } else {
                    //����û�����������������û���Ϣ
                    rjgc.body.DBUtil.st.executeUpdate("insert into user(name,password,level) values('" + stuname + "','" + stupwd + "','" + stulv + "')");
                    JOptionPane.showMessageDialog(null, "ע��ɹ�");
                }
                rjgc.body.DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getActionCommand().equals("�޸�����")) {
            ChangePwd cp = new ChangePwd();
        } else {
            System.exit(0);
        }
    }
}
