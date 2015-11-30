#!/usr/bin/perl -w

# Print line if it contains a time in the following format:
#
#     <HH>:<MM><AMPM>
#
# Where:
#
# <HH>
#     Is a numeric representation of the hour between 1 and 12.  The hour
#     may optionally be padded with a leading zero such as 01 through 09.
#
# <MM>
#     Is a numeric representation of the minutes between 00 and 60.  It must
#     be exactly 2 digits, zero padded to 2 digits if necessary.
#
# <AMPM>
#     Is either an uppercase or lowercase (not mixed case) "am" or "pm" with
#     an optional leading space.
#
# Additionally, for the time to be valid there should be anything but an 
# alphanumeric on both sides.
#
# For example:
#
#     "12:34 am" matches
#     "5:16 p.m." does not match
#     "8:01 AM" matches
#     "3:30 pm" matches
#     "9:18pM" does not match

while (<>) {
  print if /^([0]?[1-9]|[[1][0-2]):[0-5][0-9]\s*(am|pm|AM|PM)/gm;
}

