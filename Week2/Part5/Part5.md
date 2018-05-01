1、Where is the isValid method specified? Which classes provide an implementation of this method?

isValid是在Grid这个接口里面被声明的，而BoundedGrid和UnBouundedGrid函数实现了这个函数

//@file: info/gridworld/grid/Grid.java

//@line: 50

```java
  boolean isValid(Location loc);
```

//@file: info/gridworld/grid/BoundedGrid.java

//@line: 60~64

```java
 public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

//@file: info/gridworld/grid/UnBoundedGrid.java

//@line: 53~56

```java
  public boolean isValid(Location loc)
    {
        return true;
    }
```

2、Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

AbstractGrid里的getValidAdjacentLocations函数调用了isValid函数；其他几个函数没有直接对isValid的调用，他们往往是去调用getValidAdjacentLocation来实现对isValid的调用，像getEmptyAdjacentLocations和getOccupiedAdjacentLocations就是这样；而getNeighbors是通过调用getOccupiedAdjacentLocations，来实现对isValid的间接调用

//@file: info/gridworld/grid/AbstractGrid.java

//@line: 41~47

```java
for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
        {
            Location neighborLoc = loc.getAdjacentLocation(d);
            if (isValid(neighborLoc))
                locs.add(neighborLoc);
            d = d + Location.HALF_RIGHT;
        }
```

3、Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

在getNeighbors函数里面调用了Grid里面的get和getOccupiedAdjacentLocations函数，AbstractGrid类实现了getOccupiedAdjacentLocations函数，而BoundedGrid和UnboundedGrid类实现了get函数

//@file: info/gridworld/grid/AbstractGrid.java

//@line: 30~33

```java
ArrayList<E> neighbors = new ArrayList<E>();
        for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
            neighbors.add(get(neighborLoc));
        return neighbors;
```

//@file:info/gridworld/grid/AbstractGrid.java

//@line: 62~71

```java
 public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) != null)
                locs.add(neighborLoc);
        }
        return locs;
    }
```

//@file:info/gridworld/grid/BoundedGrid.java

//@line:85~91

```java
 public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
    }
```

4、Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

get函数返回一个存储在grid里的object(在没有object存在的情况下返回null)；getEmptyAdjacentLocations调用get函数来检验结果是否是null；如果不是空的，就把它add到list里面；调用get函数是唯一的测试给出的location是空的还是被占据的

//@file: info/gridworld/grid/AbstractGrid.java

//@line: 51~60

```java
 public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        for (Location neighborLoc : getValidAdjacentLocations(loc))
        {
            if (get(neighborLoc) == null)
                locs.add(neighborLoc);
        }
        return locs;
    }
```

5、What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

临近的可能的有效位置数量从八个变成了四个，这样就只会有东南西北了；

// 无码可贴

6、What ensures that a grid has at least one valid location?

当行和列的数量有小于0的时候，构造函数就会抛出一个IllegalArgumentException异常

//@file: info/grid/griworld/BoundedGrid.java

//@line: 39~46

```java
 public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }
```

7、How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

occupantArray[0].length可以得到列的数量，getNumColds返回occupantArray第一行的列数，构造函数保证了每个BoundedGrid对象都至少有一行和一列

//@file: info/grid/gridworld/BoundedGrid.java

//@line: 39~46

```java
 public BoundedGrid(int rows, int cols)
    {
        if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        occupantArray = new Object[rows][cols];
    }
```

8、What are the requirements for a Location to be valid in a BoundedGrid?

location的行值必须大于或者等于0并且小于在BoundedGrid里的行数；location的列值必须大于或者等于0并且小于BoundedGrid里的列的数量

//@file： info/grid/gridworld/BoundedGrid.java

//@line: 60~64

```java
public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }
```

9、What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

getOccupiedLocations函数会返回一个ArrayList<Location>;如果我们用row来表示行数，用colum来代表列数的话，这个函数的时间复杂度就是O(row * colum), 因为BoundedGrid里的每一个location都要检查一次到底有没有被占据；然后被占据的location就会被添加到一个ArrayList里面，这个添加操作的时间复杂度为O(1);

//@file : info/grid/gridworld/BoundedGrid.java

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

10、What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

get函数返回的类型是E；是存在pccupantArray里的任意类型；需要传入一个Location类的对象；在一个二维数组中获取某一行某一列的值（行和列的值都已知）的时间复杂度为O(1)

//@file: info/gridworld/grid/BoundedGrid.java

//@line: 87~90

```java
if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
```

11、What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

如果添加了一个不在grid里的对象的位置，就会抛出一个IllegalArgumentException异常；这个函数的时间复杂度为O(1);

//@file： info/grid/gridworld/BoundedGrid.java

//@line: 95~104

```java
if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
```

12、What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

remove函数会返回类型E，存储在BoundedGrid对象中的任意类型；如果企图移除空位置上不存在的对象，那么将会返回null；但是故意的这样去调用remove函数不会出现错误，因为remove函数有一定的容错性；remove函数的时间复杂度为O(1);

//@file: info/grid/gridworld/BoundedGrid.java

//@line: 109~116

```java
 if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
