package edu.toronto.csc301;

import java.io.IOException;
import java.util.Iterator;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class TweetStreamReadingIterator implements Iterator<ITweet> {
	
	private BufferedReader r;
	
	public TweetStreamReadingIterator (InputStream data){
		this.r = new BufferedReader (new InputStreamReader(data));
	}
	
	@Override
	public boolean hasNext (){
		try{
			return this.r.ready();
		} catch (IOException e) {
		e.printStackTrace();
		}
		return false;
			
	}
	public ITweet next (){
		String t;
		try{
			t = this.r.readLine();
			return processString(t);
		}catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}

	public ITweet processString(String tweetString){
		String tweetParts[] = tweetString.split(":");
		String username = tweetParts[0].trim();
		String text = tweetParts[1].trim();
		ITweet tweet= new Tweet(username.substring(1),text);
		return tweet;
	
	}
	
}