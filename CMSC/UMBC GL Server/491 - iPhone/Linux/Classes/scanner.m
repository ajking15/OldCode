//
//  scanner.m
//  Linux
//
//  Created by  on 12/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "scanner.h"


@implementation scanner

@synthesize savedResults, toBeSaved, ipGet, ipAddress, hostName, serverName, sshCheck, telnetCheck, ftpCheck,
saveCheck, invalidCheck, timer, thread, threadFin;

-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	// sets initial values for control booleans
	self.saveCheck = NO;	
	self.threadFin = YES;
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:YES];
	//does nothing now.
	
}

-(IBAction)saveResults
{
	// this writes out with nskeyed archiver	
	if(self.saveCheck && self.threadFin)
	{
		
		
		self.saveCheck = NO;
		if([self existCheck])
		{
			//check if save exists
			if(self.toBeSaved == nil)
			{
				//NSLog(@"nil");
				self.toBeSaved = [[[Result alloc] init] autorelease];
			}
			
			//loads values to be saved.
			self.toBeSaved.ipAddress = self.ipAddress.text;
			self.toBeSaved.hostname = self.hostName.text;
			self.toBeSaved.serverType = self.serverName.text;
			self.toBeSaved.sshCheck = self.sshCheck.text;
			self.toBeSaved.telnetCheck = self.telnetCheck.text;
			self.toBeSaved.ftpCheck = self.ftpCheck.text;
			
			[self.savedResults addObject:self.toBeSaved];
			
			//immediate archive/unarchive to fi a bug with the mutable array
			BOOL status = [NSKeyedArchiver archiveRootObject:self.savedResults toFile:[self getFilePath]];
			
			if(!status) {
				NSLog(@"Error saving to do list");
			}
			
			self.savedResults = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
			if(!self.savedResults)
			{
				self.savedResults = [NSMutableArray arrayWithObjects: nil, nil];
			}
			
			UIAlertView *alert = [[[UIAlertView alloc] initWithTitle:@""
															 message:@"Result Has Been Saved" delegate:nil
												   cancelButtonTitle:@"OK" otherButtonTitles:nil] autorelease];
			[alert show];
		} else {
			UIAlertView *alert = [[[UIAlertView alloc] initWithTitle:@"Error"
															 message:@"Existing Scan Exists" delegate:nil
												   cancelButtonTitle:@"OK" otherButtonTitles:nil] autorelease];
			[alert show];
		}
	} else {
		
		if(!threadFin)
		{
			//scan started but port scanning has yet to complete
			UIAlertView *alert = [[[UIAlertView alloc] initWithTitle:@"Error"
															 message:@"Still Scanning" delegate:nil
												   cancelButtonTitle:@"OK" otherButtonTitles:nil] autorelease];
			[alert show];
		} else {
			UIAlertView *alert = [[[UIAlertView alloc] initWithTitle:@"Error"
															 message:@"Nothing New to Scan" delegate:nil
												   cancelButtonTitle:@"OK" otherButtonTitles:nil] autorelease];
			[alert show];
		}
		
	}
}

-(NSString *)getFilePath {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"results.plist", nil];
	
	return [NSString pathWithComponents:segments];
}


-(bool)existCheck
{
	//checks current ip address agains current saves available
	//savedResults
	for(int x = 0; x < [self.savedResults count]; x++)
	{
		if([[[self.savedResults objectAtIndex:x] ipAddress] isEqualToString:self.ipAddress.text])
		{
			return NO;
		}
	}
	
	return YES;
}

