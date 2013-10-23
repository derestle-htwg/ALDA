package a1;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SortedArrayDictionary<Integer,Integer> myArr = new SortedArrayDictionary<Integer,Integer>();
		
		myArr.Insert(1, 1);
		myArr.Insert(2, 2);
		myArr.Insert(5, 5);
		myArr.Insert(0, 0);
		myArr.Insert(4, 4);
		myArr.Insert(3, 3);
		System.out.println(myArr.toString());
	}

}
