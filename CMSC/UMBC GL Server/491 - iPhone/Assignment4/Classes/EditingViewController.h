//
//  EditingViewController.h
//  Assignment4
//
//  Created by Chris Mai on 10/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

/*
 Subview where I edit my tasks
 */

@class Task;
@interface EditingViewController : UIViewController <UITextViewDelegate>{
	//members of the edit view
	IBOutlet UITextView *taskObjective;
	IBOutlet UILabel    *percentComplete;
	float sliderValue;
	UISlider *percentSlider;
	Task *currentTask;

}

//edit view members
@property(nonatomic, retain) Task *currentTask;
@property(nonatomic, retain) IBOutlet UITextView *taskObjective;
@property(nonatomic, retain) IBOutlet UILabel    *percentComplete;
@property(nonatomic) float sliderValue;
@property(nonatomic, retain) IBOutlet UISlider *percentSlider;

/*
 called when slider is moved
 updates info related to slider
 */
-(IBAction)sliderMoving;

@end
