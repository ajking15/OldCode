//
//  Task.m
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Task.h"


@implementation Task

@synthesize objective, percent, percentNumber;

+ (id)taskWithObjective:(NSString *)newObjective
               percent:(NSString *)newPercent {
	Task *game = [[[Task alloc] init] autorelease];
	game.objective = newObjective;
	game.percent = newPercent;
	
	return game;
}

-(void)encodeWithCoder:(NSCoder *)coder {
	[coder encodeObject:self.objective forKey:@"objective"];
	[coder encodeObject:self.percent forKey:@"percent"];
}

-(id)initWithCoder:(NSCoder *)coder {
	if(self = [super init]){
		self.objective = [coder decodeObjectForKey:@"objective"];
		self.percent   = [coder decodeObjectForKey:@"percent"];
		self.percentNumber = self.percent.doubleValue;
	}
	return self;
}

- (void)dealloc {
    [super dealloc];
	objective = nil;
	percent = nil;
	percentNumber = 0;
}


@end
