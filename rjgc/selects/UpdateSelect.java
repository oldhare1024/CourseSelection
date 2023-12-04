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
        setTitle("�޸��û�");
        setLayout(null);
        setBounds(300, 300, 500, 500);
        //����ı��ؼ�
        JLabel snoJLabel = new JLabel("ѧ��");
        JLabel nameJLabel = new JLabel("����");
        JLabel cnoJLabel = new JLabel("�γ̺�");
        JLabel cnameJLabel = new JLabel("�γ���");
        JLabel creditJLabel = new JLabel("ѧ��");
        JLabel scoreJLabel = new JLabel("�ɼ�");
        //�������ݵ��ı���
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
        //�����ؼ���С��λ��
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        //�������䲻�ɸ���
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
        //�ɼ�����
        scoreJLabel.setBounds(50, 270, 100, 100);
        scoreField.setBounds(100, 300, 200, 30);


        JButton yesButton = new JButton("ȷ��");
        yesButton.setBounds(100, 350, 80, 30);
        JButton noButton = new JButton("ȡ��");
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
        //���޸ĵ�ȷ��ȡ����ť���ü����¼�
        yesButton.setActionCommand("ȷ��");
        yesButton.addActionListener(this);
        noButton.setActionCommand("ȡ��");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        UpdateSelect UpdateSelect = new UpdateSelect(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ȷ��")) {
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
                    JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
                } else {
                    JOptionPane.showMessageDialog(null, "�޸�ʧ��");
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