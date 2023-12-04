package rjgc.course;

import rjgc.body.DBUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Vector;

public class UpdateCourse extends JFrame implements ActionListener {
    JTextField cnoField;
    JTextField cnameField;
    JTextField creditField;
    JTextField cbookField;
    JTextField cteacherField;
    JTextField ctimeField;

    public UpdateCourse(Object stu) {
        setTitle("�޸Ŀγ�");
        setLayout(null);
        setBounds(300, 300, 500, 500);
        //����ı��ؼ�
        JLabel cnoJLabel = new JLabel("�γ̺�");
        JLabel cnameJLabel = new JLabel("�γ���");
        JLabel creditJLabel = new JLabel("ѧ��");
        JLabel cbookJLabel = new JLabel("�̲�");
        JLabel cteacherJLabel = new JLabel("��ʦ");
        JLabel ctimeJLabel = new JLabel("ѧʱ");
        //�������ݵ��ı���
        if (stu != null) {
            cnoField = new JTextField(((Vector) stu).get(0).toString());
            cnameField = new JTextField(((Vector) stu).get(1).toString());
            creditField = new JTextField(((Vector) stu).get(2).toString());
            cbookField = new JTextField(((Vector) stu).get(3).toString());
            cteacherField = new JTextField(((Vector) stu).get(4).toString());
            ctimeField = new JTextField(((Vector) stu).get(5).toString());

        } else {
            cnoField = new JTextField();
            cnameField = new JTextField();
            creditField = new JTextField();
            cbookField = new JTextField();
            cteacherField = new JTextField();
            ctimeField = new JTextField();
        }
        //�����ؼ���С��λ��
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

        JButton yesButton = new JButton("ȷ��");
        yesButton.setBounds(100, 300, 80, 30);
        JButton noButton = new JButton("ȡ��");
        noButton.setBounds(200, 300, 80, 30);

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
        //���޸ĵ�ȷ��ȡ����ť���ü����¼�
        yesButton.setActionCommand("ȷ��");
        yesButton.addActionListener(this);
        noButton.setActionCommand("ȡ��");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        UpdateCourse UpdateCourse = new UpdateCourse(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ȷ��")) {
            try {
                DBUtil.initst();
                String cno = cnoField.getText();
                String cname = cnameField.getText();
                String credit = creditField.getText();
                String cbook = cbookField.getText();
                String cteacher = cteacherField.getText();
                String ctime = ctimeField.getText();

                int n = DBUtil.st.executeUpdate("update course set cno='" + cno + "',cname='" + cname + "',credit='" + credit + "',book='" + cbook + "',teacher='" + cteacher + "',ctime='" + ctime + "' where cno='" + cno + "'");
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
            CourseModel.reload(null);
        }
    }
}