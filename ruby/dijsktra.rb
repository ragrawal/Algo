class Array
  def swap(i, j)
    tmp = self[i]
    self[i] = self[j]
    self[j] = tmp
  end
end
class HeapNode
  attr_reader :distance, :vertex
  
  def initialize(vertex, distance)
    @distance = distance
    @vertex = vertex
  end
end

class MinHeap
  attr_reader :inputs
  def initialize()
    @inputs = []
  end
  
  def add(vertex, distance)
    @inputs.unshift(HeapNode.new(vertex, distance))
    heapify(0)
  end
  
  def extract_min
    @inputs.shift.vertex
  end
  
  def heapify(index)
    smallest = index
    l = left(index)
    r = right(index)
    smallest = l if l < length and @inputs[l].distance < @inputs[smallest].distance
    smallest = r if r < length and @inputs[r].distance < @inputs[smallest].distance
    unless smallest == index
      @inputs.swap(index, smallest)
      heapify(smallest)
    end
  end
  
  def length
    @inputs.length
  end
  
  private
  def left(index)
    2*index+1
  end
  
  def right(index)
    left(index)+1
  end
  
  def parent(index)
    (index-1)/2
  end
  
end

class Graph
  attr_reader :nodes
  
  def initialize
    @nodes = {}
  end
  
  def add_edge(source, target, weight)
    @nodes[source] ||= {:edges => {}, :parent => nil, :distance => 1 << 32}
    @nodes[target] ||= {:edges => {}, :parent => nil, :distance => 1 << 32}
    @nodes[source][:edges][target] = weight
  end
  
  def shortest_path(source)
    #initialize
    @nodes[source][:distance] = 0
    heap = MinHeap.new
    visited = Array.new(@nodes.length)
    heap.add(source, 0) 
    
    while(heap.length > 0)
      min = heap.extract_min
      visited[min] = @nodes[min][:distance]
      source_distance = @nodes[min][:distance]
      @nodes[min][:edges].each do |target, weight|
        next if visited.include?(target) or @nodes[target][:distance] < source_distance + weight
        @nodes[target][:distance] = source_distance + weight
        @nodes[target][:parent] = min
        heap.add(target, @nodes[target][:distance])
      end
    end
    return visited
  end
  
end


graph = Graph.new
STDIN.each_line do |line|
  tokens = line.chomp.split("\t")
  source = tokens.shift.to_i
  tokens.each do |token|
    target, weight = token.split(",")
    puts "#{source} --> #{weight} --> #{target}"
    graph.add_edge(source, target.to_i, weight.to_i)
  end
end
d = graph.shortest_path(1)

distances = []
[7,37,59,82,99,115,133,165,188,197].each{|i| distances << d[i] }
puts distances.join(',')