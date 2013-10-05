#include <iostream>
#include <string>
#include <algorithm>

using namespace std;

typedef string::iterator Iterator;
static string findCommonPermutations(string & word1, string & word2);

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php?page=download
 * problem&probid=110303&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Jason Riddle (jr1285@vt.edu)
 * @aurtho Quinn Liu (quinnliu@vt.edu)
 */
int main() {
	string word1;
	string word2;

	while (getline(cin, word1) && getline(cin, word2)) {
		cout << findCommonPermutations(word1, word2) << endl;
	}
	return 0;
}

/**
 * Find the common permutations in O(n) time sorting the input
 * string words.
 */
string findCommonPermutations(string &word1, string &word2) {
	// arrange both in sorted order based off position in alphabet
	sort(word1.begin(), word1.end());
	sort(word2.begin(), word2.end());

	string commonPermutations;

	Iterator pointerToWord1 = word1.begin();
	Iterator pointerToWord2 = word2.begin();

	// iterate through both words once since the letters are in sorted order
	while (pointerToWord1 != word1.end() && pointerToWord2 != word2.end()) {
		if (*pointerToWord1 == *pointerToWord2) {
			// add the common letter to our string
			commonPermutations += *pointerToWord1;

			// move on to next letter in both words
			pointerToWord1++;
			pointerToWord2++;
		} else if (*pointerToWord1 < *pointerToWord2) {
			// since the words are in sorted order the deferenced pointers can
			// be used to indicate which word is father along in terms of the alphabet
			pointerToWord1++;
		} else {
			pointerToWord2++;
		}
	}
	return commonPermutations;
}
