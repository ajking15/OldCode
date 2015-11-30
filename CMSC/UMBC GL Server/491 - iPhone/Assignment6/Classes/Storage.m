//
//  Storage.m
//  Assignment6
//
//  Created by  on 11/2/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Storage.h"


@implementation Storage

@synthesize latitude, longitude, mapType, selectedIndex, currentState, span1, span2;

-(void)encodeWithCoder:(NSCoder *)coder {
	[coder encodeFloat:self.latitude forKey:@"latitude"];
	[coder encodeFloat:self.longitude forKey:@"longitude"];
	[coder encodeDouble:self.span1 forKey:@"span1"];
	[coder encodeDouble:self.span2 forKey:@"span2"];
	[coder encodeInt:self.mapType forKey:@"mapType"];
	[coder encodeInt:self.selectedIndex forKey:@"selectedIndex"];
	[coder encodeObject:self.currentState forKey:@"currentState"];
}

-(id)initWithCoder:(NSCoder *)coder {
	if(self = [super init]){
		self.latitude = [coder decodeFloatForKey:@"latitude"];
		self.longitude = [coder decodeFloatForKey:@"longitude"];
		self.span1 = [coder decodeDoubleForKey:@"span1"];
		self.span2 = [coder decodeDoubleForKey:@"span2"];
		self.mapType = [coder decodeIntForKey:@"mapType"];
		self.selectedIndex = [coder decodeIntForKey:@"selectedIndex"];
		self.currentState = [coder decodeObjectForKey:@"currentState"];
	}
	return self;
}

- (void)dealloc {
    [super dealloc];
	self.currentState = nil;
}


@end
