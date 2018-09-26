package algo_project2;

import java.io.IOException;

public class DijkstraMain {
	public static void main(String[] args) throws IOException {
		String filename="Input_files/input4.txt";
		Dijkstra obj=new Dijkstra();
		obj.input(filename);
		long start = System.nanoTime();
		obj.dijkstra();
		long end=System.nanoTime();
		long diff=(end-start)/1000;
		System.out.print( "===============================================================\n\n");
		System.out.print( "Time elapsed for running Dijkstra's Algorithm in Microseconds : "+diff+"\n\n");
		System.out.print( "===============================================================\n\n");
		obj.result();
	}
}
