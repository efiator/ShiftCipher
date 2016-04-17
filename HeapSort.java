/***
 * @author: Elizabeth Fiator 
 * @version: February 13, 2016
 * This is the class that actually sorts items in an array
 * using a heap sort algorithm.
 */
public class HeapSort {
	
	private static int heapsize;
	//density []A=new density();
	public freq [] heapsort(freq []A)
	{
		//int [] A is the pre-condition
		buildMaxHeap(A);//builds a max heap
		for(int i = heapsize; i>0; i--)//goes through the heap
		{
			//assert(Invariant(A, i));//initializes the invariant
			swap(A, 0,i);//swaps the first element and the element at the ith index
			heapsize = heapsize-1;//decrements the heap size and maintains the invariant
			maxHeapify(A,0);//calls maxHeapify on the the first element in the heap
		} 
		//the invariant terminates here 
	return A;//returns the post-condition as a permutation of the pre-condition
	}
	
	/*Builds a max heap*/
	public void buildMaxHeap(freq A [] )
	{
		heapsize = A.length-1;//sets heapsize to the size of the array
		for (int i = heapsize/2; i>=0; i--)
		{
			maxHeapify(A,i);//calls maxHeapify on every element
			
		}
		
	}
	
	/*Goes through the array A and checks to make sure the elements are not violating
	 the max heap property. That is, no child node can be bigger than a parent node*/
	public void maxHeapify(freq A [], int i)
	{
		int left = 2*i;//left node
		int right = 2*i+1;//right no de
		int largest=i;//sets i to be largest or parent
		
		//sets largest to the biggest element after comparing it to the  element oin the left
		if (left<=heapsize && A[left].freq > A[i].freq)
			{
			 	largest = left;
			}
			else
			{
				largest =i;
			}
		
		//sets largest to the biggest element after comparing it to the element on the right
		if(right<=heapsize && A[right].freq >A[largest].freq)
		{
			largest = right;
		}
		
		//sets largest to the right element if the parent node is not largest
		if(largest != i)
		{
			swap(A, i, largest);
			maxHeapify(A, largest);//calls maxHeapify on largest
			}
		
		}
	
	/*Swaps elements when called*/
	public void swap(freq A[], int i, int k)
	{
		freq temp = new freq();
		temp = A[i];
		A[i]=A[k];
		A[k]=temp;
	}
	
	/*Checks to see of the pre-condition is true and initalizes the invariant.
	 Returns false is any node is bigger than its parent*/
	public boolean Invariant(freq[] A, int i)
	{
		boolean result = true;
		int k =0;
		while(k<=i && result)
		{
			if(A[left(i)].freq >A[parent(i)].freq || A[right(i)].freq>A[ parent(i)].freq)
			{
				result = false;
			}
		}
		return result;
	}
	
	/*Returns the parent node*/
	public int parent(int i)
	{
		return (int)Math.floor(i/2);
	}
	
	/*Returns the left node*/
	public int left(int i)
	{
		return 2*i;
	}
	 
	/*Returns the right node*/
	public int right(int i)
	{
		return 2*i+1;
	}
	

}
