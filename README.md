[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/8jM7fhXE)
# Assignment A3 - Maze Runner Take Two

  * **Student**: [Andrew Habib](habiba21@mcmaster.ca)
  * **Program**: B. Eng. In Software Engineering
  * **Course code**: SFWRENG 2AA4
  * **Course Title**: Software Design I - Introduction to Software Development 
  * Term: *Level II - Winter 2024*
  * References: Dr. Hellings - Implementation of BFS Algorithm used in this project is inspired by SFWRENG 2C03 Slide Set 9 Slide Number 159 for Breadth-First Shortest Path Algorithm on Unweighted graphs along with Slide Set 10 Number 226 for condition and path and cost storing.

## Business Logic Specification

This program explores a maze, finding a path from an entry point to an exit one.

- The maze is stored in a text file, with `#` representing walls and `␣` (_empty space_) representing passages.
- You’ll find examples of such mazes in the [`examples`](./examples) directory. 
    - You can also use the [Maze Generator](https://github.com/ace-lectures/maze-gen) to generate others.
- The Maze is surrounded by walls on its four borders, except for its entry/exit points.
    - Entry and exit points are always located on the East and West border.
    - The maze is not directed. As such, exit and entry can be interchanged.
- At the beginning of the exploration, we're located on the entry tile, facing the opposite side (e.g., if entering by the eastern entry, you're facing West).
- The program generates a sequence of instructions to reach the opposite exit (i.e., a "path"):
    - `F` means 'move forward' according to your current direction
    - `R` means 'turn right' (does not move, just change direction), and `L` means ‘turn left’. 
- A canonical path contains only `F`, `R` and `L` symbols
- A factorized path squashes together similar instructions (i.e., `FFF` = `3F`, `LL` = `2L`).
- Spaces are ignored in the instruction sequence (only for readability: `FFLFF` = `FF L FF`)
- The program takes as input a maze and print the path on the standard output.
    - For this assignment, the path does not have to be the shortest one.
- The program can take a path as input and verify if it's a legit one.

## How to run this software?

To build the program, simply package it with Maven:

```
mosser@azrael A1-Template % mvn -q clean package 
```

### Provided version (starter code)

The starter code assumes the maze file name is the first argument. 

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txt
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txt
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
WALL PASS PASS PASS PASS PASS PASS PASS PASS PASS WALL 
WALL WALL WALL PASS WALL WALL WALL PASS WALL WALL WALL 
WALL PASS PASS PASS PASS PASS WALL PASS PASS PASS WALL 
WALL PASS WALL PASS WALL WALL WALL WALL WALL PASS WALL 
WALL PASS WALL PASS PASS PASS PASS PASS WALL PASS PASS 
WALL WALL WALL PASS WALL PASS WALL WALL WALL WALL WALL 
WALL PASS PASS PASS WALL PASS PASS PASS PASS PASS WALL 
PASS PASS WALL PASS WALL PASS WALL WALL WALL PASS WALL 
WALL PASS WALL PASS WALL PASS WALL PASS PASS PASS WALL 
WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL WALL 
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

When called on a non-existing file. it prints an error message

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar ./examples/small.maz.txtd
** Starting Maze Runner
**** Reading the maze from file ./examples/small.maz.txtd
/!\ An error has occured /!\
**** Computing path
PATH NOT COMPUTED
** End of MazeRunner
```

### Delivered version

#### Command line arguments

The delivered program at the end of this assignment should use the following flags:

- `-i MAZE_FILE`: specifies the filename to be used;
- `-p PATH_SEQUENCE`: activates the path verification mode to validate that PATH_SEQUENCE is correct for the maze
- `-method {bfs, righthand}`: specifies which path computation method to use. (default is Breadth-First Search)
- `-baseline {bfs, righthand}`: specifies which path computation method to benchmark and compare performance to the selected method for maze solving. (default is right hand)

#### Examples

When no logs are activated, the programs only print the computed path on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt
4F
mosser@azrael A1-Template %
```

If a given path is correct, the program prints the message `correct path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 4F
correct path
mosser@azrael A1-Template %
```

If a given path is incorrect, the program prints the message `incorrect path` on the standard output.

```
mosser@azrael A1-Template % java -jar target/mazerunner.jar -i ./examples/straight.maz.txt -p 3F
inccorrect path
mosser@azrael A1-Template %
```

If a method is entered (Either with 'm' or 'method') followed by the name of name of the algorithm used {bfs, righthand}, the program prints the computed path with the given algorithm.

```
drew@Drew:/mnt/c/Andrew/McMaster/SFWRENG 2AA4/a3-maze-runner-take-two-Andrew-Habib$ java -jar target/mazerunner.jar -i ./examples/tiny.maz.txt -method bfs
3F L 4F R 3F
drew@Drew:/mnt/c/Andrew/McMaster/SFWRENG 2AA4/a3-maze-runner-take-two-Andrew-Habib$ java -jar target/mazerunner.jar -i ./examples/tiny.maz.txt -m righthand
5F 2L 2F R 2F R 2F 2L 2F R 2F R 3F
drew@Drew:/mnt/c/Andrew/McMaster/SFWRENG 2AA4/a3-maze-runner-take-two-Andrew-Habib$ 
```

If a baseline option is entered (Either with 'b' or 'baseline') followed by the name of the algorithm used {bfs, righthand}, the program runs in benchmark mode and outputs the computed path along with:
- Maze Import time (ms)
- Time it takes the given method to solve the maze (ms)
- Time it takes the baseline algorithm to solve the maze (ms)
- The speedup or improvement of the path (ms)

```
drew@Drew:/mnt/c/Andrew/McMaster/SFWRENG 2AA4/a3-maze-runner-take-two-Andrew-Habib$ java -jar target/mazerunner.jar -i ./examples/giant.maz.txt -method bfs -baseline righthand    
F L 2F R 2F L 6F R 2F L 6F R 2F R 2F L 2F R 2F L 2F R 8F L 4F R 4F L 6F R 2F L 4F R 2F L 2F R 4F L 4F R 2F L 18F R 4F L 4F R 2F L 2F R 2F L 4F R 4F L 2F R 2F L 2F L 2F R 4F L 2F R 4F L 2F R 10F L 6F R 2F L 2F R 6F L 2F R 2F R 4F L 2F R 2F L 14F R 4F L 4F R 2F L 2F R 8F L 10F R 2F L 4F R 2F L 6F R 2F L 4F R 2F L 6F L 2F R 2F L 4F R 5F
Maze Import Time (ms): 3.00
Method Algorithm Time (ms): 12.00
Baseline Algorithm Time (ms): 42.00
Speedup: 75.88
drew@Drew:/mnt/c/Andrew/McMaster/SFWRENG 2AA4/a3-maze-runner-take-two-Andrew-Habib$
```
