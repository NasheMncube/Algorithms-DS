#More on Huffman coding http://tinyurl.com/ycg3a27t
import sys
class Huffman:
    def __init__(self, string):
        self.freq = self.relative_freq(string)
        self.sort_freq = self.sort_freq(self.freq)
        self.tree = self.build_tree(self.sort_freq)
        self.trimmed_tree = self.remove_frequencies(self.tree)
        #self.codes = {}
        self.codes_binary = {}
        self.shifts = {}
        #assign_code(self.trimmed_tree)
        self.assign_code_binary(self.trimmed_tree)
        self.encode_string = self.encode_binary(string)
        self.decode_string = self.decode(self.trimmed_tree, self.encode_string)
    # The first step is to determine the relative frequencies of characters in the
    # input string

    def relative_freq(self, string):
        ret = {}
        for char in string:
            ret[char] = ret.get(char, 0) + 1
        return ret

    # We then build a list of tuples and sort it. This is the main input into the
    # Huffman algorithim

    def sort_freq(self, freq):
        chars = freq.keys()
        ret = []    # Sorted list of tuples
        for char in chars:
            ret.append((freq[char], char))
        ret.sort()
        return ret

    #We then build a binary tree from lowest frequency to highest
    def build_tree(self, sort_tuples):
        while len(sort_tuples) > 1:
            bottom_two    = tuple(sort_tuples[0:2]) #Two tuples to combine
            tail          = sort_tuples[2:]
            combined_freq = bottom_two[0][0] + bottom_two[1][0] #Combine freq of least frequent characters
            sort_tuples   = tail + [(combined_freq, bottom_two)]  #Insert back into list
            sort_tuples.sort()
        return sort_tuples[0] #Tree in list

    # We trim the tree of the frequency counters
    def remove_frequencies(self, tree):
        pivot = tree[1]
        if type(pivot) == str:
            return pivot
        else:
            return (self.remove_frequencies(pivot[0]), self.remove_frequencies(pivot[1]))

    #Then we need to assign the codes.
    def assign_code(self, node, default_string=''):
        if type(node) == str: # Node is just a value
            self.codes[node] = default_string
        else: #Node is root of subtree, recursively assign left and right trees
            self.assign_code(node[0], default_string+'0')
            self.assign_code(node[1], default_string+'1')

    def assign_code_binary(self, node, default_int=0b0, shift=0):
        if type(node) == str:
            self.codes_binary[node] = default_int
            self.shifts[node] = shift
            shift = 0
        else:
            shift += 1 # Have to be able to keep track of leading zeros
            self.assign_code_binary(node[0], default_int << 1, shift)
            self.assign_code_binary(node[1], (default_int << 1) + 0b1, shift)

    #Defining the functions which encodes and decodes a string
    def encode(self, input):
        output = ""
        for char in input:
            output = output + self.codes[char]
        return output

    def encode_binary(self, input):
        output = ""
        for char in input:
            output = output + bin(self.codes_binary[char])[2:].zfill(self.shifts[char])
        return output

    def decode(self, tree, encoded_string):
        output = ""
        pivot = tree
        for i in encoded_string:
            if i == '0':
                pivot = pivot[0] #Left subtree
            else:
                pivot = pivot[1] #Rigt subtree

            if type(pivot) == str:
                output = output + pivot
                pivot  = tree
        return output

    """def main(self):
        freq = self.relative_freq(self.string)
        sort_freq = self.sort_freq(self.freq)"""


def main():
    input_to_huffman = ""
    for line in sys.stdin:
        input_to_huffman += line
    #print(input_to_huffman[0:10])
    h = Huffman(input_to_huffman)
    bytes_taken_by_file = str(len(input_to_huffman))
    bytes_taken_by_compression = str(len(h.encode_string)/8)
    print("Size of file: " + bytes_taken_by_file + " bytes")
    print("Size of compressed file: " + bytes_taken_by_compression + " bytes")


if __name__ == '__main__':
    main()

"""file_name = '/Users/nashe/Algos/Huffman/TheAwfulGermanLanguage.txt'
f = open(file_name, 'r')
test_string = f.read()
print(test_string[0:10])
h = Huffman(test_string)
print(h.encode_string)"""



"""
    My implementation does give a picture of how Huffman coding works, however
    it ironically doesn't save memory but bloats memory. This is because we aren't using raw bits
    in the coding process but rather using strings of 1's and 0's. In python, these
    are equivalent to characters which means I am using 8 bits to represent 0's and 1's
    and not just using raw bits. The *_binary functions add this real compression
"""
