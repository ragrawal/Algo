def subset(suffix, req_length, output, prefix=[])
	if(prefix.length == req_length)
		output << prefix
	else
		slen = suffix.length
		suffix.each_with_index do |s, i|
			subset(suffix[i+1..slen], req_length, output, prefix + [s])
		end
	end
end

class Array
  def subset(req_length, prefix=[])
  	if(prefix.length == req_length)
  		yield prefix
  	else
  		slen = self.length
  		self.each_with_index do |s, i|
  			self[i+1..slen].subset(req_length, prefix + [s]) {|i| yield i }
  		end
  	end
  end
end

puts "starting"
[0,1,2,3].subset(2) {|i,j| puts "#{i}, #{j}" }
