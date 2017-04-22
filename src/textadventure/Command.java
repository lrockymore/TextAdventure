package textadventure;

import java.util.Objects;
import textadventure.MapArea.roomConnectionDirection;

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
 * Command.java
 * A class that handles command execution.
 */

class Command {

    private static Player player;

    static void setPlayer(Player player) {
        Command.player = player;
    }

    static void north() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_NORTH) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_NORTH)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_NORTH));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void south() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_SOUTH) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_SOUTH)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_SOUTH));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void east() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_EAST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_EAST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_EAST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void west() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_WEST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_WEST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_WEST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void northwest() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_NORTHWEST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_NORTHWEST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_NORTHWEST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void southeast() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_SOUTHEAST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_SOUTHEAST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_SOUTHEAST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void northeast() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_NORTHEAST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_NORTHEAST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_NORTHEAST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void southwest() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_SOUTHWEST) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_SOUTHWEST)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_SOUTHWEST));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void up() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_UP) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_UP)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_UP));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    static void down() {
        if (player.getCurrentMapRoom().getConnectedMapRooms().get(MapArea.roomConnectionDirection.DIRECTION_DOWN) != null) {
            if (!player.getCurrentMapRoom().isLocked(roomConnectionDirection.DIRECTION_DOWN)) {
                player.movePlayerToMapRoom(player.getCurrentMapRoom().getConnectedMapRooms().get(roomConnectionDirection.DIRECTION_DOWN));
            } else thatDirectionIsLocked();
        } else youCannotGoThatWay();
    }

    private static void youCannotGoThatWay() {
        System.out.println("You cannot go in that direction.");
    }

    private static void thatDirectionIsLocked() {
        System.out.println("You cannot go in that direction, the way is locked.");
    }

    static void quit() {
        System.out.println("Quitting game...");
        System.exit(0);
    }

    static void invalidCommand() {
        System.out.println("Command not recognized, enter 'help' to see a list of commands.");
    }

    static void help() {
        System.out.println("Commands:");
        System.out.println("Directional commands: n/north, s/south, e/east, w/west");
        System.out.println("ne/northeast, nw/northwest, se/southeast, sw/southwest");
        System.out.println("up, down");
        System.out.println("Other commands: inventory, take, drop, equip, turn, pull");
        System.out.println("                q/quit");
    }

    static void inventory() {
        System.out.println(" --- Inventory: ---");
        for (Item item : player.getInventory()) {
            System.out.println(item.getName());
        }
    }

    static void take(String inputString) {
        boolean itemFound = false;
        for(Item item : player.getCurrentMapRoom().getItemsInRoom()) {
            if (Objects.equals(item.getIdentifier().toLowerCase(), inputString.toLowerCase())) {
                player.addItemToInventory(item);
                player.getCurrentMapRoom().getItemsInRoom().remove(item);
                itemFound = true;

                System.out.println(item.getIdentifier() + " added to inventory."); // delete this notification later
                break;
            }
        }
        if (!itemFound) {
            System.out.println("No item called " + inputString + " found in current room.");
            // delete this notification later, find a better way to notify player
        }
    }

    static void drop(String inputString) {
        boolean itemFound = false;
        for (Item item : player.getInventory()) {
            if (Objects.equals(inputString.toLowerCase(), item.getIdentifier().toLowerCase())) {
                player.getCurrentMapRoom().getItemsInRoom().add(item);
                player.getInventory().remove(item);
                itemFound = true;
                System.out.println(item.getIdentifier() + " removed from inventory and added to current room.");
                break;
            }
        }
        if (!itemFound) {
            System.out.println("No item called " + inputString + " found in player's inventory.");
        }
    }

    static void turn(String inputString) {
        if (Objects.equals(inputString.toLowerCase(), "wheel")) {
            for (roomConnectionDirection direction : roomConnectionDirection.values()) {
                if (player.getCurrentMapRoom().getDirectionLock(direction) != null) {
                    if (player.getCurrentMapRoom().isLocked(direction) && player.getCurrentMapRoom().getDirectionLock(direction).getClass() == Wheel.class) {
                        Wheel wheel = (Wheel) player.getCurrentMapRoom().getDirectionLock(direction);
                        if (wheel.checkIsLocked()) {
                            wheel.turn();
                            player.getCurrentMapRoom().unlock(direction);
                            System.out.println("You turn the wheel until it stops.");
                        } else {
                            System.out.println("The wheel has already been turned all the way.");
                        }
                    } else {
                        System.out.println("There is no wheel to turn in this room.");
                    }
                }
            }
        }
    }

    static void pull(String inputString) {
        if (Objects.equals(inputString.toLowerCase(), "lever")) {
            for (roomConnectionDirection direction : roomConnectionDirection.values()) {
                if (player.getCurrentMapRoom().getDirectionLock(direction) != null) {
                    if (player.getCurrentMapRoom().isLocked(direction) && player.getCurrentMapRoom().getDirectionLock(direction).getClass() == Lever.class) {
                        Lever lever = (Lever) player.getCurrentMapRoom().getDirectionLock(direction);
                        if (lever.checkIsLocked()) {
                            lever.pull();
                            player.getCurrentMapRoom().unlock(direction);
                            System.out.println("You pull the lever.");
                        } else {
                            System.out.println("The lever has already been pulled, and it is stuck.");
                        }
                    } else {
                        System.out.println("There is no lever in this room to pull.");
                    }
                }
            }
        }
    }
}
