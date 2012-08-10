# load "/Users/ragrawal/Desktop/sort.rb"
require_relative 'debug'

module Sort
 include Debug
 
 def Sort.Swap(input, i, j)
    tmp = input[i]
    input[i] = input[j]
    input[j] = tmp
  
  end
  
  def Sort.BubbleSort(input)
  	length = input.length-1
  	puts input.inspect
	
  	(0..length-1).each do |i|
  		(0..length-1-i).each do |j|
  			if(input[j]>input[j+1])
  				Sort.Swap(input, j, j+1)
  			end
  		end
  	end
  	return input
  end
  
  def Sort.InsertionSort(input)
    lastIndex = input.length-1

    
    (1..lastIndex).each do |i|
        j = i-1
        pivot = input[i]
        while (j>=0 and input[j] > pivot)
            input[j+1] = input[j]
            j = j-1
        end
        input[j+1] = pivot  
    end
    return input
  end
  
  def Sort.SelectionSort(input)
    lastIndex = input.length-1
    (0..lastIndex-1).each do |i|
        min = i
        (i+1..lastIndex).each do |j|
           min = j if(input[j] < input[min])  
        end
        Sort.Swap(input, i, min) if(i != min)
        puts "i = #{i}, min=#{min}; #{input.inspect}" if @@debug
    end
    return input
  end
  
  def Sort.QuickSort(input, lower, upper)
    if(lower < upper)
        pivot = Sort.Partition(input, lower, upper)
        Sort.QuickSort(input, lower, pivot)
        Sort.QuickSort(input, pivot+1, upper)
    end
    return input
  end
  
  def Sort.Partition(input, lower, upper)
    pivot = input[upper]
    i = lower-1
    (lower..upper-1).each do |j|
        if(input[j] <= pivot)
          i = i+1
          Sort.Swap(input, i, j)
        end
    end
    Sort.Swap(input, i+1, upper)
    return i+1
  end
  
  def Sort.MergeSort(input, lower, upper)
    if(lower < upper)
        pivot = lower + (upper-lower)/2.floor
        Sort.MergeSort(input, lower, pivot)
        Sort.MergeSort(input, pivot+1, upper)
        Sort.Merge(input, lower, pivot, upper)
    end
    return input    
  end
  
  def Sort.Merge(input, lower, pivot, upper)
    
    left = input[lower..pivot].dup + [1000]
    right = input[pivot+1..upper].dup + [1000]

    i=0; j=0
    (lower..upper).each do |k|
      if(left[i] < right[j])
        input[k] = left[i]
        i=i+1
      else
        input[k] = right[j] 
        j = j+1
      end
    end
      
    puts "lower=#{lower}; pivot = #{pivot}; upper=#{upper}; #{input.inspect}" if @@debug
    
    
  end
  
  def Sort.HeapSort(input)
    lastIndex = input.length-1
    puts input.inspect if @@debug
    Sort.Heapify(input, 0, lastIndex)
   
    (0..lastIndex-1).each do |i|
      Sort.Swap(input, 0, lastIndex-i)
      puts input.inspect if @@debug
      Sort.Heapify(input, 0, lastIndex-i-1)
    end
    return input
  end
  
  def Sort.Heapify(input, i, lastIndex)
    left = Sort.HeapLeftChild(i)
    right = Sort.HeapRightChild(i)
    max = i
    max = left if(left < lastIndex and input[left] > input[max])
    max = right if(right < lastIndex and input[right] > input[max])
    if(max != i)
      Sort.Swap(input, i, max)
      puts ".....i=#{i}, max = #{max}; #{input.inspect}"
      Sort.Heapify(input, max, lastIndex)
    end
    
  end
  
  def Sort.HeapLeftChild(i)
    return 2*i+1
  end
  
  def Sort.HeapRightChild(i)
      return 2*i+2 
  end
  
  def Sort.HeapParent(i)
      return (i-1)/2.floor
  end
  
  
end