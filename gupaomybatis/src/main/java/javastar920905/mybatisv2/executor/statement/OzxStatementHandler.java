package javastar920905.mybatisv2.executor.statement;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import javastar920905.mybatisv2.executor.resultset.OzxResultSetHandler;
import javastar920905.mybatisv2.session.OzxConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author ouzhx on 2019/3/7.
 */
public class OzxStatementHandler {
    private OzxConfiguration ozxConfiguration;

    private OzxResultSetHandler resultSetHandler;

    public OzxStatementHandler(OzxConfiguration ozxConfiguration) {
        this.ozxConfiguration = ozxConfiguration;
        this.resultSetHandler = new OzxResultSetHandler(ozxConfiguration);
    }

    public <E> E query(OzxMapperRegistry.MapperData mapperData, Object parameter) {
        Connection conn = getConnection();

        try {
            // 3.创建语句 todo parameterHandler
            PreparedStatement preparedStatement = conn.prepareStatement(String.format(mapperData.getSql(), Integer.valueOf(String.valueOf(parameter))));

            // 4.执行语句
             preparedStatement.executeQuery();

            //ResultSetHandler
           return (E) resultSetHandler.handler(preparedStatement,mapperData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Connection getConnection() {
        // 1.注册驱动
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            // 2.建立连接
            String url = "jdbc:mysql://dbserver:3306/tax_market_dev";
            String user = "apple";
            //todo password
            String password = "";
            conn = DriverManager.getConnection(url, user, password);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
