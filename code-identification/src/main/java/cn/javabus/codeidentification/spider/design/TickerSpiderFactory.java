package cn.javabus.codeidentification.spider.design;

import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.impl.ChinaTaxSpider;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ouzhx on 2019/4/24.
 */
public class TickerSpiderFactory {

    private static final Map<EnumTicketPlateform, TicketSpider> TICKET_SPIDER_CONTAINER = new HashMap<>();


    public static TicketSpider instance(EnumTicketPlateform ticketPlateform) {
        if (TICKET_SPIDER_CONTAINER.containsKey(ticketPlateform)) {
            return TICKET_SPIDER_CONTAINER.get(ticketPlateform);
        }


        if (!TICKET_SPIDER_CONTAINER.containsKey(ticketPlateform)) {
            synchronized (TICKET_SPIDER_CONTAINER) {
                if (!TICKET_SPIDER_CONTAINER.containsKey(ticketPlateform)) {
                    TICKET_SPIDER_CONTAINER.put(ticketPlateform, new ChinaTaxSpider(ticketPlateform));
                }
            }
            return TICKET_SPIDER_CONTAINER.get(ticketPlateform);
        }

        throw new IllegalArgumentException("无法实例化对象");
    }

}
