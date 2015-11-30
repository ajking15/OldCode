//
//  Project3ViewController.m
//  Project3
//
//  Created by Christopher Mai on 9/28/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

/* To Do
 Each time one of my custom buttons is pressed I should be updating the 
 info in my label
 it should query each side and determine my conversion then do it.
 (how do i check each side)
 
 */

#import "Project3ViewController.h"

@implementation Project3ViewController

//synthesize my properties
@synthesize picker, info, data;
@synthesize dataTemp, segmentInfo, selectedSegmentIndex;
@synthesize from, to, button1, button2,  button3, button4, button5,
            button6, button7, button8, button9, button0, period, del;

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
- (void)loadView {
}
*/



// Implement viewDidLoad to do additional setup after loading the view, typically from a nib.
- (void)viewDidLoad {
    [super viewDidLoad];

	//load contents of the component depending on the current segment selection
	if([[self.dataTemp titleForSegmentAtIndex:selectedSegmentIndex] isEqualToString:@"Data"] )
	{
	    self.data = [NSArray arrayWithObjects:@"Byte", @"Kilobyte", @"Megabyte", @"Gigabyte", nil];
	} else {
		self.data = [NSArray arrayWithObjects:@"Celsius", @"Farenheit", @"Kelvin", nil];
	}
}

-(IBAction)getValue {
	self.info.text = [self.data objectAtIndex:[self.picker selectedRowInComponent:0]];
	
}

-(NSString *)pickerView:(UIPickerView *)pickerView
			titleForRow:(NSInteger)row
		   forComponent:(NSInteger)component {
	   //returns object at index used to fill the picker
		return [self.data objectAtIndex:row];
}

-(NSInteger)numberOfComponentsInPickerView:(UIPickerView *)pickerView {
	//sets number of wheels in picker
	return 2;
}

-(NSInteger)pickerView:(UIPickerView *)pickerView
numberOfRowsInComponent:(NSInteger)component {
	//returns number of items in the wheel
	return [self.data count];
}

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

//intercepts picker change
-(void)pickerView:(UIPickerView *)thePickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component
{
	//calls converter
	self.converter;	
}

//called when segment changed
-(IBAction)changeSelection:(id)sender {
	//update my local segment info
	self.segmentInfo.text = [sender titleForSegmentAtIndex: [sender selectedSegmentIndex]];
	self.selectedSegmentIndex = [sender selectedSegmentIndex];
	
	//change data in picker array based off of update
	self.viewDidLoad;
	//reload picker in ui
	[self.picker reloadAllComponents];
	self.converter;
	
	
}



//from is my left side and to is my converted
//press functions append their number and call
// a converter function
-(void)converter
{
	//pull my picker info
	NSString *convertFrom = [data objectAtIndex:[picker selectedRowInComponent:0]];
	NSString *convertTo   = [data objectAtIndex:[picker selectedRowInComponent:1]];
	
	//check if they're aren't any numbers
	if([from.text isEqual:@""])
	{
		//if so don't care and do nothin
		to.text = [NSString stringWithFormat:@""];
	} else
		//check if they're the same
	   if([convertFrom isEqualToString:convertTo])
	   {
		   //no conversion necassary
		   		   
		   to.text = [NSString stringWithFormat:@"%@", from.text];
	   } else
		   if([[self.dataTemp titleForSegmentAtIndex:selectedSegmentIndex] isEqualToString:@"Data"])
		   {
			   //im comverting data
			   //NSLog(@"converting data");
			   self.convertData;
		   } else {
			   //im converting temperatures
			   //NSLog(@"Converting temp");
			   self.convertTemp;
		   }
}

//to.text in scientific notation. depending on length

