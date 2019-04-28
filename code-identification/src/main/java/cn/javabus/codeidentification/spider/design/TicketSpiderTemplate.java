package cn.javabus.codeidentification.spider.design;

import cn.javabus.codeidentification.enums.EnumTicketPlateform;

/**
 * @author ouzhx on 2019/4/24.
 * <p>
 * 发票查验模板方法
 */
public abstract class TicketSpiderTemplate implements TicketSpider, CustomHTTPUtil {

    EnumTicketPlateform ticketPlateform;

    public TicketSpiderTemplate(EnumTicketPlateform ticketPlateform) {
        this.ticketPlateform = ticketPlateform;
    }
}
