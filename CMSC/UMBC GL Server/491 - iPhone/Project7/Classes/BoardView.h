//
//  BoardView.h
//  Project7
//
//  Created by  on 11/8/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

/*
 BoardView
 Inherits from UIView so that I may write to the UIView for my app.
 Under current design not only does the drawing, but all the logic involved with
 displaying and controlling the game is held here.
 */
@interface BoardView : UIView <UIActionSheetDelegate> {
	UILabel *status;
	UILabel *redWin;
	UILabel *blackWin;
	UILabel *tie;
	//records positions
	CGPoint position;
	CGPoint dropPoint;
	CGPoint endPoint;
	CGPoint winStart;
	CGPoint winEnd;
	NSArray *boardGame;
	//counters
	int numRedWin;
	int numBlackWin;
	int numTies;
	int currCol;
	int checkCol;
	int checkRow;
	int connectCounter;
	//logic controllers
	bool redTurn;
	bool gameStart;
	bool touchEnd;
	bool dropPiece;
	bool showPiece;
	bool gameWon;
	bool gameTie;
	bool gameOver;
}

@property(nonatomic, retain) IBOutlet UILabel *status;
@property(nonatomic, retain) IBOutlet UILabel *redWin;
@property(nonatomic, retain) IBOutlet UILabel *blackWin;
@property(nonatomic, retain) IBOutlet UILabel *tie;
@property(nonatomic, assign) CGPoint position;
@property(nonatomic, assign) CGPoint dropPoint;
@property(nonatomic, assign) CGPoint endPoint;
@property(nonatomic, assign) CGPoint winStart;
@property(nonatomic, assign) CGPoint winEnd;
@property(nonatomic, retain) NSArray *boardGame;
@property(nonatomic) int currCol;
@property(nonatomic) int checkCol;
@property(nonatomic) int checkRow;
@property(nonatomic) int connectCounter;
@property(nonatomic) int numRedWin;
@property(nonatomic) int numBlackWin;
@property(nonatomic) int numTies;
@property(nonatomic) bool redTurn;
@property(nonatomic) bool gameStart;
@property(nonatomic) bool touchEnd;
@property(nonatomic) bool dropPiece;
@property(nonatomic) bool showPiece;
@property(nonatomic) bool gameWon;
@property(nonatomic) bool gameTie;
@property(nonatomic) bool gameOver;

/*
 Draws a black circle of radius 30 at position passed in
 x - x-coordinate
 y - y-coordinate
 */
-(void)drawBlackCircleAtX:(float)x Y:(float)y; 

/*
 Draws a red circle of radius 30 at position passed in
 x - x-coordinate
 y - y-coordinate
 */
-(void)drawRedCircleAtX:(float)x Y:(float)y; 

/*
 Draws a gray circle of radius 30 at position passed in
 x - x-coordinate
 y - y-coordinate
 */
-(void)drawGrayCircleAtX:(float)x Y:(float)y; 

/*
 Draws a line from one point to another
 x1 - x-coord of starting point
 y1 - y-coord of starting point
 x2 - x-coord of ending point
 y2 - y-coord of ending point
 */
-(void)drawLineStartX:(float)x1 StartY:(float)y1 EndX:(float)x2 EndY:(float)y2;

/*
 draws left leg of the board
 */
-(void)drawLeftSupport;

/*
 draws right leg of the board
 */
-(void)drawRightSupport;

/*
 draws the yellow rectangle for the board
 */
-(void)drawBoard;

/*
 draws all the circles on the board
 */
-(void)drawBoardCircles;

/*
 changes the internal array to contain a piece
 */
-(void)dropThePiece;

/*
 check if game has been won
 */
-(void)detectWin;

/*
 check if game is tied
 */
-(void)detectTie;

/*
 check for horizontal win
 */
-(void)checkHorizontal;

/*
 check for vertical win
 */
-(void)checkVertical;

/*
 check for left diagonal win \
 */
-(void)checkLDiagonal;

/*
 check for right diagonal win /
 */
-(void)checkRDiagonal;

/*
 wrapper function for check horizontal
 */
-(void)callCheckHorizontal;

/*
 wrapper function for check vertical
 */
-(void)callCheckVertical;

/*
 wrapper function for check lDiagonal
 */
-(void)callCheckLDiagonal;

/*
 wrapper function for check RDiagonal
 */
-(void)callCheckRDiagonal;

/*
 implements resulting logic from a won game
 */
-(void)checkGameWon;

/*
 sets up variables to be used with check* functions
 x - x-coord to start check
 y - y-coord to start check
 */
-(void)setUpCheckX:(int)x Y:(int)y;

/*
 resets important game vars
 returns game board to default
 */
-(IBAction)makeNewGame;

@end
