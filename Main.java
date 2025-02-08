import java.util.Scanner;

public class Main {
    static final int SIZE = 4; // Size of the grid (4x4)
    static char[][] grid = new char[SIZE][SIZE]; // The grid
    static boolean[][] visited = new boolean[SIZE][SIZE]; // Visited cells tracker
    static int agentX = 0, agentY = 0; // Starting position of the agent
    static boolean hasGold = false;
    static boolean wumpusAlive = true;
    static boolean gameOver = false;

    public static void main(String[] args) {
        // Initialize the grid with some random hazards
        initializeGrid();

        // Start the game loop
        while (!gameOver) {
            printGrid();
            takeTurn();
        }

        if (hasGold) {
            System.out.println("You have the gold! Now, make your way out.");
        } else {
            System.out.println("Game Over!");
        }
    }

    // Initialize the grid with Wumpus, pits, and gold
    static void initializeGrid() {
        // Clear the grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '.';
            }
        }

        // Add Wumpus, pits, and gold (randomly for simplicity)
        grid[1][1] = 'W'; // Wumpus
        grid[2][2] = 'P'; // Pit
        grid[3][3] = 'G'; // Gold
    }

    // Print the grid (show only visible part based on agent's position)
    static void printGrid() {
        System.out.println("Current Grid:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (i == agentX && j == agentY) {
                    System.out.print("A "); // A represents the agent
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    // Handle the agent's turn
    static void takeTurn() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose an action: ");
        System.out.println("1. Move (W/A/S/D)");
        System.out.println("2. Shoot (Arrow keys to shoot)");
        System.out.println("3. Grab Gold (G)");

        String action = scanner.nextLine().toUpperCase();

        switch (action) {
            case "W":
            case "A":
            case "S":
            case "D":
                move(action);
                break;
            case "G":
                grabGold();
                break;
            default:
                System.out.println("Invalid action.");
                break;
        }
    }

    // Move the agent in the specified direction
    static void move(String direction) {
        int newX = agentX, newY = agentY;

        switch (direction) {
            case "W": newX--; break; // Up
            case "A": newY--; break; // Left
            case "S": newX++; break; // Down
            case "D": newY++; break; // Right
        }

        if (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE) {
            agentX = newX;
            agentY = newY;
        }

        checkCurrentSquare();
    }

    // Check the agent's current square for hazards
    static void checkCurrentSquare() {
        char currentSquare = grid[agentX][agentY];

        if (currentSquare == 'P') {
            System.out.println("You fell into a pit! Game Over.");
            gameOver = true;
        } else if (currentSquare == 'W') {
            System.out.println("You encountered the Wumpus! Game Over.");
            gameOver = true;
        } else if (currentSquare == 'G') {
            hasGold = true;
            System.out.println("You found the gold!");
        } else {
            System.out.println("No hazards here.");
        }
    }

    // Grab the gold if the agent is on the gold's square
    static void grabGold() {
        if (grid[agentX][agentY] == 'G') {
            hasGold = true;
            System.out.println("You grabbed the gold!");
        } else {
            System.out.println("No gold here.");
        }
    }
}
