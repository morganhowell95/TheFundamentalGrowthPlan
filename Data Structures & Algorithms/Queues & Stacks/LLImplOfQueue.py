#The queue is a First In First Out (FIFO) data structure; this is the linked list implementation of that data structure
#A deque can also be created (will be shown later) that acts like a queue on both the front and rear end
class Queue:

	def __init__(self):
		self.head = None
		self.tail = None

	def enqueue(self, data):
		#if queue is empty adding an element creates a node that both the head and tail point to
		if self.is_empty():
			node = Queue.Node(data)
			self.head = node
			self.tail = node
		#otherwise the tail moves along with the enqueued data
		else:
			self.tail.next = Queue.Node(data)
			self.tail = self.tail.next

	def dequeue(self):
		if self.is_empty():
			return None
		#When dequeueing we will take element from the head and then increment the head in the direction of the progressing tail
		else:
			return_node = self.head.data
			self.head = self.head.next
			return return_node
	def peek(self):
		if self.is_empty():
			return None
		else:
			return self.head.data
	
	def is_empty(self):
		return not self.head

	class Node:
		def __init__(self, data):
			self.data = data
			self.next = None


#Minor crude tests of our Queue
q = Queue()
for i in range(0,10):
        q.enqueue(i)

def notExpected(op):
        raise Exception('Queue not functioning as expected on ' + op)

if q.is_empty() != False:
        notExpected('empty')

if q.peek() != 0:
        notExpected('peek')

if q.dequeue() != 0:
        notExpected('dequeue')

if q.peek() != 1:
        notExpected('peek')

while not q.is_empty():
        q.dequeue()

if q.is_empty() != True:
        notExpected('empty')

print ("Your queue works as expected")








