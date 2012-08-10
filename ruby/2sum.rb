@hash = {}

STDIN.each do |i|
  i = i.chomp.to_i
  @hash[i]  = 1
end

@counter = 0
(2500..4000).each do |t|
  @hash.each do |n1, value|
    next if n1 > t
    n2 = t-n1
    if n2 != n1 and @hash.has_key?(n2)
      @counter = @counter + 1
      break
    end
  end
end
puts @counter
