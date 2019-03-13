public class Game {

    Difficulty.Level level;
    int num_cells;
    int num_bombs;

    public Game(Difficulty.Level level, int num_cells, int num_bombs){
        this.level = level;
        this.num_cells = num_cells;
        this.num_bombs = num_bombs;
        new Board(level);
    }
}
