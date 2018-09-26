package algo_project2;

import java.util.*;

import java.io.*;

import algo_project2.Edge;
import algo_project2.Parent;

public class kruskal

{

   int V, E;              	// V is number of vertices and E is number of edges
   private Edge edge[];   	//array of all the edges
   private Parent parents[];

	
   //Constructor for Initialization of new graph with V vertices and E edges

	public kruskal(int v, int e)

	{
		V = v;
		E = e;
		edge = new Edge[E];
		parents = new Parent[V];

		for (int i=0; i<E; ++i)
			edge[i] = new Edge();
		

		for(int i=0; i<V; ++i)
			parents[i]=new Parent();

	}



	// The main function to find Minimum spanning tree using Kruskal's algorithm

	public void KruskalMST()

	{
        double startTime, endTime, totalTime;
		startTime = System.nanoTime(); //start time before reading the file

		Edge result[] = new Edge[V]; // Tnis will store the resultant MST

		int e = 0; // An index variable, used for result[]
		int i = 0; // An index variable, used for sorted edges

		for (i=0; i<V; ++i)
			result[i] = new Edge();
		
		// Step 1: Sort all the edges in non-decreasing order of their weight
		Arrays.sort(edge);

		/*
		System.out.println("\n\n=======================================================================");
		System.out.println("Sorted Array of Edges: \n\n");
		
		for (i=0; i<E; ++i)
			System.out.println((char)(edge[i].src+65)+"\t\t " + (char)(edge[i].dest+65)+"\t" + edge[i].weight);
		
		System.out.println("\n\n=======================================================================");
		System.out.println("Parent Array of Vertices: \n\n");
		
		for (i=0; i<V; ++i)
			System.out.println("Parent and Rank of "+(char)(i+65)+ "::\t"+(char)(parents[i].parent+65)+"\t\t " + parents[i].rank+"\t");

		System.out.println("\n\n=======================================================================");
        */

		i = 0;
		
		while (e < V - 1) // Number of edges to be taken is V-1

		{
			// Step 2: Pick the smallest edge. And increment the index for the next iteration

			Edge next_edge = new Edge();

			next_edge = edge[i++];

			
	//		System.out.println("Edge"+ e + "\t:\t" + (char)(next_edge.src+65)+" \t" + (char)(next_edge.dest+65)+" \t " + next_edge.weight);
	
			int x = parents[next_edge.src].find_parent(parents, next_edge.src);
			int y = parents[next_edge.dest].find_parent(parents, next_edge.dest);

	//		System.out.println("Before Union: parent of " + (char)(next_edge.src+65)+ ":" + (char)(x+65) +" \t" + (char)(next_edge.dest+65)+ ":" + (char)(y+65) +" \t ");

			
			// If including this edge does't cause cycle, include it in result and increment the index of result for next edge

			if (x != y)
			{
				result[e++] = next_edge;
				parents[next_edge.src].Union(parents, x, y);
			}
			
	//		System.out.println("After Union : parent of " + (char)(next_edge.src+65)+ ":" + (char)(x+65) +" \t" + (char)(next_edge.dest+65)+ ":" + (char)(y+65) +" \t ");
			// Else discard the next_edge
		}

		// print the contents of result[] to display the built MST

		endTime   = System.nanoTime(); //End time after reading the file
		totalTime = (endTime - startTime)/1000;
		System.out.println("Time elapsed for running Kruskal's Algorithm in Microseconds :" + (totalTime));
		
		
		System.out.println("\n=======================================================================\n");
		
		System.out.println("Following are the edges in the constructed MST using Kruskal's Algorithm :\n");

		System.out.println("Source \t Destination \t Weight");
		System.out.println("--------------------------------");
        int cost = 0;
		for (i = 0; i < e; ++i)
		{
			System.out.println((char)(result[i].src+65)+" \t\t" + (char)(result[i].dest+65)+" \t " + result[i].weight);
		    cost = cost + result[i].weight;
		}

		System.out.println("\n=======================================================================\n");
		System.out.println("The cost of the constructed MST is :" + cost);
		System.out.println("\n=======================================================================\n");
	}

	public static void main (String[] args)throws FileNotFoundException

	{

		double startTime, endTime, totalTime; 
		File file = new File("Input_files/input1.txt");

		Scanner sc = new Scanner(file);

		int V = sc.nextInt(); // Number of vertices in graph
		int E = sc.nextInt(); // Number of edges in graph

		kruskal graph = new kruskal(V, E); //Initialization of the graph

		System.out.println("Given graph:");
		System.out.println("Source \t Destination \t Weight");

		sc.next().charAt(0);

		startTime = System.nanoTime(); //start time before reading the file
				
		for(int i=0;i<E;i++)
		{
			int source  = sc.next().charAt(0)-65;
			int dest    = sc.next().charAt(0)-65;
			int weight  = sc.nextInt();

			graph.edge[i].src = source;
			graph.edge[i].dest = dest;
			graph.edge[i].weight = weight;

			System.out.println((char)(source+65)+"\t\t " + (char)(dest+65)+"\t" + weight);
		}
		
		for (int i=0; i<V; ++i)
		{
			graph.parents[i].parent = i;
			graph.parents[i].rank = 0;
		}
		
		endTime   = System.nanoTime(); //End time after reading the file
		totalTime = (endTime - startTime)/1000;
		sc.close();

		System.out.println("\n=======================================================================\n");
		
		System.out.println("Time elapsed for reading the file in Microseconds :" + (totalTime));
		
		graph.KruskalMST();
	}

}

