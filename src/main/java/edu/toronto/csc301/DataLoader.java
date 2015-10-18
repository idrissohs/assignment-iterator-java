package edu.toronto.csc301;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Predicate;

public class DataLoader implements IDataLoader {
	 /**
	   * Return an iterator that visits all Tweets in <code>data</code>.
	   * The returned iterator reads from <code>data</code> in lazy-loading fashion.
	   * 
	   * @param data A stream of text that represents a sequence of Tweets.
	   */
	public Iterator<ITweet> iterator (InputStream data) throws IOException{
		Iterator <ITweet> stream = new TweetStreamReadingIterator(data);
		return stream;
	}
	
	
	public Iterator<ITweet> iterator (InputStream data, Set<String> hashtags) throws IOException {
		Iterator <ITweet> iter = new TweetStreamReadingIterator (data);
		Predicate <Set<String>> filter = new Predicate <Set<String>> (){
			public boolean test (Set<String> h){
				if (hashtags == null)
					return false;
				return h.containsAll(hashtags);
			}
		};
		if (hashtags == null){
			throw new NullPointerException("Null hashtag list");
		}
		if (hashtags.isEmpty()){
			throw new IllegalArgumentException("Empty Hashtag list");
		}
		Iterator <ITweet> stream = new TweetFilteringIterator (iter, filter );
		return stream;
	}
	
}