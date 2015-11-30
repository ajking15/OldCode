//
//  Project6ViewController.m
//  Project6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project6ViewController.h"

@implementation Project6ViewController

@synthesize locationsArray, currentMap, showLocation, nextLocation, prevLocation;
//@synthesize /*geoCoder,  alcatraz, mtRainer, budda, pikePlace, seattleSkyline;*/
@synthesize currentIndex, segmentControl;

/*
// The designated initializer. Override to perform setup that is required before the view is loaded.
- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil {
    if (self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil]) {
        // Custom initialization
    }
    return self;
}
*/

/*
// Implement loadView to create a view hierarchy programmatically, without using a nib.
- (void)loadView {
}
*/



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	//give my buttons images
	[self.showLocation setImage:[UIImage imageNamed:@"location.png"]];
	[self.prevLocation setImage:[UIImage imageNamed:@"previous.png"]];
	[self.nextLocation setImage:[UIImage imageNamed:@"next.png"]];
	//get the array of dictionary locations
	self.locationsArray = [NSArray arrayWithContentsOfFile:[self getFilePathLocation]];
	
	//NSDictionary *hello = [self.locationsArray objectAtIndex:0];
	//[hello objectForKey:@"description"];
	
	NSDictionary *temp = [self.locationsArray objectAtIndex:0];
	[temp objectForKey:@"title"];
	NSNumber *sigh = [[self.locationsArray objectAtIndex:0] objectForKey:@"latitude"];
	NSLog([temp objectForKey:@"title"]);
	[sigh floatValue];
	NSLog(@"%f", [[temp valueForKey:@"latitude"] floatValue]);
	NSLog(@"%@", [temp valueForKey:@"longitude"]);
	
	//CLLocationCoordinate2D location;
	//location.latitude = [sigh doubleValue];
	
	[self.currentMap setMapType:MKMapTypeStandard];
	
	
}

-(IBAction)showCurrentLocation
{
	//[self.currentMap setCenterCoordinate:location.coordinate];
	if([self.currentMap showsUserLocation])
	//if([self.currentMap userLocation].visible)
	{
		NSLog(@"NO");
		//self.currentMap.showsUserLocation = NO;
		//self.currentMap.setShowsUserLocation = NO;
		[self.currentMap setShowsUserLocation:NO];
	} else {
		NSLog(@"Yes");
		//self.currentMap.showsUserLocation = YES;
		[self.currentMap setShowsUserLocation:YES];
	}
	
//	[self.currentMap setCenterCoordinate:currentMap.region.center animated:NO];
//	self.currentMap = [[MKMapView alloc] initWithFrame:self.view.bounds];
	NSLog(@"showCurrentLocation");
}

-(IBAction)changeMapType{
	NSLog(@"changing map type");
	if(self.segmentControl.selectedSegmentIndex == 0){
		//self.currentMap.mapType = MKMapTypeStandard;
		[self.currentMap setMapType:MKMapTypeStandard];
		NSLog(@"standard");
	} else 
		if (self.segmentControl.selectedSegmentIndex == 1){
			NSLog(@"Satellite");
			//self.currentMap.mapType = MKMapTypeSatellite;
			[self.currentMap setMapType:MKMapTypeSatellite];
			//[self.currentMap mapViewWillStartLoadingMap:
		} else {
			NSLog(@"Hybrid");
			//self.currentMap.mapType = MKMapTypeHybrid;
			[self.currentMap setMapType:MKMapTypeHybrid];
		}
	
}

-(NSString *)getFilePathLocation {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Project6.app", @"locations.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(NSString *)getFilePathImage:(NSString *)picture {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Project6.app", picture, nil];
	
	return [NSString pathWithComponents:segments];
}

/*
// Override to allow orientations other than the default portrait orientation.
- (BOOL)shouldAutorotateToInterfaceOrientation:(UIInterfaceOrientation)interfaceOrientation {
    // Return YES for supported orientations
    return (interfaceOrientation == UIInterfaceOrientationPortrait);
}
*/

- (void)didReceiveMemoryWarning {
	// Releases the view if it doesn't have a superview.
    [super didReceiveMemoryWarning];
	
	// Release any cached data, images, etc that aren't in use.
}

- (void)viewDidUnload {
	// Release any retained subviews of the main view.
	// e.g. self.myOutlet = nil;
}


- (void)dealloc {
    [super dealloc];
}

@end
