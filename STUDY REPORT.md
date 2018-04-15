# 实训学习报告

## 1、 有关Vi的学习和使用

### 1.1、对于Vi的简介
>首先介绍一下Vi，它是Unix和Linux系统下标准的编辑器，，它的功能强大的令你难以置信，只要你学会了Vi，你就可以在Linux的世界里畅游无阻了。（据说强大的程序员都用这个，反正我是不相信这种鬼话的....）， 好了言归正传，我们来了解和学习一下Vi的一些概念和基本操作。
>
### 1.2、Vi的基本概念

>总的来说，Vi可以分为三种模式，他们分别是Command Mode（控制模式）、Insert Mode（插入模式）、Last Line Mode（底行模式），我们先简单介绍一下这几种模式。
1）控制模式（Command Mode): 这个模式也叫命令模式，你可以在这个模式下控制屏幕光标的移动，字符、字或行的删除，移动复制某区段及进入插入模式（在控制模式下按i可进入插入模式）。
2）插入模式（Insert Mode）：在这个模式下，我们可以进行文字的输入，也可以返回控制模式（在插入模式下按esc可回到控制模式）。
3）底行模式（Last Line Mode）：将文件保存或退出Vi，也可以设置编辑环境，进行查找字符串、列出行号等操作。

### 1.3、Vi的基本操作

#### 1.3.1、如何启动Vi并利用Vi打开文件进行编辑
> 如果你只是想打开Vi并在里面进行文本操作的时候，那么你第一步要做的就是先打开命令行，然后输入命令:   
$ vi
>
> 这个时候你就会发现我们的终端进入了Vi的文本编辑界面了。
> 但是如果你是想通过Vi打开某个文件并使用Vi来编辑它，那么你可以输入以下命令：
> $ vi filename
> 这里的filename就是我们的文件名称，输入完命令敲击回车后我们就可以进入到该文件在Vi下的编辑界面了。
> 在这里我们需要注意的地方有以下几点：
> i)、 我们进入命令行之后，所处于的模式是控制模式，也就是说我们一进入Vi的时候是不能进行文本输入的，所以我们就要从控制模式切换到插入模式，我们按一下键盘上的i可以发现命令行左下角会有Insert Mode的字样，这意味着说我们已经进入到插入模式了，然后我们就可以开始编辑文字了。
> ii)、 当我们完成编辑工作的时候，我们想保存并且退出，我们需要做的是首先按esc从插入模式切换到控制模式，然后输入：，这意味着进入底行模式，再输入wq，我们就可以保存文件并且退出了。 
#### 1.3.2、初学者需要注意的一些地方
> 1)、 在Vi中，删除文字可能并不能像你想像中的那样，按一下backspace键就能够删去你想删的内容。不信你可以试一下，你会发现光标会往回退一格而不是删掉一个字符。如果你真的想删掉一些字符，你需要这样做：
>i）、 按esc键先切换到控制模式下
>ii)、 按大写的X（注意！注意！一定是需要按大写的X，这样就可以删掉离光标最近的字符）
>2）、 牢记三种模式的切换，这样不会让你在控制模式下疯狂按键盘，电脑却对你无动于衷

#### 1.3.3、 更多命令介绍

