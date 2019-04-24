package cn.javabus.codeidentification.spider.design;

import cn.javabus.codeidentification.spider.design.TicketSpider;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.impl.ChinaTaxSpider;

/**
 * @author ouzhx on 2019/4/24.
 */
public class TickerSpiderFactory {


    public static TicketSpider instance(EnumTicketPlateform ticketPlateform) {
        if (EnumTicketPlateform.chinatax.name().equals(ticketPlateform.name())) {
            return new ChinaTaxSpider(ticketPlateform);
        }

        throw new IllegalArgumentException("无法实例化对象");
    }


}
