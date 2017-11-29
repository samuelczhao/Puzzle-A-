public class Board 
{    
	public Board(int[][] blocks)
	{
		throw new UnsupportedOperationException(); //
	}

	public int dimension()
	{
		throw new UnsupportedOperationException();
	}

	public int hamming()
	{
		throw new UnsupportedOperationException();
	}

	public int manhattan()
	{
		throw new UnsupportedOperationException();
	}

	public boolean isGoal()
	{
		throw new UnsupportedOperationException();
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
		throw new UnsupportedOperationException();
		
		// To implement this method, remove the throw above,
		// uncomment the code below, and change the
		// names of any fields to match what you're
		// storing in your Board class
		
		/*
		StringBuilder s = new StringBuilder();
		s.append(length + "\n");
		for (int i = 0; i < length; i++) 
		{
			for (int j = 0; j < length; j++) 
			{
				s.append(String.format("%2d ", (int) tiles[i][j]));
			}
			s.append("\n");
		}
		return s.toString();
		*/
	}
}