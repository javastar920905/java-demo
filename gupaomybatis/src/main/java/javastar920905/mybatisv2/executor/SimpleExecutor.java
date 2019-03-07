package javastar920905.mybatisv2.executor;

import javastar920905.mybatisv2.binding.OzxMapperRegistry;
import javastar920905.mybatisv2.executor.statement.OzxStatementHandler;
import javastar920905.mybatisv2.session.OzxConfiguration;

/**
 * @author ouzhx on 2019/3/7.
 */
public class SimpleExecutor implements OzxExecutor {
    private OzxConfiguration configuration;

    public SimpleExecutor(OzxConfiguration configuration) {
        this.configuration = configuration;
    }


    @Override
    public <T> T query(OzxMapperRegistry.MapperData mapperData, Object parameter) {

        //初始化statementHandler->parameterHandler->ResultSetHandler
        OzxStatementHandler statementHandler=new OzxStatementHandler(configuration);

        return statementHandler.query(mapperData,parameter );

//        // 1.注册驱动
//        Connection conn = null;
//        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;
//        Msg result = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//
//            // 2.建立连接
//            String url = "jdbc:mysql://dbserver:3306/tax_market_dev";
//            String user = "apple";
//            //todo password
//            String password = "";
//            conn = DriverManager.getConnection(url, user, password);
//
//            // 3.创建语句
//            preparedStatement = conn.prepareStatement(String.format(mapperData.getSql(), parameter));
//
//            // 4.执行语句
//            rs = preparedStatement.executeQuery();
//
//            // 5.处理结果（依次打印出 user 表中的4列基本数据项的值）
//            while (rs.next()) {
//                result = new Msg();
//                result.setId(rs.getString(1));
//                result.setName(rs.getString(2));
//            }
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            // 6.释放资源
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return (T) result;
    }

    public OzxConfiguration getConfiguration() {
        return configuration;
    }
}
