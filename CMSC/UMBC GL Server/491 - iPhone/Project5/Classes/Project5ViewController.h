//
//  Project5ViewController.h
//  Project5
//
//  Created by  on 10/15/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface Project5ViewController : UIViewController <UIActionSheetDelegate> {
	//labels
	UILabel *winLose;
	UILabel *guessBar;
	//ints to enforce game rules
	int numGuesses;
	int curNumGuesses;
	int correctGuesses;
	//Image view hosts to the image
	UIImageView *hangman;
	//stores the six images
	UIImage *hangImage0;
	UIImage *hangImage1;
	UIImage *hangImage2;
	UIImage *hangImage3;
	UIImage *hangImage4;
	UIImage *hangImage5;
	UIImage *hangImage6;
	//current word
	NSString *currentWord;
	//current difficulty
	NSString *wordDiff;
	//reads in the words list from the plist
	NSArray *wordsList;
	//array that holds images
	NSArray *imageArray;
	BOOL winlost;
}
//properties
@property(nonatomic, retain) IBOutlet UILabel *winLose;
@property(nonatomic, retain) IBOutlet UILabel *guessBar;
@property(nonatomic) int numGuesses;
@property(nonatomic) int curNumGuesses;
@property(nonatomic) int correctGuesses;
@property(nonatomic, retain) IBOutlet UIImageView *hangman;
@property(nonatomic, retain) UIImage *hangImage0;
@property(nonatomic, retain) UIImage *hangImage1;
@property(nonatomic, retain) UIImage *hangImage2;
@property(nonatomic, retain) UIImage *hangImage3;
@property(nonatomic, retain) UIImage *hangImage4;
@property(nonatomic, retain) UIImage *hangImage5;
@property(nonatomic, retain) UIImage *hangImage6;
@property(nonatomic, retain) NSString *currentWord;
@property(nonatomic, retain) NSString *wordDiff;
@property(nonatomic, retain) NSArray *wordsList;
@property(nonatomic, retain) NSArray *imageArray;
@property(nonatomic) BOOL winlost;

/*
 Prompts the action sheet which prompts the user if you want to quit the current game
 only prompts an action sheet midgame
 */
-(IBAction)startNewGame;

/*
 connected to all the buttons on the keyboard
 used to manipulate the buttons alpha and enabled setting
 as well as grade the guess
 */
-(IBAction)keyboardPress:(id)sender;

/*
 NSString returns the file path of the Easy.plist as a string
 */
-(NSString *)getFilePathEasy;

/*
 NSString returns the file path of the Medium.plist as a string
 */
-(NSString *)getFilePathMedium;

/*
 NSString returns the file path of the Hard.plist as a string
 */
-(NSString *)getFilePathHard;


@end

