//
//  Project7AppDelegate.m
//  Project7
//
//  Created by  on 11/8/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project7AppDelegate.h"
#import "Project7ViewController.h"

@implementation Project7AppDelegate

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
