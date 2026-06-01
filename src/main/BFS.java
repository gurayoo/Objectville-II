package main;

import cells.Cell;
import cells.Road;
import cells.utilityProviders.InternetHub;
import cells.utilityProviders.PowerPlant;
import cells.utilityProviders.UtilityProvider;
import cells.utilityProviders.WaterPumpingStation;
import cells.zones.Industrial;
import cells.zones.Zone;

import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static void distributeUtility(Cell[][] map, int x, int y) {
        Cell cell = map[x][y];
        if (!(cell instanceof UtilityProvider)) {
            return;
        }
        UtilityProvider provider = (UtilityProvider) cell;
        int capacity = provider.getCapacity();
        boolean[][] visitedMap = new boolean[map.length][map[0].length];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visitedMap[x][y] = true;
        int[][] directions = new int[4][2];
        directions[0][0] = -1;
        directions[0][1] = 0;
        directions[1][0] = 0;
        directions[1][1] = -1;
        directions[2][0] = 1;
        directions[2][1] = 0;
        directions[3][0] = 0;
        directions[3][1] = 1;
        while (!queue.isEmpty() && capacity > 0) {
            int[] current = queue.poll();
            int currentX = current[0];
            int currentY = current[1];
            Cell currentCell = map[currentX][currentY];
            if (currentCell instanceof Zone) {
                Zone zone = (Zone) currentCell;
                int received = 0;
                boolean canReceive = true;
                if (provider instanceof PowerPlant) {
                    received = zone.getElectricity();
                } else if (provider instanceof WaterPumpingStation) {
                    received = zone.getWater();
                } else if (provider instanceof InternetHub) {
                    if (zone instanceof Industrial) {
                        canReceive = false;
                    } else {
                        received = zone.getInternet();
                    }
                }
                if (canReceive == true) {
                    int remainingDemand = zone.getUtilityDemand() - received;
                    int need = Math.min(Math.max(0, remainingDemand), capacity);
                    if (need > 0) {
                        if (provider instanceof PowerPlant) {
                            zone.setElectricity(zone.getElectricity() + need);
                            System.out.println(zone.getName() + " at (" + currentX + "," + currentY + ") received " + need + " electricity ");
                        } else if (provider instanceof WaterPumpingStation) {
                            zone.setWater(zone.getWater() + need);
                            System.out.println(zone.getName() + " at (" + currentX + "," + currentY + ") received " + need + " water ");
                        } else if (provider instanceof InternetHub) {
                            zone.setInternet(zone.getInternet() + need);
                            System.out.println(zone.getName() + " at (" + currentX + "," + currentY + ") received " + need + " internet ");
                        }
                        capacity = capacity - need;
                    }
                }
            }
            for (int[] direction : directions) {
                int neighborX = currentX + direction[0];
                int neighborY = currentY + direction[1];
                if (neighborX >= 0 && neighborX < map.length && neighborY >= 0 && neighborY < map[0].length && !visitedMap[neighborX][neighborY]) {
                    Cell neighbor = map[neighborX][neighborY];
                    if (neighbor instanceof Zone || neighbor instanceof Road) {
                       visitedMap[neighborX][neighborY] = true;
                       queue.add(new int[] {neighborX, neighborY});
                    }
                }
            }
        }
    }
}
