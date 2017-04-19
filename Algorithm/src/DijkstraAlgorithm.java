/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lakindu 2015062 w1582971
 */


import java.awt.Color;
import java.util.*;

public class DijkstraAlgorithm {

    Node start;
    Node end;
    
    Node[][] gridArea;
    static double hVDistance;
    static double dDistance;
    static double ManDistance;
    static double totalCost;
    static String distan;
    String selection;

    
    public DijkstraAlgorithm(String dis){
         if (dis.equalsIgnoreCase("E")){
            // Horizontal and VerticalDistance           
            hVDistance = 1.0;
            // Diagonal Distance
            dDistance = 1.4;
           selection=dis;
            StdDraw.setPenColor(Color.RED);
        } 
        else if(dis.equalsIgnoreCase("M")) {
            // Horizontal and VerticalDistance
            hVDistance = 1.0;   
            // Diagonal Distance
            dDistance = 2.0; 
            selection = dis;
            StdDraw.setPenColor(Color.CYAN);
        }
        else if(dis.equalsIgnoreCase("C")){
            // Horizontal and VerticalDistance
            hVDistance = 1.0;  
            // Diagonal Distance
            dDistance = 1.0; 
            selection = dis;
            StdDraw.setPenColor(Color.GRAY);
        }
       
    }

    /**
     *
     * @param matrix The boolean matrix from the framework given
     * @param ai start x value
     * @param aj start y value
     * @param bi end x value
     * @param bj end x value
     * @return The path nodes
     */
    
    ArrayList<Node> distance(boolean[][] matrix, int Ai, int Aj, int Bi, int Bj) {

        int size = matrix.length;

        start = new Node(Ai, Aj);
        end = new Node(Bi, Bj);
        // The grid that is used to store nodes
        gridArea = new Node[size][size];

        // Creating nodes and finding blocked cells in matrix and mapping accordingly to our grid
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                gridArea[i][j] = new Node(i, j);
                if (matrix[i][j] == false) {
                    gridArea[i][j].blocked = true;
                }
            }
        }

        // Setting start distance to 0. 
        // All other nodes will have infinity distance at the beginning
        start.distance =0;

        // a comparator object to deal with Priority Queue
        Comparator<Node> adjacencyComparator = (left, right) -> {
            if (left.distance > (right.distance)) {
                return 1;
            }
            return -1;
        };

        // Queue to store visiting nodes
        Queue<Node> queueB = new PriorityQueue(size, adjacencyComparator);

        queueB.add(start);

          while (queueB.size() > 0) {
            Node current = queueB.remove();
            Node t;

            // Top
            if (current.x - 1 >= 0) {

                // Top Top
                t = gridArea[current.x - 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
                if(!selection.equalsIgnoreCase("m")){
                    // Top Left
                    if (current.y - 1 > 0) {
                        t = gridArea[current.x - 1][current.y - 1];
                        if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                            t.distance = current.distance + dDistance;
                            t.parent = current;
                            queueB.add(t);
                        }
                    }

                    // Top Right
                    if (current.y + 1 < size) {
                        t = gridArea[current.x - 1][current.y + 1];
                        if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                            t.distance = current.distance + dDistance;
                            t.parent = current;
                            queueB.add(t);
                        }
                    }
                }

            }

            // Left
            if (current.y - 1 > 0) {
                t = gridArea[current.x][current.y - 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }

            // Right
            if (current.y + 1 < size) {
                t = gridArea[current.x][current.y + 1];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
            }
            // Down
            if (current.x + 1 < size) {

                // Down Down
                t = gridArea[current.x + 1][current.y];
                if (!t.visited && !t.blocked && t.distance > current.distance + hVDistance) {
                    t.distance = current.distance + hVDistance;
                    t.parent = current;
                    queueB.add(t);
                }
                if(!selection.equalsIgnoreCase("m")){
                    // Down Left
                    if (current.y - 1 >= 0) {
                        t = gridArea[current.x + 1][current.y - 1];
                        if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                            t.distance = current.distance + dDistance;
                            t.parent = current;
                            queueB.add(t);
                        }
                    }

                    // Down Right
                    if (current.y + 1 < size) {
                        t = gridArea[current.x + 1][current.y + 1];
                        if (!t.visited && !t.blocked && t.distance > current.distance + dDistance) {
                            t.distance = current.distance + dDistance;
                            t.parent = current;
                            queueB.add(t);
                        }
                    }
                }

            }
            current.visited = true;
        }
        double fcost=0;
        ArrayList<Node> path = new ArrayList<>();

        // Checking if a path exists 
           if (!(gridArea[end.x][end.y].distance == Integer.MAX_VALUE)) { 
            //Trace back the path 
            Node current = gridArea[end.x][end.y];
            path.add(current);
            fcost +=current.distance; 
            while (current.parent != null) { 
                path.add(current.parent); 
                current = current.parent; 
            }  System.out.println("Total Cost: " + fcost); 
           } else {
            System.out.println("No possible path here");
           }
           

            return path; 
    }
        
}