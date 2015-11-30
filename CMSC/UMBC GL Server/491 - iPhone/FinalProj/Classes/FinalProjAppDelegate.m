//
//  FinalProjAppDelegate.m
//  FinalProj
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "FinalProjAppDelegate.h"
#import "FinalProjViewController.h"

@implementation FinalProjAppDelegate

@synthesize window;
@synthesize viewController;
@synthesize navigationController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
	//[window addSubview:[navigationController view]];
    [window makeKeyAndVisible];
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
