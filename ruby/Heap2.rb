class Array

  def swap(i, j)
    tmp = self[i]
    self[i] = self[j]
    self[j] = tmp
  end
end

class Heap2 < Array

  #constructor
  #By default set as maxHeap
  #For MinHeap, use Heap.new proc{|x,y| x > y }
  
  def initialize(compare=lambda{|x,y| x < y})  
    super()
    @compare = compare
  end
  
  #extract root element of the heap
  def extract
    return nil if self.length == 0
    tmp = self.shift
    self.unshift self.pop
    heapify_down
    return tmp
  end
  
  def peek
    self[0] 
  end
  
  #insert an element
  def add(element)
    self << element
    #heapify_up(self.size-1)
    heapify_up(self.length-1)
  
  end
  
  def print(i=0, level=0)
    return if i >= self.length
    puts "#{(['']*level*2).join(' ')}|-#{self[i]}"
    print(left(i), level+1)
    print(right(i), level+1) 
  end
  
  
  def heapify_up(index)
    return if index == 0
    p = parent(index)
    while index > 0 and @compare[self[p], self[index]] 
      self.swap(p, index)
      index = p
      p = parent(index)
    end
    
=begin
    Using Loop in HeapUp and HeapDown
    if @compare[self[p], self[index]] 
      self.swap(p, index)
      heapify_up(p)
    end
=end
  end
  private :heapify_up
  
  def heapify_down(index = 0)
    l = left(index); r = right(index)

    while true
      extreme = index
      extreme = l if l < self.length and @compare[self[extreme], self[l]]
      extreme = r if r < self.length and @compare[self[extreme], self[r]]
      
      break if (extreme == index)
      self.swap(index, extreme)
      index = extreme
      l = left(index)
      r = right(index)        
    end
    
=begin
    extreme = l if l < size and @compare[self[extreme], self[l]]
    extreme = r if r < size and @compare[self[extreme], self[r]]

    if (extreme != index)
      self.swap(index, extreme)
      heapify_down(extreme)
    end
=end
  end  
  private :heapify_down
  
  #get index of left child
  def left(i); 2*i+1; end
  private :left
  
  #get index of right child
  def right(i); 2*(i+1); end
  private :right
  
  #get index of parent
  def parent(i); (i-1)/2; end
  private :parent
  
  
  
end
