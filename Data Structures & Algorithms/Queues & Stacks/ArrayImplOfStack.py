#Stack operations are next to trivial in Python because Python arrays already have the operations built in 
#It is best to look over the Java examples for this concept, however seeing the Python operations will give you a better idea
#of internal functionality
#Remember, Stack is a LIFO data structure. L.I.F.O.: Last In First Out.
class Stack:

	def __init__(self):
		self.stack = []

	def push(self, data):
		self.stack.append(data)

	def peek(self):
		if not self.isEmpty():
			return self.stack[-1]
		else:
			return None

	def pop(self):
		if not self.isEmpty():
			return self.stack.pop()
		else:
			return None

	def isEmpty(self):
		return not self.stack


#Minor crude tests of our Stack
s = Stack()
for i in range(0,10):
	s.push(i)

def notExpected(op):
	raise Exception('Stack not functioning as expected on ' + op) 

if s.isEmpty() != False:
	notExpected('empty')

if s.peek() != 9:
	notExpected('peek')

if s.pop() != 9:
	notExpected('pop')

if s.peek() != 8:
	notExpected('peek')

while not s.isEmpty():
	s.pop()

if s.isEmpty() != True:
	notExpected('empty')

print ("Your stack works as expected")
