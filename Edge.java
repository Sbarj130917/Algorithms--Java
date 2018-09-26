package algo_project2;

public class Edge implements Comparable<Edge>
{
		int src, dest, weight;
	
		public int getSrc() {
			return src;
		}

		public void setSrc(int src) {
			this.src = src;
		}

		public int getDest() {
			return dest;
		}

		public void setDest(int dest) {
			this.dest = dest;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}
		
		//Overriding the compareTo method to sort the edges based on weight
		@Override
		public int compareTo(Edge compareEdge)
		{
			return this.weight-compareEdge.weight;
		}	
		

};

