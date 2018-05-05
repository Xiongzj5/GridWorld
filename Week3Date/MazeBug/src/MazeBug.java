//package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	public Location next; //记录下一步要行走到的位置
	public Location last; //记录上一步的位置，便于在走到死路尽头时返回
	public boolean isEnd = false; //判断有没有到地图出口
	public Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>(); //记录树的节点的栈
	public Integer stepCount = 0; //记录本迷宫走到出口所用的步数
	boolean hasShown = false;//final message has been shown
	int north, south, east, west; //只能走东南西北四个方向
	public boolean isVisit[][]; // 节点有没有被访问过
	ArrayList<Location> canTry; // 可以走的节点

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		// 设置初始返回节点(0,0)
		last = new Location(0, 0);
		canTry = new ArrayList<Location>();
		// 东南西北每个方向的初始化为1次，以后根据目标移动的情况进行增加和删除
		north = 1;
		south = 1;
		east = 1;
		west = 1;
		// 初始为足够大的boolean数组，判断是否有被访问过
		isVisit = new boolean[200][200];
		// 一开始全都为false
		for(int i=0;i<200;++i)
		{
			for(int j=0;j<200;++j)
			{
				isVisit[i][j] = false;
			}
		}
		// 找到当前位置，
		Location loc = getLocation();
		canTry.add(loc);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() 
	{
		boolean willMove = canMove();
		if (isEnd == true) 
		{
		//to show step count when reach the goal		
			if (hasShown == false) 
			{
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} 
		else if (willMove) 
		{
			isVisit[next.getRow()][next.getCol()] = true;
			move();
			//increase step count when move 
			stepCount++;
		} 
		else
		{
			goBack();
			stepCount++;
		}
		
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		ArrayList<Location> valid = new ArrayList<Location>();
		int directions[] = {Location.AHEAD, Location.RIGHT, Location.LEFT, Location.HALF_CIRCLE};
		// 对四个方向的位置进行预判断，是否合法
		// 这里我们需要一个for循环
		for(int dir: directions)
		{
			// 获取这个方向的临近位置
			Location neighborLocation = loc.getAdjacentLocation(dir + getDirection());
			// 获取到这个方向上临近位置的行和列
			int row = neighborLocation.getRow();
			int col = neighborLocation.getCol();
			// 如果位置合法，我们进行以下操作
			if(gr.isValid(neighborLocation))
			{
				Actor actor = gr.get(neighborLocation);
				// 没被访问，为空或为花
				if(!isVisit[row][col] && (actor instanceof Flower || actor == null))
				{
					valid.add(neighborLocation);
				}
				// 为石头，是不是出口
				else if(actor instanceof Rock)
				{
					Color r = Color.RED;
                    if (r.equals(actor.getColor()))
                        isEnd = true;
				}
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		ArrayList<Location> loc = getValid(getLocation());
		if(loc.isEmpty())
		{
			return false;
		}
		else //合法位置不为空就把当前位置放到canTry里面
		{
			
			canTry.add(getLocation());
			int locSize = loc.size();

			if(locSize >= 2) 
			{
				// 在记录树的节点的栈里面我们把新的路径放进去
				crossLocation.push(canTry);
				canTry = new ArrayList<Location>();
				// 我们从所有合法的节点里面选择最符合预测的节点作为我们的下一个节点
				next = bestDirection(loc);
			}
			else
			{
				next = loc.get(0);
			}

		}
		return true;
	}
	/*
	* 这个函数用来判断最佳的方向是哪一边，这个需要用到我们的预测方法
	*/
	public Location bestDirection(ArrayList<Location> loc)
	{
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return null;
		int dir;
		int up, down, left, right;
		up = down = left = right = 0;
		ArrayList<Location> array = new ArrayList<Location>();

		for(Location n: loc)
		{
			Actor actor = gr.get(n);
			if(!(actor instanceof Flower))
				array.add(n);
			else
				continue;

		}
		for(Location temp: array)
		{
			dir = getLocation().getDirectionToward(temp);
			if(dir == 0)
			{
				up = north;
			}
			else if(dir == 90)
			{
				right = east;
			}
			else if(dir == 180)
			{
				down = south;
			}
			else if(dir == 270)
			{
				left = west;
			}
		}
		int random = 1 + (int)((up + down + left + right) * Math.random());
		if(random <= left)
		{
			dir = 270;
			west++;
		}
		else if (random <= (left + right))
		{
            dir = 90;
            east++;
		} 
		else if (random <= (left + right + up)) 
		{
			dir = 0;
			north++;
		} 
		else 
		{
			dir = 180;
			south++;
		}
		Location bestLoc = null;
		for (Location temp : array) {
            if (dir == getLocation().getDirectionToward(temp))
                bestLoc = temp;
        }
        return bestLoc;

	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null)
			return;
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} else
			removeSelfFromGrid();
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}

	/*
	* goBack()函数是在迷宫走不下去的情况下，回溯到最近的一次情况
	*/
	public void goBack()
	{
		int nodeSize;
		if(canTry.isEmpty())
		{
			canTry = crossLocation.pop();
			Location loc = canTry.get(canTry.size() - 1);
			// 当前位置和canTry里面的最后一个位置之间的角度， 判断最后一次行动是往哪个方向走
			int dir = getLocation().getDirectionToward(loc);
			if(dir == 0)
			{
				south--;
			}
			else if(dir == 90)
			{
				west--;
			}
			else if(dir == 180)
			{
				north--;
			}
			else
			{
				east--;
			}
		}
		nodeSize = canTry.size();
		next = canTry.remove(nodeSize - 1);
		move();
	}
}
