//
//  BookmarkManager.h
//  Project1
//
//  Created by Christopher Mai on 9/9/09.
//  Copyright 2009 __MyCompanyName__. All rights reserved.
//

#import <Cocoa/Cocoa.h>

/*
 BookmarkManager class consisting of an array
 */
@interface BookmarkManager : NSObject {
   /* instance vars */
	NSMutableArray *manager;
	int  count;
}

@property(readonly) int count;
/* Method Declarations */

/*
 returns yes or no based on wether or not a bookmark with a unique title and url was added
 url - website url of a bookmark
 title - title used to distinguish bookmarks from another
 */
- (BOOL)addBookmarkURL:(NSURL *)url title:(NSString *)title;

/*
 returns yes or no based on wether or not a bookmark with a unique title and url was added
 url - website url of a bookmark
 title - title used to distinguish bookmarks from another
 tags - search tags based on relevance to content
 */

- (BOOL)addBookmarkURL:(NSURL *)url title:(NSString *)title tags:(NSString *)tags;

/*
 prints out all bookmarks including title, tags, and url to console
 */
- (void)listBookmarks;

/*
 prints only the bookmarks with a tag matching the one passed in to console
 tag - tag used to search current bookmarks for printing
 */
- (void)listBookmarksWithTag:(NSString *)tag;

/*
 removes a bookmark if it's url matches the passed in url
 url - url used to search current bookmarks for removal
 */
- (BOOL)removeBookmarkWithURL:(NSURL *)url;

/*
 removes a bookmark based off of it's title
 title - item used to search the bookmarks for title comparsion
 */
- (BOOL)removeBookmarkWithTitle:(NSString *)title;

/*
 returns a url for it's matching title
 title - string used for searching 
 */
- (NSURL *)urlForTitle:(NSString *)title;

/*
 returns an array of tags corresponding to it's matching title
 title - used to search the bookmarks for a match
 */
- (NSArray *)tagsForTitle:(NSString *)title;

/*
 returns a title that correspons to the matching url sent it
 url - used to search the bookmarks for a match
 */
- (NSString *)titleForURL:(NSURL *)url;

/*
 returns an array of tags which correspond to the sent in url
 url - used to search the bookmarks for a match
 */
- (NSArray *)tagsForURL:(NSURL *)url;

/*
 returns size of the array
 */
- (int)count;

/*
 used to initialize an object of type bookmark manager
 */
- (id)init;  

-(void)dealloc;

@end

/*
 Bookmark class consisting of a url, title, tags, and an array of tags
 */
@interface Bookmark : NSObject {
	NSURL    * _url;
	NSString * _title;
    NSArray * _tagsArray;
	NSString       * _tags;
}

/*
 constructor used to initialize an object of type bookmark
 */
-(id)init;

/*
 constructor used to initialize an object of type bookmark with a title and url
 url - url to be stored
 title- title to be stored
 */
-(id)initWithUrl: (NSURL *)url title:(NSString *)title;
	
/*
 constructor used to initialize an object of type bookmark with a title, url, and tags
 url - url to be stored
 title - title to be stored
 tags - tags to be stored
 */
-(id)initWithUrl: (NSURL *)url title:(NSString *)title tags:(NSString *) tags;

/*
 retrieves the url
 */
-(NSURL *)getUrl;

/*
 retrieves a string representation of the url
 */
-(NSString *)getUrlDescription;

/*
 retrieves the title
 */
-(NSString *)getTitle;

/*
 retrieves the tags
 */
-(NSString *)getTags;

/*
 retrieves the tags in an array form
 */
-(NSArray *)getTagsArray;

@end