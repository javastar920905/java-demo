package cn.javabus.codeidentification;

import cn.javabus.codeidentification.spider.impl.ChinaTaxSpider;

import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ouzhx  on ${date}
 *
 * js 代码解码
 */
public class JSCodeDecypt {
  static   String [] _$dsadksal = {"793BF52173F0030F3AD9412441EBBD0395FB8D65A56C2DA66DD6FB7D3C302C845F2A0A25087EA0374CFGJ1Z1Ug==", "readyState", "userAgent", "&res=", "Content-Type", "open", "pepp4_warriors", "//inv-veri.chinatax.gov.cn/", "location", "undefined", "MSXML2.XMLHTTP", "ActiveXObject", "text/html", "overrideMimeType", "overrideMimeType", "XMLHttpRequest", "793BF52173F0030F3AD9412441EBBD0395FB8D65A56C2DA66DD6FB7D3C302C845F2A0A25087EA0374CFGJ1Z1Ug==", "_Jo0OQK", "_Jo0OQK", "getItem", "name", "save", "userData", "name", "getAttribute", "name", "body", "display", "type", "createElement", "userData", "userData", "inv-veri.chinatax.gov.cn", "05", "document", "@E", "userData", "hidden", "userData", "style", "", "none", "userData", "addBehavior", "#default#userData", "document", "appendChild", "userData", "userData", "load", "userData", "userData", "load", "setAttribute", "userData", "", "localStorage", "_Jo0OQK", "init", "localStorage", "@E", "`e", "Microsoft.XMLHTTP", "length", "onreadystatechange", "protocol", "", "POST", "setRequestHeader", "", "application/x-www-form-urlencoded", "send", "js=1&flag=", "", "&plat=", "platform", "&ua=", "status", "responseText", "setItem", "_Jo0OQK", "pu"};

    static  Pattern pattern = Pattern.compile("^dsadksal$");

    static String jsCodeStr="var RZu1 = (function(){   function sendFlags(){       var sVWnUfDZg2 = 0;     var JTsTM3 = \"\";        var CaOKpaUHI4 = {          userData: null,        " +
            " name: _$dsadksal[32],           init: function() {              if (!CaOKpaUHI4[_$dsadksal[22]]) {                  try {                       CaOKpaUHI4[_$dsadksal[30]] = window[_$dsadksal[34]][_$dsadksal[29]]('\\x69\\x6e\\x70\\x75\\x74');                        CaOKpaUHI4[_$dsadksal[31]][_$dsadksal[28]] = _$dsadksal[37];                        CaOKpaUHI4[_$dsadksal[36]][_$dsadksal[39]][_$dsadksal[27]] = _$dsadksal[41];                        CaOKpaUHI4[_$dsadksal[38]][_$dsadksal[43]](_$dsadksal[44]);                     window[_$dsadksal[45]][_$dsadksal[26]][_$dsadksal[46]](CaOKpaUHI4[_$dsadksal[42]]);                 } catch (e) {                       return false;                   }               }               return true;            },          getItem: function(s5) {             CaOKpaUHI4[_$dsadksal[47]][_$dsadksal[49]](CaOKpaUHI4[_$dsadksal[20]]);             return CaOKpaUHI4[_$dsadksal[48]][_$dsadksal[24]](s5)           },          setItem: function(jUh6, UGjUhZ7) {              CaOKpaUHI4[_$dsadksal[50]][_$dsadksal[52]](CaOKpaUHI4[_$dsadksal[23]]);             CaOKpaUHI4[_$dsadksal[51]][_$dsadksal[53]](jUh6, UGjUhZ7);              CaOKpaUHI4[_$dsadksal[54]][_$dsadksal[21]](CaOKpaUHI4[_$dsadksal[25]]);         }       };      if (window[_$dsadksal[56]]) {           sVWnUfDZg2 = 1;         JTsTM3 = localStorage[_$dsadksal[17]];      } else {            if (CaOKpaUHI4[_$dsadksal[58]]()) {             sVWnUfDZg2 = 2;             JTsTM3 = CaOKpaUHI4[_$dsadksal[19]](_$dsadksal[18]);            } else {                sVWnUfDZg2 = 0;             JTsTM3 = \"\";            }       }       function acakjksja0() {         var nvBbvmwHT8 = window[_$dsadksal[59]];            nvBbvmwHT8[_$dsadksal[57]] = _$dsadksal[0];     }       var nIwngNc9;       function acakjksja1() {         if (window[_$dsadksal[15]]) {               nIwngNc9 = new XMLHttpRequest();                if (nIwngNc9[_$dsadksal[13]]) {                 nIwngNc9[_$dsadksal[14]](_$dsadksal[12]);               }           } else if (window[_$dsadksal[11]]) {                var _N10 = [_$dsadksal[10], _$dsadksal[62]];                for (var GDPlYG11 = 0; GDPlYG11 < _N10[_$dsadksal[63]]; GDPlYG11++) {                   try {                       nIwngNc9 = new ActiveXObject(_N10[GDPlYG11]);                       break;                  } catch (e) {}              }           }           nIwngNc9[_$dsadksal[64]] = acakjksja2;          var dJWFr12 = null;         if (!JTsTM3 && typeof(JTsTM3) == _$dsadksal[9] && JTsTM3 != 0) {                JTsTM3 = \"\";            }           var gPj_ZyVS13 = \"\";            gPj_ZyVS13 = window[_$dsadksal[8]][_$dsadksal[65]];         var aCjuamys14 = gPj_ZyVS13 + _$dsadksal[7] + _$dsadksal[6];            nIwngNc9[_$dsadksal[5]](_$dsadksal[67], aCjuamys14, true);          nIwngNc9[_$dsadksal[68]](_$dsadksal[4], _$dsadksal[70]);                        nIwngNc9[_$dsadksal[71]](_$dsadksal[72] + sVWnUfDZg2 + _$dsadksal[3] + JTsTM3+_$dsadksal[74]+navigator[_$dsadksal[75]]+_$dsadksal[76]+navigator[_$dsadksal[2]]);        }       function acakjksja2() {         if (nIwngNc9[_$dsadksal[1]] == 4) {             if (nIwngNc9[_$dsadksal[77]] == 200) {                  var AOM15 = nIwngNc9[_$dsadksal[78]];                   if (AOM15 == 1 && sVWnUfDZg2 == 1) {                        acakjksja0();                   } else if (AOM15 == 1 && sVWnUfDZg2 == 2) {                     CaOKpaUHI4[_$dsadksal[79]](_$dsadksal[80], _$dsadksal[16]);                 }               }           }       }       acakjksja1();   }sendFlags();var EI16 = 2;var _zXrZQyj17 = setInterval(function(){    if(EI16>1){       sendFlags();    }else{      clearInterval(_zXrZQyj17);    }    EI16--;},500);});new RZu1();";
    public static void main(String[] args) {
       for (int i = 0; i < _$dsadksal.length; i++) {
           String replareStr="_\\u0024dsadksal\\u005B"+i+"]";
           //System.out.println(i+"  "+_$dsadksal[i]);
           jsCodeStr=jsCodeStr.replaceAll(replareStr, _$dsadksal[i]);
       }

        System.out.println(jsCodeStr);

//        Matcher matcher = pattern.matcher(jsCodeStr);
//           while (matcher.find()) {
//               String    result = matcher.group();
//               System.out.println(result);
//           }
    }
}
