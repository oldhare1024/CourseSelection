package rjgc.stu;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddStu extends JFrame implements ActionListener {
    JTextField snoField;
    JTextField nameField;
    JTextField sexField;
    JTextField ageField;
    JTextField sdeptField;

    public AddStu() {
        setTitle("����û�");
        setLayout(null);
        setBounds(300, 300, 500, 500);

        JLabel snoJLabel = new JLabel("ѧ��");
        JLabel nameJLabel = new JLabel("����");
        JLabel sexJLabel = new JLabel("�Ա�");
        JLabel ageJLabel = new JLabel("����");
        JLabel sdeptJLabel = new JLabel("ϵ��");

        snoField = new JTextField();
        nameField = new JTextField();
        sexField = new JTextField();
        ageField = new JTextField();
        sdeptField = new JTextField();
        //���ÿؼ�λ�úʹ�С
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        nameJLabel.setBounds(50, 70, 100, 100);
        nameField.setBounds(100, 100, 200, 30);
        sexJLabel.setBounds(50, 120, 100, 100);
        sexField.setBounds(100, 150, 200, 30);
        ageJLabel.setBounds(50, 170, 100, 100);
        ageField.setBounds(100, 200, 200, 30);
        sdeptJLabel.setBounds(50, 220, 100, 100);
        sdeptField.setBounds(100, 250, 200, 30);
        JButton yesButton = new JButton("ȷ��");
        yesButton.setBounds(100, 300, 80, 30);
        JButton noButton = new JButton("ȡ��");
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
        //������û����õ�ȷ����ȡ����ť�ļ����¼�
        yesButton.setActionCommand("ȷ��");
        yesButton.addActionListener(this);
        noButton.setActionCommand("ȡ��");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        AddStu addStu = new AddStu();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ȷ��")) {
            try {
                DBUtil.initst();
                String sno = snoField.getText();
                String stuname = nameField.getText();
                String sex = sexField.getText();
                String age = ageField.getText();
                String sdept = sdeptField.getText();
                int n = DBUtil.st.executeUpdate("insert into stu values('" + sno + "','" + stuname + "','" + sex + "','" + age + "','" + sdept + "')");
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "��ӳɹ�");
                } else {
                    JOptionPane.showMessageDialog(null, "���ʧ��");
                }
                DBUtil.closeDB();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            DBUtil.closeDB();
        } else {
            this.hide();
            UserModel.reload(null);
        }
    }
}