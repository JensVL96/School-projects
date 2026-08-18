package tdt4250.dict2.no;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetWords implements Words {

	private SortedSet<CharSequence> wordSet = new TreeSet<CharSequence>();
	
	public boolean isLegalWord(CharSequence word) {
		int count = word.length(), lowerCount = 0, upperCount = 0;
		for (int i = 0; i < count; i++) {
			char c = word.charAt(i);
			if (! Character.isLetter(c)) {
				return false;
			}
			if (Character.isLowerCase(c)) {
				lowerCount++;
			} else if (Character.isUpperCase(c)) {
				upperCount++;
			}
		}
		return isLegalWord(count, lowerCount, upperCount);
	}

	private boolean isLegalWord(int count, int lowerCount, int upperCount) {
		return lowerCount == count;
	}

	@Override
	public boolean hasWord(CharSequence word) {
		return wordSet.contains(word instanceof Comparable<?> ? word : word.toString());
	}
	
	public void addWord(CharSequence word) {
		if (isLegalWord(word)) {
			wordSet.add(word);
		}
	}
	
	@Override
	public Iterator<CharSequence> iterator() {
		return wordSet.iterator();
	}
}
