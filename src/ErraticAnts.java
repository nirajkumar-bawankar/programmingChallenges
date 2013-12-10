import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ErraticAnts {

    public static void main(String[] args) {

	Scanner scanner = new Scanner(System.in);

	int numberOfProblems = scanner.nextInt();

	int currentCase = 1;

	while (currentCase < numberOfProblems + 1) {

	    int path_length = scanner.nextInt();

	    scanner.nextLine();

	    Map<Coordinate, CostLinks> map = new HashMap<Coordinate, CostLinks>();

	    Coordinate zeroCoordinate = new Coordinate(0, 0);

	    map.put(zeroCoordinate, new CostLinks().cost(0));

	    int x = 0;
	    int y = 0;

	    int path_num = 1;

	    while (path_num < path_length + 1) {

		char dir = scanner.nextLine().charAt(0);

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

		if (!map.containsKey(new Coordinate(x, y))) {
		    map.put(new Coordinate(x, y), new CostLinks());
		}

		map.get(new Coordinate(x, y)).reverseTheLink(dir)
			.cost(cost(map, x, y));

		path_num++;

	    }

	    System.out.println(map.get(new Coordinate(x, y)).cost);

	    currentCase++;

	}

	scanner.close();

    }

    private static int cost(Map<Coordinate, CostLinks> map, int x, int y) {

	int lowestCost = Integer.MAX_VALUE;

	if (map.containsKey(new Coordinate(x, y))) {
	    lowestCost = Math.min(lowestCost,
		    map.get(new Coordinate(x, y)).cost);
	}

	if (map.containsKey(new Coordinate(x - 1, y))
		&& map.get(new Coordinate(x, y)).linkS) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coordinate(x - 1, y)).cost);
	}

	if (map.containsKey(new Coordinate(x + 1, y))
		&& map.get(new Coordinate(x, y)).linkN) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coordinate(x + 1, y)).cost);
	}

	if (map.containsKey(new Coordinate(x, y - 1))
		&& map.get(new Coordinate(x, y)).linkE) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coordinate(x, y - 1)).cost);
	}

	if (map.containsKey(new Coordinate(x, y + 1))
		&& map.get(new Coordinate(x, y)).linkW) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coordinate(x, y + 1)).cost);
	}

	return lowestCost;
    }
}

/**
 * @version Dec 10, 2013
 */
class CostLinks {

    public int cost = Integer.MAX_VALUE;

    public boolean linkN;
    public boolean linkS;
    public boolean linkE;
    public boolean linkW;

    public CostLinks cost(int newCost) {
	this.cost = newCost;
	return this;
    }

    public CostLinks link(char dir) {

	if (dir == 'N') {
	    this.linkN = true;
	}

	else if (dir == 'S') {
	    this.linkS = true;
	}

	else if (dir == 'W') {
	    this.linkW = true;
	}

	else if (dir == 'E') {
	    this.linkE = true;
	}

	return this;
    }

    public CostLinks reverseTheLink(char directory) {

	if (directory == 'N') {
	    this.linkS = true;
	}

	else if (directory == 'S') {
	    this.linkN = true;
	} else if (directory == 'W') {
	    this.linkE = true;
	}

	else if (directory == 'E') {
	    this.linkW = true;
	}

	return this;
    }

}

class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public int getX() {
	return this.x;
    }

    public int getY() {
	return this.y;
    }

    public boolean equals(Object compareCoordinate) {
	return (((Coordinate) compareCoordinate).getX() == this.x && ((Coordinate) compareCoordinate)
		.getY() == this.y);
    }

    public int hashCode() {
	return this.x << 100 + this.y;
    }

}
