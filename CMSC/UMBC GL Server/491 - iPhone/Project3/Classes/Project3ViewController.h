//
//  Project3ViewController.h
//  Project3
//
//  Created by Christopher Mai on 9/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
/*
 Project3ViewController class consists of data objects pertaining
 To UIPicker
 and UISegmentedControl
 */
@interface Project3ViewController : UIViewController 
           <UIPickerViewDelegate, UIPickerViewDataSource>{
			   //members related to UIPicker
			   UIPickerView *picker;
			   NSArray *data;
			   UILabel *info;
			   
			   //members related to UISegmentedControl
			   UISegmentedControl *dataTemp;
			   NSInteger selectedSegmentIndex;
			   UILabel *segmentInfo;
			   
			   //members related to my buttons
			   UILabel *from;
			   UILabel *to;
			   UIButton *button1;
			   UIButton *button2;
			   UIButton *button3;
			   UIButton *button4;
		       UIButton *button5;
			   UIButton *button6;
			   UIButton *button7;
			   UIButton *button8;
			   UIButton *button9;
			   UIButton *button0;
			   UIButton *period;
			   UIButton *del;
			   
			   NSString *convert;
}
//UIPicker members
@property(nonatomic, retain) IBOutlet UIPickerView *picker;
@property(nonatomic, retain) NSArray *data;
@property(nonatomic, retain) IBOutlet UILabel *info;
//UISegmentedControl members
@property(nonatomic, retain) IBOutlet UISegmentedControl *dataTemp;
@property(nonatomic, retain) IBOutlet UILabel            *segmentInfo;
@property(nonatomic) NSInteger selectedSegmentIndex;
//Button stuffs
@property(nonatomic, retain) IBOutlet UILabel *from;
@property(nonatomic, retain) IBOutlet UILabel *to;
@property(nonatomic, retain) IBOutlet UIButton *button1;
@property(nonatomic, retain) IBOutlet UIButton *button2;
@property(nonatomic, retain) IBOutlet UIButton *button3;
@property(nonatomic, retain) IBOutlet UIButton *button4;
@property(nonatomic, retain) IBOutlet UIButton *button5;
@property(nonatomic, retain) IBOutlet UIButton *button6;
@property(nonatomic, retain) IBOutlet UIButton *button7;
@property(nonatomic, retain) IBOutlet UIButton *button8;
@property(nonatomic, retain) IBOutlet UIButton *button9;
@property(nonatomic, retain) IBOutlet UIButton *button0;
@property(nonatomic, retain) IBOutlet UIButton *period;
@property(nonatomic, retain) IBOutlet UIButton *del;

/*
 Returns void
 no args
 calls other functions based on situation
 */
-(void)converter;

/*
 Returns void
 no args
 only selecte when temperature selected in segment
 reads in string in from label and converts based off of current uipicker selection
 */
-(void)convertTemp;

/*
 Returns void
 no args
 Only called when data is selected in segment
 reads in string in from label and converts based off of current uipicker selection
 */
-(void)convertData;

/*
 called when segment control is changed
 sender info about the newly selected segment
 */
-(IBAction)changeSelection:(id)sender;

/*
 connected to 0 button on the interface
 adds a 0 to the from string and calls 
 converter
 */
-(IBAction)press0;

/*
 connected to 1 button on the interface
 adds a 1 to the from string and calls 
 converter
 */
-(IBAction)press1;

/*
 connected to 2 button on the interface
 adds a 2 to the from string and calls 
 converter
 */
-(IBAction)press2;

/*
 connected to 3 button on the interface
 adds a 3 to the from string and calls 
 converter
 */
-(IBAction)press3;

/*
 connected to 4 button on the interface
 adds a 4 to the from string and calls 
 converter
 */
-(IBAction)press4;

/*
 connected to 5 button on the interface
 adds a 5 to the from string and calls 
 converter
 */
-(IBAction)press5;

/*
 connected to 6 button on the interface
 adds a 6 to the from string and calls 
 converter
 */
-(IBAction)press6;

/*
 connected to 7 button on the interface
 adds a 7 to the from string and calls 
 converter
 */
-(IBAction)press7;

/*
 connected to 8 button on the interface
 adds a 8 to the from string and calls 
 converter
 */
-(IBAction)press8;

/*
 connected to 9 button on the interface
 adds a 9 to the from string and calls 
 converter
 */
-(IBAction)press9;

/*
 connected to . button on the interface
 adds a . to the from string and calls 
 converter
 */
-(IBAction)pressPeriod;

/*
 connected to delete button on the interface
 adds a delete to the from string and calls 
 converter
 */
-(IBAction)pressDelete;

/*
supposed to be connected to picker but this didn't work for me
 i couldn't seem to connect it to my picker in anyway
 */
-(IBAction)getValue;

/*
 pickerView - view connected to picker
 row - returns current row
 forComponent - current component
 */
-(NSString *)pickerView:(UIPickerView *)pickerView
			titleForRow:(NSInteger)row
		   forComponent:(NSInteger)component;

/*
 returns void
 thePickerView - view connected to picker
 row - current row selected
 component - component changed
 */
-(void)pickerView:(UIPickerView *)thePickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component;

/*
 returns number of components/rows in my picker
 pickerView - view to my picker
 */
-(NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView;

/*
 returns number of items in picker
 pickerView - view to picker
 component- number of rows in component
 */
-(NSInteger)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component; 

@end

			   
