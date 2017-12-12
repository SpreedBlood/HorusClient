package room.map;

public enum WallType {
    LEFT("wall_left.png"),
    RIGHT("wall_right.png"),
    /*DOOR_LEFT("door_left.png"),
    DOOR_RIGHT("door_right.png"),*/
    NONE("");

    private String fileName;

    WallType(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
