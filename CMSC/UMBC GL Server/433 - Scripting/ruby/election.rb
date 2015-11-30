#!/usr/bin/ruby
#
# Author: Christopher Mai
# 5 cmd line args
# name of image template
# name of data file
# name of image to be created
# name of data file which the first set of election data
# name of data file which contains the second set of election data
#
# creates a map which shows a transition of republican to democrat
#  and vice versa from the 2004 elections to the 2008 elections
#  of either maryland or the usa

require 'RMagick'
include Magick

$mapColorData = Array.new()
$imgTemplate

#this will parse the data for the maps
def parseMapData()
  mapFile = File.open(ARGV[1], 'r')

  begin
    while(line = mapFile.gets)
      #read in each line. need to parse out the name and the points
      # I'll have an array of hashs
      # each hash will have an area, coordinates array, 04 data, 08 data
      #grab the line if it isn't commented
      if line =~ /^([^#].*):(.*)$/
        tempHash = Hash.new()
        # area grab works
 	tempHash['area'] = "#$1"
	
	tempHash['coordinates'] = Array.new()

	# create an array of the coordinate pairs
	tempArray = "#$2".split(";")
        for item in tempArray
	  # break apart each pair into x and y
	  t2Array = item.split(",")
	  t2Hash = Hash.new()
	  #store them
	  t2Hash['x'] = t2Array[0]
	  t2Hash['y'] = t2Array[1]
	  #each coordinate stored
	  tempHash['coordinates'].push(t2Hash)
	end
	
	#puts tempHash['area']
        tempHash['2004'] = Hash.new()
        tempHash['2008'] = Hash.new()

        $mapColorData.push(tempHash)
      end
      #puts line
    end
  rescue
    puts "EoF"
  end
 mapFile.close
end

def parse2004()
  # read in similar to mapData
  #  Only exception must check if region is in the array
  firstFile = File.open(ARGV[3], 'r')
  
  begin
    while(line = firstFile.gets)
	if line =~ /^([^#].*?):(\d.*)$/
	  #loop through $mapColorData array and match the area
	  for item in $mapColorData
	   if(item['area'].chomp == "#$1")
		#puts "match"
	        #puts item['area']
	        #puts "#$1"
		# match works! take "#$2" and splite on ":"
	        tempArray = "#$2".split(":")
		tHash = Hash.new()
		#store votes
		tHash['republican'] = tempArray[0]
		tHash['democrat']   = tempArray[1]
		tHash['other']      = tempArray[2]
		item['2004'] = tHash
	   end
	  end

	end
    end
  rescue
    puts "hmmm, no clue really"
  end


  firstFile.close
end

def parse2008()
  secondFile = File.open(ARGV[4], 'r')

 begin
    while(line = secondFile.gets)
        if line =~ /^([^#].*?):(\d.*)$/
          #loop through $mapColorData array and match the area
          for item in $mapColorData
           if(item['area'].chomp == "#$1")
                #puts "match"
                #puts item['area']
                #puts "#$1"
                # match works! take "#$2" and splite on ":"
                tempArray = "#$2".split(":")
                tHash = Hash.new()
                #store votes
                tHash['republican'] = tempArray[0]
                tHash['democrat']   = tempArray[1]
                tHash['other']      = tempArray[2]
                item['2008'] = tHash
           end
          end

        end
    end
  rescue
    puts "hmmm, no clue really"
  end

  secondFile.close
end

def colorMap()
  # for each loop over the $mapColorData array
  # for each area calculate the color.
  # set colorfill
  for region in $mapColorData
    #calculating the color
      #calculate the trend
      # compute percentage of the state that went democrat and republican
      # for each year
	totalVotes2004 = region['2004']['republican'].to_i +
			 region['2004']['democrat'].to_i +
			 region['2004']['other'].to_i
	
	#puts totalVotes2004
	#puts region['2004']['republican'].to_f

	repub2004 = region['2004']['republican'].to_f / totalVotes2004.to_f
        #puts repub2004

	demo2004 = region['2004']['democrat'].to_f / totalVotes2004.to_f
	#puts demo2004

	trend2004 = demo2004 - repub2004

	totalVotes2008 = region['2008']['republican'].to_i +
                         region['2008']['democrat'].to_i +
                         region['2008']['other'].to_i

	repub2008 = region['2008']['republican'].to_f / totalVotes2008.to_f
        #puts repub2008

        demo2008 = region['2008']['democrat'].to_f / totalVotes2008.to_f
        #puts demo2008

	trend2008 = demo2008 - repub2008

	bias = trend2004 - trend2008
	# if it's supposed to fall into the -2 to 2 range cutting it in half
	# should put it in the -1 to 1 range
	bias = bias / 2

	#set color string based off of bias
	biasPercent = bias * 100
	biasPercent = biasPercent.to_i
	#puts biasPercent
	
	
	#set color string here
	if(biasPercent < 0)
	  #trend towards democrats
     colorString = "rgb(#{100 + biasPercent}%, #{100 + biasPercent }%, 100%)"
	 #puts colorString
	 color = Magick::Pixel.from_color(colorString)
	elsif (biasPercent > 0)
	  #trend towards repulicans
	 #color = Magick::Pixel.from_color(colorString)

      colorString = "rgb(100%, #{100 - biasPercent }%, #{100 - biasPercent}%)"
        	 color = Magick::Pixel.from_color(colorString)
	else
	  # no change at all
	  r = 0
	  g = 0 
	  b = 0
	  colorString = "0%,0%,0%"
	end

	for coord in region['coordinates']
	  x = coord['x'].to_i
	  
	  y = coord['y'].to_i
	  
	  $imgTemplate = $imgTemplate.color_floodfill(x, y, color)
	end
  end
end

if(ARGV.length != 5)
  puts "invalid number of cmd line args. 5 are required"
else
  #set image name for some reason
  $imgName = ARGV[0]
  #set name of the first data file for no reason
  $mapDataFile = ARGV[1]
  #initialize first image
  $imgTemplate = ImageList.new($imgName)
  # parse color placement data
  parseMapData()
  # parse 2004 election data
  parse2004()
  # parse 2008 election data
  parse2008()
  # color the map
  colorMap()
  # write out the file to designated name
  $imgTemplate.write(ARGV[2])
end