#### a. What will a jumper do if the location in front of it is empty, but the location two cells in front contains a flower or a rock?

> 如果一个jumper前面的一个格子是空的但是前面第二个格子里面包含着一朵鲜花或者是一个岩石，这个时候我们规定jumper采取转向动作


#### b. What will a jumper do if the location two cells in front of the jumper is out of the grid?

> 如果一个jumper前面第二个格子已经是位于grid之外了，这个时候我们规定jumper采取转向动作

#### c. What will a jumper do if it is facing an edge of the grid?

> 如果一个jumper面向着grid的一条边，这个时候我们规定jumper采取转向动作

#### d. What will a jumper do if another actor (not a flower or a rock) is in the cell that is two cells in front of the jumper?

> 如果有一个actor正处于另外一个jumper前方的第二个格子当中，这个时候我们规定这个jumper采取转向动作
#### e. What will a jumper do if it encounters another jumper in its path?

> 如果有另外一个jumper正处于一个jumper的路径上的时候，这个时候我们规定这一个jumper可以向前进行jump这个操作，位于它前面第二个格子里的jumper将会从grid中除去

#### f. Are there any other tests the jumper needs to make?

>  1） 如果jumper前面的格子已经被占据了，那么它能跳过它吗，或者是说有些actor允许跳过，有些actor不允许跳过

> 2）如果jumper头朝向的方向是东北方、东南方、西北方、西南方这样的方向，那么允不允许jumper进行jump操作；也就是说允不允许jumper进行斜对角的跳跃