package cn.javabus.codeidentification.controller;

import cn.hutool.json.JSONObject;
import cn.javabus.codeidentification.enums.EnumTicketPlateform;
import cn.javabus.codeidentification.spider.design.TickerSpiderFactory;
import cn.javabus.codeidentification.spider.design.TicketSpider;
import org.springframework.http.HttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ouzhx on 2019/4/28.
 */
@RestController
@RequestMapping("ticket")
public class SpiderController {
    @GetMapping("getCode")
    public JSONObject getCode(@RequestBody JSONObject param) {
        TicketSpider ticketSpider = TickerSpiderFactory.instance(EnumTicketPlateform.getPlatFormByName("chinatax"));
        return ticketSpider.getCode(param);
    }

    //验证码识别-以及调用发票查验接口
    @GetMapping("codeIdentification")
    public JSONObject codeIdentification(@RequestBody JSONObject param, HttpServletRequest request) {
        String yzmSj = request.getParameter("yzmSj");
        if (!StringUtils.isEmpty(yzmSj)) {
            param.put("yzmSj", yzmSj);
        }
        String index = request.getParameter("index");
        if (!StringUtils.isEmpty(index)) {
            param.put("index", index);
        }
        TicketSpider ticketSpider = TickerSpiderFactory.instance(EnumTicketPlateform.getPlatFormByName("chinatax"));
        return ticketSpider.codeIdentification(param);
    }

}
