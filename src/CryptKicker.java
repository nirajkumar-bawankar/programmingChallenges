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
 * @author Quinn Liu (quinnliu@vt.edu)
 * @author Jason Riddle (jr1285@vt.edu)
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
    public static void main(String[] args) throws IOException {
	BufferedReader bufferedReader = new BufferedReader(
		new InputStreamReader(System.in));
	String currentLine = bufferedReader.readLine();

	int currentLineLength = Integer.parseInt(currentLine.trim());

	String[] dictionaryOfWordsInDecryptedText = new String[currentLineLength];
	for (int i = 0; i < currentLineLength; i++) {
	    currentLine = bufferedReader.readLine();
	    dictionaryOfWordsInDecryptedText[i] = currentLine;
	}

	indexedDictionary = (List<String>[]) new ArrayList[DICTIONARY_WORD_MAXIMUM_LENGTH + 1];
	for (String dictionaryWord : dictionaryOfWordsInDecryptedText) {
	    int index = dictionaryWord.length();
	    // rearrange words within dictionary by their length for faster
	    // access
	    if (indexedDictionary[index] == null) {
		indexedDictionary[index] = new ArrayList<String>();
	    }
	    indexedDictionary[index].add(dictionaryWord);
	}

	while ((currentLine = bufferedReader.readLine()) != null) {
	    String[] encryptedWords = currentLine.split(" ");
	    if (encryptedWords != null && encryptedWords.length != 0) {
		System.out.println(decryptEncryptedWords(encryptedWords));
	    }
	}
    }

    private static String decryptEncryptedWords(String[] encryptedWords) {
	char[] characterMappings = new char[NUMBER_OF_UNIQUE_LETTERS];
	for (int i = 0; i < characterMappings.length; i++) {
	    characterMappings[i] = ASTERISK;
	}

	characterMappings = backtrackToGetDecryptedWords(0, characterMappings,
		encryptedWords);

	String decryptedWords = buildDecryptedString(characterMappings,
		encryptedWords);
	return decryptedWords;
    }

    private static String buildDecryptedString(char[] characterMappings,
	    String[] encryptedWords) {
	StringBuilder stringBuilder = new StringBuilder();
	for (String encryptedWord : encryptedWords) {
	    char[] charArrayOfEncryptedWords = encryptedWord.toCharArray();
	    for (char character : charArrayOfEncryptedWords) {
		if (characterMappings != null) {
		    stringBuilder.append(characterMappings[character - 'a']);
		} else {
		    stringBuilder.append(ASTERISK);
		}
	    }
	    stringBuilder.append(" ");
	}
	return stringBuilder.toString().trim();
    }

    private static char[] backtrackToGetDecryptedWords(int i,
	    char[] characterMappings, String[] encryptedWords) {
	if (i == encryptedWords.length)
	    return characterMappings;
	else {
	    for (String dictionaryWord : indexedDictionary[encryptedWords[i]
		    .length()]) {
		char[] localCharacterMappings = Arrays.copyOf(
			characterMappings, characterMappings.length);
		if (isTheEncryptedWordsAbleToBecomeTheUnencryptedWordsBasedOnTheGivenMapping(
			encryptedWords[i], dictionaryWord,
			localCharacterMappings)) {
		    localCharacterMappings = backtrackToGetDecryptedWords(
			    i + 1, localCharacterMappings, encryptedWords);
		    if (localCharacterMappings != null) {
			return localCharacterMappings;
		    }
		}
	    }
	    return null;
	}
    }

    public static boolean isTheEncryptedWordsAbleToBecomeTheUnencryptedWordsBasedOnTheGivenMapping(
	    String encryptedWords, String unencryptedWords, char[] mappings) {

	if (encryptedWords.length() != unencryptedWords.length()) {
	    return false;
	}

	char[] charArrayOfEncryptedWords = encryptedWords.toCharArray();
	char[] charArrayOfUnencryptedWords = unencryptedWords.toCharArray();

	for (int i = 0; i < charArrayOfEncryptedWords.length; i++) {
	    char encryptedChar = charArrayOfEncryptedWords[i];
	    char plaintextChar = charArrayOfUnencryptedWords[i];

	    int index = encryptedChar - 'a';
	    if (mappings[index] != ASTERISK && mappings[index] != plaintextChar) {
		return false;
	    } else {
		mappings[index] = plaintextChar;
	    }
	}
	return isInjectiveMapping(mappings);
    }

    private static boolean isInjectiveMapping(char[] mappings) {
	int[] characterMappings = new int[NUMBER_OF_UNIQUE_LETTERS];
	for (char characterToMap : mappings) {
	    if (characterToMap != ASTERISK) {
		int i = characterToMap - 'a';
		if (characterMappings[i] != 0) {
		    return false;
		} else {
		    characterMappings[i] = 1;
		}
	    }
	}
	return true;
    }
}
