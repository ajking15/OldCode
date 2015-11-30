//
//  GLViewController.h
//  Simon
//
//  Created by Dan Hood on 11/14/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <AudioToolbox/AudioToolbox.h>
#import <UIKit/UIKit.h>
#import "GLView.h"


/*
 GLViewController
 Manages board drawing using open gl.
 Also controls all the game logic involved with simon says.
 */
@interface GLViewController : UIViewController <GLViewDelegate, UIActionSheetDelegate>
{
	//ivars
	UILabel *gameCounter;
	NSString *lightColor;
	NSTimer  *timer;
	NSTimer  *controlTimer;
	NSMutableArray *computerMoves;
	NSMutableArray *playerMoves;
	
	//control ivars
	bool chooseRand;
	bool startClick;
	bool playersTurn;
	int currentIndex;
	int playerIndex;
	int roundsWon;
	
	//system sound id's. used during button light up
	SystemSoundID blue;
	SystemSoundID green;
	SystemSoundID red;
	SystemSoundID yellow;
	SystemSoundID gameEnds;
}

//properties
@property(nonatomic, retain) IBOutlet UILabel *gameCounter;
@property(nonatomic, retain) NSString *lightColor;
@property(nonatomic, retain) NSTimer *timer;
@property(nonatomic, retain) NSTimer *controlTimer;
@property(nonatomic, retain) NSMutableArray *computerMoves;
@property(nonatomic, retain) NSMutableArray *playerMoves;
@property(nonatomic)         bool startClick;
@property(nonatomic)         bool playersTurn;
@property(nonatomic)         int currentIndex;
@property(nonatomic)         bool chooseRand;
@property(nonatomic)         int playerIndex;
@property(nonatomic)         int roundsWon;
@property(nonatomic) SystemSoundID blue;
@property(nonatomic) SystemSoundID green;
@property(nonatomic) SystemSoundID red;
@property(nonatomic) SystemSoundID yellow;
@property(nonatomic) SystemSoundID gameEnds;
		  
/*
 resets all the variables and starts a new game
 */
-(IBAction)makeNewGame;

/*
 performs a computer based action.
 lights up the moves of the computer
 */
-(void)computersTurn;

/*
 wrapper function for computers turn
 defaults current index
 allows random var to be made
 */
-(void)callComputersTurn;

/*
 used to pause game play for 0.1 seconds
 */
-(void)pauseOneSec;

/*
 no longer does nothing
 redraws the view
 and starts player turn
 */
-(void)doNothing;

/*
 sets up player turn on view
 allows clicks to be registered 
 and accurate scoring of clicks
 houses timer
 */
-(void)startPlayersTurn;

/*
 called when timer runs out.
 checks to see if player succesfully won the round
 */
-(void)checkPlayersGuessFinal;

/*
 scores players guesses while timer is running
 */
-(void)playerGuess;

/*
 ends game and displays game over message
 */
-(void)gameOver;

/*
 wrapper function that calls other load functions below
 made only to look nicer
 */
-(void)loadSounds;

/*
 loads blue.caf
 */
-(void)loadBlue;

/*
 loads green.caf
 */
-(void)loadGreen;

/*
 loads red.caf
 */
-(void)loadRed;

/*
 loads yellow.caf
 */
-(void)loadYellow;

/*
 loads game-over.caf
 */
-(void)loadGameOver;

@end
