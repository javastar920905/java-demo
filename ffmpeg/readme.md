# 说明
ffmpeg 练手项目
ffmpeg 是一个快速视频和音频转换工具

# 参考文档
github https://github.com/FFmpeg/FFmpeg
官方文档   http://ffmpeg.org/ffmpeg-all.html (好吧,官方文档实在是太难读了,还是找个简单点的中文教程吧)
ffmpeg java 客户端  https://github.com/bramp/ffmpeg-cli-wrapper
命令行参考文档  http://www.cuplayer.com/player/PlayerCode/FFmpeg/2016/0804/2468.html

# ffmpeg 常用命令参考
- 语法格式 ffmpeg [global_options] {[input_file_options] -i input_url} ... {[output_file_options] output_url} ...
- 合并两个音频 ffmpeg -i 1.mp3 -i 2.mp3  -filter_complex amix=inputs=2:dropout_transition=5 output.mp3

# 简单音频拼接,不需要编程时可以下载其他工具使用
Adobe Audition  https://helpx.adobe.com/audition/tutorials.html
破解版本下载  http://www.downxia.com/downinfo/122582.html (204M)
