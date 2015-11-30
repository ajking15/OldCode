//
//  Project5AppDelegate.h
//  Project5
//
//  Created by  on 10/15/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Project5ViewController;

@interface Project5AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Project5ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Project5ViewController *viewController;

@end

