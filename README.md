# mySpringBoot
目前正在自学spring boot相关知识，该仓库主要是存放自己的学习记录
helloworld是学习springboot的第一个demo,只要是集成了mysql，❤❤❤其注意事项和我学习到的一些注解为🖤🖤🖤：
1.在pom.xml中添加mysql驱动要注意的问题：
    添加mysql驱动
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
		</dependency>
    如果这样添加缺少了版本号，在运行项目时可能会报错，说连接不到mysql,改正方法就是添加版本号：
    <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.44</version>
		</dependency>  
2.一些注解：
    2.1
      @GetMapping()其实就是@RequestMapping(value=(),RequestMethod.GET);
      @PostMapping()其实就是@RequestMapping(value=(),RequestMethod.POST);
      ...
    2.2 @PathVariable、@RequestParam
      两者的作用都是将request里的参数的值绑定到contorl里的方法参数里的，区别在于，URL写法不同。
      使用@RequestParam时，URL是这样的：http://host:port/path?参数名=参数值
      使用@PathVariable时，URL是这样的：http://host:port/path/参数值
      