[更多关于Vi的学习](https://baike.baidu.com/item/Vi/8987313) 

## 2、有关JAVA的学习

### 2.1、JAVA介绍
> Java是一门面向对象编程语言，不仅吸收了C++语言的各种优点，还摒弃了C++里难以理解的多继承、指针等概念，因此Java语言具有功能强大和简单易用两个特征。Java语言作为静态面向对象编程语言的代表，极好地实现了面向对象理论，允许程序员以优雅的思维方式进行复杂的编程   。
> Java具有简单性、面向对象、分布式、健壮性、安全性、平台独立与可移植性、多线程、动态性等特点 。Java可以编写桌面应用程序、Web应用程序、分布式系统和嵌入式系统应用程序等

### 2.2、配置JAVA环境

> １．首先要去下载好JDK，Java SE 8的官方网址是http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
根据自己的系统版本来选择是要使用32位版还是64位版。Linux提供了两种安装方式一个是.rpm，另一个是.tar.gz，我所使用的是.tar.gz。在下载前不要忘了选择Accept License Agreement。
>
> ２．可以使用下面的命令来查看自己的系统是32位还是64位

```powershell
$ getconf LONG_BIT
```

> ３．接下来我们对下载的文件进行解压

```powershell
$ tar -zxvf jdk-8u102-linux-x64.tar.gz
```

> ４．然后我们来新建一个目录，并将解压好的文件移动过去

```powershell
mkdir /usr/java
mv  ./jdk1.8.0_102 /usr/java
```

> ５．然后我们来设置环境变量，这里我们需要修改/etc/profile文件
> 先用vim打开/etc/profile文件

```powershell
vim /etc/profile
```

> ６．在文件最后添加下面的内容

``` 
export JAVA_HOME=/usr/java/jdk1.8.0_102
export JRE_HOME=/usr/java/jdk1.8.0_102/jre
export CLASSPATH=.:$JAVA_HOME/lib:$JRE_HOME/lib:$CLASSPATH
export PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
```


其中的jdk1.8.0_102请根据自己的实际文件名作出更改
添加完成后，使环境变量生效。
source /etc/profile
我们可以在终端中输入java来检测是否配置成功
java
如果配置成功便会显示提示信息

### 2.3、第一个JAVA小程序
> 不管在什么语言里，HelloWorld都是一个令人亲切和讨人喜欢的程序，让人产生一种希望，让人感觉到快乐，让人充满一种我能够掌握这门语言的幻觉。
> 那么既然配置好了环境，我们也来学一下如何利用java来写一个HelloWorld
> 首先，你需要新建一个 .java文件，并在里面输入如下代码：

```java
public class HelloWorld {
	public static void main(String args[]){
		System.out.println("Hello,World!");
}
}
```

> 然后你需要在命令行中用以下命令来编译和运行这个程序（要切换到和程序在同一目录下）：

```
$ javac HelloWorld.java // this instruction is to compile your program
$ java HelloWorld // this instruction is to run your program
```

### 2.4、有关GUI的学习
> 由于这次作业中有要求使用java来写一个简单的计算器，所以在这也说一下关于这方面的学习，和GUI有关的两个包一个是ATW一个是SWING，以下我会根据我的代码来简单总结一下这一方面的知识点，以下是代码：

```java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Calculator extends JFrame{
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton multiple = new JButton("*");
	private JButton divide = new JButton("/");
	private JButton ok = new JButton("OK");
	private JButton equal = new JButton("=");
	private JLabel symbol = new JLabel("",JLabel.CENTER);
	private JLabel result = new JLabel("",JLabel.CENTER);
	private JTextField input1 = new JTextField();
	private JTextField input2 = new JTextField();
	public Calculator(){

		
		getContentPane().setLayout(new GridLayout(2,5));
		getContentPane().add(input1);
		getContentPane().add(symbol);
		getContentPane().add(input2);
		getContentPane().add(equal);
		getContentPane().add(result);
		getContentPane().add(plus);
		getContentPane().add(minus);
		getContentPane().add(multiple);
		getContentPane().add(divide);
		getContentPane().add(ok);
		setSize(300,150);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		input1.setHorizontalAlignment(JTextField.CENTER);
		input2.setHorizontalAlignment(JTextField.CENTER);
		ActionListener al = new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String name = ((JButton)e.getSource()).getText();
				symbol.setText(name);
			}
		};
		ActionListener getresult = new ActionListener() {
			public void actionPerformed(ActionEvent x){
				result.setText("");
				String str1 = input1.getText();
				String str2 = input2.getText();
				double num1 = Double.parseDouble(str1);
				double num2 = Double.parseDouble(str2);
				double num3 = 0;
				if(symbol.getText().equals("+")){
					num3 = num2 + num1;
				}
				if(symbol.getText().equals("-")){
					num3 = num1 - num2;
				}
				if(symbol.getText().equals("*")){
					num3 = num2 * num1;
				}
				if(symbol.getText().equals("/")){
					num3 = num1 / num2;
				}
				String str3 = "" + num3;
				result.setText(str3);
			}
		};
		plus.addActionListener(al);
		minus.addActionListener(al);
		multiple.addActionListener(al);
		divide.addActionListener(al);
		equal.addActionListener(getresult);
		ok.addActionListener(getresult);
	}

	
	public static void main(String args[]){
		(new Calculator()).setVisible(true);
	}
}
```

> i) 首先因为我们可能会用到atw 和 swing包中的组件，所以首先要import一下相关的包，代码开头的四个import就是让程序包含这四个包
>
>ii）  程序一开始所看到的JButton、JLabel、JTextField就是swing包里的组件，它们分别是按钮组件，标签组件和文本框组件，我们每new一个组件都意味着我们创建了一个该组件的实体
>
>iii) 接下来你会看到一条setLayout()语句和许多条add语句。getContentPane().setLayerout()这条语句的意思是在面板上新建一个网格式布局，这样可以使得所有我们添加到面板上的组件实体呈现一个网格式的分布，他们都是等大小的，根据添加的顺序决定出现的先后顺序。 而那些add语句就是将一个个的组件实体添加到面板上。
>
>iv) 比较重要的是下面代码中的两个监听器，我们拿设置的第一个监听器举例说明：
>ActionListener al = new ActionListener()指明我们新建一个监听器，花括号里面的语句代表了这个监听器需要监听的动作和监听到相应动作之后需要作出的行动。
>
>v） 接下来我们需要把监听器赋予给我们的组件实体，使得监听器生效，使用addActionListener命令

