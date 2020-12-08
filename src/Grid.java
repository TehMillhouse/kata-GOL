public class Grid {
    public int height;
    public int width;
    private boolean[][] grid;

    public Grid(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new boolean[width][height];
    }

    private boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < this.width && y >= 0 && y < this.height;
    }

    private void assertWithinBounds(int x, int y) {
        if (!isWithinBounds(x, y)) {
            throw new IndexOutOfBoundsException("The coordinate (" + x + ", " + y + ") is out of bounds");
        }
    }

    public void setCell(int x, int y, boolean isAlive) {
        assertWithinBounds(x, y);
        this.grid[x][y] = isAlive;
    }

    public boolean getCell(int x, int y) {
        assertWithinBounds(x, y);
        return this.grid[x][y];
    }
    
    public int numberOfAliveNeighboursAt(int x, int y) {
        int liveNeighbors = 0;
        for (int neighborY = y-1; neighborY <= y+1; neighborY++) {
            for (int neighborX = x-1; neighborX <= x+1; neighborX++) {
                if (!isWithinBounds(neighborX, neighborY)) {
                    continue;
                }
                if (neighborX == x && neighborY == y) {
                    continue;
                }
                if (getCell(neighborX, neighborY)) {
                    liveNeighbors++;
                }
            }
        }
        return liveNeighbors;
    }

    public String render() {
        StringBuilder outputBuffer = new StringBuilder(height * width + height /* one "column" for newlines */);
        for (int y = 0; y < height; y++) {
            outputBuffer.append('|'); // leftmost border
            for (int x = 0; x < width; x++) {
                outputBuffer.append(getCell(x, y) ? "#|" : "_|");
            }
            outputBuffer.append('\n');
        }
        return outputBuffer.toString();
    }
}