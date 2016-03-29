package org.bubba.java8stuff;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import static junit.framework.Assert.assertEquals;
import org.junit.Test;

public class JunkTest
{

	public int sumAll(List<Integer> numbers, Predicate<Integer> p)
	{
		int total = 0;
		total = numbers.stream().filter((number) -> (p.test(number))).map((number) -> number).reduce(total, Integer::sum);
		return total;
	}

	@Test
	public void test()
	{
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

		assertEquals(21, sumAll(numbers, n -> true));
		assertEquals(12, sumAll(numbers, n -> n % 2 == 0));
		assertEquals(9, sumAll(numbers, n -> n % 3 == 0));
		assertEquals(15, sumAll(numbers, n -> n > 3));
		assertEquals(8, sumAll(numbers, n -> n == 3 || n == 5));
	}
// --------------------------------------------------

	@Test
	public void test3()
	{
		List<Person> pl = Person.createShortList();

		SearchCriteria search = SearchCriteria.getInstance();

		// Calc average age of pilots old style
		System.out.println(
			"== Calc Old Style ==");
		int sum = 0;
		int count = 0;

		for(Person p : pl)
		{
			if(p.getAge() >= 23 && p.getAge() <= 65)
			{
				sum = sum + p.getAge();
				count++;
			}
		}

		long average = sum / count;

		System.out.println("Total Ages: " + sum);
		System.out.println("Average Age: " + average);

		// Get sum of ages
		System.out.println("\n== Calc New Style ==");

		long totalAge = pl
			.stream()
			.filter(search.getCriteria("allPilots"))
			.mapToInt(p -> p.getAge())
			.sum();

		// Get average of ages
		OptionalDouble averageAge = pl
			.parallelStream()
			.filter(search.getCriteria("allPilots"))
			.mapToDouble(p -> p.getAge())
			.average();

		System.out.println(
			"Total Ages: " + totalAge);
		System.out.println(
			"Average Age: " + averageAge.getAsDouble());

	}

	@Test
	public void test4_sort()
	{
		List<String> sl = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

		Collections.sort(sl, (String p1, String p2) -> p2.compareTo(p1));

		sl.forEach(x -> System.err.print(x));
		System.err.println();

	}

	@Test
	public void test4_sort_reversed()
	{
		List<String> sl = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

		Comparator<String> c = (String p1, String p2) -> p1.compareTo(p2);
		sl.sort(c.reversed());  // c.reversed

		sl.forEach(x -> System.err.print(x));
		System.err.println();

	}

	@Test
	public void test4_reverse()
	{
		Consumer<String> b = q -> System.err.println(q);
		b.accept("abcdefghij");

		MyFunctionalInterface mfi = () -> System.err.println("mfi has run");
		mfi.doSomeWorkButYouProvideTheCodeToDoIt();
		methodToRunMfi(mfi);

		MyTwoParmFunctionalInterface mtpfi = (x, y) -> System.err.println("'" + x + "'  '" + y + "'");
		mtpfi.doSomethingButPassMeStuff(3, "qrst");
		methodToRunMtpfi(mtpfi, 2, "asdf");

		MyFunctionalInterface mmffii = () ->
		{
			for(int i = 0; i < 5; i++)
			{
				mfi.doSomeWorkButYouProvideTheCodeToDoIt();
			}
		};
		mmffii.doSomeWorkButYouProvideTheCodeToDoIt();

		System.err.println("============");
		MyFunctionalInterface mmmfffii = () ->
		{
			for(int i = 0; i < 2; i++)
			{
				System.err.println(i);
				mmffii.doSomeWorkButYouProvideTheCodeToDoIt();
			}
		};

		mmmfffii.doSomeWorkButYouProvideTheCodeToDoIt();
		System.err.println("============");
	}

	public void methodToRunMtpfi(MyTwoParmFunctionalInterface mtpfi, int x, String y)
	{
		mtpfi.doSomethingButPassMeStuff(x, y);
	}

	public void methodToRunMfi(MyFunctionalInterface mfi)
	{
		mfi.doSomeWorkButYouProvideTheCodeToDoIt();
	}

	@Test
	public void test5() throws Exception
	{
		System.out.println("b4");
		Callable x = () ->
		{
			System.out.println("sleeeeppppp");
			Thread.sleep(3000);
			return null;
		};
		System.out.println("after 1");
		x.call();
		System.out.println("after 2");

	}

