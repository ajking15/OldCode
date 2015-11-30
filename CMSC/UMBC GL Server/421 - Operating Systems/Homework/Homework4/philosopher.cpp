#include <iostream>
#include <queue>
#include <Windows.h>
#include <process.h>
#include <stdio.h>
#include <cstdlib>

using namespace std;

/*
 Christopher Mai
 chrmai1
 hw4p1
 Section 1
*/

//The Queue. Holds people at the table
queue<int> waiter;
//The Queue. Holds people not at the table
queue<int> host;
//total sticks
int sticksTotal;
//record of resources
int sticksAvailable;
//number of philosophers. doesn't need to be global
int numPhilosophers;
//number chopsticks to eat
int sticksNeeded;
//Table Limit. This will go up and down as philosophers join/leave
int capacity;
//the mutex
HANDLE ghMutex;

//this is the method fired off by _beginthread
void philosopherThread(void * ch);

//compares current resources to those needed
int checkAvailable(int held);

//manages the queue
bool waitInLine(int index);
//determines whether or not sticks are available 
//  and takes appropriate actions
int waitForSticks(int index, int held);

int main(int argc, char *argv[])
{
  cout << "CMSC 421, Dining Philosophers" << endl;
  // argv[0] is the script name with filepath
  // arguments separated by a space. use " " to pass a string
  //argv[1] = N philosophers
  //argv[2] = K chopsticks overall
  //argv[3] = M chopsticks needed to eat
  if(argc != 4){
    cout << "Incorrect number of cmd line args " << endl;

	return 0;
  }

  numPhilosophers = atoi(argv[1]);
  sticksAvailable = atoi(argv[2]);
  sticksNeeded    = atoi(argv[3]);
  //sticksAvailable = sticksTotal; //default state. no sticks in use
  capacity = sticksAvailable / sticksNeeded; //int division. rounds down by default

  cout << "# of philosophers: " << numPhilosophers << endl;

  cout << "Total chopsticks: " << sticksAvailable << endl;

  cout << "# chopsticks needed to eat: " << sticksNeeded << endl;

  cout << "Table Capacity: " << capacity << endl;



  if(sticksNeeded > sticksAvailable){
	  cout << " chopsticks needed must be less than total chopsticks" << endl;
	
	  return 0;
  }


  ghMutex = CreateMutex( 
        NULL,              // default security attributes
        FALSE,             // initially not owned
        NULL);             // unnamed mutex. can reference a previous mutex


    if (ghMutex == NULL) {
        printf("CreateMutex error: %d\n", GetLastError());
        return 0;
    }

  for(int i = 0; i < numPhilosophers; i++){
	//just use beginThread
	//  philosopherThread((void *) 2);
	_beginthread(philosopherThread, 0, (void *) i);
     //fire them off as threads
  }

  while(true){
	  //just wait for threads to execute
  }
  //just loop infinitely waiting for it to end I suppose
  //Sleep(5000);
  //I don't have a close mutex since I'm looping forever
}

// prototype required
void philosopherThread(void * ch){
	DWORD dwWaitResult;
	bool flag1 = true;
	bool meal  = true;
	//each thread has it's own copy of priority
	//int priority = 5000;
	int index = (int) ch;
	int held = 0;
	host.push(index);
	Sleep((rand() % 5000) + 1000); //should sleep anywhere from 1 - 5
	
	//tells it to keep going as it supposed to	
    while(meal == true){
	
	  while(waitInLine(index) == false){
		  //not next in line. sleep for awhile then try again
		  cout<<"Thread "<< index <<" waiting in line"<< endl;
		  Sleep((rand() % 5000) + 1000);
	  }
	  
	  cout<<"Thread "<< index <<" has been seated"<<endl; 

	  //put a bool flag with waitforsticks inside
	  while(flag1 == true){
		
		//checks if sticks are available for eating
		  //held = sticksNeeded. can eat now
		if((held = waitForSticks(index, held)) == sticksNeeded){
		  cout<<"Eating"<< endl;
		  flag1 = false;
		} else {
		  cout<<"Still need more chopsticks, sleeping"<< endl;
			//Not enough chopsticks to eat. wait and try again later
			Sleep((rand() % 5000) + 1000);

		}
	  }
     
	//Eating So sleep for awhile and return resources

	// maybe just sleep based off of priority
	Sleep((rand() % 5000) + 1000);

	//aquire lock. should wait until it gets a lock
	//don't need this lock anymore either
	//dwWaitResult = WaitForSingleObject( 
    //        ghMutex,    // handle to mutex
    //        INFINITE);  // no time-out interval
    //locked return resources
	cout <<"Thread: "<< index << " resources starting return"<<endl;
	
	flag1 = true;
	//held = 0;
	//returns random amount. put back into queue and increase capacity
	//  after all chopsticks have been returned
	//held should equal sticksNeeded
	while(held != 0){
		//puts at least one back
		int putBack = (rand() % held) + 1;
		sticksAvailable = sticksAvailable + putBack;
		held = held - putBack;
		Sleep(rand() % sticksNeeded);
	}

	capacity++;
	host.push(index);

	cout<<"Thread "<<index<<" all resouces returned"<< endl;
	//sticksAvailable = sticksAvailable + sticksNeeded;
	//cout <<"Total Resources: "<< sticksAvailable << endl;
	//awake after eating. release resources.
    //	if (! ReleaseMutex(ghMutex)) { 
          // Handle error. Shouldn't Happen 
	//}
	
	Sleep((rand() % 5000) + 1000);
	//sleep and start over
  }

  _endthread();

}

