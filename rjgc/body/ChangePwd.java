package rjgc.body;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangePwd extends JFrame implements ActionListener {
	JTextField nameField;
	JPasswordField passwordField;

	public ChangePwd() {
		setTitle("�޸�����");
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 350, 300);

		JLabel nameJLabel = new JLabel("�˺�");
		nameField = new JTextField();
		JLabel passJLabel = new JLabel("����");
		passwordField = new JPasswordField();
		nameJLabel.setBounds(50, 15, 100, 100);
		nameField.setBounds(100, 50, 200, 30);
		passJLabel.setBounds(50, 70, 100, 100);
		passwordField.setBounds(100, 100, 200, 30);
		JButton loginButton = new JButton("ȷ��");
		JButton cancelButton = new JButton("ȡ��");
		loginButton.setBounds(100, 150, 80, 30);
		cancelButton.setBounds(200, 150, 80, 30);
		add(nameJLabel);
		add(nameField);
		add(passJLabel);
		add(passwordField);
		add(loginButton);
		add(cancelButton);

		loginButton.setActionCommand("��¼");
		cancelButton.setActionCommand("ȡ��");
		loginButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setVisible(true);
	}

	public static void main(String[] args) {
		ChangePwd changePwd = new ChangePwd();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("��¼")) {
			try {
				rjgc.body.DBUtil.initst();
				String stuname = nameField.getText();
				String stupwd = passwordField.getText();
				int n = rjgc.body.DBUtil.st.executeUpdate("update user set password='" + stupwd + "' where name='" + stuname + "'");
				if (n > 0) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��");
				}
				rjgc.body.DBUtil.closeDB();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		} else {
			this.hide();
			new Login().show();
		}
	}
}
