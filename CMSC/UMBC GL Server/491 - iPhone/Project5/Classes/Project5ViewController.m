//
//  Project5ViewController.m
//  Project5
//
//  Created by  on 10/15/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "Project5ViewController.h"

@implementation Project5ViewController

@synthesize winLose, guessBar, hangman, currentWord, numGuesses, curNumGuesses, wordDiff;
@synthesize hangImage0, hangImage1, hangImage2, hangImage3, hangImage4, hangImage5, hangImage6, wordsList;
@synthesize imageArray, correctGuesses, winlost;

-(IBAction)startNewGame
{
	//if the game isn't over don't pop the action sheet
	if(winlost == NO)
	{
		UIActionSheet *alert = [[[UIActionSheet alloc] initWithTitle:@"Quit Current Game?" 
														delegate:self 
												   cancelButtonTitle:@"Cancel"
											  destructiveButtonTitle:nil
												   otherButtonTitles:@"OK", nil]
								autorelease];
	
		[alert showInView:[self view]];
	} else {
		//game is over reset
		for(int xyz = 0; xyz < [[[self view] subviews] count]; xyz++)
		{
            //NSLog(@"in the silly loop");
			if([[[[self view] subviews] objectAtIndex:xyz] isKindOfClass:[UIButton class]])
			{
				//NSLog(@"disabling buttons");
				[[[[self view] subviews] objectAtIndex:xyz] setAlpha:1];
				[[[[self view] subviews] objectAtIndex:xyz] setEnabled:YES];
			}
		}		
		[currentWord release];
		[imageArray release];
		[self viewDidLoad];
	}
}

-(void)actionSheet:(UIActionSheet *)actionSheet
clickedButtonAtIndex:(NSInteger)buttonIndex {
	if(buttonIndex == 0) {
		//ok
		//if yes reset
		for(int xyz = 0; xyz < [[[self view] subviews] count]; xyz++)
		{
            //NSLog(@"in the silly loop");
			if([[[[self view] subviews] objectAtIndex:xyz] isKindOfClass:[UIButton class]])
			{
				//NSLog(@"disabling buttons");
				[[[[self view] subviews] objectAtIndex:xyz] setAlpha:1];
				[[[[self view] subviews] objectAtIndex:xyz] setEnabled:YES];
			}
		}		
		[currentWord release];
		[imageArray release];
		[self viewDidLoad];
		
	} else {
		//cancel
		//do nothing
	}
}

