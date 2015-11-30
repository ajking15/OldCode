//
//  GLViewController.m
//  Simon
//
//  Created by Dan Hood on 11/14/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import "GLViewController.h"

#define DEGREES_TO_RADIANS(__ANGLE__) ((__ANGLE__) / 180.0 * M_PI)

@implementation GLViewController

@synthesize gameCounter, lightColor, timer, computerMoves, playerMoves, startClick, currentIndex, chooseRand, playersTurn,
			playerIndex, roundsWon, controlTimer, blue, red, green, yellow, gameEnds;

-(void)loadSounds {
	//loads all sounds
	[self loadBlue];
	[self loadGreen];
	[self loadRed];
	[self loadYellow];
	[self loadGameOver];
}

-(void)loadBlue
{
	//NS//Log(@"load blue");
	CFBundleRef mainBundle = CFBundleGetMainBundle();
	
	// Get the URL to the sound file to play. 
	CFURLRef soundFileURLRef = CFBundleCopyResourceURL(mainBundle, CFSTR("blue"),
													   CFSTR ("caf"), nil);
	
	// Create a system sound object representing the sound file
	AudioServicesCreateSystemSoundID(soundFileURLRef, &blue);
	
	//AudioServicesPlaySystemSound(blue);
}

-(void)loadGreen
{
	CFBundleRef mainBundle = CFBundleGetMainBundle();
	
	// Get the URL to the sound file to play. 
	CFURLRef soundFileURLRef = CFBundleCopyResourceURL(mainBundle, CFSTR("green"),
													   CFSTR ("caf"), nil);
	
	// Create a system sound object representing the sound file
	AudioServicesCreateSystemSoundID(soundFileURLRef, &green);
}

-(void)loadRed
{
	CFBundleRef mainBundle = CFBundleGetMainBundle();
	
	// Get the URL to the sound file to play. 
	CFURLRef soundFileURLRef = CFBundleCopyResourceURL(mainBundle, CFSTR("red"),
													   CFSTR ("caf"), nil);
	
	// Create a system sound object representing the sound file
	AudioServicesCreateSystemSoundID(soundFileURLRef, &red);
}

-(void)loadYellow
{
	CFBundleRef mainBundle = CFBundleGetMainBundle();
	
	// Get the URL to the sound file to play. 
	CFURLRef soundFileURLRef = CFBundleCopyResourceURL(mainBundle, CFSTR("yellow"),
													   CFSTR ("caf"), nil);
	
	// Create a system sound object representing the sound file
	AudioServicesCreateSystemSoundID(soundFileURLRef, &yellow);
}

-(void)loadGameOver
{
	CFBundleRef mainBundle = CFBundleGetMainBundle();
	
	// Get the URL to the sound file to play. 
	CFURLRef soundFileURLRef = CFBundleCopyResourceURL(mainBundle, CFSTR("game-over"),
													   CFSTR ("caf"), nil);
	
	// Create a system sound object representing the sound file
	AudioServicesCreateSystemSoundID(soundFileURLRef, &gameEnds);
}

-(IBAction)makeNewGame
{
	//blows out running timers to prevent app crash
	[timer invalidate];
	[controlTimer invalidate];
	//set default values
	self.roundsWon = 0;
	self.lightColor = @"none";
	self.gameCounter.text = @"";
	self.computerMoves = [NSMutableArray arrayWithObjects: nil, nil];
	//self.computerMoves = [NSMutableArray arrayWithObjects: @"green", @"blue", @"red", nil];
	
	//bring in sounds here
	[self loadSounds];
	
	//AudioServicesPlaySystemSound(gameEnds);
	//draw the view again for some reason
	[(GLView *)self.view drawView];
	//start the game
	[self callComputersTurn];
}

