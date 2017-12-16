import java.util.ArrayList;

public class Solver
{
	private Board init;
	private Boolean isSolvable;
	private SearchNode kappa;
	private SearchNode pride;
	private MinPQ<SearchNode> oG;
	private MinPQ<SearchNode> tW;
	
	private static class SearchNode implements Comparable
	{
		private SearchNode searchNode;
		private Board board;
		private int moves;
		private int priority;
		
		private SearchNode(SearchNode searchNode, Board board)
		{
			this.searchNode = searchNode;
			
			int[][] thing = new int[board.dimension][board.dimension];
			
			for (int i = 0; i < board.dimension; i++)
			{
				for (int j = 0; j < board.dimension; j++)
				{
					thing[i][j] = board.blocks[i][j];
				}
			}
			
			this.board = new Board(thing);
			
			if (searchNode == null)
			{
				moves = 0;
			}
			else
			{
				this.moves = searchNode.moves + 1;
			}
			
			priority = board.manhattan() + moves;
		}
		
		public int compareTo(Object thing)
		{
			if (priority > ((SearchNode)thing).priority)
			{
				return 1;
			}
			else if (priority == ((SearchNode)thing).priority)
			{
				return 0;
			}
			else
			{
				return -1;
			}
		}
	}
	
	public Solver(Board initial)
	{
		if (initial == null)
		{
			throw new NullPointerException();
		}

		int[][] thing = new int[initial.dimension][initial.dimension];
		
		for (int i = 0; i < initial.dimension; i++)
		{
			for (int j = 0; j < initial.dimension; j++)
			{
				thing[i][j] = initial.blocks[i][j];
			}
		}
		
		init = new Board(thing);
		
		oG = new MinPQ<SearchNode>();
		tW = new MinPQ<SearchNode>();
		
		oG.insert(kappa = new SearchNode(null, init));
		tW.insert(pride = new SearchNode(null, init.twin()));
		
		while(true)
		{
			if (kappa.board.isGoal())
			{
				isSolvable = true;
				break;
			}
			kappa = solve(kappa, oG);
			
			if (pride.board.isGoal())
			{
				isSolvable = false;
				break;
			}
			
			pride = solve(pride, tW);
		}
	}

	public boolean isSolvable()
	{
		return isSolvable;
	}

	public int moves()
	{
		if (isSolvable())
		{
			return kappa.moves;
		}
		else
		{
			return -1;
		}
	}

	public Iterable<Board> solution()
	{
		if (!isSolvable())
		{
			return null;
		}
		
		ArrayList<Board> solution = new ArrayList<Board>();
		
		while (kappa != null)
		{
			solution.add(kappa.board);
			kappa = kappa.searchNode;
		}
		
		return solution;
	}

	
	private SearchNode solve(SearchNode node, MinPQ<SearchNode> thing)
	{
		Iterable<Board> neighbors = node.board.neighbors();

        for (Board board : neighbors) 
        {
            if (node.searchNode == null || !node.searchNode.board.equals(board)) 
            {
                thing.insert(new SearchNode(node, board));
            }
        }

        return thing.delMin();
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
