//
//  RootViewController.h
//  Linux
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Guide.h"
#import "scanner.h"
#import "ResultsOrganizer.h"

@class scanner;
@class Guide;
@class ResultsOrganizer;
/*
 root view. pushes the guide book, saved scans, and ip scanner
 
 ** For some random reason trying to nil anything out in dealloc was causing problems
 and I had to rearrange the way things work to avoid crashes **
 */
@interface RootViewController : UIViewController {
	Guide *linuxGuide;
	scanner *ipScanner;
	ResultsOrganizer *organizer;
	NSMutableArray *savedResults;
	UIImageView *linuxLogo;
}

@property(nonatomic, retain) NSMutableArray *savedResults;
@property(nonatomic, retain) IBOutlet Guide *linuxGuide;
@property(nonatomic, retain) IBOutlet scanner *ipScanner;
@property(nonatomic, retain) IBOutlet ResultsOrganizer *organizer;
@property(nonatomic, retain) IBOutlet UIImageView *linuxLogo;

/*
 loads the linux guide view controller
 */
-(IBAction)loadLinuxGuide;

/*
 loads the ip scanner view
 */
-(IBAction)loadScanner;

/*
 loads the saved scans view
 */
-(IBAction)loadSaved;

/*
 retrieves the file path of the saved results plist
 */
-(NSString *)getFilePath;

@end