-(void)convertTemp
{
	NSString *convertFrom = [data objectAtIndex:[picker selectedRowInComponent:0]];
	NSString *convertTo   = [data objectAtIndex:[picker selectedRowInComponent:1]];
	
	NSDecimalNumber *convertFromNum = [NSDecimalNumber decimalNumberWithString:from.text];
	
	NSDecimalNumber *convertToNum;

	//factors set up for my conversions
	//is this really the only way to manipulate NSDecimal Numbers?
	NSDecimalNumber *factor32 = [NSDecimalNumber decimalNumberWithString:@"32"];
	NSDecimalNumber *factor95 = [NSDecimalNumber decimalNumberWithString:@"1.8"]; //(9/5)
	NSDecimalNumber *factor273 = [NSDecimalNumber decimalNumberWithString:@"273.15"];
	NSDecimalNumber *factor59 = [[NSDecimalNumber decimalNumberWithString:@"1"] decimalNumberByDividingBy:factor95];
	
	if([convertFrom isEqualToString:@"Celsius"])
	{
		if([convertTo isEqualToString:@"Farenheit"])
		{
			 //F = (9/5)C + 32
			NSDecimalNumber *temp = [convertFromNum decimalNumberByMultiplyingBy:factor95];
			convertToNum = [temp decimalNumberByAdding:factor32];
			//if length is to long print output in scientific notation
			
				to.text = [NSString stringWithFormat:@"%@", convertToNum];
		} else {
			//kelvin
			//K = C + 273.15
			convertToNum = [convertFromNum decimalNumberByAdding:factor273];
			
			
				to.text = [NSString stringWithFormat:@"%@", convertToNum];
		}
	} else 
		if([convertFrom isEqualToString:@"Farenheit"])
		{
			if([convertTo isEqualToString:@"Celsius"])
			{
				//C = (F-32) x (5/9)
				NSDecimalNumber *temp = [convertFromNum decimalNumberBySubtracting:factor32];
				convertToNum = [temp decimalNumberByMultiplyingBy:factor59];
				
				
					to.text = [NSString stringWithFormat:@"%@", convertToNum];
		} else {
			  //kelvin	
		      //k = (f + 459.67) *(5/9) or farenheit to celsius to kelvin
			  //converting to celsius first
				// F - 32 store in temp
				//NSLog(@"Farenheit: %@", convertFromNum);
				NSDecimalNumber *temp = [convertFromNum decimalNumberBySubtracting:factor32];
				//NSLog(@"Farenheit subtracted by 32: %@", temp);
				//multiplying temp by 5/9 store in convertToNum.(should have celsius)
				convertToNum = [temp decimalNumberByMultiplyingBy:factor59];
				//NSLog(@"Celsius: %@", convertToNum);
				//add 273.15 and keep it. why is this wrong
				
				convertToNum = [convertToNum decimalNumberByAdding:factor273];
				
				//NSLog(@"Kelvin %@", convertToNum);
//				NSLog(@"Kelvin %E", convertToNum);
//				NSLog(@"Kelvin %d", [convertToNum doubleValue]);
//				NSLog(@"Kelvin %E", [convertToNum doubleValue]);
				
				to.text = [NSString stringWithFormat:@"%@", convertToNum];
			}
		} else {
		  //kelvin	
			if([convertTo isEqualToString:@"Farenheit"])
			{
				//[°F] = [K] × 9⁄5 − 459.67
				NSDecimalNumber *temp = [convertFromNum decimalNumberByMultiplyingBy:factor95];
				convertToNum = [temp decimalNumberBySubtracting:[NSDecimalNumber decimalNumberWithString:@"459.67"]];
				
				to.text = [NSString stringWithFormat:@"%@", convertToNum];
				
			} else {
			  //celsius	
			  //C = K - 273.15	
				convertToNum = [convertFromNum decimalNumberBySubtracting:factor273];
				
				//NSLog(@"Kelvin to Celsius");
//				NSLog(@"Kelvin: %@", convertFromNum);
//				NSLog(@"Celsius: %@", convertToNum);
//				NSLog(@"Celsius: %E", [convertToNum doubleValue]);
				
				to.text = [NSString stringWithFormat:@"%@", convertToNum];
				
			}
		}
	//F = (9/5)C +32
    //C = (F-32) x (5/9)
	//k = c + 273.15
	//k = (f + 459.67) *(5/9)

}

