//
//  Storage.h
//  Assignment6
//
//  Created by  on 11/2/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>


@interface Storage : NSObject <NSCoding>{
	int mapType;
	int selectedIndex;
	NSString * currentState;
	float latitude;
	float longitude;	
	double span1;
	double span2;
}

@property(nonatomic) int mapType;
@property(nonatomic) int selectedIndex;
@property(nonatomic, retain) NSString *currentState;
@property(nonatomic) float latitude;
@property(nonatomic) float longitude;
@property(nonatomic) double span1;
@property(nonatomic) double span2;

@end
