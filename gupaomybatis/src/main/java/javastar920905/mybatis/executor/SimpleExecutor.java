package javastar920905.mybatis.executor;

import javastar920905.entity.Msg;

import java.sql.*;

/**
 * @author ouzhx on 2019/3/7.
 */
public class SimpleExecutor implements OzxExecutor {
    @Override
    public <T> T query(String statement, String parameter) {
        // 1.注册驱动
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Msg result = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 2.建立连接
            String url = "jdbc:mysql://dbserver:3306/tax_market_dev";
            String user = "apple";
            //todo password
            String password = "";
            conn = DriverManager.getConnection(url, user, password);

            // 3.创建语句
            preparedStatement = conn.prepareStatement(String.format(statement, Integer.valueOf(parameter)));

            // 4.执行语句
            rs = preparedStatement.executeQuery();

            // 5.处理结果（依次打印出 user 表中的4列基本数据项的值）
            while (rs.next()) {
                result = new Msg();
                result.setId(rs.getString(1));
                result.setContent(rs.getString(2));
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6.释放资源
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return (T) result;
    }
}
