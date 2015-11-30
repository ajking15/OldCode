//
//  ResultsOrganizer.m
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "ResultsOrganizer.h"


@implementation ResultsOrganizer

@synthesize savedScans, resultScan, indivResult;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        // Custom initialization
    }
    return self;
}
*/


- (void)viewDidLoad {
    [super viewDidLoad];
	
    //loads the toDoList from the plist if it exists
	self.savedScans = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
	if(!self.savedScans)
	{
		self.savedScans = [NSMutableArray arrayWithObjects: nil, nil];
	}
	
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:YES];
	//writes the plist
	BOOL status = [NSKeyedArchiver archiveRootObject:self.savedScans toFile:[self getFilePath]];
	
	if(!status) {
		NSLog(@"Error saving to do list");
	}
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
}


-(NSString *)getFilePath {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"results.plist", nil];
	//BOOL status = [NSKeyedArchiver archiveRootObject:self.toDoList toFile:[self getFilePath]];
//	
//	if(!status) {
//		NSLog(@"Error saving to do list");
//	}
	
	return [NSString pathWithComponents:segments];
}


#pragma mark Table view methods

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// Customize the number of rows in the table view.
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.savedScans count];
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
    cell.textLabel.text = [[self.savedScans objectAtIndex:indexPath.row] ipAddress];
		
	return cell;
}




// Override to support row selection in the table view.
- (void)tableView:(UITableView *)tableView
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
	if(self.resultScan == nil)
	{
		self.resultScan = [[ScanResults alloc] init];
	}
	self.indivResult = [self.savedScans objectAtIndex:indexPath.row];
	self.resultScan.scanReport = self.indivResult;
	[self.navigationController pushViewController:self.resultScan animated:YES];
	[self.resultScan release];
	self.resultScan = nil;
}




// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView
canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return YES;
}




// Override to support editing the table view.
- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (editingStyle == UITableViewCellEditingStyleDelete) {
        // Delete the row from the data source.
		[self.savedScans removeObjectAtIndex:indexPath.row];
		BOOL status = [NSKeyedArchiver archiveRootObject:self.savedScans toFile:[self getFilePath]];
		
		if(!status) {
			NSLog(@"Error saving to do list");
		}
		
		//self.savedScans = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
//		if(!self.savedScans)
//		{
//			self.savedScans = [NSMutableArray arrayWithObjects: nil, nil];
//		}
		
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
}



/*
 // Override to support rearranging the table view.
 - (void)tableView:(UITableView *)tableView moveRowAtIndexPath:(NSIndexPath *)fromIndexPath toIndexPath:(NSIndexPath *)toIndexPath {
 }
 */


/*
 // Override to support conditional rearranging of the table view.
 - (BOOL)tableView:(UITableView *)tableView canMoveRowAtIndexPath:(NSIndexPath *)indexPath {
 // Return NO if you do not want the item to be re-orderable.
 return YES;
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
	//self.savedScans = nil;
	//self.resultScan = nil;
	//self.indivResult = nil;
}


@end