-(void)callComputersTurn
{
	//sets necessary values for running
	self.currentIndex = 0;
	self.chooseRand = YES;
	//NSTimer *aTimer = [NSTimer timerWithTimeInterval:2 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
	//gives a couple of seconds before game starts
	self.timer = [NSTimer timerWithTimeInterval:2 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
	[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
	
}

/*
 works recursively due to the nature of nstimer
 opted out of using the sleep() function
 */
-(void)computersTurn
{
	[(GLView *)self.view drawView];
	
	//if there are recorded moves use them
	if(self.currentIndex < [self.computerMoves count])
	{
		self.lightColor = [self.computerMoves objectAtIndex:self.currentIndex];
		
		self.currentIndex++;
		
		
		//light up the index
		[(GLView *)self.view drawView];
		if([self.lightColor isEqualToString:@"red"])
		{
			AudioServicesPlaySystemSound(red);
		}
		if([self.lightColor isEqualToString:@"blue"])
		{
			AudioServicesPlaySystemSound(blue);
		}
		if([self.lightColor isEqualToString:@"yellow"])
		{
			AudioServicesPlaySystemSound(yellow);
		}
		if([self.lightColor isEqualToString:@"green"])
		{
			AudioServicesPlaySystemSound(green);
		}
		
		self.lightColor = @"none";
		
		//NSTimer *bTimer = [NSTimer timerWithTimeInterval:0.4 target:self selector:@selector(pauseOneSec) userInfo:nil repeats:NO];
		//[[NSRunLoop currentRunLoop] addTimer:bTimer forMode:NSDefaultRunLoopMode];
		
		self.timer = [NSTimer timerWithTimeInterval:0.4 target:self selector:@selector(pauseOneSec) userInfo:nil repeats:NO];
		[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
	} else 
		if(self.chooseRand){
			//creates a new move to add to the mutable array
			int r = arc4random() % 4;
			if(r == 0)
			{
				//red
				self.lightColor = @"red";
				AudioServicesPlaySystemSound(red);
			} else
				if(r == 1)
				{
					//blue
					self.lightColor = @"blue";
					AudioServicesPlaySystemSound(blue);
				} else 
					if(r == 2)
					{
						//yellow
						self.lightColor = @"yellow";
						AudioServicesPlaySystemSound(yellow);
					} else {
						//green
						self.lightColor = @"green";
						AudioServicesPlaySystemSound(green);
					}
			
			self.chooseRand = NO;
			
			[self.computerMoves addObject:[NSString stringWithFormat:self.lightColor]]; 
			
			[(GLView *)self.view drawView];
			
			//NSTimer *dTimer = [NSTimer timerWithTimeInterval:0.4 target:self selector:@selector(doNothing) userInfo:nil repeats:NO];
			//[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];
			
			self.timer = [NSTimer timerWithTimeInterval:0.4 target:self selector:@selector(doNothing) userInfo:nil repeats:NO];
			[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
			self.lightColor = @"none";
		}
	
}

//actually pauses for 1/10 of a second now. didn't bother to rename
-(void)pauseOneSec
{
	
	[(GLView *)self.view drawView];
	//NSTimer *dTimer = [NSTimer timerWithTimeInterval:0.1 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
	//[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];

	self.timer = [NSTimer timerWithTimeInterval:0.1 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
	[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
}

-(void)doNothing
{
	//no longer does nothing, instead calls starPlayersTurn
	//not sure why I did it this way
	[(GLView *)self.view drawView];
	[self startPlayersTurn];
}

-(void)startPlayersTurn
{
	//set timer to number of seconds = number of buttons to push
	int xyz = [self.computerMoves count];
	//allow player clicks to register
	self.playersTurn = YES;
	//sets vars needed for accuracy checking
	self.playerIndex = 0;
	self.playerMoves   = [NSMutableArray arrayWithObjects: nil, nil];
	//NSTimer *pTimer = [NSTimer timerWithTimeInterval:xyz target:self selector:@selector(checkPlayersGuessFinal) userInfo:nil repeats:NO];
	//[[NSRunLoop currentRunLoop] addTimer:pTimer forMode:NSDefaultRunLoopMode];
	
	self.controlTimer = [NSTimer timerWithTimeInterval:xyz target:self selector:@selector(checkPlayersGuessFinal) userInfo:nil repeats:NO];
	[[NSRunLoop currentRunLoop] addTimer:controlTimer forMode:NSDefaultRunLoopMode];
}

-(void)checkPlayersGuessFinal
{
	//turns off board interaction
	self.playersTurn = NO;
	//game won
	if(self.playerIndex == [self.computerMoves count])
	{
		//round won
		self.roundsWon++;
		self.gameCounter.text = [NSString stringWithFormat:@"%d", self.roundsWon];
		self.currentIndex = 0;
		self.chooseRand = YES;
		//NSTimer *pTimer = [NSTimer timerWithTimeInterval:1 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
		//[[NSRunLoop currentRunLoop] addTimer:pTimer forMode:NSDefaultRunLoopMode];
		
		self.timer = [NSTimer timerWithTimeInterval:1 target:self selector:@selector(computersTurn) userInfo:nil repeats:NO];
		[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
	} else {
		//lost
		//NSTimer *gTimer = [NSTimer timerWithTimeInterval:2 target:self selector:@selector(gameOver) userInfo:nil repeats:NO];
		//[[NSRunLoop currentRunLoop] addTimer:gTimer forMode:NSDefaultRunLoopMode];	
		self.timer = [NSTimer timerWithTimeInterval:2 target:self selector:@selector(gameOver) userInfo:nil repeats:NO];
		[[NSRunLoop currentRunLoop] addTimer:timer forMode:NSDefaultRunLoopMode];
	}
}


/*
 due to fast paced game play. 
 decided to only use touchesBegan
 saw touchesMoved/Ended to be a waste of time
 */
- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event {

	//get the position
	CGPoint pos = [[touches anyObject] locationInView:self.view];
	
	GLubyte col[4];
	
	//get the color of the spot clicked
	glReadPixels(pos.x, -pos.y + 480, 1, 1, GL_RGBA, GL_UNSIGNED_BYTE, &col[0]);
	
	//only if player interaction enabled
	if(playersTurn)
	{
		//check which color was chosen by comparing values in the 3 rby columns
		
		/*
		 color clicked is implemented
		 light up the color for a second. possibly?
		 */
		
		//check red
		if(col[0] > 0 && col[1] == 0 && col[2] == 0)
		{
			//some red color. compare position with location in computer moves array
			self.lightColor = @"red";
			[(GLView *)self.view drawView];
			[self.playerMoves addObject:[NSString stringWithFormat:self.lightColor]];
			AudioServicesPlaySystemSound(red);
			self.lightColor = @"none";
			NSTimer *dTimer = [NSTimer timerWithTimeInterval:.4 target:(GLView *)self.view selector:@selector(drawView) userInfo:nil repeats:NO];
			[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];
			
			[self playerGuess];
		} else
			if(col[0] == 0 && col[1] == 0 && col[2] > 0)
			{
				//blue
				self.lightColor = @"blue";
				[(GLView *)self.view drawView];
				[self.playerMoves addObject:[NSString stringWithFormat:self.lightColor]];
				AudioServicesPlaySystemSound(blue);
				self.lightColor = @"none";
				NSTimer *dTimer = [NSTimer timerWithTimeInterval:.4 target:(GLView *)self.view selector:@selector(drawView) userInfo:nil repeats:NO];
				[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];
				[self playerGuess];
			} else
				if(col[0] == 0 && col[1] > 0 && col[2] == 0)
				{
					//green
					self.lightColor = @"green";
					[(GLView *)self.view drawView];
					[self.playerMoves addObject:[NSString stringWithFormat:self.lightColor]];
					AudioServicesPlaySystemSound(green);
					self.lightColor = @"none";
					NSTimer *dTimer = [NSTimer timerWithTimeInterval:.4 target:(GLView *)self.view selector:@selector(drawView) userInfo:nil repeats:NO];
					[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];
					[self playerGuess];
				} else 
					if(col[0] > 0 && col[1] > 0 && col[2] == 0)
					{
						//yellow
						self.lightColor = @"yellow";
						[(GLView *)self.view drawView];
						[self.playerMoves addObject:[NSString stringWithFormat:self.lightColor]];
						AudioServicesPlaySystemSound(yellow);
						self.lightColor = @"none";
						NSTimer *dTimer = [NSTimer timerWithTimeInterval:.4 target:(GLView *)self.view selector:@selector(drawView) userInfo:nil repeats:NO];
						[[NSRunLoop currentRunLoop] addTimer:dTimer forMode:NSDefaultRunLoopMode];
						[self playerGuess];
					}
		
	}
}

//this will compare guess against corresponding index in 
-(void)playerGuess
{
	//do a compare against computerMoves using playerIndex
	if([[self.computerMoves objectAtIndex:self.playerIndex] isEqualToString:[self.playerMoves objectAtIndex:self.playerIndex]])
	{
		//correct guess
		self.playerIndex++;
		if(self.playerIndex == [self.computerMoves count])
		{
			self.playersTurn = NO;
		}
	} else {
		//failure
		[self gameOver];
	}
		//if succeed keep going
		//otherwise call game over
	//increment playerIndex
}


//called when player fails
-(void)gameOver
{
	[timer invalidate];
	[controlTimer invalidate];
	self.gameCounter.text = @"Game Over!";
	self.playersTurn = NO;
	AudioServicesPlaySystemSound(gameEnds);
}


/*
 opted to only use triangle fans.
 seemed easier to just draw quarter of a circle instead of figuring out 
 the points needed to use triangle strips
 
 if statements used for "light ups" just raising the level of color
 */
- (void)drawView:(UIView *)theView
{
	
	glClearColor(.8, .8, .8, 1);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glLoadIdentity(); 
	glTranslatef(0,0,-2.75f);
	glEnableClientState(GL_VERTEX_ARRAY);
	
    // *** BEGIN DRAWING CODE ***
	
	//black circle in the center
	//opted for a smaller inner circle as i thought it looked better
	GLfloat miniTriFan[720];	
	for (int i = 0; i < 720; i += 2) {
		// x value
		miniTriFan[i]   = (cos(DEGREES_TO_RADIANS(i)) * .4);
		// y value
		miniTriFan[i+1] = (sin(DEGREES_TO_RADIANS(i)) * .4);
	}	
	glColor4f(0.0, 0.0, 0.0, 0.0);
	//glTranslatef(0,0,-2.75f);
	glVertexPointer(2, GL_FLOAT, 0, miniTriFan); 
	glDrawArrays(GL_TRIANGLE_FAN, 0, 360); 
	
	//draw the triangleFan for red
	GLfloat redTriFan[178];
	
	for(int i = 2; i < 178; i += 2)
	{
		// x value
		redTriFan[i]   = (cos(DEGREES_TO_RADIANS(i / 2)) * .95);
		// y value
		redTriFan[i+1] = (sin(DEGREES_TO_RADIANS(i / 2)) * .95);
		
		
	}
	//sets origin for circle coords
	redTriFan[0] = .03;
	redTriFan[1] = .03;
	
	//glTranslatef(0,0,-2.75f);
	if([self.lightColor isEqualToString:@"red"])
	{
		glColor4f(1.0, 0.0, 0.0, 1.0);
	} else {
		glColor4f(0.6, 0.0, 0.0, 1.0);
	}
	glVertexPointer(2, GL_FLOAT, 0, redTriFan);
	glDrawArrays(GL_TRIANGLE_FAN, 0, 89); 
	
	//draws blue corner
	GLfloat blueTriFan[178];
	
	for(int i = 2; i < 178; i += 2)
	{
		// x value
		blueTriFan[i]   = (cos(DEGREES_TO_RADIANS(i/2)) * .95);
		// y value
		blueTriFan[i+1] = -(sin(DEGREES_TO_RADIANS(i/2)) * .95);
		
		
	}
	blueTriFan[0] = .03;
	blueTriFan[1] = -.03;
	
	//glTranslatef(0,0,-2.75f);
	//was 0 0 1 1
	if([self.lightColor isEqualToString:@"blue"])
	{
		glColor4f(0.0, 0.0, 1.0, 1.0);
	} else {
		glColor4f(0.0, 0.0, 0.6, 1.0);
	}
	glVertexPointer(2, GL_FLOAT, 0, blueTriFan);
	glDrawArrays(GL_TRIANGLE_FAN, 0, 89); 
	
	//draws the yellow corner
	GLfloat yellowTriFan[178];
	
	for(int i = 2; i < 178; i += 2)
	{
		// x value
		yellowTriFan[i]   = -(cos(DEGREES_TO_RADIANS(i/2)) * .95);
		// y value
		yellowTriFan[i+1] = -(sin(DEGREES_TO_RADIANS(i/2)) * .95);
		
		
	}
	yellowTriFan[0] = -.03;
	yellowTriFan[1] = -.03;
	
	//glTranslatef(0,0,-2.75f);
	
	if([self.lightColor isEqualToString:@"yellow"])
	{
		glColor4f(1.0, 1.0, 0.0, 1.0);
	} else {
		glColor4f(0.6, 0.6, 0.0, 1.0);
	}
	
	//glColor4f(0.6, 0.6, 0.0, 1.0);
	glVertexPointer(2, GL_FLOAT, 0, yellowTriFan);
	glDrawArrays(GL_TRIANGLE_FAN, 0, 89); 
	
	//draws the green corner
	GLfloat greenTriFan[178];
	
	for(int i = 2; i < 178; i += 2)
	{
		// x value
		greenTriFan[i]   = -(cos(DEGREES_TO_RADIANS(i/2)) * .95);
		// y value
		greenTriFan[i+1] = (sin(DEGREES_TO_RADIANS(i/2)) * .95);
		
		
	}
	greenTriFan[0] = -.03;
	greenTriFan[1] = .03;
	
	//glTranslatef(0,0,-2.75f);
	if([self.lightColor isEqualToString:@"green"])
	{
		glColor4f(0.0, 1.0, 0.0, 1.0);
	} else {
		glColor4f(0.0, 0.6, 0.0, 1.0);
	}
	//glColor4f(0.0, 0.6, 0.0, 1.0);
	glVertexPointer(2, GL_FLOAT, 0, greenTriFan);
	glDrawArrays(GL_TRIANGLE_FAN, 0, 89); 
	
	
	//draws the board
	GLfloat triFan[720];	
	for (int i = 0; i < 720; i += 2) {
		// x value
		triFan[i]   = (cos(DEGREES_TO_RADIANS(i)) * 1);
		// y value
		triFan[i+1] = (sin(DEGREES_TO_RADIANS(i)) * 1);
	}	
	glColor4f(0.0, 0.0, 0.0, 0.0);
	//glTranslatef(0,0,-2.75f);
	glVertexPointer(2, GL_FLOAT, 0, triFan); 
	glDrawArrays(GL_TRIANGLE_FAN, 0, 360); 
	
    // *** END DRAWING CODE ***
	
	glDisableClientState(GL_VERTEX_ARRAY);
    
}
-(void)setupView:(GLView*)view
{
	const GLfloat zNear = 0.01, zFar = 1000.0, fieldOfView = 45.0; 
	GLfloat size; 
	glEnable(GL_DEPTH_TEST);
	glMatrixMode(GL_PROJECTION); 
	size = zNear * tanf(DEGREES_TO_RADIANS(fieldOfView) / 2.0); 
	CGRect rect = view.bounds; 
	glFrustumf(-size, size, -size / (rect.size.width / rect.size.height), size / 
			   (rect.size.width / rect.size.height), zNear, zFar); 
	glViewport(0, 0, rect.size.width, rect.size.height);  
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity(); 
}
- (void)dealloc 
{
	self.gameCounter = nil;
	self.lightColor = nil;
	self.timer = nil;
	self.computerMoves = nil;
	self.playerMoves = nil;
	self.controlTimer = nil;
	
    [super dealloc];
}
@end
