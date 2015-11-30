//
//  LocationsViewController.m
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "LocationsViewController.h"


@implementation LocationsViewController

@synthesize locationInfo, imageView, textView;

/*
 // The designated initializer.  Override if you create the controller programmatically and want to perform customization that is not appropriate for viewDidLoad.
 - (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
 if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
 // Custom initialization
 }
 return self;
 }
 */

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	[self.imageView setImage:[UIImage imageNamed:[locationInfo imageName]]];
	self.textView.text = self.locationInfo.description;
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
//- (void)viewDidLoad {
//    [super viewDidLoad];
//	
//}


-(IBAction)returnToMap
{
	[self dismissModalViewControllerAnimated:YES];
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
	self.locationInfo = nil;
	self.imageView = nil;
	self.textView = nil;
}


@end
