package io;

import cells.Cell;
import cells.EmptyCell;
import cells.Road;
import cells.services.Hospital;
import cells.services.PoliceStation;
import cells.services.School;
import cells.utilityProviders.InternetHub;
import cells.utilityProviders.PowerPlant;
import cells.utilityProviders.WaterPumpingStation;
import cells.zones.Commercial;
import cells.zones.Housing;
import cells.zones.Industrial;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Reader {
    public static Cell[][] loadMap(String filePath) {
        try {
            List<String> list =Files.readAllLines(Paths.get(filePath));
            if (list.isEmpty()) {
                throw new IOException("ERROR!!!EMPTY LIST!!!");
            }
            int row = list.size();
            int column = list.get(0).length();
            Cell[][] map = new Cell[row][column];

            for (int a = 0; a < row; a++) {
                String line = list.get(a);
                for (int b = 0; b < column; b++) {
                    char c = line.charAt(b);
                    if (c == 'H') {
                        map[a][b] = new Housing();
                    } else if (c == 'I') {
                        map[a][b] = new Industrial();
                    } else if (c == 'C') {
                        map[a][b] = new Commercial();
                    } else if (c == 'P') {
                        map[a][b] = new PowerPlant();
                    } else if (c == 'W') {
                        map[a][b] = new WaterPumpingStation();
                    } else if (c == 'T') {
                        map[a][b] = new InternetHub();
                    } else if (c == 'F') {
                        map[a][b] = new PoliceStation();
                    } else if (c == 'D') {
                        map[a][b] = new Hospital();
                    } else if (c == 'S') {
                        map[a][b] = new School();
                    } else if (c == 'R') {
                        map[a][b] = new Road();
                    } else if (c == 'E') {
                        map[a][b] = new EmptyCell();
                    } else {
                        throw new IllegalArgumentException("THERE İS WRONG TYPE OF CHARACTER IN THE MAP!!!");
                    }

                }
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException("ERROR!!!" + e.getMessage());
        }
    }
}