## 3、 有关ANT的学习

### 3.1、什么是ANT
> Apache Ant,是一个将软件编译、测试、部署等步骤联系在一起加以自动化的一个工具，大多用于Java环境中的软件开发。由Apache软件基金会所提供

### 3.2 创建属于你的build.xml

> 要使用ANT，首先你需要创建一个.xml文件并把它放置在你的代码的根目录中。
> 以下是我写的一个可以自动化编译HelloWorld的代码,我在代码里的注释已经很详细了，所以不再对代码有过多解释。
> 但是我觉得我们对于ANT至少要知道这几点：

> 1.project元素
>
>project元素是Ant构件文件的根元素，Ant构件文件至少应该包含一个project元素，否则会发生错误。在每个project元素下，可包含多个target元素。接下来向读者展示一下project元素的各属性
>
>1）name属性
用于指定project元素的名称。
>
>2）default属性
用于指定project默认执行时所执行的target的名称。
>
>3）basedir属性
用于指定基路径的位置。该属性没有指定时，使用Ant的构件文件的附目录作为基准目录。
>
>2.target元素
>
>它为Ant的基本执行单元，它可以包含一个或多个具体的任务。多个target可以存在相互依赖关系。它有如下属性：

>1）name属性
指定target元素的名称，这个属性在一个project元素中是唯一的。我们可以通过指定target元素的名称来指定某个target。
>
>2）depends属性
用于描述target之间的依赖关系，若与多个target存在依赖关系时，需要以“,”间隔。Ant会依照depends属性中target出现的顺序依次执行每个target。被依赖的target会先执行。
>
>3）if属性
用于验证指定的属性是否存在，若不存在，所在target将不会被执行。
>
>4）unless属性
该属性的功能与if属性的功能正好相反，它也用于验证指定的属性是否存在，若不存在，所在target将会被执行。
>
>5）description属性
该属性是关于target功能的简短描述和说明。
>
>3.property元素
该元素可看作参量或者参数的定义，project的属性可以通过property元素来设 定，也可在Ant之外设定。若要在外部引入某文件，例如build.properties文件，可以通过如下内容将其引入：<property file=” build.properties”/>
property元素可用作task的属性值。在task中是通过将属性名放在“${”和“}”之间，并放在task属性值的位置来实现的。
>
```xml
<?xml version="1.0"?>
	<!--  >根据依赖关系，我们可以知道每一个target的运行顺序，依次是clean->compile->run->jar;<-->
	<project name="javacTest" default="jar" basedir=".">
		<!-->Target clean 用于在编译源文件之前，先清空build目录下的所有中间文件<-->
		<target name="clean">
			<delete dir="build"/>  
		</target>
		<!--> Target compile用于编译源文件，首先在build目录下新建classes目录，再从src文件夹中找到要编译的文件，并将编译后的类文件放在build下的classes目录中<-->
		<target name="compile" depends="clean">
			<mkdir dir="build/classes"/>
				<javac srcdir="src" destdir="build/classes" includeantruntime="on"/> 
		</target>
		<!-->Target Run用于运行java的类文件，要运行的类文件名叫做HelloWorld，类文件的路径是build/classes<-->
		<target name="run" depends="compile">
			<java classname="HelloWorld">
				<classpath>
					<pathelement path="build/classes"/>
				</classpath>
			</java>
		</target>
		<!-->Target jar用于生成归档文档<-->
		<target name="jar" depends="run">
			<jar destfile="HelloWorld.jar" basedir="build/classes">
				<manifest>
					<attribute name="Main-class" value="HelloWorld"/>
				</manifest>
			</jar>
		</target>
	</project>

```

