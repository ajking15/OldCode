//
//  Assignment6AppDelegate.m
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Assignment6AppDelegate.h"
#import "Assignment6ViewController.h"

@implementation Assignment6AppDelegate

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
