

@comparison = 0



class Array
  def swap(i, j)
    tmp = self[i]
    self[i] = self[j]
    self[j] = tmp
  end

  def median_of_three(*indexes)
    elements = []
    indexes.flatten.each{|i| elements << [self[i], i]}
    return elements.sort{|a,b| a[0] <=> b[0]}[1][1]
  end
end

def partition_first(ar, left, right)
  pivot = ar[left]
  i = left
  ar[left+1..right].each_with_index do |e, j|
    if(e < pivot)
      ar.swap(i+1, left+1+j)
      i = i + 1
    end
  end
  ar.swap(left, i)
  return i
end

def partition_last(ar, left, right)
  ar.swap(left, right)
  return partition_first(ar, left, right)
end

def partition_median(ar, left, right)
#  puts ar.inspect
  len = right-left
  median = ar.median_of_three(left, left+len/2, right)
  ar.swap(left, median) if median != left
  return partition_first(ar, left, right)
end

def sort(ar, left, right, option)
  return if right-left < 1
  @comparison = @comparison + (right-left)
  pivot = nil
  if option == "first"
    pivot = partition_first(ar, left, right)
  elsif option == "last"
    pivot = partition_last(ar, left, right)
  elsif option == "median"
    pivot = partition_median(ar, left, right)
  end
  
  sort(ar, left, pivot-1, option) 
  sort(ar, pivot+1, right, option)
end

def selection(ar, left, right, order)
  puts "#{ar.inspect}: #{left}, #{right}, #{order}"
  len = right - left
  return ar[left] if len < 1
  pivot = partition_first(ar, left, right)
  puts pivot
  
  if pivot-left == order 
    return ar[pivot] 
  elsif order > pivot-left
    return selection(ar, pivot+1, right, order-(pivot-left)-1)
  else
    return selection(ar, left, pivot-1, order)
  end
end

@inputs = [1,8,3,4,10, 9, 8, 12,13, 4, 5, 8, 9, 100]
puts selection(@inputs, 0, @inputs.length-1, 13)

=begin
@inputs = []
STDIN.each_line do |line|
  @inputs << line.chomp.strip.to_i
end
["first", "last","median"].each do |i|
  dup = @inputs.dup
  sort(dup, 0, @inputs.length-1, i)
  valid = true
  dup.each_with_index{|e, i|
    next if i==0
    valid = false if dup[i] < dup[i-1]
  }
  puts "#{i} ==> #{@comparison} ==> #{@check} ==> #{@noc} => #{valid.inspect}"
  @comparison = 0 
end
=end


