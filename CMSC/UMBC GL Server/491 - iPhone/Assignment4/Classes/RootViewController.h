//
//  RootViewController.h
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

/*
 RootViewController
 holds the tableview
 toDoList mutable array
 */

//@class declarations
@class EditingViewController;
@class Task;
@interface RootViewController : UITableViewController {
	//to do list
	NSMutableArray *toDoList;
	EditingViewController *editingView;
	Task *editingTask;
	NSInteger selectedSegmentIndex;
	UISegmentedControl *sortController;
	NSInteger segmentSelect;
	
}

@property (nonatomic, retain) NSMutableArray *toDoList;
@property (nonatomic, retain) IBOutlet EditingViewController *editingView;
@property (nonatomic, retain) Task *editingTask;
@property (nonatomic) NSInteger selectedSegmentIndex;
@property (nonatomic) NSInteger segmentSelect;
@property (nonatomic, retain) UISegmentedControl *sortController;

/*
 IBAction attached to the segmented control
 sorts array based off of sender selection
 */
-(IBAction)changeSelection:(id)sender;

/*
 method sorts the toDoList from A-Z based off of task name
 ignores case
 */
-(void)sortAtoZ;

/*
 method sorts the toDoList from 0-100 based off of percent complete
 */
-(void)sort0to100;

/*
 returns the file path to the document directory to write out the plist
 */
-(NSString *)getFilePath;

/*
 IBAction wired to the + button
 adds another row to the array
 */
-(IBAction)add;


@end
