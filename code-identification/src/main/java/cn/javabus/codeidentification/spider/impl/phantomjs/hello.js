//webpage是phantomjs的核心模块之一，它给用户提供了访问、操作、选择web文档的接口
var page = require('webpage').create();
//设置下编码格式，否则输出的可能是乱码
phantom.outputEncoding = "gbk";

//运行page.open函数，其中第一个参数是你要访问的url，第二个参数是一个回调函数。在回调函数里我们检查了下返回的状态
page.open("http://www.cnblogs.com/front-Thinking", function (status) {
    if (status === "success") {
        console.log(page.title);
    } else {
        console.log("Page failed to load.");
    }

    //退出phantomjs执行环境
    phantom.exit(0);
});