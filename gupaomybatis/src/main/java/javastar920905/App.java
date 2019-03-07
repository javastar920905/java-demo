package javastar920905;

import javastar920905.dao.MsgMapper;
import javastar920905.entity.Msg;
import javastar920905.mybatis.executor.SimpleExecutor;
import javastar920905.mybatis.session.OzxConfiguration;
import javastar920905.mybatis.session.OzxSqlSession;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        OzxSqlSession sqlSession=new OzxSqlSession(new OzxConfiguration(),new SimpleExecutor());
        MsgMapper mapper = sqlSession.getMapper(MsgMapper.class);
        Msg msg = mapper.selectByPrimaryKey(2671);
        System.out.println(msg.toString());
    }
}
