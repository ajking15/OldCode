//
//  Location.h
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <MapKit/MapKit.h>

@interface Location : NSObject <MKAnnotation> {
	CLLocationCoordinate2D coordinate;
	NSString *title;
	NSString *description;
	NSString *imageName;
	
}
@property(nonatomic, assign) CLLocationCoordinate2D coordinate;
@property(nonatomic, copy) NSString *title;
@property(nonatomic, copy) NSString *description;
@property(nonatomic, copy) NSString *imageName;

/*
 newTitle title from plist
 newDesctiption description from plist
 newImageName name of the image
 newLatitude float value of the lat
 newLongitude float value of the lat
 */
+(id)locationWithTitle:(NSString *)newTitle
	   withDescription:(NSString *)newDescription
		 withImageName:(NSString *)newImageName
		  withLatitude:(float)newLatitude
		 withLongitude:(float)newLongitude;

@end
