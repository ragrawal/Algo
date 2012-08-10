require_relative 'sort.rb'

module StringProblems
  include Debug
  
    def StringProblems.Subsequence2(s1, s2)
      return 0 if s1.empty? || s2.empty?
      
      maxlen = 0
      substring = []
      num = Array.new(s2.size){[0] * s2.size }
      
      s1.scan(/./).each_with_index do |ch1, i|
        s2.scan(/./).each_with_index do |ch2, j|
          if ch1==ch2
            num[i][j] = (i == 0 || j == 0) ? 1 : 1 + num[i-1][j-1] 
          end
          
          if(num[i][j] > maxlen)
            maxlen = num[i][j]
            substring = s1[i-num[i][j]+1..i]
          end
          
        end
      end
      return substring
    end

    #Worst Case Complexity: O(N), O(N)
    def StringProblems.has_all_unique(str)
      tmp = {}
      str.scan(/./).each do |ch|
        return false if tmp.has_key?(ch)
        tmp[ch] = true
      end
      return true
    end
    
    #Edit Distance Algorithm
    def StringProblems.edit_distance(s1, s2, cost={:del=>1, :ins=>1, :sub => 1})
      return 0 if s1.empty? and s2.empty?
      
      #initialize edit distance table
      edit = Array.new(s1.size+1){[0] * (s2.size+1)}
      backtrace = []
      
      #intiialize first row with default values
      edit[0]  = (0..s2.size).to_a
      
      #scan
      s1.scan(/./).each_with_index do |ch1, i|
        i = i+1
        edit[i][0] = i
        
        s2.scan(/./).each_with_index do |ch2, j|
          j = j +1
          
          #check down -- deletion
          edit[i][j] = edit[i-1][j] + cost[:del]
          backtrace << -1
          
          #check left - insertion
          left = edit[i][j-1] + cost[:ins] 
          edit[i][j] = left if edit[i][j] > left
          
          #check diagonal - substition
          subs = edit[i-1][j-1] + ((ch1 == ch2) ? 0 : cost[:sub])
          edit[i][j] = subs if edit[i][j] > subs

          
        end # s2        
      end # s1
      
      edit.each do |row|
        puts row.inspect
      end if @@debug
      
      operations = []
      i = s1.size
      j = s2.size
      while(i >= 0 and j >= 0)
        
      end
    end
    
    

    
end
