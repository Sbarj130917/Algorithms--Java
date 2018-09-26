package algo_project2;


// A class to represent a subset for finding union

public class Parent {
	
	int parent, rank;

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	// A utility function to find set of an element i using path compression technique

	public int find_parent(Parent parents[], int i)
	{
		//  find root and make root as parent of i (path compression)
     	if (parents[i].parent != i)
	    	parents[i].parent = find_parent(parents, parents[i].parent);
		
     	return parents[i].parent;
		//return this.parent;
	}

	// A function that does union of two sets of x and y

	public void Union(Parent parents[], int x, int y)

	{

		int xroot = find_parent(parents, x);
		int yroot = find_parent(parents, y);

		// Attach smaller rank tree under root of high rank tree (Union by Rank)

		if (parents[xroot].rank < parents[yroot].rank)

			parents[xroot].parent = yroot;

		else if (parents[xroot].rank > parents[yroot].rank)

			parents[yroot].parent = xroot;

		else // If ranks are same, then make one as root and increment its rank by one

		{
			parents[yroot].parent = xroot;
			parents[xroot].rank++;
		}

	}
	
}
