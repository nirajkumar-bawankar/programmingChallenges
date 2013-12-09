import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ErraticAnts {
    public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	int num_cases = sc.nextInt();
	for (int case_num = 0; case_num < num_cases; ++case_num) {
	    int path_length = sc.nextInt();
	    sc.nextLine();
	    Map<Coord, CostLinks> map = new HashMap<Coord, CostLinks>();
	    map.put(new Coord(0, 0), new CostLinks().cost(0));
	    int x = 0;
	    int y = 0;
	    for (int path_num = 0; path_num < path_length; ++path_num) {
		char dir = sc.nextLine().charAt(0);
		map.get(new Coord(x, y)).link(dir);
		if (dir == 'N') {
		    ++x;
		} else if (dir == 'S') {
		    --x;
		} else if (dir == 'W') {
		    ++y;
		} else if (dir == 'E') {
		    --y;
		}
		if (!map.containsKey(new Coord(x, y))) {
		    map.put(new Coord(x, y), new CostLinks());
		}
		map.get(new Coord(x, y)).linkReverse(dir).cost(cost(map, x, y));
	    }
	    System.out.println(map.get(new Coord(x, y)).cost);
	}
	sc.close();
    }

    private static int cost(Map<Coord, CostLinks> map, int x, int y) {
	int lowestCost = Integer.MAX_VALUE;
	if (map.containsKey(new Coord(x, y))) {
	    lowestCost = Math.min(lowestCost, map.get(new Coord(x, y)).cost);
	}
	if (map.containsKey(new Coord(x - 1, y))
		&& map.get(new Coord(x, y)).linkS) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coord(x - 1, y)).cost);
	}
	if (map.containsKey(new Coord(x + 1, y))
		&& map.get(new Coord(x, y)).linkN) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coord(x + 1, y)).cost);
	}
	if (map.containsKey(new Coord(x, y - 1))
		&& map.get(new Coord(x, y)).linkE) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coord(x, y - 1)).cost);
	}
	if (map.containsKey(new Coord(x, y + 1))
		&& map.get(new Coord(x, y)).linkW) {
	    lowestCost = Math.min(lowestCost,
		    1 + map.get(new Coord(x, y + 1)).cost);
	}
	return lowestCost;
    }
}

class CostLinks {
    public int cost = Integer.MAX_VALUE;
    public boolean linkN, linkS, linkE, linkW;

    public CostLinks cost(int cost) {
	this.cost = cost;
	return this;
    }

    public CostLinks link(char dir) {
	if (dir == 'N') {
	    linkN = true;
	} else if (dir == 'S') {
	    linkS = true;
	} else if (dir == 'W') {
	    linkW = true;
	} else if (dir == 'E') {
	    linkE = true;
	}
	return this;
    }

    public CostLinks linkReverse(char dir) {
	if (dir == 'N') {
	    linkS = true;
	} else if (dir == 'S') {
	    linkN = true;
	} else if (dir == 'W') {
	    linkE = true;
	} else if (dir == 'E') {
	    linkW = true;
	}
	return this;
    }

    public String toString() {
	return "CostLinks[cost=" + cost + ",links=" + (linkN ? "N" : "")
		+ (linkS ? "S" : "") + (linkE ? "E" : "") + (linkW ? "W" : "")
		+ "]";
    }
}

final class Coord {
    public final int x, y;

    public Coord(int x, int y) {
	this.x = x;
	this.y = y;
    }

    public boolean equals(Object o) {
	if (o instanceof Coord) {
	    return ((Coord) o).x == x && ((Coord) o).y == y;
	}
	return false;
    }

    public int hashCode() {
	return x << 100 + y;
    }

    public String toString() {
	return "Coord[" + x + ", " + y + "]";
    }
}
