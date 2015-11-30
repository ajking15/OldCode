#import <Foundation/Foundation.h>
#import "BookmarkManager.h"

int main (int argc, const char * argv[]) {
    NSAutoreleasePool * pool = [[NSAutoreleasePool alloc] init];

    // insert code here...
    	BookmarkManager *manage = [[BookmarkManager alloc] init];
	
	//add works
	[manage addBookmarkURL:@"www.hello.com" title:@"goodbye"];
	[manage addBookmarkURL:@"hola" title:@"como estas"];
	//add with tags works
	[manage addBookmarkURL:@"2nd book" title:@"lisp is annoying" tags:@"umbc, iphone, cmsc"];
	// prevent title and tags the same works
	[manage addBookmarkURL:@"2nd book" title:@"lisp is annoying" tags:@"iphone, cmsc"];
	
	[manage addBookmarkURL:@"3rd book" title:@"emacs is annoying" tags:@"umbc, chem, cmsc"];
	[manage addBookmarkURL:@"4th book" title:@"xkcd.com" tags:@"cmsc"];
	NSLog(@"Listing bookmarks 1st");
	[manage listBookmarks];
	NSLog(@"end bookmarks list");
	[manage addBookmarkURL:@"4th book" title:@"lisp is annoying" tags:@"umbc, iphone, cmsc"];
	//NSLog(@"Before remove");
	//list bookmarks works
    [manage listBookmarks];
	//count works
	NSLog(@"%d",[manage count]);
	//list bookmarks with tags
	[manage listBookmarksWithTag:@"umbc"];
	//
	[manage removeBookmarkWithURL:@"www.hello.com"];
	NSLog(@"after the remove with url");
	[manage listBookmarks];
	NSLog(@"after remove with title");
	[manage removeBookmarkWithTitle:@"emacs is annoying"];
	 [manage listBookmarks];
	
	NSLog(@"Test Url for title: %@", [manage urlForTitle:@"emacs is annoying"]);
	[manage tagsForTitle:@"goodbye"];
	[manage tagsForTitle:@"dklfsld"];
	[manage tagsForTitle:@"emacs is annoying"];
	NSLog(@"second bookmark listing");
	[manage listBookmarks];
	NSLog(@"listing end");
	
	NSLog(@"Test title for url: %@", [manage titleForURL:@"www.hello.com"]);
	NSLog(@"test title for url: %@", [manage titleForURL:@"dklfjlsd"]);
	NSLog(@"after title for url test");
	[manage tagsForURL:@"2nd book"];
	[manage tagsForURL:@"www.hello.com"]; //problem with not tags
	[manage tagsForURL:@"jar jar"]; //problem with nonexistant url
	[manage tagsForURL:@"4th book"];
	NSLog(@"listing bookmarks last");
	[manage listBookmarks];
	NSLog(@"list end");
	 [pool drain];
	
    return 0;
}
