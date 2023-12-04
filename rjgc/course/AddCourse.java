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
        setTitle("��ӿγ�");
        setLayout(null);
        setBounds(300, 350, 500, 500);
        //����ı��ؼ�
        JLabel cnoJLabel = new JLabel("�γ̺�");
        JLabel cnameJLabel = new JLabel("�γ���");
        JLabel creditJLabel = new JLabel("ѧ��");
        JLabel cbookJLabel = new JLabel("�鼮");
        JLabel cteacherJLabel = new JLabel("��ʦ");
        JLabel ctimeJLabel = new JLabel("ѧʱ");

        cnoField = new JTextField();
        cnameField = new JTextField();
        creditField = new JTextField();
        cbookField = new JTextField();
        cteacherField = new JTextField();
        ctimeField = new JTextField();

        //���ÿؼ�λ�úʹ�С
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
        yesButton.setBounds(100, 350, 80, 30);
        JButton noButton = new JButton("ȡ��");
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
        //������û����õ�ȷ����ȡ����ť�ļ����¼�
        yesButton.setActionCommand("ȷ��");
        yesButton.addActionListener(this);
        noButton.setActionCommand("ȡ��");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        AddCourse AddCourse = new AddCourse();
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

                int n = DBUtil.st.executeUpdate("insert into course values('" + cno + "','" + cname + "','" + credit + "','" + cbook + "','" + cteacher + "','" + ctime + "')");
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
            this.dispose();
            CourseModel.reload(null);
        }
    }
}