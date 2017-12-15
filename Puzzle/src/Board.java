import java.util.ArrayList;

public class Board 
{    
	int dimension;
	int[][] blocks;
	
	public Board(int[][] blocksss)
	{
		dimension = blocks.length;
		
		blocks = new int[blocksss.length][blocksss.length];
		
		for (int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				blocks[i][j] = blocksss[i][j];
			}
		}
	}

	public int dimension()
	{
		return dimension;
	}

	public int hamming()
	{
		int count = 0;
		
		for (int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				if (blocks[i][j] != 0)
				{
					if (blocks[i][j] != end(i, j))
					{
						count++;
					}
				}
			}
		}
		
		return count;
	}

	public int manhattan()
	{
		int count = 0;
		
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				count += manhattanDistance(i, j);
			}
		}
		
		return count;
	}

	public boolean isGoal()
	{
		for (int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				if (blocks[i][j] != end(i, j))
				{
					return false;
				}
			}
		}
		
		return true;
	}

	public Board twin()
	{	
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension - 1; j++)
			{
				if (blocks[i][j] != 0 && blocks[i][j + 1] != 0)
				{
					return new Board(swap(i, j, i, j + 1));
				}
			}
		}
		
		return null;
	}

	public Iterable<Board> neighbors()
	{
		ArrayList<Board> neighbors = new ArrayList<Board>();
		
		int blankRow = blankRow();
		int blankColumn = blankColumn();
		
		if (blankRow > 0)
		{
			neighbors.add(new Board(swap(blankRow, blankColumn, blankRow - 1, blankColumn)));
		}
		
		if (blankRow < dimension - 1)
		{
			neighbors.add(new Board(swap(blankRow, blankColumn, blankRow + 1, blankColumn)));
		}
		
		if (blankColumn > 0)
		{
			neighbors.add(new Board(swap(blankRow, blankColumn, blankRow, blankColumn - 1)));
		}
		
		if (blankColumn < dimension - 1)
		{
			neighbors.add(new Board(swap(blankRow, blankColumn, blankRow, blankColumn + 1)));
		}
		
		return neighbors;
	}

	public String toString()
	{
		StringBuilder s = new StringBuilder();
		s.append(dimension + "\n");
		for (int i = 0; i < dimension; i++) 
		{
			for (int j = 0; j < dimension; j++) 
			{
				s.append(String.format("%2d ", (int) blocks[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
	}
	
	public boolean equals(Object y)
	{
		if (!(y instanceof Board))
		{
			return false;
		}
		
		Board newBoard = (Board)y;
		
		for (int i = 0; i < dimension; i++)
		{
			for(int j = 0; j < dimension; j++)
			{
				if (blocks[i][j] != newBoard.blocks[i][j])
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	private int end(int m, int n)
	{
		if (m == dimension - 1 && n == dimension - 1)
		{
			return 0;
		}
		
		return dimension * m + n + 1;
	}
	
	private int manhattanDistance(int m, int n)
	{
		int thing = blocks[m][n];
		
		if (thing != 0)
		{
			int row = (thing - 1) / dimension;
			int column = (thing - 1) % dimension;
			
			return Math.abs(m - row) + Math.abs(n - column);
		}
		
		return 0;
	}
	
	private int[][] swap(int a, int b, int c, int d)
	{
		int [][] thing = new int[dimension][dimension];
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				thing[i][j] = blocks[i][j];
			}	
		}
		
		int temp = thing[a][b];
		
		thing[a][b] = thing[c][d];
		thing[c][d] = temp;
		
		return thing;
	}
	
	private int blankRow()
	{
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if (blocks[i][j] == 0)
				{
					return i;
				}
			}
		}
		
		return 0;
	}
	
	private int blankColumn()
	{
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				if (blocks[i][j] == 0)
				{
					return j;
				}
			}
		}
		
		return 0;
	}
}