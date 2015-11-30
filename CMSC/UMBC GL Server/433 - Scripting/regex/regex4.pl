#!/usr/bin/perl -w

# Match players that threw for 3000 or more yards and more than 30 touchdowns.
#
# For example:
# 
#    'Matt Schaub,HOU,QB,396,583,67.9,36.4,4770,8.2,298.1,29,15,230,39.5,72T,62,15,25,98.6' does not match
#    'Peyton Manning,IND,QB,393,571,68.8,35.7,4500,7.9,281.2,33,16,237,41.5,80T,59,8,10,99.9' matches
#    'Tom Brady,NE,QB,371,565,65.7,35.3,4398,7.8,274.9,28,13,214,37.9,81T,43,12,16,96.2' does not match
#    'Drew Brees,NO,QB,363,514,70.6,34.3,4388,8.5,292.5,34,11,210,40.9,75T,58,11,20,109.6' matches

while(<>) {
  print if /REGEX/;
}
