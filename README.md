# Classic-Minesweeper-Game-in-Java

Non-graphical single player Minesweeper (with 9x9 size) game in Java high level programming language by not using the OOP principles. The key idea in solving this task was to create two planes by using three-dimensional array. The first one is plane which user can see, and the second one is plane with mines and with numbers located, which the player cannot see. As there were used matrices the whole code executed linearly. 

These are used main methods:

Creatematrix(): here we create two planes and locate ‘*’ on the first matrix and ‘0’ on the second matrix. After randomly locate 10 mines on the second plane by replacing ‘0’ by the symbol of bomb;

Show_matrix(): by using it we can show the first matrix in the console;

Splashinmatrix(): here if chosen element is ‘0’, then it will open neighbor elements until there appear mine or certain number;

Play(): it is the main method that combines all functions which is used to define the rules of the game;

Generate_numbers(): generate the numbers by accessing the put_numbers() method;

Put_numbers(): this method puts appropriate numbers around mines on the second matrix;
