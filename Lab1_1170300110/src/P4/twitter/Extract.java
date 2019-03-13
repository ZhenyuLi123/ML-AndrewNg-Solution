/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

	/**
	 * Get the time period spanned by tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return a minimum-length time interval that contains the timestamp of every
	 *         tweet in the list.
	 */
	public static Timespan getTimespan(List<Tweet> tweets) {
		// min��¼�����ʱ��
		Instant min = tweets.get(0).getTimestamp();
		// max��¼�����ʱ��
		Instant max = tweets.get(0).getTimestamp();
		// ���� ����min max
		for (Tweet i : tweets) {
			if (i.getTimestamp().isAfter(max)) {
				max = i.getTimestamp();
			}
			if (i.getTimestamp().isBefore(min)) {
				min = i.getTimestamp();
			}
		}
		Timespan timespan = new Timespan(min, max);
		return timespan;
	}

	/**
	 * Get usernames mentioned in a list of tweets.
	 * 
	 * @param tweets list of tweets with distinct ids, not modified by this method.
	 * @return the set of usernames who are mentioned in the text of the tweets. A
	 *         username-mention is "@" followed by a Twitter username (as defined by
	 *         Tweet.getAuthor()'s spec). The username-mention cannot be immediately
	 *         preceded or followed by any character valid in a Twitter username.
	 *         For this reason, an email address like bitdiddle@mit.edu does NOT
	 *         contain a mention of the username mit. Twitter usernames are
	 *         case-insensitive, and the returned set may include a username at most
	 *         once.
	 */
	public static Set<String> getMentionedUsers(List<Tweet> tweets) {

		Set<String> result = new HashSet<String>();
		boolean flag = true;
		String[] text;
		///text ��ÿһ��Ҫ�����getText����
		String[] name;
		///name ���ἰ��������
		String n = null;

		int nums = 0;

		for (Tweet i : tweets) {
			text = i.getText().split(" ");
			//���ÿո�ֿ�
			for (String j : text) {
				///�������ÿһ���ַ������б���
				if (!j.isEmpty()) {
					if (j.charAt(0) == '@') {
						///�������ĳһ���ַ����ĵ�һλ��@
						nums = 0;
						flag = false;
						/// �ҵ�@ xxxx
						name = j.split("[, ? @ : ' \" ! .]");
						/// Ϊ�˷�ֹ���ֺ�������ַ�����splitһ��
						/// ���������nums ��¼�ִʺ� �õ��ַ���������������������ʹ�������ʽ��xxx.xxx���Ϸ����ü���
						for (int k = 0; k < name.length; k++) {
							if (!name[k].isEmpty()) {
								nums++;
								if (ifLegal(name[k])) {
									///�ж������Ƿ�Ϸ�
									n = name[k];
									/// n�����Ǹ�����
									flag = true;
								}
							}
						}
						if (nums == 1 && flag == true) {
							if (!ifContain(result, n)) {
								///���result�������Ƿ���n ���û��������ȥ
								result.add(n.toLowerCase());
							}
						}
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * �ж�һ���������Ƿ���ĳ���ַ���
	 * @param result
	 * @param name
	 * @return �������(�Ƿ���
	 */
	public static boolean ifContain(Set<String> result, String name) {
		boolean flag = false;
		for(String i : result) {
			if(i.toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
		
	}

	/**
	 * �ж�һ�������Ƿ�Ϸ�
	 * @param name
	 * @return �������
	 */
	public static boolean ifLegal(String name) {
//    	char[] legal  = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
//        		'o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D',
//        		'E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T',
//        		'U','V','W','X','Y','Z','-','_'};
		for (int i = 0; i < name.length(); i++) {
			int nums = name.charAt(i);
			///ʹ��ASCII���������ж�
			if (nums < 45 || (nums > 45 && nums < 48) || (nums > 57 && nums < 65) || (nums > 90 && nums < 95)
					|| (nums > 95 && nums < 97) || nums > 122) {
				return false;
			}
		}
		return true;
	}

	/// small test
	public static void main(String[] args) {
		Instant d3 = Instant.parse("2016-02-16T10:00:00Z");
		Tweet tweet3 = new Tweet(1, "alyssa", "@lzy: @hit.com @lc-12138 @Lzy @lc_123", d3);

		Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet3));
		for (String i : mentionedUsers) {
			System.out.println(i);
		}
	}

}