bool waitInLine(int index){
	DWORD dwWaitResult;
	//check top of queue
	//if index is at the top return true
	//not sure it's necessary. since only 1 thread should ever pop at a time
	dwWaitResult = WaitForSingleObject( 
            ghMutex,    // handle to mutex
            INFINITE);  // no time-out interval

	if(host.front() == index && capacity > 0){
	  //inside the restaraunt
	  host.pop();
	  capacity--;
	  ReleaseMutex(ghMutex);
	  return true;
	} else {
	  ReleaseMutex(ghMutex);
	  //otherwise return false
	  return false;
	}
}

//create check available method
int waitForSticks(int index, int held){
	//randomly take chopsticks ranging from 1 - needed
	//always grab at least 1 stick
	//use locks to have nicely formatted output?
	int taking = (rand() % (sticksNeeded - held) + 1);
	int tTotal = held + taking;

	//put into same string so lines don't interfere
	cout<<"Thread "<< index << 
	"\nCurrent Chopsticks: "<< held <<
	"\nAdditional Chopsticks: "<<taking<<
	
	"\nTotal Chopsticks: "<<tTotal<<endl;
	held = held + taking;
	return held;
	//return sticks held
	/*
	DWORD dwWaitResult;

	dwWaitResult = WaitForSingleObject( 
            ghMutex,    // handle to mutex
            INFINITE);  // no time-out interval

	  cout<<"Thread: "<< index << endl;
	  // >= since I'm allowed to have 0 resources
	  //request random resources
      if(checkAvailable(held) >= 0){
		//simulate eating up of those resources
		//take a random amount
		sticksAvailable = sticksAvailable + held - sticksNeeded;
		cout<<"Eating"<< endl;
		if (! ReleaseMutex(ghMutex)) { 
           // Handle error. Shouldn't Happen 
		}
		//-1 is the clear signal
		return -1;
	  } else {
		  //checkAvailable less than 0;
		  //int tNeeded = sticksNeeded - held; //should never be negative
		  //not correct

		  //sticksAvailable will never be > than tNeeded
		  //sticksAvailable = sticksAvailable - tNeeded;
		  //only happens when there isn't enough to eat.
		  // so held + sticksAvailable < sticksNeeded
		  held = held + sticksAvailable;

		  sticksAvailable = 0;

		  cout<<"Waiting" << endl;
		if (! ReleaseMutex(ghMutex)) { 
          // Handle error. Shouldn't Happen 
		}
		  //not eating. but waiting for chopsticks
		  Sleep(priority);
		  //any number 0 and up will be the number
		  // a process grabs that is insufficient
		  return held;
	  }
	
	*/
}

//takes in sticks currently held by the process
// subtracts sticksNeeded from held + sticksAvailable
//no longer necessary
int checkAvailable(int held){
	//basically are the resources available
	cout<<"Sticks Available: "<< sticksAvailable << endl;
	cout<<"Sticks Needed: "<< sticksNeeded << endl;
	//take into account current sticks held
	return sticksAvailable + held - sticksNeeded;
}