package room.map;

import config.StaticSettings;
import room.map.utils.HeightParser;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class RoomModel {

    private int mapSizeX;
    private int mapSizeY;

    private int doorX;
    private int doorY;

    private Tile[][] roomTiles;
    private TileState[][] tileStates;
    private RoomCamera camera;
    private Tile hoveringTile;

    public RoomModel(String heightMap, RoomCamera camera) {
        this.camera = camera;
        this.doorX = 1;
        this.doorY = 11;
        this.parseHeightMap(heightMap);
    }

    /**
     * Renders the room grid.
     *
     * @param graphics to draw the grid on
     */
    public void render(Graphics graphics) {
        graphics.clearRect(0, 0, StaticSettings.WIDTH, StaticSettings.HEIGHT);

        List<Tile> tiles = new ArrayList<>();
        Tile tile = this.roomTiles[this.doorX][this.doorY];

        // Prioritise door floor before the rest of the floor
        if (tile != null) {
            tile.renderFloor(graphics, this.camera);
        }

        for (int x = 0; x < this.mapSizeX; x++) {
            for (int y = 0; y < this.mapSizeY; y++) {

                if (x == this.doorX && y == this.doorY) {
                    continue;
                }

                if (this.tileStates[x][y] == TileState.OPEN) {
                    this.roomTiles[x][y].renderWall(graphics, this.camera);
                    tiles.add(this.roomTiles[x][y]);
                }
            }
        }

        // Prioritise door wall before the rest of the wall
        if (tile != null) {
            tile.renderWall(graphics, this.camera);
        }

        // Render the rest of the tiles
        for (Tile roomTile : tiles) {
            roomTile.renderFloor(graphics, this.camera);
        }
    }

    /**
     * Get the door X coordinate of the map
     *
     * @return the x coordinate of the door
     */
    public int getDoorX() {
        return doorX;
    }

    /**
     * Get the door Y coordinate of the map
     *
     * @return the y coordinate of the door
     */
    public int getDoorY() {
        return doorY;
    }

    /**
     * Get the camera of the room
     *
     * @return the camera
     */
    public RoomCamera getCamera() {
        return this.camera;
    }

    /**
     * Get tile at coordinates
     *
     * @param x coordinate
     * @param y coordinate
     * @return the tile on the grid if exists.
     */
    public Tile getTile(int x, int y) {
        try {
            return this.roomTiles[x][y];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
    }

    /**
     * Apply the hovering image over the tile.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void setHovering(int x, int y) {
        try {
            //If hovering over something, stop.
            if (this.hoveringTile != null) {
                this.hoveringTile.setHovering(false);
            }

            //Check if new hovering tile exist, hover it, and swap the old one with new one.
            Tile newHoveringTile = this.roomTiles[x][y];
            if (newHoveringTile != null) {
                newHoveringTile.setHovering(true);
                this.hoveringTile = newHoveringTile;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

    /**
     * Parses the heightmap and fills the array
     * @param heightMap the heightmap in string format
     */
    private void parseHeightMap(String heightMap) {
        String[] temporary = heightMap.split("\\{13}");
        this.mapSizeX = temporary[0].length();
        this.mapSizeY = temporary.length;
        this.roomTiles = new Tile[mapSizeX][mapSizeY];
        this.tileStates = new TileState[mapSizeX][mapSizeY];

        for (int y = 0; y < this.mapSizeY; y++) {
            String line = temporary[y];

            line = line.replace(Character.toString((char) 10), "");
            line = line.replace(Character.toString((char) 13), "");

            int x = 0;

            for (char square : line.toCharArray()) {
                if (square == 'x') {
                    this.tileStates[x][y] = TileState.CLOSED;
                } else {
                    this.tileStates[x][y] = TileState.OPEN;
                    this.roomTiles[x][y] = new Tile(x, y, HeightParser.getHeight(square));
                }

                x++;
            }
        }

        this.locateDoor();
        this.findWallTiles();

        System.out.println("Found door with coordinates: " + this.doorX + ", " + this.doorY);

    }

    /**
     * Finds and marks the wall tiles located on this model, it will left to right
     * and down, find the first open tile, and then mark it as a wall tile, break the loop
     * and then increment the coordinate to check the next tile.
     */
    private void findWallTiles() {
        for (int x = 0; x < this.mapSizeX; x++) {
            for (int y = 0; y < this.mapSizeY; y++) {
                TileState state = this.tileStates[x][y];

                if (state == null) {
                    return;
                }

                if (state == TileState.OPEN) {
                    this.roomTiles[x][y].setWallType(WallType.RIGHT);
                    break;
                }
            }
        }

        for (int y = 0; y < this.mapSizeY; y++) {
            for (int x = 0; x < this.mapSizeX; x++) {

                TileState state = this.tileStates[x][y];

                if (state == null) {
                    return;
                }

                if (state == TileState.OPEN) {
                    this.roomTiles[x][y].setWallType(WallType.LEFT);
                    break;
                }
            }
        }
    }

    /**
     * Locate the door by finding the first open tile
     * while iterating vertically on the tile states.
     */
    private void locateDoor() {
        try {
            for (int x = 0; x < this.mapSizeX; x++) {
                for (int y = 0; y < this.mapSizeY; y++) {
                    TileState[] states = this.tileStates[x];

                    if (states.length > 0) {
                        TileState state = this.tileStates[x][y];

                        if (state == TileState.OPEN) {
                            this.doorX = x;
                            this.doorY = y;
                            this.roomTiles[x][y].setDoor(true);
                            return;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
