//
//  Project5AppDelegate.m
//  Project5
//
//  Created by  on 10/15/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project5AppDelegate.h"
#import "Project5ViewController.h"

@implementation Project5AppDelegate

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
