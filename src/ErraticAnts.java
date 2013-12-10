import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int number_of_cases = sc.nextInt();

        int current_case = 1;

        while (current_case < number_of_cases + 1) {

            int path_length = sc.nextInt();

            sc.nextLine();

            Map<Coordinate, CostLinks> map = new HashMap<Coordinate, CostLinks>();

            Coordinate zeroCoordinate = new Coordinate(0, 0);

            map.put(zeroCoordinate, new CostLinks().cost(0));

            int x = 0;
            int y = 0;

            int path_num = 1;

            while (path_num < path_length + 1) {

                char dir = sc.nextLine().charAt(0);

                Coordinate tempCoordinate = new Coordinate(x, y);

                map.get(tempCoordinate).link(dir);

                if (dir == 'N') {
                    x++;
                }

                else if (dir == 'S') {
                    x--;
                }

                else if (dir == 'W') {
                    y++;
                }

                else if (dir == 'E') {
                    y--;
                }

                if (! map.containsKey(new Coordinate(x, y))) {
                    map.put(new Coordinate(x, y), new CostLinks());
                }

                map.get(new Coordinate(x, y)).linkReverse(dir).cost(cost(map, x, y));

                path_num++;
                
            }

            System.out.println(map.get(new Coordinate(x, y)).cost);

            current_case++;

        }

        sc.close();

    }

    private static int cost(Map<Coordinate, CostLinks> map, int x, int y) {

        int lowestCost = Integer.MAX_VALUE;

        if (map.containsKey(new Coordinate(x, y))) {
            lowestCost = Math.min(lowestCost, map.get(new Coordinate(x, y)).cost);
        }

        if (map.containsKey(new Coordinate(x - 1, y)) && map.get(new Coordinate(x, y)).linkS) {
            lowestCost = Math.min(lowestCost, 1 + map.get(new Coordinate(x - 1, y)).cost);
        }

        if (map.containsKey(new Coordinate(x + 1, y)) && map.get(new Coordinate(x, y)).linkN) {
            lowestCost = Math.min(lowestCost, 1 + map.get(new Coordinate(x + 1, y)).cost);
        }

        if (map.containsKey(new Coordinate(x, y - 1)) && map.get(new Coordinate(x, y)).linkE) {
            lowestCost = Math.min(lowestCost, 1 + map.get(new Coordinate(x, y - 1)).cost);
        }

        if (map.containsKey(new Coordinate(x, y + 1)) && map.get(new Coordinate(x, y)).linkW) {
            lowestCost = Math.min(lowestCost, 1 + map.get(new Coordinate(x, y + 1)).cost);
        }

        return lowestCost;
    }
}

class CostLinks {

    public int cost = Integer.MAX_VALUE;

    public boolean linkN, linkS, linkE, linkW;

    public CostLinks cost(int newCost) {
        cost = newCost;
        return this;
    }

    public CostLinks link(char dir) {

        if (dir == 'N') {
            linkN = true;
        }

        else if (dir == 'S') {
            linkS = true;
        }

        else if (dir == 'W') {
            linkW = true;
        }

        else if (dir == 'E') {
            linkE = true;
        }

        return this;
    }

    public CostLinks linkReverse(char dir) {

        if (dir == 'N') {
            linkS = true;
        }

        else if (dir == 'S') {
            linkN = true;
        }
        else if (dir == 'W') {
            linkE = true;
        }

        else if (dir == 'E') {
            linkW = true;
        }

        return this;
    }

}

class Coordinate {

    public int x;
    public int y;

    public Coordinate(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Object compareCoordinate) {
        return ( ((Coordinate) compareCoordinate).getX() == x && ((Coordinate) compareCoordinate).getY() == y);
    }

    public int hashCode() {
        return x << 100 + y;
    }

}
