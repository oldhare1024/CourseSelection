package rjgc.stu;

import rjgc.body.DBUtil;
import rjgc.body.Main;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.Vector;

public class UserModel extends AbstractTableModel {
    public Vector clumnNames, rowData;

    //刷新jTable
    public static void reload(String sql) {
        if (sql == null) {
            sql = "select * from stu";
        }
        Main.userModel.init(sql);
        Main.userModel.fireTableDataChanged();
    }

    public void init(String sql) {
        clumnNames = new Vector();
        clumnNames.add("学号");
        clumnNames.add("姓名");
        clumnNames.add("性别");
        clumnNames.add("年龄");
        clumnNames.add("院系");
        rowData = new Vector();
        try {
            DBUtil.initps(sql);
            while (DBUtil.rs.next()) {
                Vector row = new Vector();
                row.add(DBUtil.rs.getString(1));
                row.add(DBUtil.rs.getString(2));
                row.add(DBUtil.rs.getString(3));
                row.add(DBUtil.rs.getString(4));
                row.add(DBUtil.rs.getString(5));
                rowData.add(row);
            }
            DBUtil.closeDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return clumnNames.size();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowData.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return ((Vector) rowData.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) clumnNames.get(column);
    }
}
