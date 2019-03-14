/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

	/**
	 * Find tweets written by a particular user.
	 * 
	 * @param tweets   a list of tweets with distinct ids, not modified by this
	 *                 method.
	 * @param username Twitter username, required to be a valid Twitter username as
	 *                 defined by Tweet.getAuthor()'s spec.
	 * @return all and only the tweets in the list whose author is username, in the
	 *         same order as in the input list.
	 */
	public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
		List<Tweet> result = new ArrayList<Tweet>();
		String name;
		for (int i = 0; i < tweets.size(); i++) {
			name = tweets.get(i).getAuthor();
			///�����б���������������username���˵�tweet��ӵ�result�б���
			if (name.toLowerCase().equals(username.toLowerCase())) {
				result.add(tweets.get(i));
			}
		}
		return result;
	}

	/**
	 * Find tweets that were sent during a particular timespan.
	 * 
	 * @param tweets   a list of tweets with distinct ids, not modified by this
	 *                 method.
	 * @param timespan timespan
	 * @return all and only the tweets in the list that were sent during the
	 *         timespan, in the same order as in the input list.
	 */
	public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
		Instant start = timespan.getStart();
		Instant end = timespan.getEnd();
		Instant time;
		List<Tweet> result = new ArrayList<Tweet>();

		for (int i = 0; i < tweets.size(); i++) {
			time = tweets.get(i).getTimestamp();
			if (time.isBefore(end) && time.isAfter(start)) {
				///����������صķ���ʱ����start~end��Χ����tweet���뵽result�б���
				result.add(tweets.get(i));
			}
		}

		return result;

	}

	/**
	 * Find tweets that contain certain words.
	 * 
	 * @param tweets a list of tweets with distinct ids, not modified by this
	 *               method.
	 * @param words  a list of words to search for in the tweets. A word is a
	 *               nonempty sequence of nonspace characters.
	 * @return all and only the tweets in the list such that the tweet text (when
	 *         represented as a sequence of nonempty words bounded by space
	 *         characters and the ends of the string) includes *at least one* of the
	 *         words found in the words list. Word comparison is not case-sensitive,
	 *         so "Obama" is the same as "obama". The returned tweets are in the
	 *         same order as in the input list.
	 */
	public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
		/// �����һ���ÿո�����ĵ��ʵ�����ĸ��@�Ļ���ȡ@����Ĳ��ִ���word
		String word;
		/// text�е�ÿһ���ÿո�����ĵ���
		String[] textWord;
		int index = 0;
		List<Tweet> result = new ArrayList<Tweet>();

		for (int i = 0; i < tweets.size(); i++) {
			/// ����flag ��������жϲ����������б��У�
			textWord = tweets.get(i).getText().split(" ");
			for (int j = 0; j < textWord.length; j++) {
				word = textWord[j];
				/// ���������ô�ͼ����б��в���break����������� ��������ж���
				if (ifContain(word, words)) {
					///ע�������ifContainҪ���з��ŵĴ��� ����@֮���
					result.add(index,tweets.get(i));
					index++;
					break;
				}
			}
		}
		
	
		return result;
	}

	/**
	 * �ж�һ��word ���Ƿ��� words �б��е�ĳһ����
	 * @param word
	 * @param words
	 * @return �������
	 */
	public static boolean ifContain(String word, List<String> words) {
		List<String> lowerCase = new ArrayList<String>();
		// �Ƚ���ЩLIST�еĴ�ת��ΪСд
		for (String i : words) {
			lowerCase.add(i.toLowerCase());
		}
		
		int nums = 0;
		boolean flag = false;
		
		///��word����С���� ����һ���ַ�
		String[] wordDoing;
		wordDoing = word.split("[, ? @ : ' \" ! .]");
		
		for(int i = 0; i < wordDoing.length; i++) {
			if(!wordDoing[i].isEmpty()) {
				nums++;
				///����ҲҪ��������� ����ֹ�Ƿ�����
				if(lowerCase.contains(wordDoing[i].toLowerCase())) {
					///�ж��Ƿ����
					flag = true;
				}
			}
		}
		
		if(nums != 1) {
			flag = false;
		}
		
		return flag;
	}
	
//	small test
//	public static void main(String[] args) {
//		Instant d3 = Instant.parse("2016-02-15T10:00:00Z");
//		Instant d4 = Instant.parse("2016-02-16T11:00:00Z");
//		Tweet tweet3 = new Tweet(3, "a", "LZY", d3);
//		Tweet tweet4 = new Tweet(3, "a", "@Lzy  ", d4);
//		
////		List<Tweet> containing = containing(Arrays.asList(tweet3, tweet4), Arrays.asList("lzy"));
////		
////		System.out.println(containing.indexOf(tweet3));
////		System.out.println(containing.indexOf(tweet4));
////		
//		List<Tweet> text = Arrays.asList(tweet3, tweet4);
//		System.out.println(text.indexOf(tweet3));
//		System.out.println(text.indexOf(tweet4));
//		
//	}

}
