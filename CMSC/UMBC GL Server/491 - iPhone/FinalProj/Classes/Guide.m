//
//  Guide.m
//  FinalProj
//
//  Created by  on 12/5/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Guide.h"


@implementation Guide


@synthesize tableContents;
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
	
	self.tableContents = [NSMutableArray arrayWithObjects:@"Basic Commands", @"Services", @"Software",
						  @"Devices", @"Operating Systems", nil];
	
	//[self.tableView reloadData];
}


-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	self.tableContents = [NSMutableArray arrayWithObjects:@"Basic Commands", @"Services", @"Software",
						  @"Devices", @"Operating Systems", nil];

	//self.taskObjective.text = currentTask.objective;
//	
//	self.percentComplete.text = [NSString stringWithFormat:@"%@%%", currentTask.percent];
//	percentSlider.value = currentTask.percent.doubleValue;
//	
//	[taskObjective becomeFirstResponder];
	
}


#pragma mark Table view methods

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    return 1;
}


// Customize the number of rows in the table view.
- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section {
    return [self.tableContents count];
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
    cell.textLabel.text = [self.tableContents objectAtIndex:indexPath.row];
	cell.detailTextLabel.text = @"hello";//[NSString stringWithFormat:@"%@%% completed", [[self.toDoList objectAtIndex:indexPath.row] percent]];
	
	//change text based off of percent complete
    //if([[self.toDoList objectAtIndex:indexPath.row] percent].doubleValue == 100)
//	{
//		cell.textLabel.textColor = [UIColor grayColor];
//	} else {
//		cell.textLabel.textColor = [UIColor blackColor];
//	}
	
	return cell;
}




// Override to support row selection in the table view.
- (void)tableView:(UITableView *)tableView
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	
    // Navigation logic may go here -- for example, create and push another view controller.
	// AnotherViewController *anotherViewController = [[AnotherViewController alloc] initWithNibName:@"AnotherView" bundle:nil];
	//self.editingTask = [self.toDoList objectAtIndex:indexPath.row];
//	self.editingView.currentTask = self.editingTask;
//	[self.navigationController pushViewController:self.editingView animated:YES];
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
		//[self.toDoList removeObjectAtIndex:indexPath.row];
       // [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
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
}


@end
