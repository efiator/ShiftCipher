/*
*Author: Elizabeth Fiator 
*Version: April 11, 2016
*Description and Background: The purpose of this project is 
*to generate a possible set of keys for an encrypted text with
*a shift cipher and use those keys to decrypt the text.
*The keys are generated based on the characters with the highest
*frequencies in the encrypted text and matching these possibilities
*with the characters that have the highest frequencies in the plain 
*text alphabets.
* 
*Modifications:
*Version: April 16, 2016
*Modification: Modified to use the set of generated keys to decrypt
*the encrypted text.
*Version: April 28, 2016
*Version: April 29, 2016
*Version: May 30, 2016
*/
import java.util.*;
import javax.swing.JOptionPane;

public class Shift
{
	public final static void main(String[]args)
	{
		String text="";
		//Gets the encrypted text from the user and generates a set of possible keys.
		String inputValue = JOptionPane.showInputDialog("Please input the encrypted text");
		inputValue=inputValue.toUpperCase();
		
		int [] k=Shift.key(inputValue);
		for(int i=0; i<k.length;i++)
		{
			text=Shift.decrypt(inputValue, k[i]);
			System.out.println("A possible decrypted text is: " +text);
		}
	}

	/*
	This method calculates the frequency of all letters within an encrypted text and
	stores it in an array.
	@Param: The parameter is the encrypted text
	*/
	public static float [] cfreq(String txt)
	{

		txt=txt.toUpperCase();
		float [] numCount = new float[26];
		int total=0;
		char b;	
		//iterates through the encrypted text to find how many times each character occurs
			for(int j=0; j<txt.length(); j++)
			{
				b=txt.charAt(j);
				if(Character.isLetter(b))
				{
					numCount[b-'A']++;
				}
			}
		//finds the total number of characters
		for(int i=0; i<numCount.length;i++)
		{
			total+=numCount[i];
		}
		//calculates the frequency of each character
		for(int i=0; i<numCount.length;i++)
		{
			numCount[i]=(numCount[i]/total)*100;
		}
		//Prints the corresponding letter with each alphabetic frequency
		for(int i=0; i<numCount.length;i++)
		{
			if(numCount[i]>0)
			{
				System.out.println("The frequency of "+ (char)(i+65)+" is "+numCount[i]);
			}
		}
		return numCount;
	}

	/*
	* This method returns the frequency of each character in the English language in alphabetic order
	*/
	public static float [] numOccur()
	{
		float [] frequency = {8.167f, 1.492f, 2.782f, 4.253f, 12.702f, 2.228f, 2.015f, 6.094f, 6.966f, 0.153f, 0.772f, 4.025f, 2.406f, 6.749f, 7.507f, 1.929f, 0.095f, 5.987f, 6.327f, 9.056f, 2.758f, 0.978f, 2.361f, 0.150f, 1.974f, 0.074f};
		return frequency;

	}
   /*
	*This sorts the frequency in ascending order using HeapSort and returns the corresponding indices
	*/
	public static int [] sort(float [] a)
	{
		int [] y = new int [a.length];//stores the index of the frequencies
		HeapSort h=new HeapSort();

		freq [] numFreq=new freq[a.length];
		//creates an array of frequencies
		for(int i=0; i<a.length;i++)
		{
			numFreq[i]=new freq();
		}
		//sets the right frequency to the right index
		for(int i=0; i<a.length;i++)
		{
			float x=a[i];
			numFreq[i].set(x,i);
		}

		//sorts the frequencies
		numFreq=h.heapsort(numFreq);

		for(int i=0;i<numFreq.length;i++)
		{
			if(numFreq[i].freq>=0)
			{
			y[i]=numFreq[i].index;
			//System.out.println("Frequency of "+(char)(numFreq[i].index+65)+ " is "+ numFreq[i].freq + " and it's index is "+numFreq[i].index);
			}
	    }
		System.out.println("The indices are "+Arrays.toString(y));
		return y;//returns the indices of the sorted frequencies
	}

	
	/*This calculates the possible keys of the encrypted text
	*/
	public static int [] key(String txt)
	{
		String ptxt="";
		txt=txt.toUpperCase();
		String inputValue1 = JOptionPane.showInputDialog("Please input a value");//gets  a value from the user
		int j= Integer.parseInt(inputValue1);//converts it into an int

		int [] pfreq=Shift.sort(numOccur());//sorts the frequency of of plaintext alphabets
		int []cfreq=Shift.sort(cfreq(txt));//sorts the frequency of encrypted text alphabets

		int x=pfreq.length-j;//calculates the final index of the range of frequencies the user wants
		int [] shiftKey = new int [pfreq.length-x];//creates an array that stores the possible keys
		int y=0; 
		for(int i=pfreq.length-1;i>=x;i--)
		{
			int num=0;
			num=(cfreq[i]-pfreq[i])%26;//calculate the key for each index
			if(num<0)
			{
				num=num+26;
			//System.out.println("The shift key is "+num+" and the character is "+(char)(num+65));
		    }
		    shiftKey[y]=num;//stores the key into an array
		    y++;    
		}
	  System.out.println("The possible keys are "+Arrays.toString(shiftKey));

		return shiftKey;
	}

   /*This method uses decrypts and encrypted text using the numerical value
   	* of the key that was used to decrypt it.
	*/
	  public static String encrypt(String ptxt, int k)
    {
        char c;
        String ctxt = "";
        String [] splitStr = ptxt.split(" "); //splits the decrypted text
        for(int j=0; j<splitStr.length;j++)
        {
            String string = splitStr[j];
            ctxt=ctxt+" ";  //properly spaces out the text
            for(int i=0; i<string.length(); i++)
            {
                c=string.charAt(i);
                if(Character.isLetter(c))

                {
                    ctxt = ctxt+(char) (((string.charAt(i)+k)%26)+65);
                }
            }
        }
        return ctxt;
    }
	public static String decrypt(String ctxt, int k)
	{
		char c;
		String ptxt = "";
		String [] splitStr = ctxt.split(" "); //splits the decrypted text
		for(int j=0; j<splitStr.length;j++)
		{
			String string = splitStr[j];
			ptxt=ptxt+" ";  //properly spaces out the text
			for(int i=0; i<string.length(); i++)
			{
				c=string.charAt(i);
				if(Character.isLetter(c))

				{
					ptxt = ptxt+(char) (((string.charAt(i)-k)%26)+65);
				}
			}
		}	
		return ptxt;
	}

}