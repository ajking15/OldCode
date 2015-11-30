//
//  Task.m
//  Project4
//
//  Created by  on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Task.h"


@implementation Task

@synthesize taskToDo;

+(id) taskWithTask:(NSString *) newTask
{
	Task *toDo = [[[Task alloc] init] autorelease];	
	toDo.taskToDo = newTask;
	
	return toDo;
}

@end