-(IBAction)keyboardPress:(id)sender
{
	
	BOOL found = NO;
	
	UIButton *genericButton = sender;
	genericButton.alpha = .30;

	for(int xyz = 0; xyz < [currentWord length]; xyz++)
	{
		if([currentWord characterAtIndex:xyz] == [genericButton.titleLabel.text characterAtIndex:0])
 	    {
			guessBar.text = [guessBar.text stringByReplacingCharactersInRange:NSMakeRange(2 * xyz, 1) withString:genericButton.titleLabel.text];
			correctGuesses++;
			found = YES;
		}
	}
	
	if(!found)
	{
		//not found
		curNumGuesses++;
	}
	//NSLog(@"before setting the the image");
	if(curNumGuesses < numGuesses + 1)
	{
		[hangman setImage:[imageArray objectAtIndex:curNumGuesses]];
	}
	
	if(correctGuesses == [currentWord length])
	{
		//you won
		//dim out buttons
		//and disable
		winlost = YES;
		winLose.text = [NSString stringWithFormat:@"You Win!"];
				//NSLog(@"going to check loop");
		for(int xyz = 0; xyz < [[[self view] subviews] count]; xyz++)
		{
            //NSLog(@"in the silly loop");
			if([[[[self view] subviews] objectAtIndex:xyz] isKindOfClass:[UIButton class]])
			{
				//NSLog(@"disabling buttons");
				[[[[self view] subviews] objectAtIndex:xyz] setAlpha:.30];
				[[[[self view] subviews] objectAtIndex:xyz] setEnabled:NO];
			}
		}
	}
	
	//check if lost
	if(curNumGuesses == numGuesses && correctGuesses < [currentWord length])
	{
		//you lost
		//set label text and update word label to have the word
		//dim out buttons
		//and disable
		winlost = YES;
		winLose.text = [NSString stringWithFormat:@"You Lose!"];
		
		guessBar.text = [NSString stringWithFormat:@""];
		for(int xyz = 0; xyz < [currentWord length]; xyz++)
		{
			guessBar.text = [[guessBar text] stringByAppendingString:[NSString stringWithFormat:@"%c ", [currentWord characterAtIndex:xyz]]];
		}
		
		//NSLog(@"going to check loop");
		for(int xyz = 0; xyz < [[[self view] subviews] count]; xyz++)
		{
            //NSLog(@"in the silly loop");
			if([[[[self view] subviews] objectAtIndex:xyz] isKindOfClass:[UIButton class]])
			{
				//NSLog(@"disabling buttons");
				[[[[self view] subviews] objectAtIndex:xyz] setAlpha:.30];
				[[[[self view] subviews] objectAtIndex:xyz] setEnabled:NO];
			}
		}		
	}
	
	[genericButton setEnabled:NO];
}

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
	//read in the images
	hangImage0 = [UIImage imageNamed:@"hangman0.png"];
	hangImage1 = [UIImage imageNamed:@"hangman1.png"];
	hangImage2 = [UIImage imageNamed:@"hangman2.png"];
	hangImage3 = [UIImage imageNamed:@"hangman3.png"];
	hangImage4 = [UIImage imageNamed:@"hangman4.png"];
	hangImage5 = [UIImage imageNamed:@"hangman5.png"];
	hangImage6 = [UIImage imageNamed:@"hangman6.png"];
	//set up my control vars
	curNumGuesses = 0;
	correctGuesses = 0;
	winlost = NO;
	//NSLog(@"images loaded, current guesses set");
	//empty out the label
	winLose.text = [NSString stringWithFormat:@""];
	//update from settings
	wordDiff = [[NSUserDefaults standardUserDefaults] objectForKey:@"wordDiff"];
	numGuesses = [[NSUserDefaults standardUserDefaults] integerForKey:@"numGuesses"];
	
	//set up if settings hasn't been visited yet
	if(wordDiff == nil)
	{
		wordDiff = [NSString stringWithFormat:@"Medium"];
		numGuesses = 3;
	}
	
	//pull out the correct word list
	if([wordDiff isEqualToString:@"Easy"])
	{
		//for now load a preset word
		wordsList = [NSArray arrayWithContentsOfFile:[self getFilePathEasy]];
	} else
		if([wordDiff isEqualToString:@"Medium"])
		{
			//for now load a preset word
			wordsList = [NSArray arrayWithContentsOfFile:[self getFilePathMedium]];
		} else {
			//for now load a preset word
			wordsList = [NSArray arrayWithContentsOfFile:[self getFilePathHard]];
		}
	
	//set up my image changer
	if(numGuesses == 3)
	{
		//NSLog(@"set image array 3");
		imageArray = [NSArray arrayWithObjects:hangImage0, hangImage2, hangImage4, hangImage6, nil];
	} else {
		//NSLog(@"set image array 6");
		imageArray = [NSArray arrayWithObjects:hangImage0, hangImage1, hangImage2, hangImage3,
					  hangImage4, hangImage5, hangImage6, nil];
	}
	
	//set my image
	[hangman setImage:[imageArray objectAtIndex:curNumGuesses]];
	
	//chose a word from dictionary
	int selection = arc4random() % [wordsList count];
	currentWord = [NSString stringWithFormat:[@"%@", wordsList objectAtIndex:selection]];
	currentWord = [currentWord uppercaseString];
	
	//zero out the guess bar
	guessBar.text = @"";
	//populate with "_ "
	for(int xyz = 0; xyz < [currentWord length]; xyz++)
	{
		guessBar.text = [[guessBar text] stringByAppendingString:@"_ "];
	}

	//using self fixed it for the first couple presses. this works for all
	[currentWord retain];
	[imageArray retain];
}

-(NSString *)getFilePathEasy {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Project5.app", @"Easy.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(NSString *)getFilePathMedium {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Project5.app", @"Medium.plist", nil];
	
	return [NSString pathWithComponents:segments];
}

-(NSString *)getFilePathHard {
	//gets the file path of the home dir.
	NSArray *segments = [NSArray arrayWithObjects:NSHomeDirectory(), @"Project5.app", @"Hard.plist", nil];
	
	return [NSString pathWithComponents:segments];
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


- (void)dealloc {
    [super dealloc];
	winLose = nil;
	guessBar = nil;
	hangman = nil;
	currentWord = nil;
	numGuesses = 0;
	curNumGuesses = 0;
	correctGuesses = 0;
	wordDiff = nil;
	hangImage0 = nil;
	hangImage1 = nil;
	hangImage2 = nil;
	hangImage3 = nil;
	hangImage4 = nil;
	hangImage5 = nil;
	hangImage6 = nil;
	wordsList = nil;
	imageArray = nil;
	winlost = NO;
}

@end
