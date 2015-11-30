//
//  Project7AppDelegate.h
//  Project7
//
//  Created by  on 11/8/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Project7ViewController;

@interface Project7AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Project7ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Project7ViewController *viewController;

@end

