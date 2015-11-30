//
//  RootViewController.m
//  Linux
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "RootViewController.h"


@implementation RootViewController

@synthesize linuxGuide, linuxLogo,ipScanner, organizer, savedResults;


- (void)viewDidLoad {
    [super viewDidLoad];
	
	[self.linuxLogo setImage:[UIImage imageNamed:@"linux-logo.jpg"]];
	//self.savedResults = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
//	if(!self.savedResults)
//	{
//		self.savedResults = [NSMutableArray arrayWithObjects: nil, nil];
//	}
    // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
    // self.navigationItem.rightBarButtonItem = self.editButtonItem;
}


- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	//NSLog(@"appearing");
	self.savedResults = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
	if(!self.savedResults)
	{
		self.savedResults = [NSMutableArray arrayWithObjects: nil, nil];
	}
}

/*
 - (void)viewDidAppear:(BOOL)animated {
 [super viewDidAppear:animated];
 }
 */

-(NSString *)getFilePath {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"results.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(IBAction)loadLinuxGuide
{
	/*
	 give this class a class object of guide which will implement
	 a navigation view
	 */
	//NSLog(@"does it get here");
	if(self.linuxGuide == nil)
	{
		//NSLog(@"was nil, guide");
		self.linuxGuide = [[Guide alloc] init];
	}
	[self.navigationController pushViewController:self.linuxGuide animated:YES];
	[self.linuxGuide release];
	self.linuxGuide = nil;
	//NSLog(@"push a new view");
}


-(IBAction)loadScanner
{
	if(self.ipScanner == nil)
	{
		self.ipScanner  = [[scanner alloc] init];
	}
	
	//self.savedResults = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
//	if(!self.savedResults)
//	{
//		self.savedResults = [NSMutableArray arrayWithObjects: nil, nil];
//	}
	self.ipScanner.savedResults = self.savedResults;
	[self.navigationController pushViewController:self.ipScanner animated:YES];
	
	[self.ipScanner release];
	self.ipScanner = nil;
	//NSLog(@"push a new view");
}

-(IBAction)loadSaved
{
	
	
	if(self.organizer == nil)
	{
		self.organizer  = [[ResultsOrganizer alloc] init];
	}
	//self.savedResults = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
//	if(!self.savedResults)
//	{
//		self.savedResults = [NSMutableArray arrayWithObjects: nil, nil];
//	}
	self.organizer.savedScans = self.savedResults;
	[self.navigationController pushViewController:self.organizer animated:YES];
	[self.organizer release];
	self.organizer = nil;
	//NSLog(@"push a new view");
}



/*
 - (void)viewWillDisappear:(BOOL)animated {
 [super viewWillDisappear:animated];
 }
 */
/*
 - (void)viewDidDisappear:(BOOL)animated {
 [super viewDidDisappear:animated];
	 [self.organizer release];
	 [self.linuxGuide release];
	 [self.ipScanner release];
 }
 */

/*
 // Override to allow orientations other than the default portrait orientation.
 - (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
 // Return YES for supported orientations.
 return (interfaceOrientation == UIInterfaceOrientationPortrait);
 }
 */

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release anything that can be recreated in viewDidLoad or on demand.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
	//self.linuxGuide = nil;
	//self.linuxLogo = nil;
	//self.ipScanner = nil;
	//self.organizer = nil;
	//self.savedResults = nil;
}


@end

