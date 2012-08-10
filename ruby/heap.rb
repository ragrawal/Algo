class Array

  def swap(i, j)
    tmp = self[i]
    self[i] = self[j]
    self[j] = tmp
  end
end

class Heap 

  #constructor
  #By default set as maxHeap
  #For MinHeap, use Heap.new proc{|x,y| x > y }
  
  def initialize(compare=lambda{|x,y| x < y})  
    super()
    @compare = compare
    @elements = []
  end
  
  #get number of elements in heap
  def length
    @elements.length
    #@elements.size    
  end
  
  #extract root element of the heap
  def pop
    return nil if @elements.length == 0
    tmp = @elements.shift
    @elements.unshift @elements.pop
    heapify_down
    return tmp
  end
  
  def peek
    @elements[0] 
  end
  
  #insert an element
  def add(element)
    @elements << element
    #heapify_up(@elements.size-1)
    heapify_up(@elements.length-1)
  
  end
  
  def print(i=0, level=0)
    return if i >= @elements.length
    puts "#{(['']*level*2).join(' ')}|-#{@elements[i]}"
    print(left(i), level+1)
    print(right(i), level+1) 
  end
  
  
  def heapify_up(index)
    return if index == 0
    p = parent(index)
    while index > 0 and @compare[@elements[p], @elements[index]] 
      @elements.swap(p, index)
      index = p
      p = parent(index)
    end
    
=begin
    Using Loop in HeapUp and HeapDown
    if @compare[@elements[p], @elements[index]] 
      @elements.swap(p, index)
      heapify_up(p)
    end
=end
  end
  private :heapify_up
  
  def heapify_down(index = 0)
    l = left(index); r = right(index)

    while true
      extreme = index
      extreme = l if l < @elements.length and @compare[@elements[extreme], @elements[l]]
      extreme = r if r < @elements.length and @compare[@elements[extreme], @elements[r]]
      
      break if (extreme == index)
      @elements.swap(index, extreme)
      index = extreme
      l = left(index)
      r = right(index)        
    end
    
=begin
    extreme = l if l < size and @compare[@elements[extreme], @elements[l]]
    extreme = r if r < size and @compare[@elements[extreme], @elements[r]]

    if (extreme != index)
      @elements.swap(index, extreme)
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
