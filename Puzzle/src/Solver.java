public class Solver
{
	public Solver(Board initial)
	{
		throw new UnsupportedOperationException();
	}

	public boolean isSolvable()
	{
		throw new UnsupportedOperationException();
	}

	public int moves()
	{
		throw new UnsupportedOperationException();
	}

	public Iterable<Board> solution()
	{
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args)
	{
		// create initial board from file
		In in = new In("testInput/puzzle04.txt");
		int N = in.readInt();
		int[][] blocks = new int[N][N];
		for (int i = 0; i < N; i++)
		{
			for (int j = 0; j < N; j++)
			{
				blocks[i][j] = in.readInt();
			}
		}

		Board initial = new Board(blocks);

		// solve the puzzle
		Solver solver = new Solver(initial);

		// print solution to standard output
		if (!solver.isSolvable())
		{
			StdOut.println("No solution possible");
		}
		else 
		{
			StdOut.println("Minimum number of moves = " + solver.moves());
			for (Board board : solver.solution())
			{
				StdOut.println(board);
			}
		}
	}
}
