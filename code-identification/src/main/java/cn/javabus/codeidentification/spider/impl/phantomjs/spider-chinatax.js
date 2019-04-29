//webpage是phantomjs的核心模块之一，它给用户提供了访问、操作、选择web文档的接口
// http://phantomjs.org/api/webpage/method/open.html
var webPage = require('webpage');
var page = webPage.create();
page.settings.userAgent = 'Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko';

//设置下编码格式，否则输出的可能是乱码
phantom.outputEncoding = "gbk";
phantom.addCookie({
        'name': '_Jo0OQK', /* required property */
        'value': '5CDE5976870A32BF5C3CB1E92FB911A0C9456CCB695612AA1114B8B664FFD83A374AF91E8EB00ED71668C5C3F0D838C96924EE427F9DB336940CEC3CE42444FFD839B860DB80F26C941FB4BD9AD05BFA505FB4BD9AD05BFA505558E68D2DBDDE9ACGJ1Z1RA==', /* required property */
        'domain': 'inv-veri.chinatax.gov.cn',
        'path': '/', /* required property */
        'httponly': true,
        'secure': false,
        'expires': (new Date()).getTime() + (1000 * 60 * 60)   /* <-- expires in 1 hour */
    }
);
//
// var settings = {
//     operation: "GET",
//     encoding: "utf8",
//     headers: {
//         "Accept": "text/html, application/xhtml+xml, image/jxr, */*",
//         "Accept-Encoding": "gzip, deflate",
//         "Accept-Language": "zh-CN",
//         "Connection": "Keep-Alive",
//         "Host": "inv-veri.chinatax.gov.cn",
//         "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"
//     }
//     // ,
//     // data: JSON.stringify({
//     //     some: "data",
//     //     another: ["custom", "data"]
//     // })
// };


page.customHeaders = {
    "Accept": "text/html, application/xhtml+xml, image/jxr, */*",
    "Accept-Encoding": "gzip, deflate",
    "Accept-Language": "zh-CN",
    "Connection": "Keep-Alive",
    "Host": "inv-veri.chinatax.gov.cn",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko"
    // "Referer": "https://inv-veri.chinatax.gov.cn/"
}


//运行page.open函数，其中第一个参数是你要访问的url，第二个参数是一个回调函数。在回调函数里我们检查了下返回的状态
page.open("https://inv-veri.chinatax.gov.cn/ ", function (status) {
        var cookies = page.cookies;

        console.log('Listing cookies:');
        for (var i in cookies) {
            console.log(cookies[i].name + '=' + cookies[i].value);
        }

        if (status === "success") {
            console.log(page.title);
            //console.log(page.content);
        } else {
            console.log("Page failed to load.");
        }

        //退出phantomjs执行环境
        phantom.exit(0);
    }
);

var reqParam = {
    "fpdm": "",
    "fphm": ""
};

//js 模块化开发 http://www.ruanyifeng.com/blog/2012/10/javascript_module.html
//定义模块,接收全局参数
var ChinataxSpider = (function (reqParam) {
    //定义私有成员,外部无法获取
    var code_url = ""; //验证码请求地址
    var identification_url = "";//发票校验地址

    var data = reqParam;

    //获取验证码
    var getCode = function () {
        console.log('getCode')
    }

    //发票校验
    var doIdentification = function () {
        console.log('doIdentification')
    }

    //返回需要暴露的接口
    return {
        getCode: getCode,
        doIdentification: doIdentification
    }
})(reqParam);


//调用执行 ChinataxSpider.getCode();
