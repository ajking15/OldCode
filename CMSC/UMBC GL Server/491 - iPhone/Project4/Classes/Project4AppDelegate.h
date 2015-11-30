//
//  Project4AppDelegate.h
//  Project4
//
//  Created by  on 10/11/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

@interface Project4AppDelegate : NSObject <UIApplicationDelegate> {
    
    UIWindow *window;
    UINavigationController *navigationController;
}

@property (nonatomic, retain) IBOutlet UIWindow *window;
@property (nonatomic, retain) IBOutlet UINavigationController *navigationController;

@end

