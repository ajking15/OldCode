//
//  Result.m
//  Linux
//
//  Created by  on 12/13/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "Result.h"


@implementation Result

@synthesize ipAddress, hostname, serverType, sshCheck, telnetCheck, ftpCheck;

+(id)resultWithIPAddress:(NSString *)newIP
					host:(NSString *)newHost
				  server:(NSString *)newServer
					 ssh:(NSString *)newSSH
				  telnet:(NSString *)newTelnet
					 ftp:(NSString *)newFTP
{
	Result *res = [[[Result alloc] init] autorelease];
	res.ipAddress = newIP;
	res.hostname = newHost;
	res.serverType = newServer;
	res.sshCheck = newSSH;
	res.telnetCheck = newTelnet;
	res.ftpCheck = newFTP;
	
	return res;
}

-(void)encodeWithCoder:(NSCoder *)coder {
	//[coder encodeObject:self.objective forKey:@"objective"];
	[coder encodeObject:self.ipAddress forKey:@"address"];
	[coder encodeObject:self.hostname forKey:@"host"];
	[coder encodeObject:self.serverType forKey:@"server"];
	[coder encodeObject:self.sshCheck forKey:@"ssh"];
	[coder encodeObject:self.telnetCheck forKey:@"telnet"];
	[coder encodeObject:self.ftpCheck forKey:@"ftp"];
}

-(id)initWithCoder:(NSCoder *)coder {
	if(self = [super init]){
	//	self.objective = [coder decodeObjectForKey:@"objective"];
		self.ipAddress   = [coder decodeObjectForKey:@"address"];
		self.hostname    = [coder decodeObjectForKey:@"host"];
		self.serverType  = [coder decodeObjectForKey:@"server"];
		self.sshCheck    = [coder decodeObjectForKey:@"ssh"];
		self.telnetCheck = [coder decodeObjectForKey:@"telnet"];
		self.ftpCheck    = [coder decodeObjectForKey:@"ftp"];
	}
	return self;
}

@end
