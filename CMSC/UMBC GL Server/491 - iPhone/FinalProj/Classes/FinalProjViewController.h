//
//  FinalProjViewController.h
//  FinalProj
//
//  Created by  on 12/5/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@class Guide;
@interface FinalProjViewController : UIViewController {
	Guide *linuxGuide;
}

@property(nonatomic, retain) IBOutlet Guide *linuxGuide;

-(IBAction)loadLinuxGuide;

@end

