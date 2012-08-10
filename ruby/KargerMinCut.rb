@edges= []
@nodes = {}
class Array
  def deep_dup
   dup = []
   self.each{|i| dup << i.dup }
   return dup
  end
end

def edge_contraction(edges, nodes, index)
  #remove edge
  renameTo, delNode = edges.delete_at(index)
  
  #update connections
  nodes.delete(delNode)
  edges.each_with_index do |edge, edgeIndex|
    idx = edge.index(delNode)
    next unless idx
    edge[idx] = renameTo 
  end
  
  #remove self loops
  edges.delete_if{|i| i[0] == i[1]}
end

STDIN.each_line do |line|
  line = line.chomp
  tokens=line.split("\t")
  source = tokens.shift.to_i
  @nodes[source] = true
  tokens.each do |target|
    @edges << [source, target.to_i].sort
    @nodes[target.to_i] = true
  end
end

@edges = @edges.uniq
puts "SIZE: #{@nodes.length} X #{@edges.length}"

@min_cut = @edges.length
(0..1000).each do 
  nodes = @nodes.dup
  edges = @edges.deep_dup

  while(nodes.length > 2) do
    delEdge = rand(edges.length)
    edge_contraction(edges, nodes, delEdge)
  end

  @min_cut = edges.length if @min_cut > edges.length
end

puts "MINIMUM CUT = #{@min_cut}"