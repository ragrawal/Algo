require_relative "MinPriorityQueue.rb"

class GraphNode
  attr_reader :key, :options, :edges
  
  def initialize(key, options= {})
    @key = key
    @options = options
    @edges = {}
  end
  
  def add_edge(target, options)
    @edges[target] = options
  end
      
  
  def get_attr(key)
    return nil unless @options and @options.has_key?(key)
    @options[key]
  end
  
  def set_attr(key, value)
    @options[key] = value
  end
end



class Graph
  attr_reader :nodes
  
  def initialize
    @adj = {}
  end
  
  def add_edge(source, target, weigh)
  def add_edge(source, target, weight)
    @adj[source] ||= {:edges => {}, :color => 'white', :start => 0, :finish => 0} 
    @adj[target] ||= {:edges => {}, :color => 'white', :start => 0, :finish => 0}
    @adj[source][:edges][target]=weight
  end
  
  def shortest_path(source, target)
      reset
      @adj[source][:distance] = 0
      
      #initialize queue
      min_queue = MinPriorityQueue.new
      @adj.each{|k,v| min_queue.add({:key => v[:distance], k})}
       
      while(min_queue.length > 0)
        u = min_queue.extract()[:key]
        @adj[u][:edges].each do |target, distance|
          relax(u, target)
        end
      end
  end

  def dfs(order = nil)
    order ||= @adj.keys
    time = 0
    order.each do |k|
      next if !@adj.has_key?(k)  or @adj[k][:start] > 0
      stack = [k]
      while(stack.length > 0)
        time = time + 1
        s = stack.first
        if (@adj[s][:start] == 0)
          @adj[s][:start] = time
          @adj[s][:edges].each do |target|
              stack.unshift(target) unless !@adj.has_key?(target) or @adj[target][:start] > 0
          end
        else
          @adj[s][:finish] = time
          stack.shift
        end
      end #next element in stack
    end #next element in ordered list
  end
  
  
  def topological_sort
    dfs
    @edge.map{|k,v| 
      [i, k[:finish]]
    }.sort{|a,b| 
      a[:finish] <=> b[:finish]
    }.map{|i| i[0]}
  end
  
  def dfs_recursion(order = nil)
    reset
    time = 0
    order ||= @adj.keys
    order.each do |k|
      time = dfs_visit(k, time) if @adj.has_key?(k) and @adj[k][:color] == 'white'
    end
  end 
  
  def dfs_visit(source, time = 0)
    return time unless @adj.has_key?(source)
    time = time + 1
    @adj[source][:start] = time
    @adj[source][:color] = 'gray'
    @adj[source][:edges].each{|s|
      if @adj.has_key?(s) and @adj[s][:color] == 'white'
        @adj[s][:parent] = source
        time = dfs_visit(s, time)
      end
    }
    @adj[source][:color] = 'black'
    time = time + 1
    @adj[source][:finish] = time
    return time
  end
  
  def reverse 
    gt = Graph.new
    @adj.each do |source, v|
      v[:edges].each do |target|
        gt.add_edge(target, source)
      end
    end
    return gt
  end
  
  def nodes_count 
    return @adj.keys.length
  end
  
  def nodes
    return @adj.keys
  end
  
  def edges_count
    return @adj.map{|k,v| v[:edges].length }.inject{|sum, x| sum + x}
  end
  
  
  private
  def reset
    @adj.each{|k, v| 
      @adj[k][:color] = 'white' 
      @adj[k][:start] = 0
      @adj[k][:finish] = 0
      @adj[k][:parent] = nil
      @adj[k][:distance] = nil
    }
  end
  
  def relax(source, target)
    old_distance = @adj[target][:distance]
    new_distance =  @adj[source][:distance] + @adj[source][:edges][target]
    if old_distance.nil? or old_distance > new_distance
      @adj[target][:parent] = source
      @adj[target][:distance] = new_distance
    end
  end
  
end
  
  
  