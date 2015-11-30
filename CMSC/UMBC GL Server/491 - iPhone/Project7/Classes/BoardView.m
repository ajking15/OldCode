//
//  BoardView.m
//  Project7
//
//  Created by  on 11/8/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "BoardView.h"


@implementation BoardView

@synthesize position, boardGame, currCol, checkCol, redTurn, gameStart, touchEnd, gameOver;
@synthesize dropPiece, showPiece, dropPoint, endPoint, connectCounter, gameWon, gameTie;
@synthesize status, redWin, blackWin, tie, numRedWin, numBlackWin, numTies, checkRow;
@synthesize winStart, winEnd;

- (id)initWithFrame:(CGRect)frame {
    if (self = [super initWithFrame:frame]) {
        // Initialization code
    }
    return self;
}

- (BOOL)canBecomeFirstResponder {
	return YES;
}

//x value starts at 20. increments of 40 for columns
//lowest y 
//y at 35

- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {
	//logic for displaying piece above board
	//self.gameStart = NO;
	self.position = [[touches anyObject] locationInView:self];
	if(self.position.y < 50)
	{
		if(self.position.x > 20 && self.position.x < 300)
		{
			if(self.position.x < 60)
			{
				
				self.currCol = 0;
			} 
			else
				if(self.position.x < 100)
				{
					self.currCol = 1;
				}
				else
					if(self.position.x < 140)
					{
						
						self.currCol = 2;
					}
					else 
						if(self.position.x < 180)
						{
							
							self.currCol = 3;
						}
						else
							if(self.position.x < 220)
							{
								
								self.currCol = 4;
							}
							else 
								if(self.position.x < 260)
								{
									
									self.currCol = 5;
								} else
									if(self.position.x < 300) 
									{
										
										self.currCol = 6;
									}
		}
	}
	//	self.alreadySwiped = NO;
	//	self.label.text = @"";
	
	if([[[self.boardGame objectAtIndex:0] objectAtIndex:self.currCol] isEqualToString:@"empty"])
	{
		self.showPiece = YES;
	} else {
		self.showPiece = NO;
	}
	
	[self setNeedsDisplay];
}

- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event {
	//CGPoint current = [[touches anyObject] locationInView:self];
	self.position = [[touches anyObject] locationInView:self];
	//logic to show piece when moving
	if(self.position.y < 50)
	{
		if(self.position.x > 20 && self.position.x < 300)
		{
			if(self.position.x < 60)
			{
				
				self.currCol = 0;
			} 
			else
				if(self.position.x < 100)
				{
					
					self.currCol = 1;
				}
				else
					if(self.position.x < 140)
					{
						
						self.currCol = 2;
					}
					else 
						if(self.position.x < 180)
						{
							
							self.currCol = 3;
						}
						else
							if(self.position.x < 220)
							{
								
								self.currCol = 4;
							}
							else 
								if(self.position.x < 260)
								{
									
									self.currCol = 5;
								} else
									if(self.position.x < 300) 
									{
										
										self.currCol = 6;
									}
		}
	}
	//	self.alreadySwiped = NO;
	//	self.label.text = @"";
	
	if([[[self.boardGame objectAtIndex:0] objectAtIndex:self.currCol] isEqualToString:@"empty"])
	{
		self.showPiece = YES;
		//self.dropPiece = YES;
	} else {
		self.showPiece = NO;
		//self.dropPiece = NO;
	}
	
	[self setNeedsDisplay];
}

- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event {
	//logic to determing wether or not a piece should be dropped in the board
	if(self.position.y < 50)
	{
		if(self.position.x > 20 && self.position.x < 300)
		{
			if(self.position.x < 60)
			{
				
				self.currCol = 0;
				self.dropPiece = YES;
			} 
			else
				if(self.position.x < 100)
				{
					
					self.currCol = 1;
					self.dropPiece = YES;
				}
				else
					if(self.position.x < 140)
					{
						
						self.currCol = 2;
						self.dropPiece = YES;
					}
					else 
						if(self.position.x < 180)
						{
							
							self.currCol = 3;
							self.dropPiece = YES;
						}
						else
							if(self.position.x < 220)
							{
								
								self.currCol = 4;
								self.dropPiece = YES;
							}
							else 
								if(self.position.x < 260)
								{
									
									self.currCol = 5;
									self.dropPiece = YES;
								} else
									if(self.position.x < 300) 
									{
										
										self.currCol = 6;
										self.dropPiece = YES;
									} 
			if([[[self.boardGame objectAtIndex:0] objectAtIndex:self.currCol] isEqualToString:@"empty"])
			{
				self.dropPiece = YES;
			} else {
				self.dropPiece = NO;
			}
			
		} else {
			//self.dropPiece = NO;
		}
	} else {
		//self.dropPiece = NO;
	}
	
		
	[self setNeedsDisplay];
	//allows color change
	self.touchEnd = YES;
}

