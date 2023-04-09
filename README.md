# Conway's Game of Life

This is a JavaFX implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

## Overview

The game consists of a two-dimensional grid of cells, each cell being either alive or dead. The cells evolve according to a set of rules. At each time step, the following transitions occur for each cell:

* Any live cell with fewer than two live neighbors dies, as if by underpopulation.
* Any live cell with two or three live neighbors lives on to the next generation.
* Any live cell with more than three live neighbors dies, as if by overpopulation.
* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The game is implemented using JavaFX, which provides a graphical user interface to display the grid of cells and allow the user to interact with the game.

## Requirements

To run the game, you must install JavaFX on your system. JavaFX is included in recent versions of the Java Development Kit (JDK), so you can download and install the JDK to get started. The game has been tested on JDK version 11.

## Usage

To run the game, you can run the `GameOfLife` class from the command line:

```console
java GameOfLife
```

This will launch the game and display the graphical user interface. You can then use the mouse to toggle the state of individual cells and use the toolbar to start, pause, and reset the game.

You can also load and save game states using the "Load" and "Save" buttons in the toolbar. The game state is saved as a comma-separated value (CSV) format text file.

## Testing

The game includes a set of JUnit tests to ensure the implementation is correct. You can run the tests using any JUnit test runner, such as the one in your IDE or the `junit` command-line tool.

## Authors

Leif Eggenfellner created this implementation of Conway's Game of Life.
