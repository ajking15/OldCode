//
//  Assignment4AppDelegate.m
//  Assignment4
//
//  Created by  on 10/11/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Assignment4AppDelegate.h"
#import "RootViewController.h"


@implementation Assignment4AppDelegate

@synthesize window;
@synthesize navigationController;


#pragma mark -
#pragma mark Application lifecycle

- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
	
	[window addSubview:[navigationController view]];
    [window makeKeyAndVisible];
}


- (void)applicationWillTerminate:(UIApplication *)application {
	// Save data if appropriate
}


#pragma mark -
#pragma mark Memory management

- (void)dealloc {
	[navigationController release];
	[window release];
	[super dealloc];
}


@end

