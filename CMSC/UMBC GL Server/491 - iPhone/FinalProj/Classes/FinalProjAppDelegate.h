//
//  FinalProjAppDelegate.h
//  FinalProj
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FinalProjViewController;

@interface FinalProjAppDelegate : NSObject <UIApplicationDelegate> {
    UIWindow *window;
    FinalProjViewController *viewController;
	UINavigationController *navigationController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet FinalProjViewController *viewController;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;

@end

