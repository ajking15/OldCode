//
//  Assignment6ViewController.m
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Assignment6ViewController.h"
#import "LocationsViewController.h"

@implementation Assignment6ViewController

@synthesize currentMap, segmentControl, showLocation, nextLocation, prevLocation;
@synthesize locationsArray, coordinate, selectedIndex, locationAnnotation, locationsView, persist;

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

- (void)viewWillAppear:(BOOL)animated {
	[super viewWillAppear:animated];
}

- (void)mapView:(MKMapView *)mapView regionDidChangeAnimated:(BOOL)animated
{
	[self.currentMap region].span.latitudeDelta;
}

- (void)viewWillDisappear:(BOOL)animated {
	[super viewWillDisappear:YES];
	//writes the plist
	
	self.persist.selectedIndex = self.selectedIndex;
	self.persist.mapType = self.segmentControl.selectedSegmentIndex;
	if([self.currentMap showsUserLocation])
	{
		self.persist.currentState = [NSString stringWithFormat:@"YES"];
	} else {
		self.persist.currentState = [NSString stringWithFormat:@"NO"];
	}
	self.persist.span1 = [self.currentMap region].span.latitudeDelta;
	self.persist.span2 = [self.currentMap region].span.latitudeDelta;
	self.persist.latitude = [self.currentMap centerCoordinate].latitude;
	self.persist.longitude = [self.currentMap centerCoordinate].longitude;
	
	NSArray *hello = [NSArray arrayWithObject:self.persist];
	
	BOOL status = [NSKeyedArchiver archiveRootObject:hello toFile:[self getFilePathLocationPersistData]];
	if(!status) {
		NSLog(@"Error saving to do list");
	} 
	[self.persist release];
}

// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
	[self.showLocation setImage:[UIImage imageNamed:@"location.png"]];
	[self.prevLocation setImage:[UIImage imageNamed:@"previous.png"]];
	[self.nextLocation setImage:[UIImage imageNamed:@"next.png"]];
	
	self.selectedIndex = 3;
	self.locationsArray = [NSMutableArray arrayWithObjects:nil, nil];
	
	NSArray *helloAgain = [NSKeyedUnarchiver unarchiveObjectWithFile:[self getFilePathLocationPersistData]];
	
	self.persist = [helloAgain objectAtIndex:0];
	
	NSArray *tempArray = [NSArray arrayWithContentsOfFile:[self getFilePathLocation]];
	
	//set up my app
	if(self.persist == nil)
	{
		self.selectedIndex = 0;
	} else {
		self.segmentControl.selectedSegmentIndex = self.persist.mapType;
		
		switch (self.persist.mapType) {
			case 0:
				self.currentMap.mapType = MKMapTypeStandard;
				break;
			case 1:
				self.currentMap.mapType = MKMapTypeSatellite;
				break;
			default:
				self.currentMap.mapType = MKMapTypeHybrid;
				break;
		}
		
		if([self.persist.currentState isEqualToString:@"NO"])
		{
			[self.currentMap setShowsUserLocation:NO];
			[self.showLocation setStyle:UIBarButtonItemStyleBordered];
		} else {
			[self.currentMap setShowsUserLocation:YES];
			[self.showLocation setStyle:UIBarButtonItemStyleDone];
		}
		
		self.selectedIndex = self.persist.selectedIndex;
	}
	
	for(int x = 0; x < [tempArray count]; x++)
	{
		Location *tempLoc = [Location locationWithTitle:[[tempArray objectAtIndex:x] objectForKey:@"title"] 
										withDescription:[[tempArray objectAtIndex:x] objectForKey:@"description"]  
										  withImageName:[[tempArray objectAtIndex:x] objectForKey:@"image"] 
										   withLatitude:[[[tempArray objectAtIndex:x] valueForKey:@"latitude"] floatValue]
										  withLongitude:[[[tempArray objectAtIndex:x] valueForKey:@"longitude"] floatValue]];
		
		
		self.locationAnnotation = tempLoc;
		
		[self.locationsArray addObject:self.locationAnnotation];
		
		[self.currentMap addAnnotation:self.locationAnnotation];
	}
	
	
	
	if(self.persist == nil)
	{
		self.persist = [[Storage alloc] init];
		
		self.persist.span1 = .5;
		self.persist.span2 = .5;
		
		//NSLog(@"%f  %f", self.persist.span1, self.persist.span2);
		
		[self setCurrentView];
	} else {
		CLLocationCoordinate2D tempCoord = {self.persist.latitude, self.persist.longitude};
		
		
		
		[self.currentMap setRegion:MKCoordinateRegionMake(tempCoord,
														  MKCoordinateSpanMake(self.persist.span1, self.persist.span2)) animated:YES];
		
		[self.currentMap selectAnnotation:[[self.currentMap annotations] objectAtIndex:self.selectedIndex] animated:NO];
	}
}

