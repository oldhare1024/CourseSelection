package rjgc.course;

import rjgc.body.DBUtil;
import rjgc.body.Main;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.Vector;

public class CourseModel extends AbstractTableModel {
    public Vector clumnNames1, rowData1;

    //刷新jTable
    public static void reload(String sql) {
        if (sql == null) {
            sql = "select * from course";
        }
        Main.coursemodel.init(sql);
        Main.coursemodel.fireTableDataChanged();
    }

    public void init(String sql) {
        clumnNames1 = new Vector();
        clumnNames1.add("课程号");
        clumnNames1.add("课程名");
        clumnNames1.add("学分");
        clumnNames1.add("教材");
        clumnNames1.add("教师");
        clumnNames1.add("学时");
        rowData1 = new Vector();
        try {
            DBUtil.initps(sql);
            while (DBUtil.rs.next()) {
                Vector row = new Vector();
                row.add(DBUtil.rs.getString(1));
                row.add(DBUtil.rs.getString(2));
                row.add(DBUtil.rs.getString(3));
                row.add(DBUtil.rs.getString(4));
                row.add(DBUtil.rs.getString(5));
                row.add(DBUtil.rs.getString(6));
                rowData1.add(row);
            }
            DBUtil.closeDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return clumnNames1.size();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowData1.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return ((Vector) rowData1.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) clumnNames1.get(column);
    }
}
