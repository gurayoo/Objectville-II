package main;

import cells.Cell;
import cells.services.Hospital;
import cells.services.PoliceStation;
import cells.services.School;
import cells.services.Service;
import cells.zones.Zone;

public class GameEngine {
    Cell[][] map;

    public GameEngine(Cell[][] map) {
        this.map = map;
    }

    private void provideServices(Service service, int x, int y) {
        int radius = service.getRadius();
        for (int a = x - radius; a <= x + radius; a++) {
            for (int b = y - radius; b <= radius; b++) {
                if (a >= 0 && a < map.length && b >= 0 && b < map[0].length) {
                    double distance = Math.sqrt(Math.pow(a - x, 2) + Math.pow(b - y, 2));
                    if (distance <= radius) {
                        Cell cell = map[a][b];
                        if (cell instanceof Zone) {
                            if (service instanceof PoliceStation) {
                                ((Zone) cell).setHasSecurity(true);
                            }
                            if (service instanceof School) {
                                ((Zone) cell).setHasEducation(true);
                            }
                            if (service instanceof Hospital) {
                                ((Zone) cell).setHasHealth(true);
                            }
                        }
                    }
                }
            }
        }
    }

    private void findServices() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell instanceof Service) {
                    provideServices((Service) cell, x, y);
                }
            }
        }
    }
}
