//
//  Guide.h
//  Linux
//
//  Created by  on 12/5/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "services.h"
#import "ServiceNavigation.h"
#import "SecuringSoftware.h"
#import "OperatingSystems.h"
#import "Tux.h"

@class services;
@class ServiceNavigation;
@class SecuringSoftware;
@class OperatingSystems;
@class Tux;
/*
 main view controller for the linux guide.
 */
@interface Guide : UITableViewController {
	// array of chapters to be used with table of contents
	NSMutableArray *tableContents;
	// the chapters
	services *servChap;
	ServiceNavigation *service;
	SecuringSoftware  *softSec;
	OperatingSystems *opSys;
	Tux *penguin;
}

@property(nonatomic, retain) NSMutableArray *tableContents;
@property(nonatomic, retain) services *servChap;
@property(nonatomic, retain) ServiceNavigation *service;
@property(nonatomic, retain) SecuringSoftware *softSec;
@property(nonatomic, retain) OperatingSystems *opSys;
@property(nonatomic, retain) Tux *penguin;

@end