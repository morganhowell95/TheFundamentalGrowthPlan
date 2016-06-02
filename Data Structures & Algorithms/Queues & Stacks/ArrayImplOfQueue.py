#The queue is a First In First Out (FIFO) data structure
#As with stack, the implementation of a queue with an array in Python is trivial due to Python's list support
#Please refer to the solution in Java for a more comprehensive understanding
class Queue:

	def __init__(self):
		self.q = []

	def enqueue(self, d):
		self.q.append(d)

	def dequeue(self):
		if not self.is_empty():
			return self.q.pop(0)
		else:
			return None

	def peek(self):
		if not self.is_empty():
			return self.q[0]
		else:
			return None

	def is_empty(self):
		return not self.q




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
