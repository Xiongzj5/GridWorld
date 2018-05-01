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

import info.gridworld.grid.*;

import java.util.ArrayList;

import java.util.*;

/**
 * An <code>UnboundedGrid</code> is a rectangular grid with an unbounded number of rows and
 * columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid<E> extends AbstractGrid<E>
{
    private Object[][] occupantObj;// 用这个来存grid
    private int dimension;//表示现在的维数

    /**
     * Constructs an empty unbounded grid.
     * 初始化为16×16
     */
    public UnboundedGrid()
    {
        dimension = 16;
        occupantObj = new Object[16][16];
    }
    //对于无界的grid，get行和列都是要求返回-1
    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }
    //有一个小于0就会返回false
    public boolean isValid(Location loc)
    {
        boolean flag = ((loc.getRow() >= 0) && (loc.getCol() >= 0));
        return flag;
    }
    //遍历数组，找到所有不为空的grid
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for(int i=0;i<dimension;++i)
        {
            for(int j=0;j<dimension;++j)
            {
                if(get(new Location(i, j)) != null)
                {
                    a.add(new Location(i, j));
                }
            }
        }
        return a;
    }
    //根据传入的loc的行和列来找对对象
    public E get(Location loc)
    {
        if (!isValid(loc)){
            throw new  IllegalArgumentException(loc + " is not valid!");}
        if(loc.getCol() >= dimension || loc.getRow() >= dimension){
            return null;}
        return occupantObj[loc.getRow()][loc.getCol()];
    }
    //把obj放到相应的loc，用传入的位置的行和列的数据作为坐标，如果位置在地图之外，
    //我们就一直扩充地图
    //直到地图足够大到容纳loc这个坐标
    public E put(Location loc, E obj)
    {
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
        if(loc.getCol() >= dimension || loc.getRow() >= dimension)
        {
            int size = dimension;
            while(loc.getCol() >= dimension || loc.getRow() >= dimension){
                size *= 2;
            }
            Object[][] tempArray = new Object[size][size];
            for(int i=0;i<dimension;++i)
            {
                for(int j=0;j<dimension;++j){
                    tempArray[i][j] = occupantObj[i][j];
                }
            }
            occupantObj = tempArray;
            dimension = size;
        }
        E temp = get(loc);
        occupantObj[loc.getRow()][loc.getCol()] = obj;
        return temp;
    }
    //移除loc位置的对象，如果为空就抛出异常
    public E remove(Location loc)
    {
        if(!isValid(loc)){
            throw new  IllegalArgumentException(loc + " is not valid"); 
        }
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        if(loc.getCol() >= dimension || loc.getRow() >= dimension){
            return null;
        }
        E temp = get(loc);
        occupantObj[loc.getRow()][loc.getCol()] = null;
        return temp;
    }
}