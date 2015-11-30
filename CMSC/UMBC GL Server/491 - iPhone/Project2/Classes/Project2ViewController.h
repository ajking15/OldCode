//
//  Project2ViewController.h
//  Project2
//
//  Created by Christopher Mai on 9/17/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>


/*
 holds labels and textfields for interface
 */
@interface Project2ViewController : UIViewController <UITextFieldDelegate>{
	UILabel *tipAmount;
	UILabel *total;
	UITextField *checkAmount;
	UITextField *tipPercent;
}

/*
 connected to calculate button. determines tip and final total based off of entries in the 
 text fields check amount and tip percentage
 */
-(IBAction)calculateTip;

@property(nonatomic, retain) IBOutlet UILabel *tipAmount;  
@property(nonatomic, retain) IBOutlet UILabel *total;
@property(nonatomic, retain) IBOutlet UITextField *checkAmount;
@property(nonatomic, retain) IBOutlet UITextField *tipPercent;

@end

