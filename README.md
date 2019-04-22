# minesweeper

## Development of Minesweeper Game in 4 iterations

>Developed by: _**Ana Herrero** and **Alba Díaz**_  
>Programming Language: **Java**  
>IDE: _IntelliJ Idea_

In this project, the following classes have been created: Main, Difficulty, Game, Board, Cell, EmptyCell, BombCell; and the interface ClickObserver

**NOTE!** A "resources" folder was created inside the src folder of the project to hold the icons for bombs and flags that will be used in the code.

* **Main:**
In charge of creating a new Difficulty class instance.

* **Difficulty:**
In charge of showing the first GUI of the game, in which the player will choose a specific level, by clicking on the desired level button.

* **Game:**
In charge of creating a new Board class instance. It also has a method in charge to show a popup to notify the user whether he/she has won or lost the game

* **Board:**
In charge of the second GUI of the game, that displays all different cells. It also has the method to place bombs. This class implements the ClickObserver interface, and calls its methos cellClicked whenever a cell is clicked through an Action Listener.

* **Cell:**
In charge of creating all cell objects and storing them in a HashMap whose KeyValues are Point objects that refer to the cell position coordinates in the board. It also has the setter and getter methods for the cell object attributes. It implements an Action Listener that whenever an EmptyCell is clicked, it calls the methods needed to count if there are any bombs on surrounding cells, taking into account boundaries' constraints. It also implements a Mouse Listener to be able to place flags wherever the user thinks there is a bomb. **WARNING:** Once a flag is placed, it cannot be undone, meaning the flag cannot be taken away, and it will not be possible to click on that cell.

* **EmptyCell** and **BombCell:**
Both are child classes of Cell class. They are in charge of setting the specifics in their attributes that differentiate one another.
