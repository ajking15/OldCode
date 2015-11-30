//
//  LinuxAppDelegate.h
//  Linux
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

@interface LinuxAppDelegate : NSObject <UIApplicationDelegate> {
    
    UIWindow *window;
    UINavigationController *navigationController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;

@end

