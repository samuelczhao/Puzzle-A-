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
		for (int i = 0; i < )
		{
			for()
			{
				
			}
		}
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
		throw new UnsupportedOperationException();
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
}