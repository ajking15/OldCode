//
//  BookmarkManager.m
//  Project1
//
//  Created by Christopher Mai on 9/9/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import "BookmarkManager.h"

/* manages an array of bookmarks */
@implementation BookmarkManager 

@synthesize count = _count;

/* add bookmark with designated url and title. tags empty */
- (BOOL)addBookmarkURL:(NSURL *)url title:(NSString *)title {
	//have to check if url or title already exists
	
	//search for existing title
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	while(mark = [enumerator nextObject]) {
		Bookmark *book; // = [[Bookmark alloc] init];
		
		book = mark;
		
		if([[book getTitle] isEqualToString:title]){
		//	[book release];
			return NO;
		}
		//[book release];
	}
	
	//search for existing url
	enumerator = [manager objectEnumerator];
	
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; // = [[Bookmark alloc] init];
		
		book = mark;
		
		//use url descriptions to compare as strings
		if([[book getUrlDescription] isEqualToString:[url description]]){
		//	[book release];
			return NO;
		}
		//[book release];
	}
	
	
	Bookmark *book = [[Bookmark alloc] initWithUrl:url	title:title];
	

	[manager addObject:book];
	//[book release];
	return YES;
}

/* add bookmark with designated url, title, and tags. */
- (BOOL)addBookmarkURL:(NSURL *)url title:(NSString *)title tags:(NSString *)tags {
	//have to check if url or title already exists
	
	//search for existing title
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	while(mark = [enumerator nextObject]) {
		Bookmark *book;
		
		book = mark;
		
		if([[book getTitle] isEqualToString:title])
		{
		//	[book release];
			return NO;
		}
		//[book release];
	}
	
	//search for existing url
	enumerator = [manager objectEnumerator];
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book;
		
		book = mark;
		
		//use url description to compare as strings
		if([[book getUrlDescription] isEqualToString:[url description]]){
		//	[book release];
			return NO;
		}
		//[book release];
	}

	Bookmark *book = [[Bookmark alloc] initWithUrl:url title:title tags:tags];
	
	[manager addObject:book];
		
	return YES;
}

/* list all bookmarks */
- (void)listBookmarks {
	NSEnumerator *enumerator = [manager objectEnumerator];
	id mark;
	Bookmark *book; 
	while(mark = [enumerator nextObject]) {
		
		
		book = mark;
		
		NSLog(@"%@ < %@ > Tags: %@", [book getTitle], [book getUrl], [book getTags]);
		
	}
	//[enumerator release];
	//[book autorelease];
}

/* list any and all bookmarks containing designated tag */
- (void)listBookmarksWithTag:(NSString *)tag {
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
    Bookmark *book; 
	while(mark = [enumerator nextObject]) {
			
		book = mark;
		    
		for(int x = 0; x < [[book getTagsArray] count]; x++)
		{
			if([[[book getTagsArray] objectAtIndex:x] isEqualToString:tag])
			{
				NSLog(@"%@ < %@ > Tags: %@", [book getTitle], [book getUrl], [book getTags]);
			}
		}
	}

}

/* removes bookmark for designated url */
- (BOOL)removeBookmarkWithURL:(NSURL *)url {
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book;
		
		book = mark;
		
		if([[book getUrlDescription] isEqualToString:[url description]])
		{
			[manager removeObject:book];
			
			[book release];
			
			return YES;
		}
		
	}
	
	return NO;
}

/* removes bookmark with designated title */
- (BOOL)removeBookmarkWithTitle:(NSString *)title {
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; 		
		book = mark;
		
		if([[book getTitle]isEqualToString:title])
		{
			[manager removeObject:book];
			
			[book release];
			
			return YES;
		}
	}
	
	return NO;
}

/* returns url for designated title */
- (NSURL *)urlForTitle:(NSString *)title {
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; 
		
		book = mark;
		
		if([[book getTitle]isEqualToString:title])
		{
			
			return [book getUrl];
		}
		
	}
	
	return nil;
}

/* returns tags for designated title */
- (NSArray *)tagsForTitle:(NSString *)title {
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; 		
		book = mark;
		
		if([[book getTitle]isEqualToString:title])
		{
			NSArray* array = [[NSArray alloc] init];
			if([[book getTagsArray]  count] > 0)
			{
				//returns matching tags
				return [book getTagsArray];
			}
			[array autorelease];
			//matched but no tags
			return array;
		}
	}
	//no match
	return nil;
}

/* returns title for designated url */
- (NSString *)titleForURL:(NSURL *)url {
	//find the url
	NSEnumerator *enumerator = [manager objectEnumerator];
	
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; 		
		book = mark;
		
		if([[book getUrlDescription] isEqualToString:[url description]])
		{
			//url found returning title
			return [book getTitle];
		}
		
	}
	//url not found
	return nil;
}

/* returns tags corresponding to designated url */
- (NSArray *)tagsForURL:(NSURL *)url {
	NSEnumerator *enumerator = [manager objectEnumerator];
	id mark;
	
	while(mark = [enumerator nextObject]) {
		Bookmark *book; 
		
		book = mark;
		
		//check if url matches
		if([[book getUrlDescription] isEqualToString:[url description]])
		{
			//check if array is empty
			if([[book getTagsArray] count] > 0)
			{
				//returns tags in an array
				return [book getTagsArray];
			}
			//returns empty array
			return [[NSArray alloc] init];
		}
		
	}
	//url not present
	return nil;
}

/* returns number of bookmarks in manager */
- (int)count {
	return manager.count;
}


/* constructor */
- (id)init {
	//call supper init
	if (self = [super init]) {
		
		//initialize array and count
		manager = [[NSMutableArray alloc] init];
		count = 0;
	}
	
	return self;
}


-(void)dealloc{
	//blow out the array
	[manager removeAllObjects];
	//release the array
	[manager release];
	
	[super dealloc];
}

@end

@implementation Bookmark
//call new bookmark

		-(id)init {
			if (self = [super init]) {
				
				//someone calls an init that doesn't do anything, so nothing happens
			}
			
			return self;
		}
		
-(id)initWithUrl: (NSURL *)url title:(NSString *)title{
	NSLog(@"%@", title);
	if (self = [super init]) {
		_url = url;
		_title = title;
		_tags = @"No Tags";
    }
	
	return self;
}

-(id)initWithUrl: (NSURL *)url title:(NSString *)title tags:(NSString *) tags{
	if (self = [super init]) {
		_url = url;
		_title = title;
		_tags = tags;
		_tagsArray = [tags componentsSeparatedByString:@","];
	}
	
	return self;
	
}

-(NSURL *)getUrl{
	return _url;
}

-(NSString *)getUrlDescription{
	return [_url description];
}

-(NSString *)getTitle{
	return _title;
}


-(NSString*)getTags{
	return _tags;
}

-(NSArray *)getTagsArray{
	return _tagsArray;
}





@end