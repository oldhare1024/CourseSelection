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
        setTitle("����û�");
        setLayout(null);
        setBounds(300, 300, 500, 500);

        JLabel snoJLabel = new JLabel("ѧ��");
        JLabel cnoJLabel = new JLabel("�γ̺�");

        snoField = new JTextField();
        cnoField = new JTextField();

        //���ÿؼ�λ�úʹ�С
        snoJLabel.setBounds(50, 20, 100, 100);
        snoField.setBounds(100, 50, 200, 30);
        cnoJLabel.setBounds(50, 70, 100, 100);
        cnoField.setBounds(100, 100, 200, 30);

        JButton yesButton = new JButton("ȷ��");
        yesButton.setBounds(100, 300, 80, 30);
        JButton noButton = new JButton("ȡ��");
        noButton.setBounds(200, 300, 80, 30);
        add(snoJLabel);
        add(snoField);
        add(cnoJLabel);
        add(cnoField);

        add(yesButton);
        add(noButton);
        //�����ѡ�����õ�ȷ����ȡ����ť�ļ����¼�
        yesButton.setActionCommand("ȷ��");
        yesButton.addActionListener(this);
        noButton.setActionCommand("ȡ��");
        noButton.addActionListener(this);

        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        AddSelect AddSelect = new AddSelect();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ȷ��")) {
            try {
                DBUtil.initst();
                String sno = snoField.getText();
                String cno = cnoField.getText();

                int n = DBUtil.st.executeUpdate("insert into sc values('" + sno + "','" + cno + "',0)");
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
            SelectsModel.reload(null);
        }
    }
}