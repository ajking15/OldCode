//
//  Location.m
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Location.h"

@implementation Location
@synthesize coordinate, title, description, imageName;// latitude, longitude;
+(id)locationWithTitle:(NSString *)newTitle
	   withDescription:(NSString *)newDescription
		 withImageName:(NSString *)newImageName
		  withLatitude:(float)newLatitude
		 withLongitude:(float)newLongitude
{
	Location *temp = [[Location alloc] init];
	CLLocationCoordinate2D newCoordinate = {newLatitude, newLongitude};
	temp.title = newTitle;
	temp.description = newDescription;
	temp.imageName = newImageName;
	temp.coordinate = newCoordinate;
	
	return temp;	
}

- (void)dealloc {
    [super dealloc];
	self.title = nil;
	self.description = nil;
	self.imageName = nil;
}


@end
