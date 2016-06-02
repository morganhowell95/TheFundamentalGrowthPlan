#Stack is a Last In First Out (LIFO) data structure
#Data is stored by building the list "backwards" and traversing forwards to return popped values
class Stack:

	def __init__(self):
		self.tail = None

	def push(self, d):
		#create new node to store data
		node = Stack.Node(d)
		#link new node "behind" the old node
		if not self.is_empty():
			old_node = self.tail
			self.tail = node
			self.tail.next = old_node
		#if no linked list currently exists, create a node associated with tail pointer
		else:
			self.tail = Stack.Node(d)

	def peek(self):
		if not self.is_empty():
			return self.tail.data
		else:
			return None
	
	def pop(self):
		if not self.is_empty():
			rdata = self.tail.data
			self.tail = self.tail.next
			return rdata
		else:
			return None

	def is_empty(self):
		return not self.tail

	#Local class representing the nodes of our linked list
	class Node:
		def __init__(self, data):
			self.data = data
			self.next = None


#Minor crude tests of our Stack
s = Stack()
for i in range(0,10):
        s.push(i)

def notExpected(op):
        raise Exception('Stack not functioning as expected on ' + op)

if s.is_empty() != False:
        notExpected('empty')

if s.peek() != 9:
        notExpected('peek')

if s.pop() != 9:
        notExpected('pop')

if s.peek() != 8:
        notExpected('peek')

while not s.is_empty():
        s.pop()

if s.is_empty() != True:
        notExpected('empty')

print ("Your stack works as expected")
