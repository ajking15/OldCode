//
//  EditingViewController.m
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "EditingViewController.h"
#import "Task.h"

@implementation EditingViewController

//synthesize my properties 
@synthesize taskObjective, currentTask, percentComplete;
@synthesize sliderValue, percentSlider;

-(IBAction)sliderMoving
{
	//update some of my values on slider movement
   	self.percentComplete.text = [NSString stringWithFormat:@"%.0f %%", percentSlider.value];
	currentTask.percent = [NSString stringWithFormat:@"%.0f", percentSlider.value];
	currentTask.percentNumber = [currentTask.percent doubleValue];
	
}

//gets called when you load the edit window
-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	self.taskObjective.text = currentTask.objective;
	
	self.percentComplete.text = [NSString stringWithFormat:@"%@%%", currentTask.percent];
	percentSlider.value = currentTask.percent.doubleValue;
	
	[taskObjective becomeFirstResponder];
	
}

//called when slider is called
- (void)setValue:(float)value animated:(BOOL)animated
{
	self.percentComplete.text = [NSString stringWithFormat:@"%f", value];
}

-(void)textViewDidChange:(UITextView *)textView {
 	self.currentTask.objective = textView.text;	
}



//this is called when keyboard disappears. current status unused
-(void)textViewDidEndEditing:(UITextView *)textView {
	if([textView.text isEqualToString:@""])
	{
		textView.text = [NSString stringWithFormat:@"New Task"];	
	}
	self.currentTask.objective = textView.text;
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
	taskObjective = nil;
	currentTask = nil;
	percentComplete = nil;
	sliderValue = 0;
	percentSlider = nil;
}


@end
