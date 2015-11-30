//
//  Task.h
//  Project4
//
//  Created by  on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Task : NSObject {
	NSString *taskToDo;
}

@property(nonatomic, copy) NSString *taskToDo;

+(id)taskWithTask:(NSString *)newTask;

@end
