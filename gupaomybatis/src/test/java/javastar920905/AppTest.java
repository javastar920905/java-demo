package javastar920905;


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import javastar920905.dao.MsgMapper;
import javastar920905.entity.Msg;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * Unit test for simple App.
 */
public class AppTest {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        SqlSession sqlSession = getSqlSession();
       // Object o =(Msg) sqlSession.selectOne("select * from msg where id=2671 ");
       MsgMapper mapper = sqlSession.getMapper(MsgMapper.class);
       Msg msg = mapper.selectByPrimaryKey(2671);
        System.out.println(msg.toString());
    }

    public static SqlSession getSqlSession() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://dbserver:3306/tax_market_dev");
        dataSource.setUser("apple");
        dataSource.setPassword("X0dxeLpK");
        Environment environment = new Environment("mysql", new JdbcTransactionFactory(), dataSource);


        Configuration configuration = new Configuration(environment);
        configuration.addMapper(MsgMapper.class);
        MapperBuilderAssistant mapperBuilderAssistant=new MapperBuilderAssistant(configuration, "");


        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(configuration);

        return sqlSessionFactory.openSession();
    }
}
