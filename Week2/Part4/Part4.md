1、What methods are implemented in Critter?

Critter类里面实现了act、getActors、processActors、getMoveLocations、selectMoveLocation、makeMove函数。

//@file: info/gridworld/actor/Critter.java

//@line: 38、56、71、88、104、125

```java
public void act(){}

public ArrayList<Actor> getActors(){}

public void processActors(ArrayList<Actor> actors){}

public ArrayList<Location> getMoveLocations(){}

public Location selectMoveLocation(ArrayList<Location> locs){}

public void makeMove(Location loc){}
```
2、What are the five basic actions common to all critters when they act?

所有的critters都有这五个公共的基本动作：getActors、processActors、getMoveLocations、selectMoveLocation、makeMove.因为所有的critters子类都继承自critters类

//@file: info/gridworld/actor/Critter.java

//@line: 38、56、71、88、104、125

```java
public void act(){}

public ArrayList<Actor> getActors(){}

public void processActors(ArrayList<Actor> actors){}

public ArrayList<Location> getMoveLocations(){}

public Location selectMoveLocation(ArrayList<Location> locs){}

public void makeMove(Location loc){}
```

3、Should subclasses of Critter override the getActors method? Explain.

如果critter类的子类选择actor的方式与critter类不一样的话，我们就需要在子类里面重写；如果选择方式是一样的话，我们就直接使用继承自critter类的的getActors函数

//@file: projects/critters/CrabCritters.java

//@line: 46~54

```java
ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

```

4、Describe the way that a critter could process actors.

对于Ctitter类的processActors而言，首先processActors会传入一个存储Actor的ArrayList，我们对于这个ArrayList从头到尾进行一次for循环，对于ArrayList里的每一个Actor，如果这个Actor不是一个岩石且这个Actor不是一个critter的话，我们就把它从地图上移除

//@file: info/gridworld/actor/Critter.java

//@line: 71~78

```java
public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```

对于ChameleonCritter这个类的processActors而言，同样的这个函数回传一个存储Actor的ArrayList，我们随机选取这个ArrayList里的一个Actor，并将这个chameleoncritter的颜色设置成被选取的actor的颜色

//@file: projects/critters/ChameleonCritter.java

//@line: 36~45

```java
public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0)
            return;
        int r = (int) (Math.random() * n);

        Actor other = actors.get(r);
        setColor(other.getColor());
    }
```

5、What three methods must be invoked to make a critter move? Explain each of these methods.

getMoveLocations、selectMoveLocation、makeMove函数被用来使一个critter移动

首先会调用getMoveLocations这个函数，getMoveLocations函数找到地图上在自身位置附近的所有的空格子，并返回一个ArrayList，这个ArrayList存储着所有的所找到的空格子

//@file :info/gridworld/actor/Critter.java

//@line : 88~91

```java
public ArrayList<Location> getMoveLocations()
    {
        return getGrid().getEmptyAdjacentLocations(getLocation());
    }
```

然后会调用selectMoveLocation函数，刚才getMoveLocations函数所返回的ArrayList会被传入这个函数，这个函数会从传入的这个ArrayList里面随机选择一个空格子，并返回它的位置变量(Location)

//@file: info/gridworld/actor/Critter.java

//@line : 104~111

```java
public Location selectMoveLocation(ArrayList<Location> locs)
    {
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
    }
```

最后会调用一个叫做makeMove的函数，它接受了从selectMoveLocation函数返回的位置变量，如果这个位置变量为null，那么我们的critter对象就会从地图中移除；如果这个位置变量不为null，那么makeMove就回去调用moveTo函数，将我们的critter对象移到这个位置变量所代表的位置

//@file: info/gridworld/actor/Critter.java

//@line: 125~131

```java
public void makeMove(Location loc)
    {
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
    }
```

6、Why is there no Critter constructor?

因为Critter类继承自Actor类，而Actor类已经有构造函数的实现，所以Critter类就会默认的继承Actor的构造函数而不需要自己重新创建一个构造函数

//@file: info/gridworld/actor/Critter.java

//@line: 31

```java
public class Critter extends Actor
```

7、Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

虽然ChameleonCritter没有重写act函数，但是ChameleonCritter重写了processActors和makeMove函数，并且act函数里面有调用这两个被ChameleonCritter重写的函数，所以虽然ChameleonCritter没有重写act函数，其动作也和Critter有所不同

//@file: projects/critters/ChameleonCritter.java

//@line: 36、50

```java
public void processActors(ArrayList<Actor> actors)

public void makeMove(Location loc)
```

8、Why does the makeMove method of ChameleonCritter call super.makeMove?

ChameleonCritter的makeMove函数，首先会设置朝向的方向，接下来一句就使用了super.makeMove，这句话的意思是调用被继承类Critter的makeMove函数来使得自己的位置发生改变；总而言之，chameleonCritter类里的makeMove函数就是在被继承类的makeMove的基础上多了一个转向的功能

