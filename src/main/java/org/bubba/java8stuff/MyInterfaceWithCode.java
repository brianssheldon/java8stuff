package org.bubba.java8stuff;

public interface MyInterfaceWithCode {
	public static String getStuff()
	{
		return "this interface has code in it";
	}
	
	default String getMoreStuff()
	{
		return "this interface has even more code in it";
	}
}

/* ***********************************************************
 *                                                           *
 * Copyright (C) 2015 The Hertz Corporation                  *
 * All Rights Reserved. (Unpublished.)                       *
 * The information contained herein is confidential and      *
 * proprietary to The Hertz Corporation and may not be       *
 * duplicated, disclosed to third parties, or used for any   *
 * purpose not expressly authorized by it.  Any unauthorized *
 * use, duplication or disclosure is prohibited by law.      *
 *                                                           *
 ************************************************************/