- (void)drawRect:(CGRect)rect {
	//draws everything board related
	[self drawLeftSupport];
	[self drawRightSupport];
	[self drawBoard];
	
	if(self.gameWon || self.gameTie)
	{
		self.gameOver = YES;
	}
	
	//checks wether or not to draw piece above a column
	if(!self.gameOver)
	{
		if(self.gameStart == YES && self.dropPiece == NO && self.showPiece == YES)
		{
			if(self.position.x > 20 && self.position.x < 300 && self.position.y < 50)
			{
				if(self.redTurn != YES)
				{
					[self drawBlackCircleAtX:((40 * self.currCol) + 40) Y:35];
				} else {
					[self drawRedCircleAtX:((40 * self.currCol) + 40) Y:35];
				}
			}
		}
		
		//[self drawGrayCircleAtX:((40 * self.prevCol) + 40) Y:35];
		//sets up default game values
		if(self.gameStart == NO)
		{
			NSMutableArray *row1 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			NSMutableArray *row2 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			NSMutableArray *row3 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			NSMutableArray *row4 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			NSMutableArray *row5 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			NSMutableArray *row6 = [NSMutableArray arrayWithObjects: @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", @"empty", nil];
			self.boardGame = [NSArray arrayWithObjects: row1, row2, row3, row4, row5, row6, nil];
			self.status.text = @"Black's Turn";
			self.dropPiece = NO;
			self.gameStart = YES;
			self.gameWon = NO;
			self.gameTie = NO;
			self.gameOver = NO;
		}
		
		//drops the piece into the board
		if(self.dropPiece == YES)
		{
			[self dropThePiece];
			if(self.touchEnd == YES)
			{
				self.touchEnd = NO;
				if(self.redTurn != YES)
				{
					self.redTurn = YES;
					self.status.text = @"Red's Turn";
				} else {
					self.redTurn = NO;
					self.status.text = @"Black's Turn";
				}
			}
			
			
						
			
		}
		
		//updates labels		
		self.redWin.text = [NSString stringWithFormat:@"Red: %d", self.numRedWin];
		self.blackWin.text = [NSString stringWithFormat:@"Black: %d", self.numBlackWin];
		self.tie.text = [NSString stringWithFormat:@"Ties: %d", self.numTies];
	} else {
		
	}
	//draws circles in board
	[self drawBoardCircles];

	
	//updates wins
	if(self.gameWon)
	{
		[self drawLineStartX:self.winStart.y StartY:self.winStart.x EndX:self.winEnd.x EndY:self.winEnd.y];
		
		if(self.redTurn)
		{
			self.status.text = @"Black Wins!";
		} else {
			self.status.text = @"Red Wins!";
		}
	}
	
	//updates ties
	if(!self.gameWon)
	{
		if(!self.gameTie)
		{
		   [self detectTie];
		//self.
		}
	}
}

-(void)dropThePiece {
	self.dropPiece = NO;
	//this method should only be called if it's over a column and space exists
	for(int x = 5; x > -1; x--)
	{
		//tempArray = [self.boardGame objectAtIndex:x];
		
		if([[[self.boardGame objectAtIndex:x] objectAtIndex:self.currCol] isEqualToString:@"empty"])
		{
			self.dropPoint = CGPointMake(x, self.currCol);
			if(self.redTurn == YES)
			{
				//[tempArray set
				[[self.boardGame objectAtIndex:x] removeObjectAtIndex:self.currCol];
				[[self.boardGame objectAtIndex:x] insertObject:@"red" atIndex:self.currCol];
				
				//[tempArray objectAtIndex:self.currCol] = @"red";
			} else {
				[[self.boardGame objectAtIndex:x] removeObjectAtIndex:self.currCol];
				[[self.boardGame objectAtIndex:x] insertObject:@"black" atIndex:self.currCol];	
			}
			[self detectWin];
			
			break;
		}
	}
}

//This is going to have to loop over the board
//will need a separate detect tie method
//detects wins
-(void)detectWin
{
	for(int x = 0; x < 6; x++)
	{
		for(int y = 0; y < 7; y++)
		{
			if(![[[self.boardGame objectAtIndex:x] objectAtIndex:y] isEqualToString:@"empty"])
			{
				if(self.gameWon == NO)
				{
					self.winStart = CGPointMake(x, y);
				}
				
				if(self.gameWon == NO)
				{
					[self setUpCheckX:x Y:y];
					[self callCheckHorizontal];
				}
				//I only need half. remember symmetry
				if(self.gameWon == NO)
				{
					[self setUpCheckX:x Y:y];
					[self callCheckVertical];
				}
				
				if(self.gameWon == NO)
				{
					[self setUpCheckX:x Y:y];
					[self callCheckLDiagonal];
				}
				
				if(self.gameWon == NO)
				{
					[self setUpCheckX:x Y:y];
					[self callCheckRDiagonal];
				}
			}
		}
	}
	
}

//detect ties
-(void)detectTie
{
	int tieCheck = 0;
	
	for(int x = 0; x < 7; x++)
	{
		if(![[[self.boardGame objectAtIndex:0] objectAtIndex:x] isEqualToString:@"empty"])
		{
			tieCheck++;
		}
	}
	
	if(tieCheck == 7)
	{
		self.gameTie = YES;
		self.numTies++;
		self.status.text = @"Tie!";
		self.tie.text = [NSString stringWithFormat:@"Ties: %d", self.numTies];
	}
}

-(void)setUpCheckX:(int)x Y:(int)y {
	self.connectCounter = 1;
	self.checkCol = y;
	self.checkRow = x;
	
}

-(void)checkGameWon {
	if(connectCounter == 4)
	{
		self.winEnd = CGPointMake(self.checkCol, self.checkRow);
		self.gameWon = YES;
		
		if(self.redTurn)
		{
			self.numRedWin++;
		} else {
			self.numBlackWin++;
		}
	}
	
}
-(void)callCheckHorizontal {
	[self checkHorizontal];
	[self checkGameWon];
}

-(void)callCheckVertical {
	[self checkVertical];
	[self checkGameWon];
}

-(void)callCheckLDiagonal {
	[self checkLDiagonal];
	[self checkGameWon];
}

-(void)callCheckRDiagonal {
	[self checkRDiagonal];
	[self checkGameWon];
}

//recursively checks for a horizontal solution
-(void)checkHorizontal {
	if(self.checkCol < 6 && self.connectCounter < 4)
	{
		//within bounds
		if([[[self.boardGame objectAtIndex:self.checkRow] objectAtIndex:(self.checkCol + 1)] 
			isEqualToString:[[self.boardGame objectAtIndex:self.checkRow] objectAtIndex:self.checkCol]])
		{
			self.checkCol++;
			self.connectCounter++;
			[self checkHorizontal];
		}
	} 
}

//recursively checks a vertical solution
-(void)checkVertical {

	if(self.checkRow < 5 && self.connectCounter < 4)
	{
		//within bounds
		if([[[self.boardGame objectAtIndex:(self.checkRow + 1)] objectAtIndex:self.checkCol] 
			isEqualToString:[[self.boardGame objectAtIndex:self.checkRow] objectAtIndex:self.checkCol]])
		{
			self.checkRow++;
			self.connectCounter++;
			[self checkVertical];
		}
	} 
	
}

//recursively checks a LDiagonal win
-(void)checkLDiagonal {
	if(self.checkRow < 5 && self.checkCol < 6 && self.connectCounter < 4)
	{
		//within bounds
		if([[[self.boardGame objectAtIndex:(self.checkRow + 1)] objectAtIndex:(self.checkCol + 1)] 
			isEqualToString:[[self.boardGame objectAtIndex:self.checkRow] objectAtIndex:self.checkCol]])
		{
			self.checkRow++;
			self.checkCol++;
			self.connectCounter++;
			[self checkLDiagonal];
		}
	} 
}

//recursively checks a RDiagonal win
-(void)checkRDiagonal {
	if(self.checkRow < 5 && self.checkCol > 0 && self.connectCounter < 4)
	{
		//within bounds
		if([[[self.boardGame objectAtIndex:(self.checkRow + 1)] objectAtIndex:(self.checkCol - 1)] 
			isEqualToString:[[self.boardGame objectAtIndex:self.checkRow] objectAtIndex:self.checkCol]])
		{
			self.checkRow++;
			self.checkCol--;
			self.connectCounter++;
			[self checkRDiagonal];
		}
	} 
}

//draws a black circle
-(void)drawBlackCircleAtX:(float)x Y:(float)y {
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextSetFillColorWithColor(ctx, [UIColor blackColor].CGColor);
	//radius of 20
	CGContextAddArc(ctx, x, y, 15, 0, 2 * M_PI, 1);
	CGContextFillPath(ctx);
}

//draws a red circle
-(void)drawRedCircleAtX:(float)x Y:(float)y {
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextSetFillColorWithColor(ctx, [UIColor redColor].CGColor);
	//radius of 20
	CGContextAddArc(ctx, x, y, 15, 0, 2 * M_PI, 1);
	CGContextFillPath(ctx);
}

//draws a gray circle
-(void)drawGrayCircleAtX:(float)x Y:(float)y {
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextSetFillColorWithColor(ctx, [UIColor lightGrayColor].CGColor);
	//radius of 20
	CGContextAddArc(ctx, x, y, 15, 0, 2 * M_PI, 1);
	CGContextFillPath(ctx);
}

//draws the show win line
-(void)drawLineStartX:(float)x1 StartY:(float)y1 EndX:(float)x2 EndY:(float)y2 {

	int tempx = (40 * x1) + 40;
	int tempy = (40 * y1) + 70;
	int tx = (40 * x2) + 40;
	int ty = (40 * y2) + 70;
	
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextBeginPath(ctx);
	
	CGContextMoveToPoint(ctx, tempx, tempy);
	CGContextAddLineToPoint(ctx, tx, ty);
	CGContextSetLineWidth(ctx, 5);
	CGContextSetStrokeColorWithColor(ctx, [UIColor greenColor].CGColor);
	CGContextSetLineCap(ctx, kCGLineCapRound);
	CGContextStrokePath(ctx);
}

//draws left board leg
-(void)drawLeftSupport {
	// x, y, width, height
	CGRect rectangle = CGRectMake(10, 50, 10, 280);
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextAddRect(ctx, rectangle);
	CGContextSetFillColorWithColor(ctx, [UIColor blueColor].CGColor);
	CGContextFillPath(ctx);
}

//draws right board leg
-(void)drawRightSupport {
	// x, y, width, height
	CGRect rectangle = CGRectMake(300, 50, 10, 280);
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextAddRect(ctx, rectangle);
	CGContextSetFillColorWithColor(ctx, [UIColor blueColor].CGColor);
	CGContextFillPath(ctx);
	
}

//draws a yellow rect for a board basis
-(void)drawBoard {
	// x, y, width, height
	//this should be a square. width = height
	CGRect rectangle = CGRectMake(20, 50, 280, 240);
	CGContextRef ctx = UIGraphicsGetCurrentContext();
	CGContextAddRect(ctx, rectangle);
	CGContextSetFillColorWithColor(ctx, [UIColor yellowColor].CGColor);
	CGContextFillPath(ctx);	
}

//fills out the board with contents of board game array
-(void)drawBoardCircles {
	//starting with vertical
	for(int x = 0; x < 6; x++)
	{
		for(int y = 0; y < 7; y++)
		{
			//note these are important for circle placement
			NSArray *tempArray = [self.boardGame objectAtIndex:x];
			int tempx = (40 * y) + 40;
			int tempy = (40 * x) + 70;
			
			if([[tempArray objectAtIndex:y] isEqualToString:@"empty"])
			{
				[self drawGrayCircleAtX:tempx Y:tempy];
			} else
				if([[tempArray objectAtIndex:y] isEqualToString:@"red"])
				{
					//red
					[self drawRedCircleAtX:tempx Y:tempy];
				} else {
					//black
					[self drawBlackCircleAtX:tempx Y:tempy];
				}
		}
	}
}

//action sheet for new game
-(IBAction)makeNewGame
{
	UIActionSheet *sheet = [[[UIActionSheet alloc] initWithTitle:nil
														delegate:self 
											   cancelButtonTitle:@"Return to Game"
										  destructiveButtonTitle:@"Quit Game"
											   otherButtonTitles:nil] autorelease];
	[sheet showInView:self];
}

//logic for new game
- (void)actionSheet:(UIActionSheet *)actionSheet
clickedButtonAtIndex:(NSInteger)buttonIndex {
	//quit game = 0;
	if(buttonIndex == 0)
	{
		self.gameWon = NO;
		self.gameTie = NO;
		self.gameOver = NO;
		self.currCol = 0;
		self.checkCol = -1;
		self.checkRow = -1;
		self.redTurn = NO;
		self.gameStart = NO;
		[self setNeedsDisplay];
	}
}

- (void)dealloc {
    [super dealloc];
	self.status = nil;
	self.redWin = nil;
	self.blackWin = nil;
	self.tie = nil;
	self.boardGame = nil;
}


@end
