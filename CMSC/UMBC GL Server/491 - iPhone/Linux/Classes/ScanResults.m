//
//  ScanResults.m
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "ScanResults.h"


@implementation ScanResults

@synthesize scanReport, ipAddress, hostName, serverType, sshCheck, telnetCheck, ftpCheck; 

-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	self.ipAddress.text = self.scanReport.ipAddress;
	self.hostName.text = self.scanReport.hostname;
	self.serverType.text = self.scanReport.serverType;
	self.sshCheck.text = self.scanReport.sshCheck;
	self.telnetCheck.text = self.scanReport.telnetCheck;
	self.ftpCheck.text = self.scanReport.ftpCheck;
	
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
	//self.scanReport = nil;
//	self.ipAddress = nil;
//	self.hostName = nil;
//	self.serverType = nil;
//	self.sshCheck = nil;
//	self.telnetCheck = nil;
//	self.ftpCheck = nil;
}


@end
