package javastar920905.dao;

import javastar920905.entity.Msg;

/**
 * @author ouzhx on 2019/3/7.
 */
public interface MsgMapper {
    Msg selectOne();

    Msg selectByPrimaryKey(Integer userId);
}
