#Errored
array_code[0]="/* HelloWorld.java
 */

public class HelloWorld
{
	public static void main(String[] args) {
		System.out.println(\"Hello World!\");
	}
}"
array_code[1]="/* CallingMethodsInSameClass.java
 *
 * illustrates how to call static methods a class
 * from a method in the same class
 */

public class CallingMethodsInSameClass
{
	public static void main(String[] args) {
		printOne();
		printOne();
		printTwo();
	}

	public static void printOne() {
		System.out.println(\"Hello World\");
	}

	public static void printTwo() {
		printOne();
		printOne();
	}
}"
array_code[2] ="public class Factorial
{
	public static void main(String[] args)
	{	final int NUM_FACTS = 100;
		for(int i = 0; i < NUM_FACTS; i++)
			System.out.println( i + " " + factorial(i));
	}
	
	public static int factorial(int n)
	{	int result = 1;
		for(int i = 2; i <= n; i++)
			result *= i;
		return result;
	}
}" 
array_name[3]="public class EnhancedFor
{
	public static void main(String[] args)
	{	int[] list ={1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		int sum = sumListEnhanced(list);
		System.out.println(\"Sum of elements in list: \" + sum);

		System.out.println(\"Original List\");
		printList(list);
		System.out.println(\"Calling addOne\");
		addOne(list);
		System.out.println(\"List after call to addOne\");
		printList(list);
		System.out.println(\"Calling addOneError\");
		addOneError(list);
		System.out.println(\"List after call to addOneError. Note elements of list did not change.\");
		printList(list);
	}

	// pre: list != null
	// post: return sum of elements
	// uses enhanced for loop
	public static int sumListEnhanced(int[] list)
	{	int total = 0;
		for(int val : list)
		{	total += val;
		}
		return total;
	}

	// pre: list != null
	// post: return sum of elements
	// use traditional for loop
	public static int sumListOld(int[] list)
	{	int total = 0;
		for(int i = 0; i < list.length; i++)
		{	total += list[i];
			System.out.println( list[i] );
		}
		return total;
	}

	// pre: list != null
	// post: none.
	// The code appears to add one to every element in the list, but does not
	public static void addOneError(int[] list)
	{	for(int val : list)
		{	val = val + 1;
		}
	}

	// pre: list != null
	// post: adds one to every element of list
	public static void addOne(int[] list)
	{	for(int i = 0; i < list.length; i++)
		{	list[i]++;
		}
	}

	public static void printList(int[] list)
	{	System.out.println(\"index, value\");
		for(int i = 0; i < list.length; i++)
		{	System.out.println(i +  + list[i]);
		}
	}



}"
array_name[4] = "public class PrimitiveParameters
{	
	public static void main(String[] args)
	{	go();
	}
	
	public static void go()
	{	int x = 3;
		int y = 2;
		System.out.println(\"In method go. x: \" + x + \" y: \" + y);
		falseSwap(x,y);
		System.out.println(\"in method go. x: \" + x + \" y: \" + y);
		moreParameters(x,y);
		System.out.println(\"in method go. x: \" + x + \" y: \" + y);
	}
	
	public static void falseSwap(int x, int y)
	{	System.out.println(\"in method falseSwap. x: \" + x + \" y: \" + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println(\"in method falseSwap. x: \" + x + \" y: \" + y);
	}
	
	public static void moreParameters(int a, int b)
	{	System.out.println(\"in method moreParameters. a: \" + a + \" b: \" + b);
		a = a * b;
		b = 12;
		System.out.println(\"in method moreParameters. a: \" + a + \" b: \" + b);
		falseSwap(b,a);
		System.out.println(\"in method moreParameters. a: " " b: " + "b\");	
	}
}"
array_name[5] = "public class StringExample
{	public static void main(String[] args)
	{	String s1 = \"Computer Science\";
		int x = 307;
		String s2 = s1 + " " + x;
		String s3 = s2.substring(10,17);
		String s4 = \"is fun\";
		String s5 = s2 + s4;
		

		
		//showing effect of precedence
		
		x = 3;
		int y = 5;
		String s6 = x + y + \"total\";
		String s7 = \"total \" + x + y;
		String s8 = " " + x + y + \"total\";
		System.out.println(\"s6: \" + s6);
		System.out.println(\"s7: \" + s7);
		System.out.println(\"s8: \" + s8);
	}
}"
array_code[6] = "
public class BinaryConverter {
    
    public static void main(String[] args){
        for(int i = -5; i < 33; i++){
            System.out.println(i + ": " + toBinary(i));
            System.out.println(i);
            //always another way
            System.out.println(i + ": " + Integer.toBinaryString(i));
        }
    }
    
    /*
     * pre: none
     * post: returns a String with base10Num in base 2
     */
    public static String toBinary(int base10Num){
        boolean isNeg = base10Num < 0;
        base10Num = Math.abs(base10Num);        
        String result = "";
        
        while(base10Num > 1){
            result = (base10Num % 2) + result;
            base10Num /= 2;
        }
        assert base10Num == 0 || base10Num == 1 : \"value is not <= 1: \" + base10Num;
        
        result = base10Num + result;
        assert all0sAnd1s(result);
        
        if( isNeg )
            result = "-" + result;
        return result;
    }
    
    /*
     * pre: cal != null
     * post: return true if val consists only of characters 1 and 0, false otherwise
     */
    public static boolean all0sAnd1s(String val){
        assert val != null : \"Failed precondition all0sAnd1s. parameter cannot be null\";
        boolean all = true;
        int i = 0;
        char c;
        
        while(all && i < val.length()){
            c = val.charAt(i);
            all = c == '0' || c == '1';
            i++;
        }
        return all;
    }
}"
array_code[7] = "import java.math.BigInteger;
import java.util.Random;

public class PrimeEx {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		printTest(10, 4);
		printTest(2, 2);
		printTest(54161329, 4);
		printTest(1882341361, 2);
		printTest(36, 9);

		System.out.println(isPrime(54161329) + \" expect false\");
		System.out.println(isPrime(1882341361) + \"expect true\");
		System.out.println(isPrime(2) + \" expect true\");
		int numPrimes = 0;
		Stopwatch s = new Stopwatch();
		s.start();
		for(int i = 2; i < 10000000; i++) {
			if(isPrime(i)) {
				numPrimes++;
			}
		}
		s.stop();
		System.out.println(numPrimes + " " + s);
		s.start();
		boolean[] primes = getPrimes(10000000);
		int np = 0;
		for(boolean b : primes)
			if(b)
				np++;
		s.stop();
		System.out.println(np + " " + s);

		System.out.println(new BigInteger(1024, 10, new Random()));
	}

	public static boolean[] getPrimes(int max) {
		boolean[] result = new boolean[max + 1];
		for(int i = 2; i < result.length; i++)
			result[i] = true;
		final double LIMIT = Math.sqrt(max);
		for(int i = 2; i <= LIMIT; i++) {
			if(result[i]) {
				// cross out all multiples;
				int index = 2 * i;
				while(index < result.length){
					result[index] = false;
					 index += i;
				}
			}
		}
		return result;
	}


	public static void printTest(int num, int expectedFactors) {
		Stopwatch st = new Stopwatch();
		st.start();
		int actualFactors = numFactors(num);
		st.stop();
		System.out.println(\"Testing \" + num + \" expect \" + expectedFactors + \", \" +
				\"actual \" + actualFactors);
		if(actualFactors == expectedFactors)
			System.out.println(\"PASSED\");
		else
			System.out.println(\"FAILED\");
		System.out.println(st.time());
	}

	// pre: num >= 2
	public static boolean isPrime(int num) {
		assert num >= 2 :   + num;
		final double LIMIT = Math.sqrt(num);
		boolean isPrime = (num == 2) ? true : num % 2 != 0;
		int div = 3;
		while(div <= LIMIT && isPrime) {
			isPrime = num % div != 0;
			div += 2;
		}
		return isPrime;
	}

	// pre: num >= 2
	public static int numFactors(int num) {
		assert num >= 2 : + num;
		int result = 0;
		final double SQRT = Math.sqrt(num);
		for(int i = 1; i < SQRT; i++) {
			if(num % i == 0) {
				result += 2;
			}
		}
		if(num % SQRT == 0)
			result++;
		return result;
	}

}"
array_code[8] = "import java.awt.Rectangle;

public class ObjectVarsAsParameters
{	public static void main(String[] args)
	{	go();
	}
	
	public static void go()
	{	Rectangle r1 = new Rectangle(0,0,5,5);
		System.out.println("'In method go. r1 '" + r1 + "\n");
		// could have been 
		//System.out.prinltn(\"r1\" + r1.toString());
		r1.setSize(10, 15);
		System.out.println("'In method go. r1' " + r1 + "\n");
		alterPointee(r1);
		System.out.println("'In method go. r1' " + r1 + "\n");
		
		alterPointer(r1);
		System.out.println("'In method go. r1 '" + r1 + "\n");
	}
	
	public static void alterPointee(Rectangle r)
	{	System.out.println("'n method alterPointee. r' " + r + "\n");
		r.setSize(20, 30);
		System.out.println("'In method alterPointee. r '" + r + "\n");
	}
	
	public static void alterPointer(Rectangle r)
	{	System.out.println("'In method alterPointer. r' " + r + "\n");
		r = new Rectangle(5, 10, 30, 35);
		System.out.println("'In method alterPointer. r' " + r + "\n");
	}
	
	
}"
array_code[9] = "//Mike Scott
//examples of array manipulations

public class ArrayExamples
{	public static void main(String[] args)
	{	int[] list = {1, 2, 3, 4, 1, 2, 3};
		findAndPrintPairs(list, 5);
		bubblesort(list);
		showList(list);

		list = new int[]{ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		bubblesort(list);
		showList(list);

		list = new int[]{11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0, -1, -2};
		bubblesort(list);
		showList(list);

		list = new int[]{1};
		bubblesort(list);
		showList(list);
	}


	// pre: list != null, list.length > 0
	// post: return index of minimum element of array
	public static int findMin(int[] list)
	{	assert list != null && list.length > 0 : ;

		int indexOfMin = 0;
		for(int i = 1; i < list.length; i++)
		{	if(list[i] < list[indexOfMin])
			{	indexOfMin = i;
			}
		}

		return indexOfMin;
	}


	/*
	 *pre: list != null, newSize >= 0
	 *post: nothing. the method does not succeed it resizing the
	 * argument
	 */
	public static void badResize(int[] list, int newSize)
	{	assert list != null && newSize >= 0 : " 'failed precondition'";

		int[] temp = new int[newSize];
		int limit = Math.min(list.length, newSize);

		for(int i = 0; i < limit; i++)
		{	temp[i] = list[i];
		}

		// uh oh!! Changing pointer, not pointee. This breaks the
		// relationship between the parameter and argument
		list = temp;
	}


	/*
	 *pre: list != null, newSize >= 0
	 *post: returns an array of size newSize. Elements from 0 to newSize - 1
	 *	will be copied into the new array
	 */
	public static int[] goodResize(int[] list, int newSize)
	{	assert list != null && newSize >= 0 : failed precondition;

		int[] result = new int[newSize];
		int limit = Math.min(list.length, newSize);

		for(int i = 0; i < limit; i++)
		{	result[i] = list[i];
		}

		return result;
	}


	/*
	 *pre: list != null
	 *post: prints out the indices and values of all pairs of numbers
	 *in list such that list[a] + list[b] = target
	 */
	public static void findAndPrintPairs(int[] list, int target)
	{	assert list != null : "'failed precondition'";

		for(int i = 0; i < list.length; i++)
		{	for(int j = i + 1; j < list.length; j++)
			{	if(list[i] + list[j] == target)
				{	System.out.println("'The two elements at indices' " + i + " 'and '" + j
						+ " 'are '" + list[i] + " 'and' " + list[j] + " 'add up to' " + target);
				}
			}
		}
	}


	/*
	 *pre: list != null;
	 *post: sort the elements of list so that they are in ascending order
	 */
	public static void bubblesort(int[] list)
	{
		assert list != null : "'failed precondition'";

		int temp;
		boolean changed = true;
		for(int i = 0; i < list.length && changed; i++)
		{	changed = false;
			for(int j = 0; j < list.length - i - 1; j++)
			{	assert (j > 0) && (j + 1 < list.length) : "'loop counter j " + j +
					"is out of bounds'.";
				if(list[j] > list[j+1])
				{	changed = true;
					temp = list[j + 1];
					list[j + 1] = list[j];
					list[j] = temp;
				}
			}
		}

		assert isAscending( list );
	}


	public static void showList(int[] list)
	{	for(int i = 0; i < list.length; i++)
			System.out.print( list[i] + " " );
		System.out.println();
	}

	/* 	pre: list != null
		post: return true if list is sorted in ascedning order, false otherwise
	*/
	public static boolean isAscending( int[] list )
	{	boolean ascending = true;
		int index = 1;
		while( ascending && index < list.length )
		{	assert index >= 0 && index < list.length;

			ascending = (list[index - 1] <= list[index]);
			index++;
		}

		return ascending;
	}
}"
array_code[13] = "// Mike Scott
// 2d array manipulation examples

//import
import java.awt.Color;


public class FilterExample
{
	/*
	 *pre: image != null, image.length > 1, image[0].length > 1
	 *	image is a rectangular matrix, neighberhoodSize > 0
	 *post: return a smoothed version of image
	 */
	public Color[][] smooth(Color[][] image, int neighberhoodSize)
	{	//check precondition
		assert image != null && image.length > 1 && image[0].length > 1
				&& ( neighberhoodSize > 0 ) && rectangularMatrix( image )
				: "'Violation of precondition: smooth'";

		Color[][] result = new Color[image.length][image[0].length];

		for(int row = 0; row < image.length; row++)
		{	for(int col = 0; col < image[0].length; col++)
			{	result[row][col] = aveOfNeighbors(image, row, col, neighberhoodSize);
			}
		}

		return result;
	}


	// helper method that determines the average color of a neighberhood
	// around a particular cell.
	private Color aveOfNeighbors(Color[][] image, int row, int col, int neighberhoodSize)
	{	int numNeighbors = 0;
		int red = 0;
		int green = 0;
		int blue = 0;

		for(int r = row - neighberhoodSize; r <= row + neighberhoodSize; r++)
		{	for(int c = col - neighberhoodSize; c <= col + neighberhoodSize; c++)
			{	if( inBounds( image, r, c ) )
				{	numNeighbors++;
					red += image[r][c].getRed();
					green += image[r][c].getGreen();
					blue += image[r][c].getBlue();
				}
			}
		}

		assert numNeighbors > 0;
		return new Color( red / numNeighbors, green / numNeighbors, blue / numNeighbors );
	}

	//helper method to determine if given coordinates are in bounds
	private boolean inBounds(Color[][] image, int row, int col)
	{	return (row >= 0) && (row <= image.length) && (col >= 0)
				&& (col < image[0].length);
	}

	//private method to ensure mat is rectangular
	private boolean rectangularMatrix( Color[][] mat )
	{	boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;

		while( isRectangular && row < mat.length )
		{	isRectangular = ( mat[row].length == COLUMNS );
			row++;
		}

		return isRectangular;
	}
}"
array_code[10] = "import java.util.Scanner;

public class Life {
    public static void show(boolean[][] grid){
        String s = "";
        for(boolean[] row : grid){
            for(boolean val : row)
                if(val)
                    s += "'*'";
                else
                    s += "'.'";
            s += "\n";
        }
        System.out.println(s);
    }
    
    public static boolean[][] gen(){
        boolean[][] grid = new boolean[10][10];
        for(int r = 0; r < 10; r++)
            for(int c = 0; c < 10; c++)
                if( Math.random() > 0.7 )
                    grid[r][c] = true;
        return grid;
    }
    
    public static void main(String[] args){
        boolean[][] world = gen();
        show(world);
        System.out.println();
        world = nextGen(world);
        show(world);
        Scanner s = new Scanner(System.in);
        while(s.nextLine().length() == 0){
            System.out.println();
            world = nextGen(world);
            show(world);
            
        }
    }
    
    public static boolean[][] nextGen(boolean[][] world){
        boolean[][] newWorld 
            = new boolean[world.length][world[0].length];
        int num;
        for(int r = 0; r < world.length; r++){
            for(int c = 0; c < world[0].length; c++){
                num = numNeighbors(world, r, c);
                if( occupiedNext(num, world[r][c]) )
                    newWorld[r][c] = true;
            }
        }
        return newWorld;
    }
    
    public static boolean occupiedNext(int numNeighbors, boolean occupied){
        if( occupied && (numNeighbors == 2 || numNeighbors == 3))
            return true;
        else if (!occupied && numNeighbors == 3)
            return true;
        else
            return false;
    }

    private static int numNeighbors(boolean[][] world, int row, int col) {
        int num = world[row][col] ? -1 : 0;
        for(int r = row - 1; r <= row + 1; r++)
            for(int c = col - 1; c <= col + 1; c++)
                if( inbounds(world, r, c) && world[r][c] )
                    num++;

        return num;
    }

    private static boolean inbounds(boolean[][] world, int r, int c) {
        return r >= 0 && r < world.length && c >= 0 &&
        c < world[0].length;
    }
    
    
    
}"
array_code[11] = "import java.util.Scanner;

public class ScannerAndKeyboard
{

	public static void main(String[] args)
	{	Scanner s = new Scanner(System.in);
		System.out.print( "'Enter your name: '"  );
		String name = s.nextLine();
		System.out.println( "'Hello'" + name + "'!'" );
	}
}"
array_code[12] = "import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class ReadAndPrintScores
{
    public static void main(String[] args)
    {	try
	{   Scanner s = new Scanner( new File("'scores.dat'") );
	    while( s.hasNextInt() )
	    {	System.out.println( s.nextInt() );
	    }
	}
	catch(IOException e)
	{	System.out.println( e );
	}
    }
}"
array_name[0] = "Hello world"
array_name[1] = "StringConcat"
array_name[2] = "ForLoop"
array_name[3] = "Enhance Loop"

array_name[4] ="ValueParameter"
array_name[5] = "StringEx"
array_name[6] = "BinaryConverter"
array_name[7] = "Prime"
array_name[8] = "Pointer"
array_name[9] = "Array"
array_name[10] = "2DArray"
array_name[11] = "PointerArray"
array_name[12] = "StickArray"
array_name[13] = "Stack"

max=13
for (( i=0; i <= $max; ++i ))
do
	for((j=0; j <= 8; ++j ))
	do
		touch "Activity"+"$array_name[$i]"+"Question[$j].java"
    		echo "$array_code[$i]"+"$array_code[$j]">"Activity"+"$array_name[$i]"+"Question[$j].java"
		git add .
		git commit --date=$i/$j/2017 -m "Activity"+"$array_name[$i]"+"Question[$j].java"
	done
done
git push --all


git filter-branch --env-filter \
    'if [ $GIT_COMMIT = 119f9ecf58069b265ab22f1f97d2b648faf932e0 ]
     then
         export GIT_AUTHOR_DATE="Fri Jan 2 21:38:53 2009 -0800"
         export GIT_COMMITTER_DATE="Sat May 19 01:01:01 2007 -0700"
     fi'



