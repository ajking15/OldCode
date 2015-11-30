//
//  LocationsViewController.h
//  Assignment6
//
//  Created by  on 10/28/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Location.h"

/*
 this will probably get changed to be the flip view
 */

@class Location;
@interface LocationsViewController : UIViewController {
	Location *locationInfo;
	UITextView *textView;
	UIImageView *imageView;
	
}

@property(nonatomic, retain) Location *locationInfo;
@property(nonatomic, retain) IBOutlet UITextView *textView;
@property(nonatomic, retain) IBOutlet UIImageView *imageView;

/*
 reassigns the modal view
 */
-(IBAction)returnToMap;

@end
