//
//  Project6AppDelegate.h
//  Project6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Project6ViewController;

@interface Project6AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Project6ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Project6ViewController *viewController;

@end

