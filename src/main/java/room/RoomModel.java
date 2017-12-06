package room;

import config.StaticSettings;

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

    RoomModel(String heightMap, RoomCamera camera) {
        this.camera = camera;
        this.parseHeightMap(heightMap);
    }

    private void parseHeightMap(String heightMap) {
        String[] temporary = heightMap.split("\\{13}");
        this.mapSizeX = temporary[0].length();
        this.mapSizeY = temporary.length;
        this.roomTiles = new Tile[mapSizeX][mapSizeY];
        this.tileStates = new TileState[mapSizeX][mapSizeY];

        System.out.println(temporary[0].length());
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
                    this.roomTiles[x][y] = new Tile(x, y, getHeight(square));
                }

                x++;
            }
        }
    }

    void render(Graphics graphics) {
        graphics.clearRect(0, 0, StaticSettings.WIDTH, StaticSettings.HEIGHT);
        for (int x = 0; x < roomTiles[0].length; x++) {
            for (int y = 0; y < roomTiles[x].length; y++) {
                if (this.tileStates[x][y] == TileState.OPEN)
                    this.roomTiles[x][y].render(graphics, this.camera);
            }
        }
    }

    public RoomCamera getCamera() {
        return this.camera;
    }

    private double getHeight(char c) {
        switch (String.valueOf(c)) {
            case "0":
                return 0;
            case "1":
                return 1;
            case "2":
                return 2;
            case "3":
                return 3;
            case "4":
                return 4;
            case "5":
                return 5;
            case "6":
                return 6;
            case "7":
                return 7;
            case "8":
                return 8;
            case "9":
                return 9;
            case "a":
                return 10;
            case "b":
                return 11;
            case "c":
                return 12;
            case "d":
                return 13;
            case "e":
                return 14;
            case "f":
                return 15;
            case "g":
                return 16;
            case "h":
                return 17;
            case "i":
                return 18;
            case "j":
                return 19;
            case "k":
                return 20;
            case "l":
                return 21;
            case "m":
                return 22;
            case "n":
                return 23;
            case "o":
                return 24;
            case "p":
                return 25;
            case "q":
                return 26;
            case "r":
                return 27;
            case "s":
                return 28;
            case "t":
                return 29;
            case "u":
                return 30;
            case "v":
                return 31;
            case "w":
                return 32;
            case "x":
                return 33;
            case "y":
                return 34;
            case "z":
                return 35;
            default:
                return 0;
        }
    }

}
