package com.algo.sort;

/*
Shell Sort
<ul>
	<li>Developed by Donald L. Shell in 1959</li>
	<li>Based on the insertion sort but adds a new feature that dramatically improves the insertion sort's performance</li>
	<li>Faster than O(N^2) algorithms and easy to implement</li>
	<li>In Insertion sort, to move a small item sitting on far right to its proper place on the left requires moving all the intervening items. This step takes close to N copies just for one item. On averate item must be moved N/2 spaces which takes N times N/2 shifts for a total of N^2/2 copies. The performance could be improved if we could somehow move a smaller item many spaces to the left without shifting all the intermediate items individually, an approach taken by ShellSort. </li>
	<li>Shell sort applies insertion sort approach on elements at various gaps and keeps on reducing the gap.</li>
	<li><a href="http://www.youtube.com/watch?v=qzXAVXddcPU">Animation</a>
</ul>
*/
public class ShellSort extends AbstractSort{
	public String name(){
		return "Shell Sort";
	}
	
	public String description(){
		return "Worst case time complexity depends on the gap sequence. \n In the worst case its O(N^2). If the gap sequence is \n based on Knuth formula (3^k-1) then it is O(N^3/2);\n Space Complexity: O(1)";
	} 
	
	public void sort(Comparable[] array){
		this.elements = array;
		if(!isValid()) return;
		
		int length = elements.length;
		
		//Calculate Gap
		int gap = 1;
		while(gap < length/3) gap = 3*gap + 1;

		//Shell sort
		while(gap > 0){
			for(int pointer=gap; pointer < length; pointer++){
				Comparable value = elements[pointer];
				int j = pointer;
				for(; j >=gap  && elements[j-gap].compareTo(value) > 0; j-=gap)
					elements[j] = elements[j-gap];
				elements[j] = value;
			}
			gap = (gap-1)/3;
		}

		return ;
	}
}