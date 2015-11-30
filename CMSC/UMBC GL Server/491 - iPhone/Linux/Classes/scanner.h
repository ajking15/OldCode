//
//  scanner.h
//  Linux
//
//  Created by  on 12/11/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CFNetwork/CFNetwork.h>
#import "Result.h"
#include <arpa/inet.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>



@class Result;
/*
 class pertaining to ip scanning.
 best used with websites.
 scanning some ip addresses may cause a time wait out
 */
@interface scanner : UIViewController <UITextFieldDelegate> {
	NSMutableArray *savedResults;
	NSTimer *timer;
	NSThread *thread;
	Result      *toBeSaved;
	UITextField *ipGet;
	UILabel     *ipAddress;
	UILabel     *hostName;
	UILabel     *serverName;
	UILabel     *sshCheck;
	UILabel     *telnetCheck;
	UILabel     *ftpCheck;
	bool saveCheck;
	bool invalidCheck;
	bool threadFin;
}

@property(nonatomic, retain) NSMutableArray *savedResults;
@property(nonatomic, retain) Result *toBeSaved;
@property(nonatomic, retain) NSTimer *timer;
@property(nonatomic, retain) NSThread *thread;
@property(nonatomic, retain) IBOutlet UITextField *ipGet;
@property(nonatomic, retain) IBOutlet UILabel *ipAddress;
@property(nonatomic, retain) IBOutlet UILabel *hostName;
@property(nonatomic, retain) IBOutlet UILabel *serverName;
@property(nonatomic, retain) IBOutlet UILabel *sshCheck;
@property(nonatomic, retain) IBOutlet UILabel *telnetCheck;
@property(nonatomic, retain) IBOutlet UILabel *ftpCheck;
@property(nonatomic) bool saveCheck;
@property(nonatomic) bool invalidCheck;
@property(nonatomic) bool threadFin;

/*
 attempts to scan currently entered ip address
 */
-(IBAction)scanIP;

/*
 repeatedly called by an nstimer.
 checks to see if the additional thread has finished execution.
 */
-(void)threadFinished;

/*
 saves results to file. if it is a unique ip
 */
-(IBAction)saveResults;

/*
 actions performed. dependent on whether ip valid
 */
-(void)checkInvalid;

/*
 checks to see if ip is unique for saving
 */
-(bool)existCheck;

/*
 tries to connect to services telnet, ssh, tcp using bsd sockets
 */
-(void)checkServices;

/*
 obtains file path for the results.plist for data persistance
 */
-(NSString *)getFilePath;

/*
 checks whether or not telnet is running using bsd sockets
 */
-(bool)checkTelnet;

/*
 obtains an http response using nsurl/nsurlrequest/nsurlconnection
 */
-(void)getHttpResponse;

/*
 using bsd sockets obtains host name
 */
-(bool)getHostNameBSD;

/*
 checks whether or not ftp is running using bsd sockets
 */
-(bool)checkFTP;

/*
 checks whether or not ssh is running using bsd sockets
 */
-(bool)checkSSH;

@end
