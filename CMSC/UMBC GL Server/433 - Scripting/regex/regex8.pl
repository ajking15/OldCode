#!/usr/bin/perl -w

# Convert each line in the CSV into an XML-like structure as shown below:
#
# <student campusid="CAMPUS_ID">
#     <name>FULL_NAME</name>
#     <rank>CLASS_RANK</rank>
#     <phone>(XXX) XXX-XXXX</phone>
# </student>
#
# For example, the following line...
#
#     '1234567890,"Doe,John Q",Graded,3.00,Undergraduate Degree - Computer Science - BS,Senior,AB12345,AB12345@umbc.edu,410/455-1000'
#
# ...would be reported as...
#
#     '<student campusid="AB12345">[NEWLINE][TAB]<name>Doe,John Q</name>[NEWLINE][TAB]<rank>Senior</rank>[NEWLINE][TAB]<phone>(410) 455-1000</phone>[NEWLINE]</student>'
#
# ...where [NEWLINE] is literally a newline character and [TAB] is literally a
# tab character.

while(<>) {
  print if s/REGEX/REPLACE/;
}
