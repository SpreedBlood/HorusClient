package room.map;

import config.StaticSettings;
import main.Mouse;
import room.map.utils.HeightParser;

import java.awt.*;

/**
 * Created by SpreedBlood on 2017-12-06.
 */
public class RoomModel {

    private int mapSizeX;
    private int mapSizeY;
    private Tile[][] roomTiles;
    private TileState[][] tileStates;
    private RoomCamera camera;
    private Tile hoveringTile;

    private int doorX;
    private int doorY;

    public RoomModel(String heightMap, RoomCamera camera) {
        this.camera = camera;
        this.doorX = 1;
        this.doorY = 11;
        this.parseHeightMap(heightMap);
    }

    public void render(Graphics graphics) {
        graphics.clearRect(0, 0, StaticSettings.WIDTH, StaticSettings.HEIGHT);
        for (int x = 0; x < this.mapSizeX; x++) {
            for (int y = 0; y < this.mapSizeY; y++) {
                if (this.tileStates[x][y] == TileState.OPEN)
                    this.roomTiles[x][y].render(graphics, this.camera);
            }
        }
    }

    public int getDoorX() {
        return doorX;
    }

    public int getDoorY() {
        return doorY;
    }

    public RoomCamera getCamera() {
        return this.camera;
    }

    public Tile getTile(int x, int y) {
        try {
            return this.roomTiles[x][y];
        } catch (ArrayIndexOutOfBoundsException ignored) {
            return null;
        }
    }

    public void setHovering(int x, int y) {
        try {
            //If hovering over something, stop.
            if (this.hoveringTile != null)
                this.hoveringTile.setHovering(false);

            //Check if new hovering tile exist, hover it, and swap the old one with new one.
            Tile newHoveringTile = this.roomTiles[x][y];
            if (newHoveringTile != null) {
                newHoveringTile.setHovering(true);
                this.hoveringTile = newHoveringTile;
            }
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }
    }

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
    }

}
