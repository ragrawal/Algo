def mid_point(left, right)
  left + (right-left)/2
end

class Integer
  N_BYTES = [42].pack('i').size
  N_BITS = N_BYTES * 8
  MAX = 2 ** (N_BITS - 2) - 1
  MIN = -MAX - 1
end

def inversion(input, left, right)
  
  return 0 if input.nil? 
 
  n = right-left
  return 0 if n < 1
  
  pivot = mid_point(left, right)
  linv = inversion(input, left, pivot)
  rinv = inversion(input, pivot+1, right)
  split = split_inversion(input, left, right)
  
  return linv + rinv + split
end
  
def split_inversion(input, left, right)
  pivot = mid_point(left, right)
  
  b = input[left..pivot].dup + [Integer::MAX]
  c = input[pivot+1..right].dup + [Integer::MAX]
  
  i = 0
  j = 0
  inversion = 0
  (left..right).each do |k|
    if b[i] < c[j]
      input[k] = b[i]
      i = i+1
    else
      input[k] = c[j]
      inversion = inversion + (b.length-1 - i)
      j = j + 1
    end
  end
  puts "#{input[left..right].inspect} --> #{inversion}" 
  return inversion
end


@input = []
STDIN.each_line do |line|
  next if line.chomp.nil? or line.chomp.empty?
  @input << line.chomp.to_i
end
puts "Array Length: #{@input.length}"
puts inversion(@input, 0, @input.length-1)

  
