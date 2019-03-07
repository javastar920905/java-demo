package javastar920905.mybatis.executor;

/**
 * @author ouzhx on 2019/3/7.
 */
public interface OzxExecutor {

//    <E> List<E> query(String statement, Object parameter);


    <T> T query(String statement, String parameter);
}
