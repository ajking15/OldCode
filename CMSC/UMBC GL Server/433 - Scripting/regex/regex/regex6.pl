#!/usr/bin/perl -w

# Convert forum/wiki/blog tags into the corresponding HTML tag.
# The tags to be converted are [b], [i] and [u].  These tags
# are wrapped around text and are closed with a corresponding
# [/b], [/i] or [/u] tag.  Print the line regardless of substitution.
#
# For example, the following lines...
#
#     "[b]this is bolded[/b]"
#     "[u][b]continued onto next line"
#
# ...would get converted to...
#
#     "<b>this is bolded</b>"
#     "<u><b>continued onto next line"

while (<>) {
   s/\[(.+?)\]/<$1>/mg;
   print;
}

