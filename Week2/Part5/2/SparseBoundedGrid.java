/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location; 
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private Map<Location, E> occupantMap; // 存储所有的grid在一个地图上
    private int colNum;    //列数
    private int rowNum;    //行数
    /**
     * 首先要在构造函数里面构建一个无界的全部是空的grid
     * 使用的数据结构是map
     * 可以把它看作是一个动态的数组
     * 记录下构造时map的行数和列数
     * 根据初始列数行数来初始化新建map
     * 
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        colNum = cols;
        rowNum = rows;
        occupantMap = new HashMap<Location,E>();
    }

    public int getNumRows()
    {
        return rowNum;
    }

    public int getNumCols()
    {
        return colNum;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> tempList = new ArrayList<Location>();
        for(Location loc: occupantMap.keySet())
        {
            tempList.add(loc);
        }
        return tempList;
    }
    //获取到相应位置的对象
    public E get(Location loc)
    {
        if (loc == null)
            throw new IllegalArgumentException("loc == null");
        return occupantMap.get(loc);
    }
    /*
    * 当loc或者object为null的时候，有相应的异常抛出
    */
    public E put(Location loc, E obj)
    {
        if(loc == null)
        throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }
    /*
    * 对于map，我们直接使用remove函数，把该位置的对象移除掉
    */
    public E remove(Location loc)
    {
        if (loc == null)
            throw new IllegalArgumentException("loc == null");
        return occupantMap.remove(loc);
    }
}
