//
//  ScanResults.h
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "Result.h"

@class Result;
/*
 called by results organizer. displays saved scans information
 */
@interface ScanResults : UIViewController {
	Result *scanReport;
	// need fields represenative of the scanReport
	UILabel *ipAddress;
	UILabel *hostName;
	UILabel *serverType;
	UILabel *sshCheck;
	UILabel *telnetCheck;
	UILabel *ftpCheck;
}

@property(nonatomic, retain) Result   *scanReport;
@property(nonatomic, retain) IBOutlet UILabel *ipAddress;
@property(nonatomic, retain) IBOutlet UILabel *hostName;
@property(nonatomic, retain) IBOutlet UILabel *serverType;
@property(nonatomic, retain) IBOutlet UILabel *sshCheck;
@property(nonatomic, retain) IBOutlet UILabel *telnetCheck;
@property(nonatomic, retain) IBOutlet UILabel *ftpCheck;

@end
