//
//  Project3AppDelegate.h
//  Project3
//
//  Created by  on 9/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Project3ViewController;

@interface Project3AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Project3ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Project3ViewController *viewController;

@end

