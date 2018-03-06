package com.javastar920905.util;

/**
 * @author ouzhx on 2018/3/3.
 */
public class StringUtil {

    /**
     数组转String 用,隔开
     **/
    private static String arrayToString(String[] arrays) {
        if (arrays != null && arrays.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String s : arrays) {
                stringBuilder.append(s).append(",");
            }
            String str = stringBuilder.toString();
            return str.substring(0, str.length() - 1);
        } else {
            return "";
        }
    }

}
