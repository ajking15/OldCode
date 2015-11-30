//
//  Task.h
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 Custom class which holds the information for each cell in the table
 */
@interface Task : NSObject <NSCoding>{
	//two strings to represent info
	NSString *objective;
	NSString *percent;
	//due to the way nssortdescriptors work also stored as a double
	double percentNumber;
}

@property(nonatomic, retain) NSString	*objective;
@property(nonatomic, retain) NSString *percent;
@property(nonatomic) double percentNumber;

/*
 task constructor
 newObjective string representing new item in the toDo list
 newPercent string representing percent completion
 id returned a task object
 */
+ (id)taskWithObjective:(NSString *)newObjective
               percent:(NSString *)newPercent;

@end
