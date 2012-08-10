require_relative 'debug'
require_relative 'sort'

module Numbers
  include Debug

  
  #Find three numbers that sum to N
  def Numbers.find_sum_of_three(input, num, duplicate=true)
      sorted = Sort.BubbleSort(input)
      puts sorted.inspect 
      output = []
      
      lastIndex = sorted.length-1
      correction = (duplicate) ? 1 : 2
      
      
      (0..lastIndex-correction).each do |i|
        j = (duplicate) ? i : i+1
        k = lastIndex
        vali = sorted[i]
        while( (duplicate && j <= k) || (!duplicate && j < k))
           valj = sorted[j]
           valk = sorted[k]
           
           output << [vali, valj, valk] if vali + valj + valk == num
           
           if (vali + valj + valk > num) 
             k = k - 1
           else 
              j = j + 1
           end
        end
      end
      return output
  end
  
  #Given a m*n grid starting from (1, 1). At any point (x, y), you has two choices for the next move: 1) move to (x+y, y); 2) move to (x, y+x); From point (1, 1), how to move to (m, n) in least moves? (or there's no such a path)
  
  # I'd offer another possible solution: if either m or n is greater, you automatically know what the final move must have been, since if, for instance, m>=n, (m-n,n) is on the grid but (m, n-m) is not since n-m<=0. Hence, it is easy to trace back the only possible moves one can use to get to (m,n) with very little thought/programming, and no recursion. Naturally, then, since x and y must always be >=1, it follows that there can only exist no solution if there is a point (going backwards from (m,n)) that lands you on the line x=y, x!=1. Thus, a solution exists if and only if GCD(m,n)=1 - i.e. m,n do not divide each other.
  
  #Find max subarray - kadane's algorithm
  def Numbers.MaxSubArray(input)
    max_sum = -1000
    cur_sum = 0
    a = 0
    b = 0
    cur_start  = 0
    input.each_with_index do |value, index|
      cur_sum = cur_sum + value
      if cur_sum >= max_sum
        max_sum = cur_sum
        a = cur_start
        b = index
      end
      if cur_sum < 0
        cur_sum = 0
        cur_start = index+1
      end
        
    end
    return [a, b]
    
  end

  
end