```

13、Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

我认为这是一个很有效率的实现方法，除了getOccupiedLocations函数的时间复杂度是O(row*column)之外，其他函数的时间复杂度都是O(1)；一个BoundedGrid不仅仅存了所有占有物，它还存了那些未被占领位置上的null值，从这一点上来看，如果我们设计一种grid，只存储那些占据格子的占有物，不去管那些空位值的话，可能会是一种更好的实现方法

//无码可贴

14、Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

Location类必须实现hashCode函数和equals函数.hashCode函数必须返回相同的值当两个位置相等的时候；Location类实现了Comparable接口，因此，实现了compareTo函数使得Location类成为一个非抽象类.当两个位置相等的时候，函数compareTo应该返回0；如果使用TreeMap的话，那么需要可以用来比较的map的键值；而Location类满足以上所有的需求

//无码可贴

15、Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

UnboundedGrid用HashMap作为它的数据结构来保存grid里的对象，在UnBoundedGrid里面，所有的非空位置都是合法的.所以UnBoundedGrid类里面的isValid函数总是返回true，它不会去检查为空的位置. 在一个Map对象里，null是一个合法的值. 而在一个UnBoundedGrid对象里面，null不是一个合法的位置. 因此，UnBoundedGrid类里的get、put、remove函数都会去检查传入的参数location，一旦非法就会抛出一个NullPointerException异常; 当传入的参数是null的时候，在BoundedGrid里面，在访问一个location里的对象之前就会调用isValid函数，如果location参数是null的话，试图调用getRow()就会导致一个NullPointerException异常被抛出. 

16、What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

get、put、remove函数的平均时间复杂度为O(1). 当使用TreeMap而不是HashMap的话，平均时间复杂度会变成O(log n),n代表grid里面被占据格子的数量.

17、How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

大多数情况下，getOccupiedLocations函数会以一种不同的顺序来返回occupants. HashMap的键值会被基于索引的哈希表代替.  这种顺序往往跟遍历的先后顺序有关，而这又跟对象在哈希表中存储的位置有关 . TreeMap采用平衡二叉搜索数来存储键值并通过中序遍历的方式来遍历整棵树. 键值会以在Location中compareTo函数里被定义的递增的方式一样被访问.

18、Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

可以，map可以用于实现一个bounded grid. 如果HashMap被用于实现bounded grid, getOccupiedLocation函数的平均时间复杂度会变成O(n), n代表着grid中对象的数量. 如果bounded grid快要满了，用map来实现的方式会用掉更多的内存，因为map不仅存那些对象，还存储他们的location. 而一个二维的数组只存储对象不去存储location， 每次访问一个对象会传入一个Location类的参数，代表了要访问对象的位置

19、Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the SparseBoundedGrid.

| Methods  | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version | 
| -------- | -------- | -------- | -------- | -------- |
| getNeighbors | O(c) | O(c) | O(1) | O(log n) |
| getEmptyAdjacentLocations | O(c) | O(c) | O(1) | O(log n) |
| getOccupiedAdjacentLocations | O(c) | O(c) | O(1) | O(log n) |
| getOccupiedLocations | O(r+n) | O(r+n) | O(n) | O(n) |
| get | O(c) | O(c) | O(1) | O(log n) |
| move | O(c) | O(c) | O(1) | O(log n) |
| remove | O(c) | O(c) | O(1) | O(log n) |

20、Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

get函数的时间复杂度为 O(1)

put函数的时间复杂度要分情况来说明： 

1）当行和列都在当前array的范围之内的话，那么时间复杂的就是o(1),因为我们就直接根据location进行相关操作就ok，不用再对我们的grid进行操作

2） 当行和列有超过当前范围的时候，那么时间复杂度就是O(dimension * dimension), 因为我们要通过循环不断扩大地图使得这个location处于我们的地图当中，我们还必须把原地图里的数据放入到新地图里才行，所以时间复杂度是O(dimension * dimension)