-(void)convertData
{
	//determine left side and right side
	NSString *convertFrom = [data objectAtIndex:[picker selectedRowInComponent:0]];
	NSString *convertTo   = [data objectAtIndex:[picker selectedRowInComponent:1]];
	NSString *factor = [NSString stringWithFormat:@"1024"];
	
	
	
	
	NSDecimalNumber *convertFromNum = [NSDecimalNumber decimalNumberWithString:self.from.text];
	NSDecimalNumber *convertToNum;
    NSDecimalNumber *conversionFactor = [NSDecimalNumber decimalNumberWithString:factor];
	
	
	
	//this huge structure of ifs kinda depresses me
	if([convertFrom isEqualToString:@"Byte"])
	{
		if([convertTo isEqualToString:@"Kilobyte"])
		{
			//1024 bytes = kilobyte
			convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
			
			
				self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
			
		} else
			if([convertTo isEqualToString:@"Megabyte"])
			{
				//1048576 bytes = megabyte
				convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
				convertToNum = [convertToNum decimalNumberByDividingBy:conversionFactor];
				
				
					self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
				
			} else {
			  //gigabyte	
			  //1073741824 bytes = gigabyte
				convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
				convertToNum = [convertToNum decimalNumberByDividingBy:conversionFactor];
				convertToNum = [convertToNum decimalNumberByDividingBy:conversionFactor];
				
				
					self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
				
			}
	} else
		if([convertFrom isEqualToString:@"Kilobyte"])
		{
			if([convertTo isEqualToString:@"Byte"])
			{
				//0.0009765625 kilo = bytes
				convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
				
				
				self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
				
			} else
				if([convertTo isEqualToString:@"Megabyte"])
				{
					//1024 kilo = megabyte
					convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
					
				    to.text = [NSString stringWithFormat:@"%@", convertToNum];
					
				} else {
					//gigabyte	
					//1048576 kilo = gigabyte
					convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
					convertToNum = [convertToNum decimalNumberByDividingBy:conversionFactor];
					
					self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
				}
		} else
			if([convertFrom isEqualToString:@"Megabyte"])
			{
				if([convertTo isEqualToString:@"Byte"])
				{
					//9.5367431640625e-7 mega = bytes
					//1/1024/1024
					convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
					convertToNum = [convertToNum decimalNumberByMultiplyingBy:conversionFactor];
					
					
						to.text = [NSString stringWithFormat:@"%@", convertToNum];
					
				} else
					if([convertTo isEqualToString:@"Kilobyte"])
					{
						//0.0009765625 mega = kilo
						convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
						
						self.to.text = [NSString stringWithFormat:@"%@", convertToNum];

					} else {
						//gigabyte	
						//1024 mega = giga
						convertToNum = [convertFromNum decimalNumberByDividingBy:conversionFactor];
						
						
						
						self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
					
					}
			} else {
				if([convertTo isEqualToString:@"Byte"])
				{
					//9.313225746154785e-10 giga = bytes
					//1/1024/1024/1024
					convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
					convertToNum = [convertToNum decimalNumberByMultiplyingBy:conversionFactor];
					convertToNum = [convertToNum decimalNumberByMultiplyingBy:conversionFactor];
					
					self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
					
				} else
					if([convertTo isEqualToString:@"Kilobyte"])
					{
						//9.5367431640625e-7 giga = kilo
						//1/1024/1024
						convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
						convertToNum = [convertToNum decimalNumberByMultiplyingBy:conversionFactor];
					
						self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
						
					} else {
						//megabyte
						//0.0009765625 giga = mega
						convertToNum = [convertFromNum decimalNumberByMultiplyingBy:conversionFactor];
						
							self.to.text = [NSString stringWithFormat:@"%@", convertToNum];
					}
			}
	//1024byte = kilobyte
	//1024kilo = megabyte
	//1024mega = gigabyte
}

/*
 Probably could have made one function that could pull the title of the
 button that pressed it. but oh well
 copy and pasting is just as fast
 also i don't know how
 */

-(IBAction)press0
{
	//correctly adds a 0 to the end of the from field
	//was going to filter out
//	if([self.from.text length] > 0)
//	{
		from.text = [NSString stringWithFormat:@"%@%d", from.text, 0];
		self.converter;
//	}
}



-(IBAction)press1
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 1];
	self.converter;
}

-(IBAction)press2
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 2];
	self.converter;
}

-(IBAction)press3
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 3];
	self.converter;
}

-(IBAction)press4
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 4];
	self.converter;
}

-(IBAction)press5
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 5];
	self.converter;
}

-(IBAction)press6
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 6];
	self.converter;
}

-(IBAction)press7
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 7];
	self.converter;
}

-(IBAction)press8
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 8];
	self.converter;
}

-(IBAction)press9
{
	from.text = [NSString stringWithFormat:@"%@%d", from.text, 9];
	self.converter;
}

-(IBAction)pressPeriod
{
	NSRange range = [from.text rangeOfString:@"."];
	
	if(range.location == NSNotFound)
	{
		//didn't contain a period	
		from.text = [NSString stringWithFormat:@"%@.", from.text];
	    self.converter;
	} else {
		//already contained a period
	}
}

//this needs to be looked at
-(IBAction)pressDelete{
	if([from.text length] > 0)
	{
		from.text = [from.text substringToIndex:[from.text length] - 1];
		self.converter;
	} else 
		if([from.text length] == 1)
		{
			from.text = [NSString stringWithFormat:@""];
		}
}



- (void)dealloc {
    [super dealloc];
	//release everything
	picker = nil;
	info = nil;
	data = nil;
	
	dataTemp = nil;
	segmentInfo = nil; 
	//selectedSegmentIndex = nil; //integer can't go nil
	
	from = nil;
	to = nil; 
	button1 = nil;
	button2 = nil;
	button3 = nil;
	button4 = nil; 
	button5 = nil;
	button6 = nil;
	button7 = nil;
	button8 = nil;
	button9 = nil;
	button0 = nil;
	period = nil;
	del = nil;
	
}

@end