-(void)annotationSelected:(id)sender {
	UIButton *cipher = sender;
	
	//for some reason this would always be nil
	if(self.locationsView == nil)
	{
		self.locationsView = [[LocationsViewController alloc] init];
	}
	
	self.locationsView.locationInfo = [self.locationsArray objectAtIndex:[cipher tag]];
	
	self.locationsView.modalTransitionStyle = UIModalTransitionStyleFlipHorizontal;	
	//push the side view
	switch ([cipher tag]) {
		case 0:
			//TeaGarden			 
			[self presentModalViewController:self.locationsView animated:YES];
			break;
		case 1:
			//Alcatraz
			[self presentModalViewController:self.locationsView animated:YES];
			break;
		case 2:
			//Rainier
			[self presentModalViewController:self.locationsView animated:YES];
			break;
		case 3:
			//Pike Place
			[self presentModalViewController:self.locationsView animated:YES];
			break;
		default:
			//skyline
			[self presentModalViewController:self.locationsView animated:YES];
			break;
	}
	
	//release
	[self.locationsView release];
}

//updates the selectedIndex
-(IBAction)showNextLocation:(id)sender;
{
	//[[self.locationsArray objectAtIndex:self.selectedIndex] setSelected:NO animated:NO];
	//where 4 is my highest array index
	if(self.selectedIndex == 4)
	{
		self.selectedIndex = 0;
	} else {
		self.selectedIndex++;
	}
	
	[self setCurrentView];
}

//updates the selectedIndex
-(IBAction)showPrevLocation:(id)sender
{
	//[[self.locationsArray objectAtIndex:self.selectedIndex] setSelected:NO animated:NO];
	//where 0 is my lowest array index
	if(self.selectedIndex == 0)
	{
		self.selectedIndex = 4;
	} else {
		self.selectedIndex--;
	}
	
	[self setCurrentView];
}

//focuses the view on pin of the selected index
-(void)setCurrentView {
	[self.currentMap setRegion:MKCoordinateRegionMake([[self.locationsArray objectAtIndex:self.selectedIndex] coordinate],
													  MKCoordinateSpanMake(self.persist.span1, self.persist.span2)) animated:YES];
	
	[self.currentMap selectAnnotation:[[self.currentMap annotations] objectAtIndex:self.selectedIndex] animated:NO];
	
}

//changes map type
-(IBAction)updateMap:(id)sender {
	switch ([sender selectedSegmentIndex]) {
		case 0:
			self.currentMap.mapType = MKMapTypeStandard;
			break;
		case 1:
			self.currentMap.mapType = MKMapTypeSatellite;
			break;
		default:
			self.currentMap.mapType = MKMapTypeHybrid;
			break;
	}
}

//toggles currentLocation
-(IBAction)showCurrentLocation
{
	if([self.currentMap showsUserLocation])
	{
		[self.currentMap setShowsUserLocation:NO];
		[self.showLocation setStyle:UIBarButtonItemStyleBordered];
	} else {
		[self.currentMap setShowsUserLocation:YES];
		[self.showLocation setStyle:UIBarButtonItemStyleDone];
	}
}

-(NSString *)getFilePathLocation {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Assignment6.app", @"locations.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(NSString *)getFilePathLocationPersistData {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Documents", @"PersistState.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

//create my annotation callout
- (MKAnnotationView *)mapView:(MKMapView *)mapView
			viewForAnnotation:(id <MKAnnotation>)annotation
{
	MKPinAnnotationView *testView = nil;
	
	if([annotation isMemberOfClass:[Location class]])
	{
		
		testView = (MKPinAnnotationView *)[mapView dequeueReusableAnnotationViewWithIdentifier:[annotation title]];
		
		if(testView == nil)
		{
			testView = [[MKPinAnnotationView alloc] initWithAnnotation:annotation reuseIdentifier:[annotation title]];
			
			UIButton *customButton = [UIButton buttonWithType:UIButtonTypeDetailDisclosure];
			
			
			for(int x = 0; x < [mapView.annotations count]; x++)
			{
				if([[[mapView.annotations objectAtIndex:x] title] isEqualToString:[annotation title]])
				{
					[customButton setTag:x];
				}
			}
			
			[customButton addTarget:self action:@selector(annotationSelected:) forControlEvents:UIControlEventTouchUpInside];
			
			[testView setRightCalloutAccessoryView:customButton];
		}
		
		
		
		if(testView)
		{
			[testView setPinColor:MKPinAnnotationColorRed];
			[testView setAnimatesDrop:YES];
			[testView setCanShowCallout:YES];
		}
		
	}
	
	return testView;
}

//set selected for my annotations
- (void)mapViewDidFinishLoadingMap:(MKMapView *)mapView {
	[mapView selectAnnotation:[[mapView annotations] objectAtIndex:self.selectedIndex] animated:NO];
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
	self.currentMap = nil;
	self.segmentControl = nil;
	self.showLocation = nil;
	self.nextLocation = nil;
	self.prevLocation = nil;
	self.locationsArray = nil;
	self.locationAnnotation = nil;
	self.locationsView = nil;
	self.persist = nil;
}

@end
