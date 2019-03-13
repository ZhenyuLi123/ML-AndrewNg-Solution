/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even
 * exist as a key in the map; this is true even if A is followed by other people
 * in the network. Twitter usernames are not case sensitive, so "ernie" is the
 * same as "ERNie". A username should appear at most once as a key in the map or
 * in any given map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

	/**
	 * Guess who might follow whom, from evidence found in tweets.
	 * 
	 * @param tweets a list of tweets providing the evidence, not modified by this
	 *               method.
	 * @return a social network (as defined above) in which Ernie follows Bert if
	 *         and only if there is evidence for it in the given list of tweets. One
	 *         kind of evidence that Ernie follows Bert is if Ernie
	 * @-mentions Bert in a tweet. This must be implemented. Other kinds of evidence
	 *            may be used at the implementor's discretion. All the Twitter
	 *            usernames in the returned social network must be either authors
	 *            or @-mentions in the list of tweets.
	 */
	public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
		String name;
		Set<String> nameSet = null;
		Map<String, Set<String>> follow = new ConcurrentHashMap<String, Set<String>>();
		int times = 0;

		// Set<String> getMentionedUsers(List<Tweet> tweets)

		List<Tweet> tweetsOnes = new ArrayList<Tweet>();

		for (Tweet i : tweets) {
			name = i.getAuthor();
			///�Ѽ���������������ͬ������

			if (times == 0) {
				///�Ѽ���������������ͬ������
				for (Tweet j : tweets) {
					if (j.getAuthor().toLowerCase().equals(i.getAuthor().toLowerCase())) {
						tweetsOnes.add(j);
					}
				}
				//�Ѽ���һ��ӽ�ȥ
				nameSet = Extract.getMentionedUsers(tweetsOnes);
				follow.put(name, nameSet);
				times++;
			} else {
				if (!follow.containsKey(name.toLowerCase())) {
					for (Tweet j : tweets) {
						if (j.getAuthor().toLowerCase().equals(i.getAuthor().toLowerCase())) {
							tweetsOnes.add(j);
						}
					}
					nameSet = Extract.getMentionedUsers(tweetsOnes);
					follow.put(name, nameSet);
				}
			}
			//ÿ�ν���һ��ѭ�������tweets�������
			tweetsOnes.clear();
		}

		/// smarter!!!
		/// hashtags!!!
		Set<String> hashtags1;
		Set<String> hashtags2;
		String name1;
		String name2;
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			hashtags1 = getHashtags(i);
			for (Tweet j : tweets) {
				name2 = j.getAuthor();
				/// �ڲ���ͬ��tweet�б���
				if (!j.equals(i)) {
					hashtags2 = getHashtags(j);
					/// �õ������hashtag
					if (ifContainEquel(hashtags1, hashtags2)) {
						/// ���������ͬ����ôҪ������~
						/// ������Ҫ��ԭӳ���в����� �����ϵ ����Ҫ���жϡ�
						/// ֻ��Ҫ����һ�룬��һ�뽫��forѭ�����ٴμ���
						nameSet = follow.get(name1);
						if (!nameSet.contains(name2)) {
							nameSet.add(name2);
							follow.put(name1, nameSet);
						}
					}
				}
			}
		}

		/// smarter!!!
		/// a<-->b   b<--->c  �� a <--> c
		Map<String, Set<String>> followNew = new ConcurrentHashMap<String, Set<String>>();
		followNew = follow;

		Set<String> nameSet1;
		Set<String> nameSet2;
		Set<String> nameSet3;
		Set<String> nameSetNew1 = new HashSet<String>();
		Set<String> nameSetNew2 = new HashSet<String>();
		
		/// ����A�������ἰ�����ˣ����������һ��B��Ȼ�����B�����ἰ�����ˡ������A˵�� A--B
		// Ȼ�����B�ἰ������ ͬ�� �ó� B--C ��ô���� A--C
		///ѭ��/ifǶ�׵��е��
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			nameSet1 = Extract.getMentionedUsers(Filter.writtenBy(tweets, name1));
			if (!nameSet1.isEmpty()) {
				///��A�ἰ�����˵ı���
				for (String j : nameSet1) {
					if (!j.toLowerCase().equals(name1.toLowerCase())) {
						boolean flag = false;
						///����flag���������һ�������ע��ϵ 
						if (ifContainEachOther(follow, name1, j)) {
							flag = true;
						}
						if (flag == true) {
							///����л����ע��ϵ �����Ǹ��˶��������Ƿ������Ƶ��໥��ע��ϵ
							boolean flag2 = false;
							nameSet2 = Extract.getMentionedUsers(Filter.writtenBy(tweets, j));
							if (!nameSet2.isEmpty()) {
								for (String k : nameSet2) {
									if (!k.toLowerCase().equals(name1.toLowerCase())) {
										///����ҵ���~
										if (ifContainEachOther(follow, j, k)) {
											flag2 = true;
										}
										if (flag2 == true) {
											///Ӧ�ý�����뵽�µ�ӳ���
											nameSet3 = Extract.getMentionedUsers(Filter.writtenBy(tweets, k));
											/// name1 - i , i - j ����
											/// ��Ϊ�޷�ֱ���޸� Ҫ����һ��������Ȼ�����ӽ�ȥ.
											/// ��Ҳ��֪��Ϊɶ�����޸ģ�
											/// ��Ϊ�����޷��޸ģ�
											/// �ٶ�һ�º����������ġ�
											if (!nameSet1.contains(k.toLowerCase())) {
												nameSetNew1.clear();
												for(String m : nameSet1) {
													nameSetNew1.add(m);
												}
												nameSetNew1.add(k.toLowerCase());
												followNew.put(name1, nameSetNew1);
											}

											if (!nameSet3.contains(name1.toLowerCase())) {
												nameSetNew2.clear();
												for(String m : nameSet3) {
													nameSetNew2.add(m);
												}
												nameSetNew2.add(name1.toLowerCase());
												followNew.put(k, nameSetNew2);
											}

										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		//��ת�����д��� ���A ���� B  B ���� C  Bת����һ��C�����أ���ô���Բµ�A����C
		//�������� mapRetweet ����ĳ������ת�����������ص����߼���(Ϊһ��mapӳ��
		Map<String, Set<String>> mapRetweet = retweetMap(tweets);
		Map<String, Set<String>> followNewNew = new ConcurrentHashMap<String, Set<String>>();
		Set<String> nameSetNew = new HashSet<String>();
		followNewNew = followNew;
		
		for (Tweet i : tweets) {
			name1 = i.getAuthor();
			nameSet1 = Extract.getMentionedUsers(Filter.writtenBy(tweets, name1));
			if (!nameSet1.isEmpty()) {
				///��A�ἰ�����˽��б���
				for (String j : nameSet1) {
					if (!j.toLowerCase().equals(name1.toLowerCase())) {
						if(mapRetweet.containsKey(j)) {
							///��Bת����tweets�����߼����б���
							nameSet2 = mapRetweet.get(j);
							for(String k : nameSet2) {
								///����µĸ����ϵ
								if(!k.toLowerCase().equals(name1.toLowerCase())) {
									if(!nameSet1.contains(k.toLowerCase())) {
										nameSetNew.clear();
										for(String m : nameSet1) {
											nameSetNew.add(m);
										}
										nameSetNew.add(k.toLowerCase());
										followNewNew.put(name1, nameSetNew);
									}
								}
							}
						}
					}
				}
			}
		}
		
		

		return followNewNew;
	}

	/**
	 * ĳ���������� ---- ת�����������ߵ�ӳ��
	 * @param tweets
	 * @return һ��mapͼ
	 */
	public static Map<String, Set<String>> retweetMap(List<Tweet> tweets) {
		Map<String, Set<String>> map = new ConcurrentHashMap<String, Set<String>>();
		int cnt = 0;
		String name;
		String namedoing;
		Set<String> nameRT = new HashSet<String>();
		String text;
		///ѭ�������������صõ����� --> ת���������ߵĹ�ϵ����
		for(Tweet i : tweets) {
			if(cnt == 0) {
				///��һ�ν���ѭ��
				name = i.getAuthor();
				text = i.getText();
				if(text.charAt(0)=='R'&&text.charAt(1)=='T'&&text.charAt(2)==' '&&text.charAt(3)=='@') {
					namedoing = text.split(" ")[1];
					namedoing = namedoing.split("@")[1];
					namedoing = namedoing.split(":")[0];
					//�� RT @xxx: ���м򵥵Ĵ���
					nameRT.add(namedoing);
					//����map��
					map.put(name,nameRT);
					cnt++;
				}
				
			}else {
				name = i.getAuthor();
				text = i.getText();
				if(text.charAt(0)=='R'&&text.charAt(1)=='T'&&text.charAt(2)==' '&&text.charAt(3)=='@') {
					if(map.containsKey(name.toLowerCase())) {
						///���ϱ�ͬ��
						nameRT = map.get(name);
						namedoing = text.split(" ")[1];
						namedoing = namedoing.split("@")[1];
						namedoing = namedoing.split(":")[0];
						nameRT.add(namedoing);
						map.put(name,nameRT);
					}else {
						nameRT.clear();
						namedoing = text.split(" ")[1];
						namedoing = namedoing.split("@")[1];
						namedoing = namedoing.split(":")[0];
						nameRT.add(namedoing);
						map.put(name,nameRT);
					}
				}
			}
		}
		return map;
	}
	/**
	 * �ж�һ��ͼ�� x ��ӳ�� �������Ƿ���� y ��y��ӳ��ļ����к���x
	 * ��Ȼ��Ϊ�˷��� �ж�������֮���Ƿ����ע��
	 * @param map
	 * @param x
	 * @param y
	 * @return ���
	 */
	public static boolean ifContainEachOther(Map<String, Set<String>> map, String x, String y) {
		Set<String> nameSet1;
		Set<String> nameSet2;
		if (map.containsKey(x)) {
			nameSet1 = map.get(x);
		} else {
			return false;
		}
		if (map.containsKey(y)) {
			nameSet2 = map.get(y);
		} else {
			return false;
		}

		if (nameSet1.isEmpty() || nameSet2.isEmpty()) {
			return false;
		} else {
			for (String i : nameSet1) {
				if (i.toLowerCase().equals(y.toLowerCase())) {
					for (String j : nameSet2) {
						if (j.toLowerCase().equals(x.toLowerCase())) {
							return true;
						}
					}
				}
			}
		}

		/// can't get here
		return false;

	}

	/**
	 * �ж�SET���Ƿ����ĳһ��StringԪ��
	 * @param x
	 * @param name
	 * @return ���
	 */
	public static boolean ifContain2(Set<String> x, String name) {
		boolean flag = false;
		for (String i : x) {
			if (i.toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * �ж�����set String ���Ƿ�����ͬ��String
	 * @param x
	 * @param y
	 * @return ���
	 */
	public static boolean ifContainEquel(Set<String> x, Set<String> y) {
		boolean falg = false;
		for (String i : x) {
			for (String j : y) {
				if (j.toLowerCase().equals(i.toLowerCase())) {
					falg = true;
				}
			}
		}
		return falg;
	}

	/**
	 * ��������#���
	 * @param tweet
	 * @return����һ�������е�#����hashtags��
	 */
	public static Set<String> getHashtags(Tweet tweet) {
		Set<String> result = new HashSet<String>();
		String[] text = tweet.getText().split(" ");
		String[] hashtags;
		for (String i : text) {
			if (!i.isEmpty()) {
				if (i.charAt(0) == '#') {
					hashtags = i.split("#");
					for (String j : hashtags) {
						if (!j.isEmpty()) {
							result.add(j);
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Find the people in a social network who have the greatest influence, in the
	 * sense that they have the most followers.
	 * 
	 * @param followsGraph a social network (as defined above)
	 * @return a list of all distinct Twitter usernames in followsGraph, in
	 *         descending order of follower count.
	 */
	public static List<String> influencers(Map<String, Set<String>> followsGraph) {

		List<String> result = new ArrayList<String>();
		Set<Map.Entry<String, Set<String>>> entries = followsGraph.entrySet();
		Iterator<Map.Entry<String, Set<String>>> iteratorMap = entries.iterator();
		Set<String> names;

		///ά��һ�����֡���@����������
		String[] name = new String[4000];
		int[] nums = new int[4000];
		int cnt = 0;
		int index = 0;
		///����߽� �������������
		int bounder = 0;

		///����followsGraph
		while (iteratorMap.hasNext()) {
			Map.Entry<String, Set<String>> next = iteratorMap.next();
			if (cnt == 0) {
				///��һ�ν���
				names = next.getValue();
				for (String i : names) {
					name[index] = i;
					nums[index] = 1;
					index++;
					bounder++;
					cnt++;
				}
			} else {
				/// names �Ǹ������غ��е��ἰ�����˵�����
				names = next.getValue();
				for (String i : names) {
					if (ifContain(name, i, bounder)) {
						///������˾��ҵ��Ǹ��˵Ķ�Ӧ���±�
						index = getIndex(name, i, bounder);
						///���ἰ��+1
						nums[index]++;
					} else {
						///���û�о��ٴ����µ�
						name[bounder] = i;
						nums[bounder] = 1;
						///ע��߽�+1
						bounder++;
					}
				}
			}
		}

		/// ���ϴ����õ�һ����String�����������б��ἰ�����˵����֡�
		/// int�������Ŷ�Ӧ���˵ı��ἰ�Ĵ���

		int i, j, temp;
		String tempString;
		for (j = 0; j < bounder - 1; j++) {
			for (i = 0; i < bounder - 1 - j; i++) {
				if (nums[i] < nums[i + 1]) { /// �Ӵ�С
					temp = nums[i];
					nums[i] = nums[i + 1];
					nums[i + 1] = temp;

					tempString = name[i];
					name[i] = name[i + 1];
					name[i + 1] = tempString;

				}
			}
		}

		/// ����

		/// �������
		for (int k = 0; k < bounder; k++) {
			result.add(name[k].toLowerCase());
		}

		/// ����

		return result;
	}

	/**
	 * �õ�ĳ���˵��ڼ����е��±�
	 * @param names
	 * @param name
	 * @param bounder
	 * @return �±�
	 */
	public static int getIndex(String[] names, String name, int bounder) {
		int index = -1;
		for (int i = 0; i < bounder; i++) {
			if (names[i].toLowerCase().equals(name.toLowerCase())) {
				index = i;
			}
		}
		return index;
	}

	/**
	 * �ڼ����������Ƿ���name
	 * @param names
	 * @param name
	 * @param bounder
	 * @return ���
	 */
	public static boolean ifContain(String[] names, String name, int bounder) {
		boolean flag = false;
		for (int i = 0; i < bounder; i++) {
			if (names[i].toLowerCase().equals(name.toLowerCase())) {
				flag = true;
			}
		}
		return flag;
	}

	///small test
	public static void main(String[] args) {

		Instant d1 = Instant.parse("2016-02-15T10:00:00Z");
		Instant d2 = Instant.parse("2016-02-16T11:00:00Z");
		Instant d3 = Instant.parse("2016-02-18T11:00:00Z");
		Instant d4 = Instant.parse("2016-02-19T11:00:00Z");
		Instant d5 = Instant.parse("2016-02-19T11:00:00Z");
		Instant d6 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d7 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d8 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d9 = Instant.parse("2016-02-14T11:00:00Z");
		Instant d10 = Instant.parse("2016-02-14T11:00:00Z");

		Tweet tweet1 = new Tweet(1, "a", "@b", d1);
		Tweet tweet2 = new Tweet(2, "b", "@a @c", d2);
		Tweet tweet3 = new Tweet(3, "c", "@b", d3);
		/// abc Ӧ�û����ע
		Tweet tweet4 = new Tweet(4, "d", "#abb", d4);
		Tweet tweet5 = new Tweet(5, "e", "#abb", d5);
		/// de �����ע
		Tweet tweet6 = new Tweet(6, "f", "@g", d6);
		Tweet tweet7 = new Tweet(7, "g", "RT @h: balabala", d7);
		Tweet tweet8 = new Tweet(8, "h", "balabala", d8);
		/// f follows g ///  g follows h /// g retweet h  -> f follows h
		Tweet tweet9 = new Tweet(9, "i", "@h", d9);
		Tweet tweet10 = new Tweet(10, "j", "@lc", d10);
		/// h > a/b/c > d/e/g > lc (i,j����follow)
		
		
		Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, 
				 tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10));
		// tweet4, tweet5, tweet6,
		// tweet7, tweet8, tweet9, tweet10)

		
		Set<Map.Entry<String, Set<String>>> entries = followsGraph.entrySet();
		Iterator<Map.Entry<String, Set<String>>> iteratorMap = entries.iterator();

		while (iteratorMap.hasNext()) {
			Map.Entry<String, Set<String>> next = iteratorMap.next();
			System.out.println(next.getKey());
			for(String i : next.getValue()) {
				System.out.println(i);
			}
		}
		

		System.out.println("\n");

		List<String> influence = new ArrayList<String>();

		influence = influencers(followsGraph);

		for (String i : influence) {
			System.out.print(i);
		}

	}

}
