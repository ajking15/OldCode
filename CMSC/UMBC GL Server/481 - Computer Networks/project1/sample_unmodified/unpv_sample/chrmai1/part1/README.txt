# Christopher Mai
# CMSC 481
# Computer Networks
# README.txt

Compile:
make

Run:
  Client:
    client localhost port < test_input.txt

    #where localhost is either localhost or 127.0.0.1
    #where port is anything above the reserved ports

  Server:
    server port
    
    #where port is one separate from the port used in client.
    # unless you wait for a while as it takes some time for the 
    # port to be released by the kernel

Test:
  Client:
    Modify test_input.txt and run as shown above.

  Server:
    Automatically set to listen. Nothing really to check

# Reference Code
# Taken from sample code download on blackboard.