	@Test
	public void test6() throws Exception
	{
		System.out.println("------1-------");
		List<String> list = new ArrayList();
		for(int i = 0; i < 10; i++)
		{
			list.add("aa" + i);
		}
		Stream stream = list.stream();
		stream.forEach(System.err::println);
		System.out.println("------2-------");
		list.forEach(x -> System.err.println(x));
		System.out.println("------3-------");
		list.forEach(System.err::println);
		System.out.println("------4-------");
	}

	@Test
	public void test7() throws Exception
	{
		System.out.println("------1------- " + MyInterfaceWithCode.getStuff());

		MyInterfaceWithCode x = new ExtendsMyInterfaceWithCode();
		System.out.println("------2------- " + x.getMoreStuff());
	}

	@Test
	public void test8()
	{
		System.out.println("------1------- ");
	}

	public <X, Y> void processStuff(
		Iterable<X> source,
		Predicate<X> tester,
		Function<X, Y> mapper,
		Consumer<Y> block)
	{
		for(X p : source)
		{
			if(tester.test(p))
			{
				Y data = mapper.apply(p);
				block.accept(data);
			}
		}
	}

	@Test
	public void test9()
	{
		String string = "abcdefghij";
		List<String> sl = new ArrayList<>();

		for(int i = 0; i < string.length(); i++)
		{
			sl.add("" + string.substring(i, i + 1));
		}

		processStuff(
			sl,
			p -> p.equals("b") || p.equals("e") || p.equals("a"),
			p -> p + "x" + p.toUpperCase() + p.toLowerCase() + p.hashCode(),
			email -> System.err.println("made it " + email)
		);

		sl.stream()
			.filter(p -> p.equals("j") || p.equals("c") || p.equals("f"))
			.map(p -> p + "x" + p.toUpperCase() + p.toLowerCase() + p.hashCode())
			.forEach(email -> System.err.println("made it " + email));
	}

	@Test
	public void test10()
	{
		List<String> sl = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");

		sl.stream()
			.map(p -> p + "x" + p.toUpperCase() + p.toLowerCase() + p.hashCode())
			.forEach(email -> System.err.println("all " + email));

		sl.stream()
			.filter(p -> p.equals("j") || p.equals("c") || p.equals("f"))
			.forEach(email -> System.err.println("made it  just letters" + email));

		sl.stream()
			.filter(p -> p.equals("j") || p.equals("c") || p.equals("f"))
			.map(p -> p + "x" + p.toUpperCase() + p.toLowerCase() + p.hashCode())
			.forEach(email -> System.err.println("made it " + email));
	}

	@Test
	public void test11()
	{
		Map<String, MyFunctionalInterfaceTwoParmsAndReturnsSomething> map = new HashMap();
		Map<String, BiFunction> map2 = new HashMap();

		MyFunctionalInterfaceTwoParmsAndReturnsSomething add = (a, b) -> a + b;
		MyFunctionalInterfaceTwoParmsAndReturnsSomething subtract = (a, b) -> a - b;
		MyFunctionalInterfaceTwoParmsAndReturnsSomething multiply = (a, b) -> a * b;
		MyFunctionalInterfaceTwoParmsAndReturnsSomething divide = (a, b) -> a / b;

		map.put("add", add);
		map.put("subtract", subtract);
		map.put("multiply", multiply);
		map.put("divide", divide);

		BiFunction<Integer, Integer, Integer> bi = (a, b) -> a + b;

		map2.put("add2", bi);

		map.put("add2", (a, b) -> a + b);
		map.put("subtract2", (a, b) -> a - b);
		map.put("multiply2", (a, b) -> a * b);
		map.put("divide2", (a, b) -> a / b);

		map.put("dostuff", (a, b) -> (a * b) - a);

		map.put("remainder", (int a, int b) ->
		{
			int ttl = 0;
			int x = 0;
			while(ttl + b <= a)
			{
				ttl = add.doit(ttl, b);
				x = add.doit(x, 1);
			}

			return a - (x * b);
		});

		assertEquals(11, map.get("add").doit(5, 6));
		assertEquals(-1, map.get("subtract").doit(5, 6));
		assertEquals(30, map.get("multiply").doit(5, 6));
		assertEquals(8, map.get("divide").doit(50, 6));

		assertEquals(11, map.get("add2").doit(5, 6));
		assertEquals(-1, map.get("subtract2").doit(5, 6));
		assertEquals(30, map.get("multiply2").doit(5, 6));
		assertEquals(8, map.get("divide2").doit(50, 6));

		assertEquals(250, map.get("dostuff").doit(50, 6));
		assertEquals(2, map.get("remainder").doit(50, 6));
	}

