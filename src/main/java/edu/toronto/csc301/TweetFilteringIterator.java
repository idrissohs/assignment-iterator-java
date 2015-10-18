package edu.toronto.csc301;


import java.util.Iterator;
import java.util.function.Predicate;
import java.util.Set;
import java.util.NoSuchElementException;


public class TweetFilteringIterator implements Iterator<ITweet> {
	
	Iterator<ITweet> filtered;
	Iterator<ITweet> data;
	Predicate<Set<String>> filter;
	ITweet next;
	boolean has=false;
	
	public TweetFilteringIterator (Iterator <ITweet> data , Predicate<Set<String>> filter) {
		super();
		this.filter = filter;
		this.data = data;
	}
	@Override
	public boolean hasNext (){
		if (has) {
            return true;
        } else {
            return setNext();
        }
	}
	
	@Override
	public ITweet next (){
		if (!has) {
            if (!setNext()) {
                throw new NoSuchElementException();
            }
        }
        has = false;
        return next;
	}
	private boolean setNext() {
        while (data.hasNext()) {
            ITweet tweet = data.next();
            if (filter.test(tweet.getHashTags())) {
            	System.out.println("true");
                next = tweet;
                has = true;
                return true;
            }
        }
        System.out.println("false");
        return false;
    }

}