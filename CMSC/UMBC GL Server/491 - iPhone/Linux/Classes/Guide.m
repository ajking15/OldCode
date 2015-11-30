//
//  Guide.m
//  Linux
//
//  Created by  on 12/5/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Guide.h"


@implementation Guide

@synthesize tableContents, service, servChap, softSec, opSys, penguin;

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
	
	self.tableContents = [NSMutableArray arrayWithObjects:@"Basic Commands", @"Securing Services", @"Security Software",
						  @"Operating Systems", nil];
	
	//[self.tableView reloadData];
}


-(void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
	
	self.tableContents = [NSMutableArray arrayWithObjects:@"Basic Commands", @"Securing Services", @"Security Software",
						  @"Operating Systems", @"Tux", nil];
	
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
	
	return cell;
}

/*
 Linux
 Debian
 Ubuntu
 Red Hat Linux
 Fedora
 SUSE
 Slackware
 Gentoo
 Unix
 Solaris
 HP-UX
 FreeBSD
 AIX
 */


// Override to support row selection in the table view.
- (void)tableView:(UITableView *)tableView
didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
	//http://en.wikipedia.org/wiki/Tux
    // Navigation logic may go here -- for example, create and push another view controller.
	
	if([indexPath indexAtPosition:[indexPath length] - 1] == 0)
	{
		// commands
		self.service = [[ServiceNavigation alloc] init];
		[self.navigationController pushViewController:self.service animated:YES];
		[self.service release];
		self.service = nil;
	} else 
		if([indexPath indexAtPosition:[indexPath length] - 1] == 1)
		{
			// services
			self.servChap = [[services alloc] init];
			[self.navigationController pushViewController:self.servChap animated:YES];
			[self.servChap release];
			self.servChap = nil;
		} else
			if([indexPath indexAtPosition:[indexPath length] - 1] == 2)
			{
				// software
				self.softSec = [[SecuringSoftware alloc] init];
				[self.navigationController pushViewController:self.softSec animated:YES];
				[self.softSec release];
				self.softSec = nil;
			} else 
				if([indexPath indexAtPosition:[indexPath length] - 1] == 3){
					// operating systems
					self.opSys = [[OperatingSystems alloc] init];
					[self.navigationController pushViewController:self.opSys animated:YES];
					[self.opSys release];
					self.opSys = nil;
				} else {
					// tux
					self.penguin = [[Tux alloc] init];
					[self.navigationController pushViewController:self.penguin animated:YES];
					[self.penguin release];
					self.penguin = nil;
				}
	
	
}




// Override to support conditional editing of the table view.
- (BOOL)tableView:(UITableView *)tableView
canEditRowAtIndexPath:(NSIndexPath *)indexPath {
    // Return NO if you do not want the specified item to be editable.
    return NO;
}




// Override to support editing the table view.
//- (void)tableView:(UITableView *)tableView commitEditingStyle:(UITableViewCellEditingStyle)editingStyle forRowAtIndexPath:(NSIndexPath *)indexPath {
//    
//    if (editingStyle == UITableViewCellEditingStyleDelete) {
//        // Delete the row from the data source.
//		//[self.toDoList removeObjectAtIndex:indexPath.row];
//		// [tableView deleteRowsAtIndexPaths:[NSArray arrayWithObject:indexPath] withRowAnimation:UITableViewRowAnimationFade];
//    }   
//	//  else if (editingStyle == UITableViewCellEditingStyleInsert) {
//	//        // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view.
//	//    }   
//}



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
}


@end
