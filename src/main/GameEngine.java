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
    Resources resources = new Resources();

    public GameEngine(Cell[][] map) {
        this.map = map;
    }

    private void provideServices(Service service, int x, int y) {
        int radius = service.getRadius();
        for (int a = x - radius; a <= x + radius; a++) {
            for (int b = y - radius; b <= y + radius; b++) {
                if (a >= 0 && a < map.length && b >= 0 && b < map[0].length) {
                    double distance = Math.sqrt(Math.pow(a - x, 2) + Math.pow(b - y, 2));
                    if (distance <= radius) {
                        Cell cell = map[a][b];
                        if (cell instanceof Zone) {
                            if (service instanceof PoliceStation) {
                                ((Zone) cell).setHasSecurity(true);
                                System.out.println(((Zone) cell).getName() + " at (" + a + "," + b + ") received security service");
                            }
                            if (service instanceof School && (Zone) cell instanceof Housing) {
                                ((Zone) cell).setHasEducation(true);
                                System.out.println(((Zone) cell).getName() + " at (" + a + "," + b + ") received education service");
                            }
                            if (service instanceof Hospital && (Zone) cell instanceof Housing) {
                                ((Zone) cell).setHasHealth(true);
                                System.out.println(((Zone) cell).getName() + " at (" + a + "," + b + ") received health service");
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
        resources.reset();
        int housingCount = 0;
        int industrialCount = 0;
        int commercialCount = 0;
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell instanceof Housing) {
                    Housing housing = (Housing) cell;
                    resources.setPopulation(resources.getPopulation() + housing.getCurrentOutput());
                    housingCount++;
                } else if (cell instanceof Industrial) {
                    Industrial industrial = (Industrial) cell;
                    resources.setGoods(resources.getGoods() + industrial.getCurrentOutput());
                    industrialCount++;
                } else if (cell instanceof Commercial) {
                    Commercial commercial = (Commercial) cell;
                    resources.setLifestyle(resources.getLifestyle() + commercial.getCurrentOutput());
                    commercialCount++;
                }
            }
        }
        int populationCount = industrialCount + commercialCount;
        int populationPerZone = 0;
        int goodsPerZone = 0;
        int lifestylePerZone = 0;
        if (populationCount > 0) {
            populationPerZone = resources.getPopulation() / populationCount;
        }
        if (commercialCount > 0) {
            goodsPerZone = resources.getGoods() / commercialCount;
        }
        if (housingCount > 0) {
            lifestylePerZone = resources.getLifestyle() / housingCount;
        }
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell instanceof Industrial) {
                    Industrial industrial = (Industrial) cell;
                    industrial.setCurrentPopulation(populationPerZone);
                    if (populationPerZone > 0) {
                        System.out.println("Industrial at (" + x + "," + y + ") received " + populationPerZone + " population");
                    }
                } else if (cell instanceof Commercial) {
                    Commercial commercial = (Commercial) cell;
                    commercial.setCurrentPopulation(populationPerZone);
                    if (populationPerZone > 0) {
                        System.out.println("Commercial at (" + x + "," + y + ") received " + populationPerZone + " population");
                    }
                    commercial.setCurrentGoods(goodsPerZone);
                    if (goodsPerZone > 0) {
                        System.out.println("Commercial at (" + x + "," + y + ") received " + goodsPerZone + " goods");
                    }
                } else if (cell instanceof Housing) {
                    Housing housing = (Housing) cell;
                    housing.setCurrentLifestyle(lifestylePerZone);
                    if (lifestylePerZone > 0) {
                        System.out.println("House at (" + x + "," + y + ") received " + lifestylePerZone + " lifestyle");
                    }
                }
            }
        }
    }

    private void updateZones() {
        for (int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[0].length; y++) {
                Cell cell = map[x][y];
                if (cell instanceof Zone) {
                    Zone zone = (Zone) cell;
                    int oldLevel = zone.getLevel();
                    zone.tick();
                    int newLevel = zone.getLevel();
                    if (zone instanceof Housing) {
                        System.out.println("House at (" + x + "," + y + ") generated " + zone.getCurrentOutput() + " population");
                    } else if (zone instanceof Industrial) {
                        System.out.println("Industrial at (" + x + "," + y + ") generated " + zone.getCurrentOutput() + " goods");
                    } else if (zone instanceof Commercial) {
                        System.out.println("Commercial at (" + x + "," + y + ") generated " + zone.getCurrentOutput() + " lifestyle");
                    }
                    if (newLevel > oldLevel) {
                        System.out.println(zone.getName() + " at (" + x + "," + y + ") levels up from " + oldLevel + " to " + newLevel);
                    } else if (newLevel < oldLevel) {
                        System.out.println(zone.getName() + " at (" + x + "," + y + ") levels down from " + oldLevel + " to " + newLevel);
                    }
                }
            }
        }
    }
}