import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Problem statement can be viewed at:
 * http://www.programming-challenges.com/pg.php
 * ?page=downloadproblem&probid=110204&format=html
 *
 * The following is a solution for the above problem.
 *
 * @author Andrey Yemelyanov
 */
public class CryptKicker {
    private static final int NUMBER_OF_UNIQUE_LETTERS = 26;
    private static final int DICTIONARY_WORD_MAXIMUM_LENGTH = 16;
    private static final char ASTERISK = '*';

    private static List<String>[] indexedDictionary;

    /**
     * @param args
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(
		new InputStreamReader(System.in));
	String currentLine = bufferedReader.readLine();

	int currentLineLength = Integer.parseInt(currentLine.trim());

	// store dictionary with the words that will appear in the dycrypted text
	String[] dictionary = new String[currentLineLength];
	for (int i = 0; i < currentLineLength; i++) {
	    currentLine = bufferedReader.readLine();
	    dictionary[i] = currentLine;
	}

	// index dictionary words by their length
	// this will make an operation that returns all dictionary
	// words of some length n run in O(1) time.
	indexedDictionary = (List<String>[]) new ArrayList[DICTIONARY_WORD_MAXIMUM_LENGTH + 1];
	for (String dictionaryWord : dictionary) {
	    int index = dictionaryWord.length();
	    if (indexedDictionary[index] == null) {
		indexedDictionary[index] = new ArrayList<String>();
	    }
	    indexedDictionary[index].add(dictionaryWord);
	}

	while ((currentLine = bufferedReader.readLine()) != null) {
	    String[] encryptedWords = currentLine.split(" ");
	    if (encryptedWords != null && encryptedWords.length != 0)
		System.out.println(decrypt(encryptedWords));
	}
    }

    private static String decrypt(String[] encryptedWords) {
	char[] mappings = new char[NUMBER_OF_UNIQUE_LETTERS];
	for (int i = 0; i < mappings.length; i++)
	    mappings[i] = ASTERISK;

	mappings = backtrack(0, mappings, encryptedWords);

	return buildDecryptedString(mappings, encryptedWords);
    }

    private static String buildDecryptedString(char[] mappings,
	    String[] encryptedWords) {
	StringBuilder builder = new StringBuilder();
	for (String word : encryptedWords) {
	    for (char character : word.toCharArray()) {
		if (mappings != null) {
		    builder.append(mappings[character - 'a']);
		} else {
		    builder.append(ASTERISK);
		}
	    }
	    builder.append(" ");
	}

	return builder.toString().trim();
    }

    private static char[] backtrack(int i, char[] mappings,
	    String[] encryptedWords) {
	if (i == encryptedWords.length) // all encrypted words have been
					// successfully decrypted, return final
					// mappings
	    return mappings;
	else {
	    for (String dictWord : indexedDictionary[encryptedWords[i].length()]) {
		char[] localMappings = Arrays.copyOf(mappings, mappings.length);
		if (mappingPossible(encryptedWords[i], dictWord, localMappings)) {
		    localMappings = backtrack(i + 1, localMappings,
			    encryptedWords);
		    if (localMappings != null)
			return localMappings;
		}
	    }
	    return null;
	}
    }

    /**
     * mappingPossible("abc", "xyz") returns true whereas mappingPossible("iif",
     * "abc") returns false because 'i' will be mapped to both 'a' and 'b'
     */
    public static boolean mappingPossible(String encrypted, String plaintext,
	    char[] mappings) {

	if (encrypted.length() != plaintext.length()) // words must be of the
						      // same length
	    return false;

	char[] encryptedChars = encrypted.toCharArray();
	char[] plaintextChars = plaintext.toCharArray();

	// this part makes sure that mapping [all -> the] is false
	for (int i = 0; i < encryptedChars.length; i++) {
	    char encryptedChar = encryptedChars[i];
	    char plaintextChar = plaintextChars[i];

	    int index = encryptedChar - 'a';
	    if (mappings[index] != ASTERISK && mappings[index] != plaintextChar)
		return false; // CONFLICT - this encryptedChar has already been
			      // previously mapped to some other plaintextChar
			      // // thus, the proposed [encrypted -> plaintext]
			      // mapping is invalid
	    else
		mappings[index] = plaintextChar;
	}

	// this part ensures that mapping [the -> all] is false
	return injective(mappings);
    }

    private static boolean injective(char[] mappings) {
	int[] injectiveMappings = new int[NUMBER_OF_UNIQUE_LETTERS];
	for (char mappedChar : mappings) {
	    if (mappedChar != ASTERISK) {
		int index = mappedChar - 'a';
		if (injectiveMappings[index] != 0)
		    return false;
		else
		    injectiveMappings[index] = 1;
	    }
	}
	return true;
    }
}
