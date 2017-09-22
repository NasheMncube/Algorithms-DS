import Graph as g
import PriorityQueue as pq

data = [('A', 'B', 1), ('B', 'C', 2), ('D', 'C', 4), ('A', 'D', 7), ('D', 'D', 2)

         ,('A', 'C', 6), ('A', 'E', 1), ('D', 'E', 3), ('B', 'F', 5), ('C', 'F', 3)]

graph = g.Graph(data)

pq = pq.PriorityQueue()

for node in graph.nodes:
    pq.push_to_heap(node, node.dist)

source = 'F'
graph.set_source(source)
source_node = graph.fetch_node(source)
pq.adjust_priority(source_node, 0)
pq.sort_heap()
source_node = pq.pop_from_heap()[0]
destination = 'E'
destination_node = graph.fetch_node(destination)
path = []

def dijkstra(pq, current_node):
    path.append(current_node)
    if current_node.name == destination:
        print_path()
        return
    else:
        edges_from_current = current_node.edges
        adjust_priorites(edges_from_current, current_node)
        #print_heap()
        current_node = pq.pop_from_heap()[0]
        return dijkstra(pq, current_node)

def adjust_priorites(edges, current_node):
    for edge in edges:
        connection1 = edge.n1
        connection2 = edge.n2
        if connection1 != current_node:
            pq.adjust_priority(connection1, (current_node.dist + edge.size))
            continue
        elif connection2 != current_node:
            pq.adjust_priority(connection2, (current_node.dist + edge.size))
            continue

def print_path():
    print path[0].name
    for node in path[1:]:
        print "-> %s" % node.name

def print_heap(pq):
    print "-----------------"
    for item in pq.heap:
        print item[0].name, item[1]
    print "-----------------"

dijkstra(pq, source_node)
