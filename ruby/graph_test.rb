require_relative "Graph.rb"
graph = Graph.new

STDIN.each_line do |line|
  line = line.chomp
  tokens = line.split(/\s+/)
  source = tokens.shift.to_i
  tokens.each{|target| graph.add_edge(source, target.to_i)}
end

nodes = graph.nodes.sort.reverse

gt = graph.reverse

gt.dfs_recursion nodes.sort.reverse

order = gt.adj.map{|k, v| [k, v[:start], v[:finish]]}.sort{|a, b| a[2] <=> b[2]}.reverse.map{|i| i[0]}

graph.dfs_recursion order 
clusters = []

max_finish = 0
cluster = nil
order = graph.adj.map{|k, v| [k, v[:start], v[:finish]] }.sort{|a, b| a[1] <=> b[1]}


order << [nil, nil, 3 * graph.nodes_count]
order.each do |i|
  if cluster.nil?
    cluster = [i[0]]
    max_finish = i[2]
  elsif i[2] > max_finish
    clusters << cluster.dup
    cluster = [i[0]]
    max_finish = i[2]
  else
    cluster << i[0]
  end
end

clusters.each{|i| 
  puts "#{i.inspect}\t#{i.length}" 
}

