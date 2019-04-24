package cn.javabus.codeidentification.enums;

import lombok.AllArgsConstructor;

/**
 * @author ouzhx on 2019/4/24.
 */
@AllArgsConstructor
public enum EnumTicketPlateform {
    chinatax("全国增值税发票查验平台", "https://inv-veri.chinatax.gov.cn/");
    public String plateformName;
    public String url;
}