### 3.3、使用ANT自动化编译运行程序
> 完成好build.xml的编写后，把它放到源代码的根目录下，然后通过终端进入到这个目录下，输入ant即可自动化编译你的程序了。文件结构如下图所示：
> |-build
> ||-classes
> |||-在这个目录下存放class文件
> |-src
> ||-在这个目录下存放.java文件
> |-build.xml 

## 4、关于JUnit的学习

### 4.1、设置你的工作目录并编写你的测试文件
> HelloWorld
|- lib
||- junit.jar（需要去官网下载）
|- src
||- HelloWorld.java
||- TestHelloWorld.java（这是测试文件）
> 按照这个目录来设置你的工作目录，新建一个测试文件并编写以下代码：
```java
import java.io.ByteArrayOutputStream; // As we need to capture the message in standard output stream, we need this class
import java.io.PrintStream; // Used to backup the standard output
import static org.junit.Assert.*; // Junit components

public class TestHelloWorld {
    PrintStream outstream = null; // Used to backup the standard output stream
    ByteArrayOutputStream mybytes = null; // Used to store the captured data
    HelloWorld helloworld; // Used to create an instace of our HelloWorld

    @org.junit.Before // prepare for test
    public void setUp() throws Exception {
        mybytes = new ByteArrayOutputStream(); // Instantiate the byte array
        outstream = System.out; // Bind outstream with the oringinal standard output
        System.setOut(new PrintStream(mybytes)); // Redirect output stream to our byte array
    }

    @org.junit.Test // Testing
    public void testResult() throws Exception {
        helloworld = new HelloWorld(); // Instantiate our class
        String str = new String("Hello,World\n"); // Expected output
        assertEquals(str, mybytes.toString()); // Assert: str == output
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.setOut(outstream); // Recovery the standard output
    }
}
```
> 按照这个要求编写完测试文件的代码之后，我们需要先打开终端，进入到含有JUnit.jar的路径下，然后在终端里面输入两条命令：
> 1)这条命令是用来编译测试文件的
```
$ javac -cp .:junit.jar TestHelloWorld.java
```
>2) 这条命令是用来运行测试文件来测试我们的代码的
```
$ java -cp .:junit.jar org.junit.runner.JUnitCore TestHelloWorld
```
> 在正常执行的情况下我们会得到一下输出：
```
JUnit version 4.9
.
Time: 0.006

OK (1 test)
```
> 这说明我们的测试文件正常运行且我们的代码没有任何错误

