//
//  SimonAppDelegate.m
//  Simon
//
//  Created by Dan Hood on 11/14/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "SimonAppDelegate.h"
#import "GLView.h"

@implementation SimonAppDelegate

@synthesize window;
@synthesize glView;

- (void)applicationDidFinishLaunching:(UIApplication *)application {
    
}


- (void)applicationWillResignActive:(UIApplication *)application {

}


- (void)applicationDidBecomeActive:(UIApplication *)application {

}


- (void)dealloc {
	[window release];
	[glView release];
	[super dealloc];
}

@end
