//
//  Project2ViewController.m
//  Project2
//
//  Created by  on 9/17/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project2ViewController.h"

@implementation Project2ViewController

@synthesize tipAmount;
@synthesize tipPercent;
@synthesize checkAmount;
@synthesize total;

/* calculates the tip*/
-(IBAction)calculateTip{
	NSScanner *scanner = [NSScanner scannerWithString:self.tipPercent.text];
	BOOL   tipSuccess;
	double tip;
	
	//scans for tip
	if([scanner scanDouble:&tip])
	{
		if(tip > 0)
		{
			//huzzah success
			tipSuccess = YES;
		} else {
			//scanned tip was negative
			self.tipPercent.text = [NSString stringWithFormat:@"Enter a positive Tip Percentage"];
			tipSuccess = NO;
		}
		
	} else {
		//no number
		self.tipAmount.text = [NSString stringWithFormat:@""];
		tipSuccess = NO;
	}
	//scan for check amount
	scanner = [NSScanner scannerWithString:self.checkAmount.text];
	BOOL checkSuccess;
	double check;
	
	if([scanner scanDouble:&check])
	{
		if(check > 0)
		{
			checkSuccess = YES;
		} else {
			self.checkAmount.text = [NSString stringWithFormat:@"Enter a positive Check Amount"];
			checkSuccess = NO;
		}
	} else {
		self.checkAmount.text = [NSString stringWithFormat:@"Enter a Monetary amount ($15.74 = 15.74)"];
		self.total.text = [NSString stringWithFormat:@""];
		checkSuccess = NO;
	}
	
	//check if tip and check amounts are both valid
	if(tipSuccess && checkSuccess == YES)
	{
		if(tip && check > 0)
		{
			//place the number into a string with 2 decimal places for consistent rounding
			self.checkAmount.text = [NSString stringWithFormat:@"%.02f", check];
			self.tipPercent.text  = [NSString stringWithFormat:@"%.02f", tip];
			
			//pull out decimals with two places for accurate math and reprinting
			scanner = [NSScanner scannerWithString:self.checkAmount.text];
			[scanner scanDouble:&check];
			
			scanner = [NSScanner scannerWithString:self.tipPercent.text];
			[scanner scanDouble:&tip];
			
			//do math and adjust print statements	
			double tipPaid = check * (tip / 100);
			double finalTotal = check + tipPaid;
			
			//update fields
			self.tipAmount.text = [NSString stringWithFormat:@"%.02f", tipPaid];
			self.total.text = [NSString stringWithFormat:@"%.02f", finalTotal];
			self.checkAmount.text = [NSString stringWithFormat:@"%.02f", check];
			self.tipPercent.text  = [NSString stringWithFormat:@"%.02f", tip];
		} else
			if(tip < 0)
			{
				self.tipPercent.text = [NSString stringWithFormat:@"Enter a positive Tip Percentage"];
			} else {
				self.checkAmount.text = [NSString stringWithFormat:@"Enter a positive Check Amount"];
			}
	} 
	
}

//do something similar for label maybe
-(BOOL)textFieldShouldReturn:(UITextField *)textField {
	[textField resignFirstResponder];
	//[textField 
	return YES;
}
	
//allows app to rotate orientation
//-(BOOL)shouldAutorotateToInterfaceOrientation:
// (UIInterfaceOrientation)interfaceOrientation {
//	return YES;
//}

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
- (void)loadView{
}
*/


/*
// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];
}
*/


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
	self.tipAmount    = nil;
	self.total        = nil;
	self.tipPercent   = nil;
	self.checkAmount  = nil;
    [super dealloc];
}

@end
