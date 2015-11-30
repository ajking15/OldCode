package lab9;

/**
 * The Stack class demonstrates LIFO mechanism using an array. 
 * @author audumbar
 *
 */
public class Stack {
	/**
	 * Maximum size of the stack
	 */
	public static final int MAXSIZE = 4;
	/**
	 * Array to store stack elements
	 */
	private int stack[];
	/**
	 * Index that points to topmost element of the stack, it initialized to -1.
	 */
	private int stackTop;
	/**
	 * Size of the stack
	 */
	private int stackSize;
	
	
	/**
	 * Default Constructor, it initializes instance variables.
	 */
	public Stack() {
		stack = new int[MAXSIZE];
		stackTop = -1;
		stackSize = MAXSIZE;
	}
	
	/**
	 * This constructor validates the given size, and then initializes variables.
	 * @throws RuntimeException if the stack size given is invalid
	 * @param size	size of the stack
	 */
	public Stack(int size)
	{
		stack = new int[size];
		stackTop = -1;
		stackSize = size;		
	}
	/**
	 * This method returns the top element of the stack . 
	 * @return stack top element
	 * @throws StackEmpty if stack array is empty 
	 */
	public int pop () {
		return stack[stackTop--];	
	}
	/**
	 * This method puts an integer at the top of the stack.
	 * @param var integer to be added to stack
	 * @throws StackFull if the stack overflows
	 */
	public void push(int var) {
		stack[++stackTop] = var;
	}
}
