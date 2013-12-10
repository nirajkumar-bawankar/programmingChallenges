import java.util.StringTokenizer;
import java.util.Scanner;

public class Nine {

    public static void main(String args[]) {
	Scanner scanner = new Scanner(System.in);
	int milliseconds;
	int seconds;
	char colon;
	int[] nines = new int[100];
	for (int i = 0; i <= 99; i++) {
	    nines[i] = 0;
	    if (i % 10 == 9)
		nines[i]++;
	    if (i / 10 == 9)
		nines[i]++;
	}
	while (scanner.hasNextLine()) {
	    StringTokenizer stringTokenizer = new StringTokenizer(
		    scanner.nextLine());

	    while (stringTokenizer.hasMoreTokens()) {
		milliseconds = Integer.parseInt(stringTokenizer.nextToken());
		stringTokenizer.nextToken();
		seconds = Integer.parseInt(stringTokenizer.nextToken());

		int T0 = 60 * milliseconds + seconds;
		int bestm = 0, bests = 0;
		int best9 = -1;
		int besterror = 1000000;

		for (int mm = 0; mm <= 99; mm++) {
		    for (int ss = 0; ss <= 99; ss++) {
			int T9 = 60 * mm + ss;
			int ans = T9 - T0;
			int error = (ans < 0) ? -ans : ans;
			if (error * 10 >= T0)
			    continue;
			int num9 = nines[mm] + nines[ss];
			if (num9 > best9
				|| (num9 == best9 && error < besterror)
				|| (num9 == best9 && error == besterror && mm < bestm)
				|| (num9 == best9 && error == besterror
					&& mm == bestm && ss < bests)) {
			    bestm = mm;
			    bests = ss;
			    best9 = num9;
			    besterror = error;
			}
		    }
		}
		System.out.println(Integer.toString(bestm) + ":"
			+ Integer.toString(bests));
	    }
	}
    }
}
