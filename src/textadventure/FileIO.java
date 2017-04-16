package textadventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

/**
 * Text-based Adventure Game
 *
 * A project for CMSC 495 7982
 * Trends and Projects in Computer Science
 * University of Maryland University College
 *
 * Jeff Schouw
 * Mansukh Saini
 * Lionel Rockymore
 *
 * FileIO.java
 * A class that groups all functionality for file input/output.
 */

class FileIO {

    // *******************
    // ***** Methods *****
    // *******************

    static MapArea loadMapFromFile() {
        MapArea newMapArea = new MapArea();
        File file = new File("src/testmap.txt");
        try {
            Scanner scanner = new Scanner(file);
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                String[] tokensInLine = line.split("\\$"); // $ (dollar) symbol used to split parameters for room
                if (Objects.equals(tokensInLine[0].toLowerCase(), "room")) {
                    MapRoom newRoom = new MapRoom(tokensInLine[1], tokensInLine[2]);
                    newMapArea.addRoomToArea(newRoom);
                } else if (Objects.equals(tokensInLine[0].toLowerCase(), "connection")) {
                    MapRoom firstRoom = newMapArea.getRoomsInArea().get(Integer.parseInt(tokensInLine[1]));
                    MapRoom secondRoom = newMapArea.getRoomsInArea().get(Integer.parseInt(tokensInLine[2]));
                    MapArea.roomConnectionDirection connectionDirection = MapArea.getDirectionFromString(tokensInLine[3]);
                    newMapArea.connectRooms(firstRoom, secondRoom, connectionDirection);
                } else if (Objects.equals(tokensInLine[0].toLowerCase(), "item")) {
                    MapRoom room = newMapArea.getRoomsInArea().get(Integer.parseInt(tokensInLine[1]));
                    String itemName = tokensInLine[2];
                    String itemDescription = tokensInLine[3];
                    String itemIdentifier = tokensInLine[4];
                    Item item = new Item(itemName, itemDescription, itemIdentifier);
                    room.getItemsInRoom().add(item);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return newMapArea;
    }
}
