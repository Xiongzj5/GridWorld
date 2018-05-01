1、How would you access the row value for loc1?

调用getRow函数，使用方式为loc1.getRow();

// @file:info/gridworld/grid/Location.java

// @line: 110~113

```java
public int getRow()
    {
        return row;
    }
```

2、What is the value of b after the following statement is executed?

euqals是一个判断位置是否相同的函数，loc1和loc2的位置不相同，所以b的值是false

//@file:info/gridworld/grid/Location.java

//@line: 205～212

```java
public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }

```

3、What is the value of loc3 after the following statement is executed?

loc3的坐标为（4,4），因为loc2的坐标是(3,4),向南移一即行数加一，所以是(4,4)

//@file:info/gridworld/grid/Location.java

//@line 147~148

```java
else if (adjustedDirection == SOUTH)
            dr = 1;
```

//@line 168

```java
 return new Location(getRow() + dr, getCol() + dc);
```

4、What is the value of dir after the following statement is executed?

dir的值为135度，loc1的位置是(4,3), (6,5)在loc1的东南方向，所以是135度

//@file:info/gridworld/grid/Location.java

//@line 180~187

```java
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = RIGHT - angle;
 
```

//@line 194

```java
    return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
```

5、How does the getAdjacentLocation method know which adjacent location to return?

getAdjacentLocation函数里会传入一个参数，这个参数指明了临近位置的方向；并且这个函数会返回在地图方向中和传入参数所指定的方向最接近的那个位置

//@file:info/gridworld/grid/Location.java

//@line: 133~137

```java
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
```

6、How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

我们可以声明一个Grid对象叫grid，通过这个对象来调用getOccupiedLocations(),就可以得到所有网格中objects的集合，如果要获取到数量的话，就再调用一个size函数就好，即grid.getOccupiedLocations().size();

//@file:info/gridworld/grid/BoundedGrid.java

//@line: 71~80

```java
for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }
```

如果想获取到地图中所有的空格子数量，只需要获取到格子总数再减去已经被占领的格子数即可，即grid.getNumRows()*grid.getNumCols() - grid.getOccupiedLocations().size()
getNumRows()和getNumCols()分别是获取网格的行数和列数的，即可以得到总格子数

//@file:info/gridworld/grid/BoundedGrid.java

//@line: 48~51 53~58

```java
public int getNumRows()
    {
        return occupantArray.length;
    }
```

```java
public int getNumCols()
    {
        // Note: according to the constructor precondition, numRows() > 0, so
        // theGrid[0] is non-null.
        return occupantArray[0].length;
    }
```

7、How can you check if location (10,10) is in a grid?

声明一个Grid类的对象grid，调用isValid函数：grid.isValid(new Location(10,10)),true即在gird里面，反之。

//@file:info/gridworld/grid/BoundedGrid.java

//@line: 60~64

```java
public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

8、Grid contains method declarations, but no code is supplied in the methods. Why Where can you find the implementations of these methods?

Grid是一个接口，接口的作用是具体说明另外一些类必须实现的一些函数。你可以在grid文件下AbstractGrid.java、BoundedGrid.java还有UnboundedGrid.java里面找到在Grid里面声明的函数的实现。而AbstractGrid只是一个抽象类，它只实现了Grid接口里的部分函数，只有BoundedGrid和UnboundeGrid这两个类，他们继承或者说是AbstractGrid的扩展，含有Grid接口里声明的所有函数

//@file:info/gridworld/grid/AbstracGrid.java

//@line: 26

```java
public abstract class AbstractGrid<E> implements Grid<E>
```

//@file:info/gridworld/grid/BoundedGrid.java

//@line: 29

```java
public class BoundedGrid<E> extends AbstractGrid<E>
```

//@file:info/gridworld/grid/UnboundedGrid.java

//@line: 31

```java
public class UnboundedGrid<E> extends AbstractGrid<E>
```

9、All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

使用ArrayList非常的方便，因为他不需要我们手动的去分配内存来决定它的大小，也就是说它可以根据存入变量的数量自动的扩大和缩小自身；但是array就不行，我们必须手动决定array的大小，而且它是固定大小的，当达到存储的上限的时候，我们如果想去增大它的大小就会变得非常麻烦，会产生额外很多的开销，增加了工作难度。而且如果我们使用array的话，首先我们要额外使用一个计数器来记录object的数量，以便于确定array的初始大小；你可能还要遍历整个Grid，来把所有的object存到array里面等。

//@file:info/gridworld/grid/UnboundedGrid.java

//@line: 60~62(ArrayList可以直接往自身不断添加新的对象，并不需要人为规定大小)

```java
ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet())
            a.add(loc);
```

10、Name three properties of every actor.

一个actor有这些特性，颜色(color)、位置(location)、方向(direction)、所对应的grid；

//@file:info/gridworld/actor/Actor.java

//@line: 31~34

```java
    private Grid<Actor> grid;
    private Location location;
    private int direction;
    private Color color;
```

11、When an actor is constructed, what is its direction and color?

当一个actor被创建的时候，它的方向是朝北的(North)，color为蓝色(blue)

//@file:info/gridworld/actor/Actor.java

//@line: 41~42

```java
        color = Color.BLUE;
        direction = Location.NORTH;
```

12、Why do you think that the Actor class was created as a class instead of an interface?

一个接口只会具体说明另外一些类必须实现的一些函数，接口本身是不会去实现这些函数的，更不会有接口的实例化；而actor既有自身的行为又有自身的各种状态，所以说actor是一个类而不会是一个接口

//@file:info/gridworld/actor/Actor.java

//@line: 39~45(Actor有实例化函数，说明不是接口)

```java
public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
```

13、Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?      

1) 不行，如果一个actor已经被放进了grid，则不能再对它进行放入操作。如果要进行这样的操作的话，程序会抛出一个IllegalStateException异常提醒你这个actor已经存在于grid里面

//@file:info/gridworld/actor/Actor.java

//@line: 117~119

```java
   if (grid != null)
            throw new IllegalStateException(
                    "This actor is already contained in a grid.");
