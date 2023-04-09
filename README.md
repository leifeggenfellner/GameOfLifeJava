# Conway's Game of Life

This is a JavaFX implementation of [Conway's Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).

## Overview

The game consists of a two-dimensional grid of cells, where each cell can be either alive or dead. The cells evolve over time according to a set of rules. At each time step, the following transitions occur for each cell:

* Any live cell with fewer than two live neighbors dies, as if by underpopulation.
* Any live cell with two or three live neighbors lives on to the next generation.
* Any live cell with more than three live neighbors dies, as if by overpopulation.
* Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

The game is implemented using JavaFX, which provides a graphical user interface to display the grid of cells and allow the user to interact with the game.

## Requirements

To run the game, you need to have JavaFX installed on your system. JavaFX is included in recent versions of the Java Development Kit (JDK), so you can simply download and install the JDK to get started. The game has been tested on JDK version 11.

## Usage

To run the game, you can simply run the `GameOfLife` class from the command line:

```java
java GameOfLife

```

This will launch the game and display the graphical user interface. You can then use the mouse to toggle the state of individual cells and use the toolbar to start, pause, and reset the game.

You can also load and save game states using the "Load" and "Save" buttons in the toolbar. The game state is saved as a text file in comma-separated value (CSV) format.

## Testing

The game includes a set of JUnit tests to ensure that the implementation is correct. You can run the tests using any JUnit test runner, such as the one included in your IDE or the `junit` command-line tool.

## Authors

This implementation of Conway's Game of Life was created by [Your Name Here].
