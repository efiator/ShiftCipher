/*Author: Elizabeth Fiator(with assistance from Luis)
 * Version: March 8, 2016
 * Description: This is the class that returns the 
 * frequency and their indices.
 */
public class freq {
	float freq;
	int index;
public freq()
{
	this.freq=-1;//initializes the frequency
	this.index=-1;//initializes the index
}

//Sets the frequency and its index together
public void set(float x, int i)
{
	this.freq = x;//the calculated frequency
	this.index=i;//the index of the calculated frequency
}
}