```

2）不行，如果你想对一个已经被移除的actor再次进行remove操作，程序会抛出一个IllegalStateException异常提醒你这个actor已经不在grid内了

//@file:info/gridworld/actor/Actor.java

//@line: 135~137

```java
        if (grid == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
```

3)把一个actor放入grid，先移除它再把它添加进grid是合法的操作，不会抛出任何异常的。运行程序首先会有一个actor被放入grid，然后它会被移除，再然后它会被添加进入grid

//@file:info/gridworld/actor/Actor.java

//@line: 143~145

```java

        grid.remove(location);
        grid = null;
        location = null;
```

//@line: 124~126

```java
 gr.put(loc, this);
        grid = gr;
        location = loc;
```

14、How can an actor turn 90 degrees to the right?

可以利用actor类里的setDirection函数，具体的调用方式为：setDirection(getDirection() + 90)

//@file:info/gridworld/actor/Actor.java

//@line: 80~85

```java
public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
```

15、同上

16、Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

canMove函数利用isValid函数来判断Bug移动的下一个目标位置是否合法，如果不合法，就返回false，这样就可以保证一个bug不会尝试向着grid之外移动

//@file:info/gridworld/actor/Bug.java

//@line: 98~99

```java
 if (!gr.isValid(next))
            return false;
```

17、Which statement(s) in the canMove method determines that a bug will not walk into a rock?

canMove函数利用了expr instanceof Name的方法来确定bug不会走向和rock同一个格子

//@file:info/gridworld/actor/Bug.java

//@line: 100~101

```java
Actor neighbor = gr.get(next);
 return (neighbor == null) || (neighbor instanceof Flower);
```

18、Which methods of the Grid interface are invoked by the canMove method and why?

在canMove函数里使用的接口Grid里的函数有isValid和get函数，对这两个函数的使用确保了bug移动的下一个目标位置是网格中的一个合法的位置。

//@file:info/gridworld/actor/Bug.java

//@line: 98~100

```java
if (!gr.isValid(next))
            return false;
        Actor neighbor = gr.get(next);
```

19、Which method of the Location class is invoked by the canMove method and why?

在canMove函数里使用的Location类里的函数有getLocation和getAdjacentLocation函数，这两个函数获取了当前bug和bug移动的下一个目标位置的坐标

//@file: info/gridworld/actor/Bug.java

//@line: 96～97

```java
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
        
```

20、Which methods inherited from the Actor class are invoked in the canMove method?

getGrid、getLocation以及getDirection三个函数是从Actor类继承而来的

//@file: info/gridworld/actor/Bug.java

//@line: 93~97

```java
Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
```

21、What happens in the move method when the location immediately in front of the bug is out of the grid?

如果bug前面位置已经是位于网格之外了，那么bug就会把自身从网格中删掉，并在原来的位置上留下一朵鲜花

//@file: info/gridworld/actor/Bug.java

//@line: 78~83

```java
if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```

22、Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

我们需要用一个loc变量来表示bug的原位置，我们不能通过频繁的调用getLocation来获取bug的位置，因为当bug移动后，它的位置就发生了改变，这个时候你需要在bug原来所在的位置上放置一朵鲜花，但是你已经获取不到它原来的位置了;所以这个时候用一个loc变量来存bug的原位置就变的十分有必要了

//@file: info/gridworld/actor/Bug.java

//@line: 76~83

```java
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```

23、Why do you think the flowers that are dropped by a bug have the same color as the bug?

在Bug类的move函数里面，我们可以看到每一次新建一个鲜花的对象的时候，我们就会调用一次getColor函数作为新建一个鲜花对象的参数，所以我们可以知道在bug原位置生成的花是和bug一个颜色的

//@file; info/gridworld/actor/Bug.java

//@line: 82~83

```java
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```

24、When a bug removes itself from the grid, will it place a flower into its previous location?

1)把一个bug从grid里移除的方式有两种情况，一种是直接调用removeSelfFromGrid()函数，这个函数是继承自Actor类的，直接调用这个函数是不会有鲜花被放置的，因为在Actor类里该函数的实现并没有新建鲜花对象并放置的操作

//@file: info/gridworld/actor/Actor.java

//@line: 143~145

```java
        grid.remove(location);
        grid = null;
        location = null;
```

2)第二种情况是bug移动的下一个目标位置已经位于grid之外了，这个时候bug会将自身从grid中移除，并在原位置上放置一朵鲜花，在move函数里面我可以看到相关放置鲜花的操作

//@file: info/gridworld/actor/Bug.java

//@line: 78~83

```java
if (gr.isValid(next))
            moveTo(next);
        else
            removeSelfFromGrid();
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
```

25、Which statement(s) in the move method places the flower into the grid at the bug's previous location?

move函数里面最后有一句putSelfInGrid函数的调用，这条语句将一个鲜花对象放入了grid中bug的原位置上

//@file: info/gridworld/actor/Bug.java

//@line: 83

```java
 flower.putSelfInGrid(gr, loc);
```

26、 If a bug needs to turn 180 degrees, how many times should it call the turn method?

如果一个bug需要旋转180度，那么他需要调用四次turn函数，因为调用turn函数只能向右旋转45度

//@file: info/gridworld/actor/Bug.java

//@line: 62~65

```java
public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
```






























