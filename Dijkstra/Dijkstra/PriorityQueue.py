class PriorityQueue:
    def __init__(self):
        self.heap = []

    def push_to_heap(self, item, priority):
        entry = [item, priority]
        self.heap.append(entry)
        self.sort_heap()

    def sort_heap(self):
        self.heap = sorted(self.heap, key=lambda tup: tup[1], reverse=True)

    def pop_from_heap(self):
        return self.heap.pop()

    def adjust_priority(self, key, adjustement_in_priority):
        for item in self.heap:
            if item[0] == key:
                item[1] = adjustement_in_priority
                item[0].dist = adjustement_in_priority
                self.sort_heap()
                return
