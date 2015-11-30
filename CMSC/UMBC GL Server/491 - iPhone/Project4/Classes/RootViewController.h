//
//  RootViewController.h
//  Project4
//
//  Created by  on 10/11/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//
#import <Foundation/Foundation.h>
#import "Task.h"


@interface RootViewController : UITableViewController {
	NSMutableArray *tasks;
}

@property(nonatomic, retain) NSMutableArray *tasks;

@end
