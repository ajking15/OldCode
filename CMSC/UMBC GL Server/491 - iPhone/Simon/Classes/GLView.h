//
//  GLView.h
//  Simon
//
//  Created by Dan Hood on 11/14/09.
//  Copyright __MyCompanyName__ 2009. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <OpenGLES/EAGL.h>
#import <OpenGLES/ES1/gl.h>
#import <OpenGLES/ES1/glext.h>


/*
 GLViewDelegate this was given code not going to comment.
 */
@protocol GLViewDelegate
- (void)drawView:(UIView *)theView;
- (void)setupView:(UIView *)theView;
@end

@interface GLView : UIView 
{
    
@private

    GLint backingWidth;
    GLint backingHeight;
    
    EAGLContext *context;    
    GLuint viewRenderbuffer, viewFramebuffer;
    GLuint depthRenderbuffer;
    
    id <GLViewDelegate>     delegate;
}
@property (assign) /* weak ref */ id <GLViewDelegate> delegate;
- (void)drawView;
@end
