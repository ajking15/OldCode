#!/usr/bin/perl -w

# Match lines that report an HTTP status code in the 400's or the 500's.
#
# Each line is in a format known as the Combined Log Format, as follows:
# 
#     %h %l %u %t "%r" %>s %b "%{Referer}i" "%{User-agent}i"
#
# Where...
#
# %h - This is the IP address of the client (remote host)
# %l - The RFC 1413 identity of the client - usually (but not always) a hyphen
# %u - This is the userid of the person requesting the document as determined
#      by HTTP authentication (frequently, though not always a hyphen)
# %t - The time that the request was received. The format is:
#           [day/month/year:hour:minute:second zone]
# "%r" - The request line from the client is given in double quotes - the
#      request line contains several peices of information
#          * The method used by the client - word in all caps
#          * A space
#          * The path to the resource requsted
#          * A space
#          * The protocol used either "HTTP/1.0" or "HTTP/1.1"
# %>s - A 3 digit HTTP status code 
# %b - A number representing the size (in bytes) of the response, or "-" if no
#      content was returned
# "%{Referer}i" - the URL of the refering URL, or "-" if no referer
# "%{User-agent}i" - the User-Agent HTTP request header - pretty much anything
#     not a quote
#
# For example:
#
#     '66.99.72.2 - - [19/Feb/2006:19:01:21 -0500] "GET /some/protected/resource HTTP/1.1" 403 288 "-" "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"' matches
#     '130.85.25.99 - - [19/Feb/2006:00:16:13 -0500] "GET /a/page/thats/there/index.xml HTTP/1.1" 200 7636 "-" "Java/1.4.2_10"' does not
  
while(<>) {
  print if /REGEX/;
}
