//
//  Assignment6AppDelegate.h
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Assignment6ViewController;

@interface Assignment6AppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    Assignment6ViewController *viewController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet Assignment6ViewController *viewController;

@end

