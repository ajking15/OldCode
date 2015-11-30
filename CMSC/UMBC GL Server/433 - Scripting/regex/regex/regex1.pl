#!/usr/bin/perl -w

# Print lines that contain an opening and closing HTML heading tag.  Valid 
# tags are h1, h2, h3, h4, h5, h6 regardless of the case.  Tags may contain
# attributes inside of the opening portion of the same number.  Tags may also
# have any arbitary content inside of them.  You may assume that a '>'
# character will not appear inside of the attribute's value portion.
#
# For example:
#
#      '<h1>foo</h1>' matches
#      '<h2 class="foo">foo</h2>' matches
#      '<h3>bar</H3>' matches
#      '<h4 class="blah" id="foo">baz</h4>' matches
#      '<h5><span class="big">B</span>ig</h5>' matches
#      '<h9>foo</h9>' does not match
#      '<h1>foo bar baz...' does not match

while(<>) {
  print if /<h[1-6].*?>.*<\/h[1-6]>/ig;
}