	@Test
	public void test12()
	{
		Map<String, BiFunction> map = new HashMap();

		BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
		BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
		BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
		BiFunction<Integer, Integer, Integer> divide = (a, b) -> a / b;
		BiFunction<Integer, Integer, Integer> remainder = (a, b) -> a % b;
		BiFunction<Integer, Integer, Integer> remainder2 = (a, b) ->
		{
			int ttl = 0;
			int x = 0;
			while(ttl + b <= a)
			{
				ttl = add.apply(ttl, b);
				x = add.apply(x, 1);
			}

			return a - (x * b);
		};

		map.put("add", add);
		map.put("subtract", subtract);
		map.put("multiply", multiply);
		map.put("divide", divide);
		map.put("remainder", remainder);
		map.put("remainder2", remainder2);

		assertEquals(11, map.get("add").apply(5, 6));
		assertEquals(-1, map.get("subtract").apply(5, 6));
		assertEquals(30, map.get("multiply").apply(5, 6));
		assertEquals(8, map.get("divide").apply(50, 6));
		assertEquals(2, map.get("remainder").apply(50, 6));
		assertEquals(2, map.get("remainder2").apply(50, 6));
	}

	@Test
	public void test13()
	{
		List<BiFunction> list = new ArrayList();

		BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
		BiFunction<Integer, Integer, Integer> subtract = (a, b) -> a - b;
		BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
		BiFunction<Integer, Integer, Integer> divide = (a, b) -> a / b;
		BiFunction<Integer, Integer, Integer> remainder = (a, b) -> a % b;
		BiFunction<Integer, Integer, Integer> remainder2 = (a, b) ->
		{
			int ttl = 0;
			int x = 0;
			while(ttl + b <= a)
			{
				ttl = add.apply(ttl, b);
				x = add.apply(x, 1);
			}

			return a - (x * b);
		};

		list.add(add);
		list.add(subtract);
		list.add(multiply);
		list.add(divide);
		list.add(remainder);
		list.add(remainder2);

		for(BiFunction func : list)
		{
			System.err.println("list: " + func.apply(5, 6));
		}
	}

	@Test
	public void test14()
	{
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		IntSummaryStatistics stats = numbers
			.stream()
			.mapToInt((x) -> x)
			.summaryStatistics();

		System.out.println("Highest number in List : " + stats.getMax());
		System.out.println("Lowest number in List : " + stats.getMin());
		System.out.println("Sum of all numbers : " + stats.getSum());
		System.out.println("Average of all numbers : " + stats.getAverage());
		
		System.err.println("toString: " + stats.toString()); // toString: IntSummaryStatistics{count=10, sum=55, min=1, average=5.500000, max=10}
	}
	
	@Test
	public void test15()
	{
		System.err.println("" + Date.from(Clock.systemDefaultZone().instant()).toString());	
	}
	
	@Test
	public void test16()
	{
		System.err.println("----16----");
		List<Person> list = new ArrayList();
		for(int i = 0; i < 20; i++)
		{
			list.add(getPerson(i));
		}
		
		int agetest = 19;
		
		list.stream()
			.filter( p -> Gender.MALE == p.getGender() && p.getAge() > agetest)
			.forEach(p -> System.err.println(p));
		
		System.err.println("----end of 16----");
	}
	
	Person getPerson(int i)
	{
		Gender g = (i % 2 == 0) ? Gender.FEMALE : Gender.MALE;
		
		return new Person.Builder()
			.surName("aaa" + i + "aaa")
			.givenName("bbb" + i + "bbb")
			.address("123" + i + " address")
			.age(i + 11)
			.email("yoda@starwars" + i + ".space")
			.gender(g)
			.phoneNumber("666" + i +"6666")
			.build();
	}
}
