//
//  Project6ViewController.h
//  Project6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreLocation/CoreLocation.h>
#import <MapKit/MapKit.h>

@interface Project6ViewController : UIViewController <MKMapViewDelegate, CLLocationManagerDelegate>{
	NSArray *locationsArray;
	MKMapView *currentMap;
	//MKReverseGeocoder *geoCoder;
//	MKPlacemark *alcatraz;
//	MKPlacemark *budda;
//	MKPlacemark *mtRainer;
//	MKPlacemark *pikePlace;
//	MKPlacemark *seattleSkyline;
	UIBarButtonItem *showLocation;
	UIBarButtonItem *nextLocation;
	UIBarButtonItem *prevLocation;
	NSInteger        currentIndex;
	UISegmentedControl *segmentControl;
	CLLocationManager *locationManager;
	
}

@property(nonatomic, retain) CLLocationManager *locationManager;
@property(nonatomic, retain) NSArray *locationsArray;
@property(nonatomic, retain) IBOutlet MKMapView *currentMap;
//@property(nonatomic, retain) MKReverseGeocoder *geoCoder;
//@property(nonatomic, retain) MKPlacemark *alcatraz;
//@property(nonatomic, retain) MKPlacemark *budda;
//@property(nonatomic, retain) MKPlacemark *mtRainer;
//@property(nonatomic, retain) MKPlacemark *pikePlace;
//@property(nonatomic, retain) MKPlacemark *seattleSkyline;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *showLocation;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *nextLocation;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *prevLocation;
@property(nonatomic) NSInteger currentIndex;
@property(nonatomic, retain) IBOutlet UISegmentedControl *segmentControl;

-(NSString *)getFilePathLocation;

-(NSString *)getFilePathImage:(NSString *)picture; 

-(IBAction)showCurrentLocation;

-(IBAction)changeMapType:(id)sender;
@end

