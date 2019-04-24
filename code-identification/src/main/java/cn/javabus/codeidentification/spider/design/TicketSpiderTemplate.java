package cn.javabus.codeidentification.spider.design;

import cn.javabus.codeidentification.enums.EnumTicketPlateform;

/**
 * @author ouzhx on 2019/4/24.
 * <p>
 * 发票查验模板方法
 */
public abstract class TicketSpiderTemplate implements TicketSpider {

    EnumTicketPlateform ticketPlateform;

    public TicketSpiderTemplate(EnumTicketPlateform ticketPlateform) {
        this.ticketPlateform = ticketPlateform;
    }

    /**
     * 获取验证码
     *
     * @return
     */
    protected abstract String getCode();


    /**
     * 验证码识别
     *
     * @param code
     * @return
     */
    protected abstract String codeIdentification(String code);

    @Override
    public void doTicketVerrify() {

    }
}