-(IBAction)scanIP
{
	//also have to check if save exists based off of ip address
	//also check if ip is the same don't bother doing anything
	
	self.saveCheck = YES;
	[self.ipGet resignFirstResponder];
	
	//http://www.umbccd.before-reality.net/
	self.ipGet.text = [self.ipGet.text stringByReplacingOccurrencesOfString:@"http://" withString:@""];
	self.ipAddress.text = @"No IP";
	self.hostName.text = @"No Host";
	self.serverName.text = @"No Server Name";
	self.ftpCheck.text = @"Checking...";
	self.sshCheck.text = @"Checking...";
	self.telnetCheck.text = @"Checking...";
	self.invalidCheck = NO;
	// ido all five of these. only the first 3 need their own threads
	self.threadFin = NO;
	self.thread = [[NSThread alloc] initWithTarget:self selector:@selector(checkServices) object:nil];
	[self.thread start];
	
	
	//130.85.54.24
	// assorted sources
	//http://web.mit.edu/macdev/Development/MITSupportLib/SocketsLib/Documentation/sockets.html
	
	//http://developer.apple.com/mac/library/documentation/Networking/Conceptual/CFNetwork/CFHTTPTasks/CFHTTPTasks.html#//apple_ref/doc/uid/TP30001132-CH5-SW2
	
	//CFStringRef bodyData = CFSTR(""); // Usually used for POST data
	//	http://www.iana.org/assignments/port-numbers
	//  http://beej.us/guide/bgnet/output/html/singlepage/bgnet.html#structs
	//http://en.wikipedia.org/wiki/Getaddrinfo
	//http://www.qnx.com/developers/docs/6.3.2/neutrino/lib_ref/g/getaddrinfo.html
	//http://www.qnx.com/developers/docs/6.3.2/neutrino/lib_ref/g/getaddrinfo.html#UsingHints
	//http://userpages.umbc.edu/~dhood2/courses/cmsc433/spring2009/?section=Home
	//head requests
	// http://en.wikipedia.org/wiki/HTTP#Request_methods
	//130.85.54.24
	//130.85.217.39
	// check for thread end
	self.timer = [NSTimer timerWithTimeInterval:1 target:self selector:@selector(threadFinished) userInfo:nil repeats:YES];
	[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
}

-(void)threadFinished
{
	if([self.thread isFinished])
	{
		[self.timer invalidate];
		self.threadFin = YES;
		[self checkInvalid];
	}
}

-(void)checkInvalid
{
	if(self.invalidCheck)
	{
		UIAlertView *alert = [[[UIAlertView alloc] initWithTitle:@"Error"
														 message:@"Invalid IP" delegate:nil
											   cancelButtonTitle:@"OK" otherButtonTitles:nil] autorelease];
		
		[alert show];
		
	} else {
		[self getHostNameBSD];
		[self getHttpResponse];
	}
}

-(void)checkServices
{
	// separate autorelease pool is necessary for separate threads
	NSAutoreleasePool *pool =  [[NSAutoreleasePool alloc] init];
		
	if([self checkFTP] == NO || [self checkSSH] == NO || [self checkTelnet] == NO)
	{
		//	NSLog(@"Called");
		
		self.invalidCheck = YES;
	} else {
		self.invalidCheck = NO;
	}
	[pool release];
}

//File Transfer Protocol FTP
// port 21
-(bool)checkFTP
{
	int status;
	int sockfd;
	struct addrinfo hints;
	struct addrinfo *servinfo;  // will point to the results
	const char *result;
	result = [self.ipGet.text UTF8String]; //this points to the ip being connected to
	
	memset(&hints, 0, sizeof hints); // make sure the struct is empty
	hints.ai_family = AF_UNSPEC;     // don't care IPv4 or IPv6
	hints.ai_socktype = SOCK_STREAM; // TCP stream sockets
	//hints.ai_flags = AI_PASSIVE;     // fill in my IP for me
	
	if ((status = getaddrinfo(result, "21", &hints, &servinfo)) != 0) {
		return NO;
	}
	
	// make a socket:
	
	sockfd = socket(servinfo->ai_family, servinfo->ai_socktype, 0 /*servinfo->ai_protocol */);
	
	if(sockfd > -1)
	{
		//NSLog(@"Socket Created");
		// connect 	
		int test = connect(sockfd, servinfo->ai_addr, servinfo->ai_addrlen);
		if(test > -1)
		{
			//NSLog(@"FTP");
			self.ftpCheck.text = @"Running";
		} else {
			//NSLog(@"ftp sigh, %d", test);
			self.ftpCheck.text = @"Not Running";
		}
	} else {
		self.ftpCheck.text = @"Not Running";
		//NSLog(@"ftp arrgh, %d", sockfd);
	}
	
	// getpeername something to output section 5.10
	close(sockfd);
	// ... do everything until you don't need servinfo anymore ...
	freeaddrinfo(servinfo); // free the linked-list
	// getnameinfo() Look up the host name and service name information for a given struct sockaddr.
	
	return YES;
}

-(bool)checkSSH
{
	int status;
	int sockfd;
	struct addrinfo hints;
	struct addrinfo *servinfo;  // will point to the results
	const char *result;
	result = [self.ipGet.text UTF8String]; //this points to the ip being connected to
	
	memset(&hints, 0, sizeof hints); // make sure the struct is empty
	hints.ai_family = AF_UNSPEC;     // don't care IPv4 or IPv6
	hints.ai_socktype = SOCK_STREAM; // TCP stream sockets
	
	
	
	if ((status = getaddrinfo(result, "22", &hints, &servinfo)) != 0) {
		return NO;
	}
	
	// make a socket:
	
	sockfd = socket(servinfo->ai_family, servinfo->ai_socktype, 0 /*servinfo->ai_protocol */);
	
	if(sockfd > -1)
	{
		//NSLog(@"Socket Created");
		// connect 	
		int test = connect(sockfd, servinfo->ai_addr, servinfo->ai_addrlen);
		if(test > -1)
		{
			//NSLog(@"SSH");
			self.sshCheck.text = @"Running";
		} else {
			//NSLog(@"sigh, %d", test);
			self.sshCheck.text = @"Not Running";
		}
	} else {
		self.sshCheck.text = @"Not Running";
		//NSLog(@"arrgh, %d", sockfd);
	}
	
	
	
	// getpeername something to output section 5.10
	close(sockfd);
	// ... do everything until you don't need servinfo anymore ...
	freeaddrinfo(servinfo); // free the linked-list
	// getnameinfo() Look up the host name and service name information for a given struct sockaddr.
	return YES;
}

//port 23 
-(bool)checkTelnet
{
	int status;
	int sockfd;
	struct addrinfo hints;
	struct addrinfo *servinfo;  // will point to the results
	const char *result;
	result = [self.ipGet.text UTF8String]; //this points to the ip being connected to
	
	memset(&hints, 0, sizeof hints); // make sure the struct is empty
	hints.ai_family = AF_UNSPEC;     // don't care IPv4 or IPv6
	hints.ai_socktype = SOCK_STREAM; // TCP stream sockets
	//hints.ai_flags = AI_PASSIVE;     // fill in my IP for me
	
	
	if ((status = getaddrinfo(result, "23", &hints, &servinfo)) != 0) {
		return NO;
	}
	
	
	// make a socket:
	
	sockfd = socket(servinfo->ai_family, servinfo->ai_socktype, 0 /*servinfo->ai_protocol */);
	
	if(sockfd > -1)
	{
		//NSLog(@"Socket Created");
		// connect 	
		int test = connect(sockfd, servinfo->ai_addr, servinfo->ai_addrlen);
		if(test > -1)
		{
			//NSLog(@"telnet");
			self.telnetCheck.text = @"Running";
		} else {
			//NSLog(@"sigh, %d", test);
			self.telnetCheck.text = @"Not Running";
		}
	} else {
		//NSLog(@"arrgh, %d", sockfd);
		self.telnetCheck.text = @"Not Running";
	}
	
	
	
	// getpeername something to output section 5.10
	close(sockfd);
	// ... do everything until you don't need servinfo anymore ...
	freeaddrinfo(servinfo); // free the linked-list
	// getnameinfo() Look up the host name and service name information for a given struct sockaddr.]
	return YES;
}


/*
 attempts http response
 if found delegate will be called
 also, all calls require http:// in front or it fails
 */
-(void)getHttpResponse
{
	//NSLog(@"inside getHttpResponse");
	if(![self.ipGet.text isEqualToString:@""] && self.ipGet.text != nil)
	{
		self.ipAddress.text = self.ipGet.text;
		//	NSLog(self.ipGet.text);
		NSString *temp = [NSString stringWithFormat:@"http://%@", self.ipGet.text];
		NSURL *url = [NSURL URLWithString:temp];
		NSURLRequest *request = [NSURLRequest requestWithURL:url];
		
		NSURLConnection *connection = [[NSURLConnection alloc] initWithRequest:request delegate:self];
	}	
}

/*
 
 */
-(bool)getHostNameBSD
{
	struct addrinfo * result;
    struct addrinfo * res;
	//struct servent  * ptr;
    int error;
	
	const char *string;
	string = [self.ipGet.text UTF8String];
	
    // resolve the domain name into a list of addresses 
    error = getaddrinfo(string, "80", NULL, &result);
	
    if (error != 0)
    {   
        return NO;
    }   
	
	
    // loop over all returned results and do inverse lookup 
    for (res = result; res != NULL; res = res->ai_next)
    {   
        char hostname[NI_MAXHOST] = "";
		
        error = getnameinfo(res->ai_addr, res->ai_addrlen, hostname, NI_MAXHOST, NULL, 0, 0); 
		
        if (error != 0)
        {
            fprintf(stderr, "error in getnameinfo: %s\n", gai_strerror(error));
            continue;
        }
		
        if (*hostname)
        {
			//  NSLog(@"hostname: %s\n", hostname);
			if(strcmp(hostname, "") == 0)
			{
				
			} else {
				self.hostName.text = [NSString stringWithFormat:@"%s", hostname];
			}
			
        }
		
    }   
	
    freeaddrinfo(result);
	return YES;
}

/*
 HTTP (the web) is port 80, telnet is port 23, SMTP is port 25,
 
 This is the dicitonary
 */
- (void)connection:(NSURLConnection *)connection didReceiveResponse:(NSURLResponse *)response {
	NSHTTPURLResponse *httpResponse = (NSHTTPURLResponse*)response;
	
	//NSLog(@"called");
	if ([response respondsToSelector:@selector(allHeaderFields)]) {
		//NSLog(@"yay");
		NSDictionary *dictionary = [httpResponse allHeaderFields];
		//NSLog([dictionary description]);
		NSString *server = [dictionary valueForKey:@"Server"];
		if(server == nil)
		{
			self.serverName.text = @"No Server";
		} else { 
			self.serverName.text = server;
		}
		//NSLog([dictionary description]);
	} else {
		//NSLog(@"aww");
	}
}


-(BOOL)textFieldShouldReturn:(UITextField *)textField {
	[textField resignFirstResponder];
	//[textField 
	return YES;
}

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
 - (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
 if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
 // Custom initialization
 }
 return self;
 }
 */

/*
 // Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
 - (void)viewDidLoad {
 [super viewDidLoad];
 }
 */

/*
 // Override to allow orientations other than the default portrait orientation.
 - (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
 // Return YES for supported orientations
 return (interfaceOrientation == UIInterfaceOrientationPortrait);
 }
 */

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
	//self.savedResults = nil;
//	self.timer = nil;
//	self.thread = nil;
//	self.toBeSaved = nil;
//	self.ipGet = nil;
//	self.ipAddress = nil;
//	self.hostName = nil;
//	self.serverName = nil;
//	self.sshCheck = nil;
//	self.telnetCheck = nil;
//	self.ftpCheck = nil;
	
}


@end
