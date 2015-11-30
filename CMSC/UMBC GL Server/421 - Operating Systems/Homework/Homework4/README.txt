Christopher Mai
chrmai1
hw4p1
Section 1

Compile/Run:
 compiled and run in Microsoft Visual C++ 2010 Express

 Takes three integer arguments in this order.
   # philosophers
   # chopsticks
   # chopsticks required to eat

Concurrency:
  Each Philosopher Runs in it's own thread more or less independent of
    the other philosophers

Deadlock Prevention:

  Of the four necessary conditions for deadlock, circular waiting, is prevented. As one of the conditions is prevented deadlock doesn't exist. Circular waiting is prevented by Implementing a waiter of sorts. Given a number of chopsticks and a number of chopsticks needed to eat there is a given number of philosophers that can eat at one time with 0 chance of deadlock. Shorter version, I limit the number of philosophers that can draw from the total number of chopsticks.

 
Starvation Prevention: 
  Starvation is prevented by implementing a waiting queue. As stated above only a limited number of philosophers are capable of sitting at the table. However to get to the table they must first be seated by the "host". Only the philosopher at the top of the queue can be seated. Philosophers who have eaten are placed at the bottome of the queue. In this way all philosopher will be given a chance at eating.
