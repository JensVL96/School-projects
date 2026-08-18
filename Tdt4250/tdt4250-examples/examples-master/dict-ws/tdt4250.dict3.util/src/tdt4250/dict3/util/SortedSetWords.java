package tdt4250.dict3.util;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import tdt4250.dict3.util.internal.AbstractMutableWords;

public class SortedSetWords extends AbstractMutableWords {

	private SortedSet<CharSequence> wordSet = new TreeSet<CharSequence>();
	
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
