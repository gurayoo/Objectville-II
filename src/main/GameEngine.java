package main;

import cells.Cell;
import cells.services.Hospital;
import cells.services.PoliceStation;
import cells.services.School;
import cells.services.Service;
import cells.zones.Commercial;
import cells.zones.Housing;
import cells.zones.Industrial;
import cells.zones.Zone;

public class GameEngine {
    Cell[][] map;
    Resources resources=new Resources();

    public GameEngine(Cell[][] map) {
        this.map = map;
    }

    private void provideServices(Service service, int x, int y) {
        int radius = service.getRadius();
        for (int a = x - radius; a <= x + radius; a++) {
            for (int b = y - radius; b <= y+ radius; b++) {
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

    private void reset() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell instanceof Zone) {
                    ((Zone) cell).reset();
                }
            }
        }
    }

    private void distributeResources() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];


                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int supply = zone.getCurrentOutput();

                    if (zone instanceof Housing) {
                        resources.setPopulation(resources.getPopulation() + supply);
                    }
                    else if (zone instanceof Industrial) {
                        resources.setGoods(resources.getGoods() + supply);
                    }
                    else if (zone instanceof Commercial) {
                        resources.setLifestyle(resources.getLifestyle() + supply);
                    }
                }
            }
        }

        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];

                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int need = zone.getUtilityDemand();

                    if (zone instanceof Industrial) {
                        Industrial industrial = (Industrial) zone;

                        if (resources.getPopulation() >= need) {
                            resources.setPopulation(resources.getPopulation() - need);
                            industrial.setCurrentPopulation(industrial.getCurrentPopulation() + need);
                        }
                    }
                    else if (zone instanceof Commercial) {
                        Commercial commercial = (Commercial) zone;

                        if (resources.getPopulation() >= need && resources.getGoods() >= need) {
                            resources.setPopulation(resources.getPopulation() - need);
                            resources.setGoods(resources.getGoods() - need);

                            commercial.setCurrentPopulation(commercial.getCurrentPopulation() + need);
                            commercial.setCurrentGoods(commercial.getCurrentGoods() + need);
                        }
                    }
                    else if (zone instanceof Housing) {
                        Housing housing = (Housing) zone;
                        if (resources.getLifestyle() >= need) {
                            resources.setLifestyle(resources.getLifestyle() - need);
                            housing.setCurrentLifestyle(housing.getCurrentLifestyle() + need);
                        }
                    }
                }
            }
        }
    }
    private void updateZones() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell != null) {
                    cell.tick();
                }
            }
        }
    }
}
