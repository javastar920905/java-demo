package cn.javabus.codeidentification.spider.design;


import cn.hutool.json.JSONObject;

/**
 * @author ouzhx on 2019/4/24.
 */
public interface TicketSpider {

    /**
     * 获取验证码
     * @param param 获取验证码需要传递的参数
     * @return
     */
    JSONObject getCode( JSONObject param);

    /**
     * 刷新验证码
     * @param
     * @return
     */
    JSONObject refreshCode();


    /**
     * 验证码识别-以及调用发票查验接口
     *
     * @param param 验证码返回结果 做参数
     * @return
     */
    JSONObject codeIdentification(JSONObject param);

}
