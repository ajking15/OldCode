//
//  RootViewController.m
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "RootViewController.h"
#import "Task.h"
#import "EditingViewController.h"

@implementation RootViewController

@synthesize toDoList, editingView, editingTask, sortController, selectedSegmentIndex, segmentSelect;

- (void)viewDidLoad {
    [super viewDidLoad];
   
    //loads the toDoList from the plist if it exists
	self.toDoList = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePath]];
	//[self.tableView reloadData];
	
	//sorts the loaded toDoList
	if(segmentSelect == 0)
	{
		[self sortAtoZ];
		//NSLog(@"should be sorting A to Z");
	} else {
		//NSLog(@"should be sorting 0 to 100");
		[self sort0to100];
	}
	//opens up the edit button item
     self.navigationItem.leftBarButtonItem = self.editButtonItem;
}

- (void)viewWillAppear:(BOOL)animated {
    [super viewWillAppear:animated];
	if(editingTask) {
		//sortController.
		if(segmentSelect == 0)
		{
			if([editingTask.objective isEqualToString:@""])
			{
				editingTask.objective = [NSString stringWithFormat:@"New Task"];	
			}
			[self sortAtoZ];
			//NSLog(@"should be sorting A to Z");
		} else {
			//NSLog(@"should be sorting 0 to 100");
			if([editingTask.objective isEqualToString:@""])
			{
				editingTask.objective = [NSString stringWithFormat:@"New Task"];	
			}
			
			[self sort0to100];
		}
		
		[self.tableView reloadData];
		
		NSIndexPath *path = [NSIndexPath indexPathForRow:[self.toDoList
														  indexOfObject:self.editingView] inSection:0];
		NSArray *paths = [NSArray arrayWithObject:path];
		[self.tableView reloadRowsAtIndexPaths:paths withRowAnimation:NO];
		editingTask = nil;
	}
	
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:YES];
	//writes the plist
	BOOL status = [NSKeyedArchiver archiveRootObject:self.toDoList toFile:[self getFilePath]];
	
	if(!status) {
		NSLog(@"Error saving to do list");
	}
}


-(IBAction)changeSelection:(id)sender
{
	//sort my mutable array based off of which selector was chosen
	if([[sender titleForSegmentAtIndex: [sender selectedSegmentIndex]] isEqualToString:@"A-Z"])
	{
	    //sort A-Z	objective
		sortController.selectedSegmentIndex = 0;
		segmentSelect = 0;
		[self sortAtoZ];			
	} else {
	    //sort by percent complete   percent
		sortController.selectedSegmentIndex = 1;
		segmentSelect = 1;
		[self sort0to100];
	}
	//reload table
	[self.tableView reloadData];
}

-(void)sortAtoZ
{
	//creates a sort descripter based off of my objective. with selector caseInsensitiveCompare
	NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"objective" ascending:YES 
																	selector:@selector(caseInsensitiveCompare:)];

	//place descriptor in the array
	NSArray *sortDescriptors = [[NSArray alloc] initWithObjects:&sortDescriptor count:1];
	
	//sort the toDoList
	[toDoList sortUsingDescriptors:sortDescriptors];
	
	//release the descriptor and array
	[sortDescriptors release];
	[sortDescriptor release];	
}

-(void)sort0to100
{
	//sorts based off of the percentNumber which is a double which goes along side my string percent
	NSSortDescriptor *sortDescriptor = [[NSSortDescriptor alloc] initWithKey:@"percentNumber" ascending:YES];
		
	//place is array
	NSArray *sortDescriptors = [[NSArray alloc] initWithObjects:&sortDescriptor count:1];	
	
	//sort
	[toDoList sortUsingDescriptors:sortDescriptors];
	
	//release descriptor and array
	[sortDescriptors release];
	[sortDescriptor release];
}

-(NSString *)getFilePath {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"toDoList.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(IBAction)add {
	//checks case on initial startup where array is nil and uninitialized
	if(toDoList == nil)
	{
		self.toDoList = [NSMutableArray arrayWithObjects:nil, nil];	
	}
	//new task
	Task *newTask = [[Task alloc] init];
	newTask.objective = [NSString stringWithFormat:@""];
	newTask.percent = [NSString stringWithFormat:@"0"];
	newTask.percentNumber  = [newTask.percent doubleValue];
	editingTask = newTask;
	self.editingView.currentTask = newTask;
	[self.navigationController pushViewController:self.editingView animated:YES];
	[self.toDoList addObject: newTask];
	
	//sorting my list
	if(sortController.selectedSegmentIndex == 0)
	{
		[self sortAtoZ];
	} else {
		[self sort0to100];
	}
	
	//add to table
	NSIndexPath *newTaskPath = [NSIndexPath indexPathForRow:[self.toDoList count] - 1 inSection:0];
	
	NSArray *newTaskPaths = [NSArray arrayWithObject:newTaskPath];
	[self.tableView insertRowsAtIndexPaths:newTaskPaths withRowAnimation:NO];
	
	[newTask release];
}
/*
- (void)viewDidAppear:(BOOL)animated {
    [super viewDidAppear:animated];
}
*/
/*
- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:animated];
}
*/
/*
- (void)viewDidDisappear:(BOOL)animated {
	[super viewDidDisappear:animated];
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


#pragma mark Table view methods

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// Customize the number of rows in the table view.
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.toDoList count];
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
    cell.textLabel.text = [[self.toDoList objectAtIndex:indexPath.row] objective];
	cell.detailTextLabel.text = [NSString stringWithFormat:@"%@%% completed", [[self.toDoList objectAtIndex:indexPath.row] percent]];
	
	//change text based off of percent complete
    if([[self.toDoList objectAtIndex:indexPath.row] percent].doubleValue == 100)
	{
		cell.textLabel.textColor = [UIColor grayColor];
		} else {
		cell.textLabel.textColor = [UIColor blackColor];
	}
		
	return cell;
}




// Override to support row selection in the table view.
- (void)tableView:(UITableView *)tableView
  didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

    // Navigation logic may go here -- for example, create and push another view controller.
	// AnotherViewController *anotherViewController = [[AnotherViewController alloc] initWithNibName:@"AnotherView" bundle:nil];
	self.editingTask = [self.toDoList objectAtIndex:indexPath.row];
	self.editingView.currentTask = self.editingTask;
	[self.navigationController pushViewController:self.editingView animated:YES];
	// [anotherViewController release];
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
		[self.toDoList removeObjectAtIndex:indexPath.row];
        [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
    }   
  //  else if (editingStyle == UITableViewCellEditingStyleInsert) {
//        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
//    }   
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


- (void)dealloc {
    [super dealloc];
	toDoList = nil;
	editingView = nil;
	editingTask = nil;
	sortController = nil;
	selectedSegmentIndex = 0;
	segmentSelect = 0;
}


@end

