package cn.javabus.codeidentification.spider.impl;

import cn.javabus.codeidentification.spider.design.TicketSpiderTemplate;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;

/**
 * @author ouzhx on 2019/4/24.
 *
 * 全国增值税发票查验
 */
public class ChinaTaxSpider extends TicketSpiderTemplate {
    public ChinaTaxSpider(EnumTicketPlateform ticketPlateform) {
        super(ticketPlateform);
    }

    @Override
    protected String getCode() {
        return null;
    }

    @Override
    protected String codeIdentification(String code) {
        return null;
    }

}

