//
//  ResultsOrganizer.h
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ScanResults.h"
#import "Result.h"

@class Result;
@class ScanResults;
/*
 navigation view controller that stores saved scans shown by IP address.
 similar to assignment 4. supports swipe delete
 */
@interface ResultsOrganizer : UIViewController {
	NSMutableArray *savedScans;
	ScanResults *resultScan;
	Result *indivResult;
}

@property(nonatomic, retain) NSMutableArray *savedScans;
@property(nonatomic, retain) ScanResults *resultScan;
@property(nonatomic, retain) Result *indivResult;

/*
 gets file path of results.plist
 */
-(NSString *)getFilePath;


@end
