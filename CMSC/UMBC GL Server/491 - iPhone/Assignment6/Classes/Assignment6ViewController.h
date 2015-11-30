//
//  Assignment6ViewController.h
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <MapKit/MapKit.h>
#import "Location.h"
#import "Storage.h"

@class Location;
@class Storage;
@class LocationsViewController;
@interface Assignment6ViewController : UIViewController <MKMapViewDelegate>{
	MKMapView *currentMap;
	UISegmentedControl *segmentControl;
	UIBarButtonItem *showLocation;
	UIBarButtonItem *nextLocation;
	UIBarButtonItem *prevLocation;
	NSMutableArray *locationsArray;
	int selectedIndex;
	CLLocationCoordinate2D coordinate;
	Storage *persist;
	Location *locationAnnotation;
	LocationsViewController *locationsView;	
}

@property(nonatomic, retain) IBOutlet MKMapView *currentMap;
@property(nonatomic, retain) IBOutlet UISegmentedControl *segmentControl;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *showLocation;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *nextLocation;
@property(nonatomic, retain) IBOutlet UIBarButtonItem *prevLocation;
@property(nonatomic, retain) NSMutableArray *locationsArray;
@property(nonatomic, assign) CLLocationCoordinate2D coordinate;
@property(nonatomic) int selectedIndex;
@property(nonatomic, retain) IBOutlet LocationsViewController *locationsView;
@property(nonatomic, retain) Location *locationAnnotation;
@property(nonatomic, retain) Storage *persist;

/*
 changes the mapType
 sender is the segment control
 */
-(IBAction)updateMap:(id)sender;

/*
 displays the flip view
 sender is the button
 */
-(void)annotationSelected:(id)sender; 

/*
 Drops the blue pin which denotes current location
 */
-(IBAction)showCurrentLocation;

/*
 Sends you to the next pin in the view
 */
-(IBAction)showNextLocation:(id)sender;

/*
 Sends you to the previous pin in the view
 */
-(IBAction)showPrevLocation:(id)sender;

/*
 Gets the location of the locations.plist
 */
-(NSString *)getFilePathLocation;

/*
 Finds the file path of the persisted data plist
 */
-(NSString *)getFilePathLocationPersistData;

/*
 changes the view to center on currently selected pin
 */
-(void)setCurrentView;

@end

