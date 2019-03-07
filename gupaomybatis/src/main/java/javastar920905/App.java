package javastar920905;


import javastar920905.dao.MsgMapper;
import javastar920905.entity.Msg;
import javastar920905.mybatisv2.executor.SimpleExecutor;
import javastar920905.mybatisv2.session.OzxConfiguration;
import javastar920905.mybatisv2.session.OzxSqlSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        OzxConfiguration ozxConfiguration = new OzxConfiguration();
        OzxSqlSession sqlSession=new OzxSqlSession(ozxConfiguration,new SimpleExecutor(ozxConfiguration));
        MsgMapper mapper = sqlSession.getMapper(MsgMapper.class);
        Msg msg = mapper.selectByPrimaryKey(2671);
        System.out.println(msg.toString());
    }
}
