//
//  ServiceNavigation.h
//  Linux
//
//  Created by  on 12/12/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "general.h"
#import "ubuntu.h"
#import	"fedora.h"

@class fedora;
@class ubuntu;
@class general;
/*
 subchapter of the guide class. holds views to three chapters on services 
 */
@interface ServiceNavigation : UIViewController {
	NSMutableArray *serviceChapters;
	general *gencmd;
	ubuntu  *unucmd;
	fedora  *fedcmd;
}

@property(nonatomic, retain) NSMutableArray *serviceChapters;
@property(nonatomic, retain) general *gencmd;
@property(nonatomic, retain) ubuntu  *unucmd;
@property(nonatomic, retain) fedora  *fedcmd;


@end
