package rjgc.selects;

import rjgc.body.DBUtil;
import rjgc.body.Main;

import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.Vector;

public class SelectsModel extends AbstractTableModel {
    public Vector clumnNames2, rowData2;

    //刷新jTable
    public static void reload(String sql) {
        if (sql == null) {
            sql = "SELECT sc.sno,stu.name,sc.cno,course.cname,credit,score FROM sc,stu,course where sc.sno=stu.sno and course.cno=sc.cno";
        }
        Main.selectsmodel.init(sql);
        Main.selectsmodel.fireTableDataChanged();
    }

    public void init(String sql) {
        clumnNames2 = new Vector();
        clumnNames2.add("学号");
        clumnNames2.add("姓名");
        clumnNames2.add("课程号");
        clumnNames2.add("课程名");
        clumnNames2.add("学分");
        clumnNames2.add("分数");
        rowData2 = new Vector();
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
                rowData2.add(row);
            }
            DBUtil.closeDB();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return clumnNames2.size();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return rowData2.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        // TODO Auto-generated method stub
        return ((Vector) rowData2.get(rowIndex)).get(columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        // TODO Auto-generated method stub
        return (String) clumnNames2.get(column);
    }
}
