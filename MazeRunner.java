package Assignment4;

import BasicIO.*;
import Media.*;
import java.lang.Math.*;

/** This class ...
  *
  * @author Ahmed Yaser
  * @student ID : 7063944
  * @course : COSC 1P03 
  * @version 1.0 (28/03/2021)                                                */


public class MazeRunner {
  
  private ASCIIDataFile input; //maze input file 
  private ASCIIOutputFile output; //maze output file
  
  private char[][] mazeArray;
  private int row , col;
  
  private int ranRowStart, ranColStart, ranRowEnd, ranColEnd;
  private int rowStart, colStart, rowEnd, colEnd;
  
  
  
  public MazeRunner() {
    
    input = new ASCIIDataFile(); //mazze input
    output = new ASCIIOutputFile(); //maze output
    
    row = input.readInt(); //reads the row size of the maze
    col = input.readInt(); //reads the colomn size of the maze
    
    mazeArray = new char[row][col]; //initializing the array
    
    generateRandomPos();
    readMaze();
    
    //To check if there is a path found then print the solution else there is no solution or path
    if(findPath(ranRowStart, ranColStart)){
      output.newLine();
      output.writeString("Gretel's starting position: (" + rowStart + ", " + colStart + ")" );
      mazeArray[rowStart][colStart] = 'G';
      output.newLine();
      output.writeString("Hansel was found at: (" + rowEnd + ", " + colEnd + ")" );
      output.newLine();
    }
    else{
      output.writeString("No Solution for path");
    }
    input.close();
    printMaze();
    
  }//Constructor
  
  /*This Method
   * reads the maze inputted by the user
   */
  
  public void readMaze() {
    for(int i = 0; i<mazeArray.length; i++) {
      String t = input.readLine();
      for(int j = 0; j<mazeArray[i].length; j++) {
        mazeArray[i][j] = t.charAt(j); //storing the characters into the initialized character array
        findStart(i,j); //places G position 
        findEnd(i,j); //places H position
      }
    }
    
  } //readMaze method
  
  /*This Method
   * prints the array
   */
  
  public void printMaze() {
    output.newLine();
    for(int i = 0; i<mazeArray.length; i++) {
      for(int j = 0; j<mazeArray[i].length; j++) {
        output.writeC(mazeArray[i][j]);
      }
      output.newLine();
    }
    
  }//printMaze method
  
  /*This method
   * 
   * Generates a random position that will be assigned to G and H
   * it generates the random posiiion using math.random 
   */
  public void generateRandomPos() {
    
    ranRowStart = (int) ((row - 2) * Math.random() + 1);
    ranColStart = (int) ((col - 2) * Math.random() + 1);
    ranRowEnd = (int) ((row - 2) * Math.random() + 1);
    ranColEnd = (int) ((col - 2) * Math.random() + 1);
    
    if(ranRowStart == ranRowEnd && ranColStart == ranColEnd) {
      ranRowStart = (int) ((row - 2) * Math.random() + 1);
      ranColStart = (int) ((col - 2) * Math.random() + 1);
      ranRowEnd = (int) ((row - 2) * Math.random() + 1);
      ranColEnd = (int) ((col - 2) * Math.random() + 1);
    }
    
    
  }//generateRandom Method
  
  /*This Method
   * finds the starting position of G
   * @param x is the row
   * @param y is the colomn 
   */
  
  public void findStart(int x, int y){
    
    if(mazeArray[ranRowStart][ranColStart] == '#') {
      ranRowStart = (int)((row-2) * Math.random() + 1);
      ranColStart = (int)((col-2) * Math.random() + 1);
      
      if(ranRowEnd == ranRowStart && ranColEnd == ranColStart) {
        findStart(x, y);
      }
      if(mazeArray[ranRowStart][ranColStart] == '#'){
        findStart(x,y);
      }
    }
    else {
      mazeArray[ranRowStart][ranColStart] = 'G';
      rowStart = ranRowStart;
      colStart = ranColStart;
    }
    
  }//findStart Method
  
  /*This method
   * finds the position of H
   * @param x is the row
   * @param y is the colomn
   * 
   * 
   */
  public void findEnd(int x, int y) {
    
    if(mazeArray[ranRowEnd][ranColEnd] == '#'){
      
      ranRowEnd = (int)((row-2) * Math.random() + 1);
      ranColEnd = (int)((col-2) * Math.random() + 1);
      
      if(ranRowStart == ranRowEnd && ranColStart == ranColEnd){
        findEnd(x, y);
      }
      if(mazeArray[ranRowEnd][ranColEnd] == '#') {
        findEnd(x, y);
      }
    }
    else{
      mazeArray[ranRowEnd][ranColEnd] = 'H';
      rowEnd = ranRowEnd;
      colEnd = ranColEnd;
    }
    
  }//findEnd Method
  
  /* This method
   * is the the recursive method
   * it finds the required path
   * @param x is the maze row
   * @param y is the maze colomn
   * 
   */
  
  public boolean findPath(int x, int y){
    
    if(mazeArray[x][y] == 'H') { //The base case incase we found Hansel
    return true;
  }
  if((mazeArray[x][y] != ' ') && mazeArray[x][y] != 'G' || mazeArray[x][y] == '.'){ //base case 2 and 3 
    return false;
  }
  mazeArray[x][y] = '.'; //places a dot at the location already visited that has no solution
  
  //recursive sequences to check for soltuions
  
  //recursive method that checks up for solution. if solution is found then continue path
  if(findPath(x-1,y)){
    mazeArray[x][y] = '^';
    return true;
  }
   //recursive method that checks right for solution. if solution is found then continue path
  if(findPath(x,y+1)){
    mazeArray[x][y] = '>';
    return true;
  }
   //recursive method that checks dowm for solution. if solution is found then continue path
    if(findPath(x+1,y)){
    mazeArray[x][y] = 'v';
    return true;
  }
    //recursive method that checks left for solution. if solution is found then continue path
    if(findPath(x,y-1)){
      mazeArray[x][y] = '<';
      return true;
    }
    return false;
}
  
  public static void main(String[] args) {MazeRunner c = new MazeRunner();}
}//MazeRunner


      
                         
  
  
  
  
    