//
//  Project3AppDelegate.m
//  Project3
//
//  Created by  on 9/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project3AppDelegate.h"
#import "Project3ViewController.h"

@implementation Project3AppDelegate

@synthesize window;
@synthesize viewController;


- (void)applicationDidFinishLaunching:(UIApplication *)application {    
    
    // Override point for customization after app launch    
    [window addSubview:viewController.view];
    [window makeKeyAndVisible];
}


- (void)dealloc {
    [viewController release];
    [window release];
    [super dealloc];
}


@end
