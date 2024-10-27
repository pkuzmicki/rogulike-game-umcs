public class Tile {
    final int tileWidth = 300;
    final int tileHeight = 100;
    boolean collision = false;
    int row;
    int col;
    public Tile(int row, int col){
        this.row = row;
        this.col = col;
    }

    int[] xPoints = {row, row - tileWidth/2, row, row + tileWidth/2};
    int[] YPoints = {col - tileHeight/2, col, col + tileHeight/2, col};

}
