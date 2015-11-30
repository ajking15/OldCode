//
//  ServiceNavigation.m
//  Linux
//
//  Created by  on 12/12/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "ServiceNavigation.h"


@implementation ServiceNavigation


@synthesize serviceChapters,  gencmd, unucmd, fedcmd;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        // Custom initialization
    }
    return self;
}
*/


// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	
	self.serviceChapters = [NSMutableArray arrayWithObjects:@"General", @"Ubuntu", @"Fedora",
						  @"Debian", @"Red Hat", nil];
}

-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	self.serviceChapters = [NSMutableArray arrayWithObjects:@"General", @"Ubuntu", @"Fedora", nil];
		
	//self.taskObjective.text = currentTask.objective;
	//	
	//	self.percentComplete.text = [NSString stringWithFormat:@"%@%%", currentTask.percent];
	//	percentSlider.value = currentTask.percent.doubleValue;
	//	
	//	[taskObjective becomeFirstResponder];
	
}

//-(NSString *)getFilePath {
//	//gets the file path of the home dir.
//	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"results.plist", nil];
//	
//	return [NSString pathWithComponents:segments];
//}

#pragma mark Table view methods

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// Customize the number of rows in the table view.
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.serviceChapters count];
}


// Customize the appearance of table view cells.
- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    static NSString *CellIdentifier = @"Default";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:CellIdentifier];
    if (cell == nil) {
        cell = [[[UITableViewCell alloc] initWithStyle:UITableViewCellStyleSubtitle //changed defalut to subtitle
									   reuseIdentifier:CellIdentifier] autorelease];
    }
    
	// Configure the cell.
	cell.accessoryType = UITableViewCellAccessoryDisclosureIndicator;
    cell.textLabel.text = [self.serviceChapters objectAtIndex:indexPath.row];
		
	return cell;
}




// Override to support row selection in the table view.
- (void)tableView:(UITableView *)tableView
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
    // Navigation logic may go here -- for example, create and push another view controller.
		
	if([indexPath indexAtPosition:[indexPath length] - 1] == 0)
	{
		
		self.gencmd  = [[general alloc] init];
		[self.navigationController pushViewController:self.gencmd animated:YES];
		[self.gencmd release];
		self.gencmd = nil;
	} else 
		if([indexPath indexAtPosition:[indexPath length] - 1] == 1)
		{
			// ubuntu
			self.unucmd  = [[ubuntu alloc] init];
			[self.navigationController pushViewController:self.unucmd animated:YES];
			[self.unucmd release];
			self.unucmd = nil;
		} else
			if([indexPath indexAtPosition:[indexPath length] - 1] == 2)
			{
				// fedora
				self.fedcmd = [[fedora alloc] init];
				[self.navigationController pushViewController:self.fedcmd animated:YES];
				[self.fedcmd release];
				self.fedcmd = nil;
			} 	
	
}




// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView
canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}


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
	//self.serviceChapters = nil;
//	self.gencmd = nil;
//	self.unucmd = nil;
//	self.fedcmd = nil;
}


@end
