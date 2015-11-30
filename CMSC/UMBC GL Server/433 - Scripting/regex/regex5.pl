#!/usr/bin/perl -w

# Convert any lines containing wiki image markup to actual HTML image markup.
# Wiki image tags are formatted as follows:
#
# '[[Image:'
#     All tags start out with 2 opening square brackets followed by 'Image:'
#     (regardless of case).
#
# 'base_filename'
#     Next there is a required file name.  For the purpose of this assignment
#     this name will consist of letter (regardless of case), numbers and
#     underscores.
#
# '.ext'
#     Followed by a period and either gif, jpg, or png (regardless of case)
#
# '|Some Alternate Text'
#     After the file extension, there may OPTIONALLY be a pipe followed by
#     free form text.  You may assume that this section will not contain any
#     newlines or square brackets - all other characters are fair game.
#
# ']]'
#     Lastly, there are a pair of closing square brackets.
#
# For example, the following wiki image tags...
#
#     [[Image:wiki.png]]
#     [[Image:wiki.png|Wikipedia, The Free Encyclopedia.]]
#
# ...would get converted to the following HTML image tags
#
#     <img src="wiki.png" alt=""/>
#     <img src="wiki.png" alt="Wikipedia, The Free Encyclopedia."/>

while(<>) {
  s/REGEX/REPLACE/;
  print;
}