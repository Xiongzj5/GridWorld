在我的JumpBug类里面一共有四个函数，他们分别是：
> 1) JumpBug的构造函数
>
>   在构造函数里面，我们会设置jumpbug的颜色、位置等信息，并把我们的jumpbug放置在网格上
>
> 2) 用于返回一个String类型变量的getStr函数
>
>   在canJump函数被调用的时候，如果canJump函数返回true那么这个str的内容就是"can"；如果canJump函数返回false那么这个str的内容就是"cannot"
>
> 3) 用于判断是否满足跳跃条件的canJump函数
>
> 如果jumpbug满足跳跃条件的时候，我们的canJump函数会返回true；否则将返回false；
>
> 4) 用于移动的act函数
>
> act函数接受canJump函数返回的结果，如果接受到true，我们就让jumpbug向前跳两格，否则就让jumpbug向右旋转45度；

我的test函数只针对构造函数，判断跳跃条件函数和移动函数进行了检测，主要是检测一下这几个函数是否有在正常的运转

一、 首先是对构造函数JumpBug函数的测试，构造函数主要是给我们被创建的新对象设置一个初始颜色，我们设置的初始颜色为黄色；设置完颜色后我们把新建的jumpbug对象以及几个rock对象放入新建的地图中。

那么对于构造函数的检测，为了检测一下构造函数是否有正常运转，我们就在测试函数中获取到新建对象的颜色，并用断言的方式判断是否和我们预先设定的初始颜色一样
```java
@Test // 运行中
    public void testInitialColor() throws Exception {
       Color color = jumpone.getColor();
       assertEquals(color, Color.YELLOW); // Assert: 初始颜色是否是黄色
    }
```

二、其次是对caJump函数的检测，我们知道canJump函数是一个boolean类型的函数，这个函数返回值只能是true或者是false，我们稍微修改一下canJump函数，令它在返回true的时候把我们创建的一个全局的String类型的变量str(初始值为空)的内容修改为"can",在返回false的时候把str的内容修改为"cannot"，然后我们通过对这个str进行检测来判断函数是否正常运转
```java
    @Test
    public void testCanJump() throws Exception{
        boolean flat = jumpone.canJump();
        boolean result = false;
        if(jumpone.getStr() == "")
        	fail("failed");
        if(jumpone.getStr() == "can")
            result = true;
        assertEquals(flat,result);

    }
```

三、最后是对act函数的检测，act函数最终产生的结果只有两种，移动或者原地不动但是开始旋转。一个是canJump返回true的结果，一个是canJump返回false的结果。我们只需要检测在act函数调用前后我们的游戏对象位置的变化即可。当返回的是true的时候，那么行差的绝对值和列差的绝对值之和肯定是2,返回false的话，之和肯定是0

```java
 @Test
    public void testact() throws Exception {
        Location loc = jumpone.getLocation();
        jumpone.act();
        Location next = jumpone.getLocation();
        int deltaX = next.getRow() - loc.getRow();
        int deltaY = next.getCol() - loc.getCol();
        if(jumpone.getStr() == "cannot")
            assertEquals(deltaX+deltaY, 0);
        else{
            if(deltaX < 0)
                deltaX *= -1;
            if(deltaY < 0)
                deltaY *= -1;
            assertEquals(deltaX+deltaY,2);
        }
    }
```