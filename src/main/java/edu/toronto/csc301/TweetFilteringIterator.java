package edu.toronto.csc301;


import java.util.Iterator;
import java.util.Set;
import java.lang.NullPointerException;
import java.lang.IllegalArgumentException;
import java.util.NoSuchElementException;


public class TweetFilteringIterator implements Iterator<ITweet> {
	
	Iterator<ITweet> filtered;
	Iterator<ITweet> data;
	Set<String> filter;
	ITweet next;
	
	public TweetFilteringIterator (Iterator <ITweet> data , Set<String> filter) {
		this.filter = filter;
		this.data = data;
		if (this.filter == null){
			throw new NullPointerException("Null hashtag list");
		}
		if (this.filter.isEmpty()){
			throw new IllegalArgumentException("Empty Hashtag list");
		}
		toNext();
	}
	@Override
	public boolean hasNext (){
		return next != null;
	}
	@Override
	public ITweet next (){
		if (next == null){
			throw new NoSuchElementException();
		}
		ITweet cur = next;
		toNext();
		return cur;
	}
	private void toNext (){
		next = null;
		while (data.hasNext()){
			ITweet cur = data.next();
			if (cur != null && cur.getHashTags().containsAll(this.filter)){
				next=cur;
				break;
			}
		}
	
}
}