//
//  Project2AppDelegate.h
//  Project2
//
//  Created by  on 9/17/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Project2ViewController;

@interface Project2AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Project2ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Project2ViewController *viewController;

@end

