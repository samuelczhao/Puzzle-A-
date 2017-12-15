public class Board 
{    
	int[][] blocks;
	int dimension;
	
	public Board(int[][] blocks)
	{
		this.blocks = blocks;
		dimension = blocks.length;
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
		int[][] twin = new int[dimension][dimension];
		
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension; j++)
			{
				twin[i][j] = blocks[i][j];
			}	
		}
		
		
		for (int i = 0; i < dimension; i++)
		{
			for (int j = 0; j < dimension - 1; j++)
			{
				if (blocks[i][j] != 0 && blocks[i][j + 1] != 0)
				{
					int temp = twin[i][j + 1];
					
					twin[i][j + 1] = twin[i][j];
					twin[i][j] = temp;
					
					return new Board(twin);
				}
			}
		}
		
		return new Board(twin);
	}

	public Iterable<Board> neighbors()
	{
		throw new UnsupportedOperationException();
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
}