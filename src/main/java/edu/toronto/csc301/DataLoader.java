package edu.toronto.csc301;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

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
		Iterator <ITweet> stream = new TweetFilteringIterator (data, hashtags);
	}
	
}