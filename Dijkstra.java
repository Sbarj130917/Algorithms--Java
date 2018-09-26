package algo_project2;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class Dijkstra {
	private int adjMatrix[][];
	private int vertex,edge,source;
	private int[] dist;
	private int[] path;

	public void input(String filename) throws IOException {
		FileReader fd=null;

		try {
			fd=new FileReader(new File(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(fd!=null) {
			BufferedReader br=new BufferedReader(fd);
			String line;
			if((line=br.readLine())!=null) {
				String[] arr=line.split(" ");
				vertex=Integer.parseInt(arr[0]);
				//creating adjacency matrix
				adjMatrix=new int[vertex][vertex];
				edge=Integer.parseInt(arr[1]);
				for(int i=0;i<edge;i++) {
					line=br.readLine();
					arr=line.split(" ");
					adjMatrix[(arr[0].charAt(0))-65][(arr[1].charAt(0))-65]=Integer.parseInt(arr[2]);
					adjMatrix[(arr[1].charAt(0))-65][(arr[0].charAt(0))-65]=Integer.parseInt(arr[2]);
				}
			}
			if((line=br.readLine())!=null) {
				source=line.charAt(0)-65;
			}
			//taking default source as first index, if not explicitly mentioned in the input file
			else { 
				source=0;
			}
		}
	}

	public void dijkstra() {
		dist=new int[vertex];
		boolean[] visited=new boolean[vertex];
		// Initializing distance with max value and all nodes with False
		for(int i=0;i<vertex;i++) {
			dist[i]=Integer.MAX_VALUE;
			visited[i]=false;
		}
		path=new int[vertex];
		// Initializing source values
		dist[source]=0;
		path[source]=-1;
		for(int i=0;i<vertex;i++) {
			int nextVertex=-1;
			int shortestDistance=Integer.MAX_VALUE;
			for(int v=0;v<vertex;v++) {
				if(visited[v]==false && dist[v]<shortestDistance) {
					nextVertex=v;
					shortestDistance=dist[v];
				}
			}
			visited[nextVertex]=true;
			for(int v=0;v<vertex;v++) {
				int edgeDistance=adjMatrix[nextVertex][v];
				if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < dist[v])) {
					path[v] = nextVertex;
					dist[v] = shortestDistance + edgeDistance;
				}
			}
		}
	}

	public void result() {
		System.out.print("Vertex\t\tTotal Cost\t\tPath");
		for (int i = 0; i < vertex; i++)
		{
			if (i!= source)
			{
				System.out.print("\n" + getChar(source) + " -> ");
				System.out.print(getChar(i) + " \t\t ");
				System.out.print(dist[i] + "\t\t\t");
				int v=i;
				String str="";
				while(path[v]!=-1) {
					str=getChar(v)+" "+str;
					v=path[v];
				}
				System.out.print(str);
			}
		}
	}
	private char getChar(int i) {
		String alphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return alphabet.charAt(i);
	}
}



