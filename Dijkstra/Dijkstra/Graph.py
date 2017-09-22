from sys import maxint as INFINITY
class Edge:
    def __init__(self, n1, n2, size):
        self.n1 = n1
        self.n2 = n2
        self.size = size
        n1.edges.append(self)
        n2.edges.append(self)

class Node:
    def __init__(self, name, dist=INFINITY):
        self.name = name
        self.edges = []
        self.dist = dist

class Graph:
    def __init__(self, data):
        self.edges = []
        self.nodes = []
        for item in data:
            n1 = self.fetch_node(item[0])
            n2 = self.fetch_node(item[1])
            e  = Edge(n1, n2, item[2])
            self.edges.append(e)

    def print_graph(self):
        for item in self.data:
            print "N1: %s, N2: %s, Size: %d" % (item.n1.name, item.n2.name, item.size)

    def fetch_node(self, label):
        for node in self.nodes:
            if node.name == label:
                return node
        new_node = Node(label)
        self.nodes.append(new_node)
        return new_node

    def set_source(self, label):
        for node in self.nodes:
            if node.name == label:
                node.dist = 0
                return
        raise ValueError("Source not in graph")


"""nodes = [('A', 'B', 3), ('A', 'C', 1), ('B', 'C', 2)]
graph = Graph(nodes)

connections_to_b = graph.return_connections('B')
print(connections_to_b)"""
