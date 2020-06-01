package arithmetic;

import javax.swing.JOptionPane;

/**
 * Defines a method for calculating the cube of an integer.  The integer value to be cubed is input by the user.
 *
 * @version March 2020
 * @author Hugh Osborne
 */
public class Cube {
	/**
	 * Cube an integer.
	 * to do
	 * This method should be provided with a proof.
	 *
	 * @param n the number to be cubed.
	 * @return n cubed.
	 */
	public static int cube(int n) {
		  int cube = 0, threeNsquared = 0, threeN = 0;

		  int i = 0;
		  while (i < n) {
			  cube = cube + threeNsquared + threeN + 1;
			  threeNsquared = threeNsquared + 2*threeN + 3;
			  threeN = threeN + 3;
			  i++;
		  }
		  return cube;
	}

	/**
	 * Run the cube method.
	 *
	 * @param args (ignored)
	 */
	public static void main(String[] args) {
		int x = ReadInt.read("Please input a number to be squared");
		int result = cube(x);
		JOptionPane.showMessageDialog(null,x + "^3=" + result,"Function result",JOptionPane.INFORMATION_MESSAGE);
	}

}
