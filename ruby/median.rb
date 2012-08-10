require_relative 'heap2.rb'

class Median
  attr_accessor :smaller, :bigger
  def initialize
    @smaller = Heap2.new proc{|x,y| x < y }  #HeapMax.new
    @bigger = Heap2.new proc{|x,y| x > y }   #HeapMin.new
  end
  
  def add(i)
    small = @smaller.peek
    big = @bigger.peek

    if small == nil or i <= small or (big != nil and i < big)
      @smaller.add(i)
    elsif big == nil or i >= big
      @bigger.add(i)
    end
    balance
  end
  
  def median
    index = (size+1) / 2 #.ceil.to_i #(size % 2 == 0 ?  size/2 : (size+1)/2 )
    return @smaller.peek if(index <= @smaller.length )
    return @bigger.peek  
  end
      
  
  def print
    @smaller.print
    @bigger.print
  end
  
  def size
    @smaller.length + @bigger.length
  end
  
  #balance two heaps
  def balance
    diff = @smaller.length - @bigger.length
    if diff.abs > 1
      if diff > 0
        @bigger.add(@smaller.extract)
      else
        @smaller.add(@bigger.extract)
      end
    end
  end
  private :balance
end

@median = Median.new
@x = 0
STDIN.each_line do |line|
  i = line.chomp.to_i
  @median.add(i)
  @x = @x + @median.median
end
puts @x

