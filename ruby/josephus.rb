def remaining(n, k) 
    if(n==1)
      return 0
    else
      return (remaining(n-1, k)+k+1) % n
    end
end

population = ARGV[0].to_i
step = ARGV[1].to_i
puts "Population = #{population}"
puts "setp = #{step}"
puts "Last Man Standing = #{remaining(population, step)}"

=begin
0,1,2,3,4,5,6
Die = 3,  Population: 0,1,2,4,5,6     Start: 4 ==> 0
Die = 0,  Population: 1,2,4,5,6       Start: 1
Die = 5,  Population: 1,2,4,6         Start: 6
Die = 4,  Population: 1,2,6,          Start: 6
Die = 6,  Population: 1,2             Start: 1
Die = 2,  Population: 1 
Return 1


remaining(7,3) ==> (4+4) % 7 ==> 1
remaining(6,3) ==> (0+4) % 6 ==> 4
remaining(5,3) ==> (1+4) % 5 ==> 0
remaining(4,3) ==> (1+4) % 4 ==> 1
remaining(3,3) ==> (0+4) % 3 ==> 1 
remaining(2,3) ==> (0+4) % 2 ==> 0
remaining(1,3) ==> 0


 
=end

