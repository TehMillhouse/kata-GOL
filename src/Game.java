import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException, InterruptedException {
        int width = 11;
        int height = 9;
        Grid currentGrid = new Grid(width, height);

        makeGliderAt(currentGrid, 1, 1);
        animateGame(currentGrid, 20);
    }

    private static void animateGame(Grid currentGrid, int steps) throws IOException, InterruptedException {
        Grid nextGrid = new Grid(currentGrid.width, currentGrid.height);
        System.out.println(currentGrid.render());
        for (int i = 0; i < steps; i++) {
            computeStep(currentGrid, nextGrid);
            Grid tmp = currentGrid;
            currentGrid = nextGrid;
            nextGrid = tmp;

            Runtime.getRuntime().exec("clear");
            Thread.sleep(300);
            System.out.println(currentGrid.render());
        }
    }

    private static void makeGliderAt(Grid grid, int x, int y) {
        grid.setCell(x+1, y+0, true);
        grid.setCell(x+2, y+1, true);
        grid.setCell(x+0, y+2, true);
        grid.setCell(x+1, y+2, true);
        grid.setCell(x+2, y+2, true);
    }

    public static void computeStep(Grid current, Grid next) {
        for (int y = 0; y < current.height; y++) {
            for (int x = 0; x < current.width; x++) {
                next.setCell(x, y, shouldBecomeAlive(current, x, y));
            }
        }
    }

    public static boolean shouldBecomeAlive(Grid grid, int x, int y) {
        int numberOfAliveNeighbours = grid.numberOfAliveNeighboursAt(x, y);
        if (numberOfAliveNeighbours == 2)
            return grid.getCell(x, y);
        if (numberOfAliveNeighbours == 3)
            return true;
        return false;
    }

}