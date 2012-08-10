class Person
    attr_reader :position, :succ, :alive
    attr_writer :position, :succ, :alive
    
    # Everyone is alive, initially
    def initialize(pos)
        @position = pos
        @alive = true
    end
    
    # For creating a linked chain of people
    def createChain(n)
        return self unless n>0
        @succ = Person.new(@position + 1)
        @succ.createChain(n-1)
    end
    
    # Kill every nth person
    # Current position in the cycle is pos
    # there are remaining people remaining
    # Stop killing if we're the last one.
    def kill(pos,nth,remaining)
        return @succ.kill(pos,nth,remaining) if !@alive
        return self if (remaining == 1)
        
        if pos == nth
            @alive = false
            puts self
            pos = 0
            remaining-=1
        end
        @succ.kill(pos+1,nth,remaining)
    end
    
    # Information about this person
    def to_s
        "Person \##@position, #{@alive ? 'alive' : 'dead'}"
    end
end

# Set n to anything much higher (like 10, say)
# And the program hangs, or has an "Illegal Instruction"
n = ARGV[1].to_i

first = Person.new(1)
last  = first.createChain(n-1)
last.succ = first

winner = first.kill(1,3,n)
# If I use puts "Winner: " + winner, I get an error:
#    in `+': failed to convert Person into String (TypeError)
#puts "Winner: " + winner
puts "Winner: ", winner