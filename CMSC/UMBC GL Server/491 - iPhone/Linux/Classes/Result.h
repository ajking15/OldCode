//
//  Result.h
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>

/*
 stores information used in saving scan results
 */
@interface Result : NSObject <NSCoding> {
	// what fields do i want
	// ip address
	// host name
	// telnet running
	// ssh running
	// server type
	// array of vulnerabilities
	NSString *ipAddress;
	NSString *hostname;
	NSString *serverType;
	NSString *sshCheck;
	NSString *telnetCheck;
	NSString *ftpCheck;
}

@property(nonatomic, retain) NSString *ipAddress;
@property(nonatomic, retain) NSString *hostname;
@property(nonatomic, retain) NSString *serverType;
@property(nonatomic, retain) NSString *sshCheck;
@property(nonatomic, retain) NSString *telnetCheck;
@property(nonatomic, retain) NSString *ftpCheck;

+(id)resultWithIPAddress:(NSString *)newIP
					host:(NSString *)newHost
				  server:(NSString *)newServer
					 ssh:(NSString *)newSSH
				  telnet:(NSString *)newTelnet
					 ftp:(NSString *)newFTP;

@end
