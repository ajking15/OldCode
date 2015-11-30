#!/usr/bin/perl -w

# Find and replace all instance of valid Visa, MasterCard, Discover or
# American Express card numbers with '...' followed by the last 4 numbers.
# The following table details the digits each card must begin with as well as
# the number of digits allowed for each card.  In order to be a valid card
# number it must not surrounded by a "word" character (in the regex sense)
#
# Card Type               Starts With       Number Digits
# -------------------------------------------------------
# American Express        34 or 37          15
# Discover                65 or 6011        16
# MasterCard              51 through 55     16
# Visa                    4                 13 or 16
#
# For example, the following lines...
#
#     'not dans amex: 341234567890123'
#     '412345678901234'
#     '6011012345678901 is a discover card'
#
# ...would get converted to...
#
#     'not dans amex: ...0123'
#     '412345678901234'
#     '...8901 is a discover card'

while(<>) {
  s/REGEX/REPLACE/;
  print;
}
