package cn.javabus.codeidentification;

import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.design.TickerSpiderFactory;
import cn.javabus.codeidentification.spider.design.TicketSpider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CodeIdentificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeIdentificationApplication.class, args);
        //全國增值稅查驗對象,啟動就初始化
        TicketSpider ticketSpider = TickerSpiderFactory.instance(EnumTicketPlateform.getPlatFormByName("chinatax"));


    }

}
