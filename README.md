# ViewCampus
以微信小程序为客户端，用来展示和查看校园内部的活动信息的工具
(Wechat mini program as the client, used to display and view the campus activity information tools)
# 一：项目具体介绍（Project specific introduction）
## 1.来自何处(Its source)
  视校园是我的本科毕业设计。
  (ViewCampus is my undergraduate graduation project.)
## 2.软件功能(The main function of the project)
  使用腾讯地图服务，在地图中显示学校内部正在举行的校园活动。
  (Use the Tencent Map service to show the campus activities taking place within the school on the map.)
# 二：如何部署(How to deploy)
_项目部署较为简单_
## 1.前端(Front end)
下载并使用微信小程序开发工具打开ViewCampus_mini。
(Download and open ViewCampus_mini using wechat mini program development tool.)
详细请看[ViewCampus_微信小程序的部署](https://blog.csdn.net/weixin_46748886/article/details/130738219?spm=1001.2014.3001.5501)
## 2.后端(Back end)
其实，我觉得最重要的还是后端，有了后端就有了服务，管你前端用什么，尽管请求服务获取数据就好了，前端什么的你看着搞就行🤭🤭🤭
后端使用了spring boot框架,用maven管理依赖。具体有什么依赖可以看pom.xml文件。你需要根据自己的情况修改application.yml里面的配置。
(In fact, I think the most important is the back end, with the back end you have a service, regardless of what you use the front end, just ask the service to get data, front-end what you look at the problem 🤭🤭🤭.
The back end uses the spring boot framework and maven to manage dependencies. You can see what the dependencies are in the pom.xml file. You need to modify the configuration in application.yml according to your own situation.)
详细请看[ViewCampus_后端部署](https://blog.csdn.net/weixin_46748886/article/details/130738305?spm=1001.2014.3001.5501)
## 3.后台管理(Background management)
后台管理是通过JFrame完成的，界面很简陋，功能很简单，可有可无。毕竟，没有个后台管理系统，指导老师们不好放过你😀😀😀
使用eclipse打开ViewCampus_ManageSystem，记得给eclipse搞WindowBuilder插件，不然你会后悔看这个后台管理的😅😅😅
(Background management is completed through JFrame, the interface is very simple, the function is very simple, dispensable. After all, without a back office management system, advisors can't let you go 😀😀😀.
Use eclipse to open ViewCampus_ManageSystem, and remember to get a WindowBuilder plug-in for eclipse, or you'll regret watching 😅😅😅)
# 三：声明(statement)
## 1.提醒(warn)
此项目仅是我为实现自己的想法和毕业设计而赶工完成的，时间很赶。因此，项目质量不好。如果你是学生，这个项目可能会帮到你一些。
(This project is only for the realization of my own ideas and graduation design and the time is very tight. Therefore, the quality of the project is not good. If you're a student, this program might help you a little.)
## 2.预计的项目改进(Possible project improvements)
我正在考虑改进此项目，改进目标大致为：添加活动过滤、提高软件响应速度、优化代码结构。
(I am considering improvements to this project, with the general goals of adding activity filtering, improving software responsiveness, and optimizing code structure.)
# [ViewCampusPlus](https://github.com/Diudiunan/ViewCampusPlus)