// 无码可贴

9、How would you make the ChameleonCritter drop flowers in its old location when it moves?

我们可以新建一个Location记下ChameleonCritter移动前的位置，在ChameleonCritter移动之后，我们新建一个Flower对象，并把它放置在ChameleonCritter的原位置上。具体代码如下所示

```java
 public void makeMove(Location loc)
    {
        Location oldloc = getLocation();
        setDirection(getLocation().getDirectionToward(loc));
        super.makeMove(loc);
        if(oldloc != loc)
        {
            Color color = getColor();
            Flower flo1 = new Flower(color);
            flo1.putSelfInGrid(getGrid(),oldloc);
        }
    }
```

10、Why doesn't ChameleonCritter override the getActors method?

因为ChameleonCritter类对于getActors这个函数并没有什么特别的要求，所以就直接继承Critter类的getActors函数而不需要重写它

// 无码可贴

11、Which class contains the getLocation method?

Actor类有getLocation这个函数，所有Actor的子类都会继承这个函数

//@file: info/gridworld/actor/Actor.java

//@line: 102~105

```java
  public Location getLocation()
    {
        return location;
    }
```

12、 How can a Critter access its own grid?

一个Critter想获取到自己的grid可以使用getGrid函数，这个函数是在Actor类中实现的，所有的子类都继承了这个函数

//@file: info/gridworld/actor/Actor.java

//@line: 92~95

```java
 public Grid<Actor> getGrid()
    {
        return grid;
    }
```

13、Why doesn't CrabCritter override the processActors method?

一个CrabCritter会吃掉所有从getActors函数返回的对象，这个做法和被继承类Critter里的做法是相同的，因此我们没有必要去重写它

//@file: info/gridworld/actor/Critter.java

//@line: 71~78

```java
 public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
```

14、Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

CrabCritter会找到位于它正前方，左前方和右前方位置的其他actors， 所有在这些位置被找到的actors都会在processActors函数被调用时被吃掉，而其他方向的不会。

@file: projects/critters/CrabCritter.java

@line: 46~54

```java
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
```

15、Why is the getLocationsInDirections method used in CrabCritter?

因为CrabCritter要寻找位于它正前方、左前方和右前方的其他actors，这个函数转入一个关于directions的数组，这个数组代表了CrabCritter能吃到的所有方向，将这个数组传入getLocationsInDirections，就可以得到CrabCritter能吃到的所有位置，从而得到CrabCritter能吃到的所有actors

//@file: projects/critters/CrabCritter.java

//@line: 103~112

```java
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();
    
        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
```

16、If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

位置是(3,4)，面向的是方，那么getActors能返回的位置可能会有(4,4)、（4,3）、(4,5)

//@file: projects/critters/CrabCritter.java

//@line: 47~54

```java
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }
```

17、What are the similarities and differences between the movements of a CrabCritter and a Critter?

相似点： 在他们移动的时候，它们不会转弯以改变自己的方向；

//@file: projects/critters/CrabCritter.java

//@line: 90

```java
super.makeMove(loc);
```

//@file: info/gridworld/actor/Critter.java

//@line: 127~130

```java
        if (loc == null)
            removeSelfFromGrid();
        else
            moveTo(loc);
```
他们会从可移动的位置集合中，随机的选择一个位置并移动的那个位置

//@file: info/gridworld/actor/Critter.java

//@line: 106~110

```java
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
```


不同点：一个crab只会向它的左边或者右边移动，而一个critter可能向任意一个合法方向移动；

//@file: projects/critters/CrabCritter.java

//@line: 81~87

```java
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
```

//@file: info/gridworld/actor/Critter.java

//@line: 106~110

```java
        int n = locs.size();
        if (n == 0)
            return getLocation();
        int r = (int) (Math.random() * n);
        return locs.get(r);
```

当一个crab无法移动的时候，它会随机选择向左或者向右旋转90度， 当一个critter无法移动的时候，他不会旋转；

//@file: projects/critters/CrabCritter.java

//@line: 81~87

```java
 double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
```

//@file: info/gridworld/actor/Critter.java

//@line: 127~128

```java
        if (loc == null)
            removeSelfFromGrid();
```

18、How does a CrabCritter determine when it turns instead of moving?

在makeMove函数里面，它会判断传入的参数loc和当前位置是否相等，如果相等则意味着不能移动，不相等则意味着可以移动

//@file: projects/critters/CrabCritter.java

//@line: 79~88

```java
 if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
```

19、Why don't the CrabCritter objects eat each other?

CrabCritter的processActors是继承自Critter的，在Critter类中的processActors函数里明确规定了，只有当actor不为岩石或者不为Critter类的对象的时候，才能将其吃掉

//@file: info/gridworld/actor/Critter.java

//@line: 73~77

```java
  for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
```