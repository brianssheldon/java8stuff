package org.bubba.iec;

import static junit.framework.Assert.assertEquals;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class Iec_questions_1_2_3_Test 
{
    @Test
	public void testReverse_1()
	{
		char[] cc = {'a','b','c','d','e','f','g','h'};
		int x = 0;
		char hold;
		
		for(int i = cc.length -1; i >= cc.length / 2; i--)
		{
			hold = cc[i];
			cc[i] = cc[x];
			cc[x] = hold;
			x++;
		}
		
		String result = String.valueOf(cc);
		assertEquals("hgfedcba", result);
		
		assertEquals('h', cc[0]);
		assertEquals('a', cc[7]);
		assertEquals('d', cc[4]);
	}
	
    @Test
	public void testCountSubstrings_2()
	{
		String searchMe = "abcdabcdabcd";
		assertEquals(0, count("",""));
		assertEquals(0, count(null,null));
		assertEquals(0, count("",null));
		assertEquals(0, count(null,""));

		assertEquals(0, count(searchMe,"q"));
		assertEquals(3, count(searchMe,"a"));
		assertEquals(3, count(searchMe,"b"));
		assertEquals(3, count(searchMe,"c"));
		assertEquals(3, count(searchMe,"d"));
		assertEquals(3, count(searchMe,"ab"));
		assertEquals(3, count(searchMe,"bc"));
		assertEquals(3, count(searchMe,"cd"));
		assertEquals(3, count(searchMe,"abc"));
		assertEquals(3, count(searchMe,"abcd"));
	}
	
	int count(String searchMe, String searchForMe)
	{
//	this is what I would really do -	 return StringUtils.countMatches(searchMe, searchForMe);  
		
		if(StringUtils.isBlank(searchMe) 
			|| StringUtils.isEmpty(searchForMe)
			|| !searchMe.contains(searchForMe))
			return 0;
		
		int count = 0;
		
		while(searchMe.contains(searchForMe))
		{
			count ++;
			searchMe = searchMe.substring(searchMe.indexOf(searchForMe) + 1);
		}
		
		return count;
	}
	
    @Test
	public void testCountSubstringsRecursion_3()
	{
		String searchMe = "abcdabcdabcd";
		assertEquals(0, countRecursion("",""));
		assertEquals(0, countRecursion(null,null));
		assertEquals(0, countRecursion("",null));
		assertEquals(0, countRecursion(null,""));

		assertEquals(0, countRecursion(searchMe,"q"));
		assertEquals(3, countRecursion(searchMe,"a"));
		assertEquals(3, countRecursion(searchMe,"b"));
		assertEquals(3, countRecursion(searchMe,"c"));
		assertEquals(3, countRecursion(searchMe,"d"));
		assertEquals(3, countRecursion(searchMe,"ab"));
		assertEquals(3, countRecursion(searchMe,"bc"));
		assertEquals(3, countRecursion(searchMe,"cd"));
		assertEquals(3, countRecursion(searchMe,"abc"));
		assertEquals(3, countRecursion(searchMe,"abcd"));
	}
	
	static int kount = 0;
	
	int countRecursion(String searchMe, String searchForMe)
	{
		kount = 0;
		
		if(StringUtils.isBlank(searchMe) 
			|| StringUtils.isEmpty(searchForMe)
			|| !searchMe.contains(searchForMe))
			return 0;
		
		repeat(searchMe, searchForMe);
		
		return kount;
	}
	
	void repeat(String searchMe, String searchForMe)
	{
		searchMe = searchMe.substring(searchMe.indexOf(searchForMe) + 1);
		
		if(searchMe.contains(searchForMe))
			repeat(searchMe, searchForMe);
		kount++;
	}
}