#!/usr/bin/ruby
#
# 
#

require 'getoptlong'

$dataArray = Array.new()
$countryArray = Array.new()
$athleteArray = Array.new()
$fileOut

opts = GetoptLong.new(
   ['--medals-by-country',  GetoptLong::NO_ARGUMENT],
   ['--medals-by-athlete',  GetoptLong::NO_ARGUMENT],
   ['--alpha-by-country',   GetoptLong::NO_ARGUMENT]
)

opts.quiet = true


#attempt to parse each argument
begin

  #ignores cmd line args and flags not defined
  #call proper sorting method in each
  # flag remains a cmdline arg if not picked up by getoptlong
  opts.each do |opt, arg|
     case opt
	when '--medals-by-country'
	  #puts "medals by country"
          $flag = "medalsByCountry"
	  #medalsByCountry()
	when '--medals-by-athlete'
	  #puts "medals by athlete"
          $flag = "medalsByAthlete"
          #medalsByAthlete()
	when '--alpha-by-country'
	  #puts "alpha by country"
          $flag = "alphaByCountry"
	  #alphaByCountry()
	end
  end

 rescue => e
   #puts "Error: #{e}"
   exit 1

end



def readDataFile()
  begin
    olympicsFile = File.open(ARGV[0], 'r')
    
    while(line = olympicsFile.gets)
     #okay it loops the correct number of times    
     tempArray = line.split("\t")
	
     # this is fine
     tempHash = Hash.new()
     tempHash['sport']    = tempArray[0]
     tempHash['athlete']  = tempArray[1]
     tempHash['country']  = tempArray[2]
     tempHash['medal']    = tempArray[4]
     tempHash['gold']     = 0
     tempHash['silver']   = 0
     tempHash['bronze']   = 0

	#start medal count
      if(tempArray[4].to_s.chomp == "GOLD")
           tempHash['gold'] = tempHash['gold'] + 1
         elsif(tempArray[4].to_s.chomp  == "SILVER")
           tempHash['silver'] = tempHash['silver'] + 1
         elsif(tempArray[4].to_s.chomp  == "BRONZE")
           tempHash['bronze'] = tempHash['bronze'] + 1
         end

     found = 0
     #check for existing entry
     for data in $dataArray      
	 if(data['athlete'].to_s.chomp == tempHash['athlete'].to_s.chomp)
	   #puts "athlete match"
	   #duplicate name check country

	   if(data['country'].to_s.chomp == tempHash['country'].to_s.chomp)
	   #puts "data match"
	
	   #duplicate entry. update medal count
	   
	    if(tempArray[4].to_s.chomp == "GOLD")
             data['gold'] = data['gold'] + 1
	     #puts "gold up"
            elsif(tempArray[4].to_s.chomp  == "SILVER")
             data['silver']= data['silver'] + 1
	     #puts "silver up"
            elsif(tempArray[4].to_s.chomp  == "BRONZE")
             data['bronze'] = data['bronze'] + 1
	     #puts "bronze up"
            end
	   found = 1
	  else
	   #same name. but different country. add them
	  
	  end

         else 
	   #different name
	  
         end

     end # end of the while loop

     if($dataArray.empty?)
	$dataArray.push(tempHash)
     elsif(found == 0)
	$dataArray.push(tempHash)
     end

    end

    olympicsFile.close
  rescue => e
    puts "Rescue: #{e}"
    olympicsFile.close
  end 
end

def convertCountry()
  #looping over the athletes
  for data in $dataArray
    # a new hash per athlete
    countryHash = Hash.new()	
    #given a snap shot of the athlete's achievments
    countryHash['country'] = data['country']
    countryHash['gold']    = data['gold']
    countryHash['silver']  = data['silver']
    countryHash['bronze']  = data['bronze']
    #puts countryHash.inspect()
    #countryHash['sport'] = Array.new()
    countryHash['event'] = Array.new()
	#event holds the sport and medal won
    sportHash = Hash.new()
    sportHash['sport'] = data['sport']
    sportHash['medal'] = data['medal']

    countryHash['event'].push(sportHash)
    #countryHash is a properly formatted version of data    
    if($countryArray.empty?)
      $countryArray.push(countryHash)
    else
      #loop over countries in array. checking for duplicate country
      countryExists = 0
      #for each new athlete check if the country they represent exists
      for country in $countryArray
	#check if it's a duplicate country

        if(countryHash['country'].to_s.chomp == country['country'].to_s.chomp)
	  countryExists = 1
	  # record of country exists. check for record of event
	  eventFound = 0
	  medalMatch = 0

          for event in country['event']
	    #check the event['sport']

	    if(data['sport'].to_s.chomp == event['sport'].to_s.chomp)
              #sport match. compare medal values
	      eventFound = 1

	      if(data['medal'].to_s.chomp == event['medal'].to_s.chomp)
		medalMatch = 1
		puts "medal matched"
	      end #end of medal type check

            end #end of sport name check if statement

          end #end of for loop over the events
        
          if(eventFound == 0)
            #unique sport add a hash to the array
		if(data['medal'].to_s.chomp == "GOLD")
		 country['gold'] = country['gold'] + 1
		elsif(data['medal'].to_s.chomp == "SILVER")
		 country['silver'] = country['silver'] + 1
	        elsif(data['medal'].to_s.chomp == "BRONZE")
		 country['bronze'] = country['bronze'] + 1
		end
		
	    efHash = Hash.new()
            efHash['sport'] = event['sport']
	    efHash['medal'] = event['medal']
	    country['event'].push(efHash)
          end  #end of eventFound if statement       

	  if(eventFound == 1 && medalMatch == 0)
            #matched sport. unique event
	    #update gold/silver/bronze value
		if(data['medal'].to_s.chomp == "GOLD")
                 country['gold'] = country['gold'] + 1
                elsif(data['medal'].to_s.chomp == "SILVER")
                 country['silver'] = country['silver'] + 1
                elsif(data['medal'].to_s.chomp == "BRONZE")
                 country['bronze'] = country['bronze'] + 1
                end


            efHash = Hash.new()
            efHash['sport'] = data['sport']
            efHash['medal'] = data['medal']
            country['event'].push(efHash)
          end  #end of eventFound/medalMatch if statement

	else
	  #country didn't match. search not over
        end #end of country name compare if
      end #end of loop over the countryArray

      
      if(countryExists == 0)
        #country never matched add it
	# that works
	$countryArray.push(countryHash)
      end # end of country exists if

    end #end of country array empty if statement

    #country Hash Filled out
  end #end of loop over $dataArray
end #end of convertCountry


#parse the flag	

  readDataFile()
  convertCountry() #eventually move this line to the proper switch block

#put an if statement here with the sorting code
if($flag == "medalsByCountry")
  #p uts "medal by country sort"
  $title = "Sorted by Medal per Country"
  #uses $countryArray
  # descending order by overall-> gold -> silver -> bronze -> country
  # only country sorted ascending rest sorted descending
  # 
  #  okay this works
  $countryArray.sort! do |a,b|
    a['overall'] = a['gold'].to_i + a['silver'].to_i + a['bronze'].to_i
    b['overall'] = b['gold'].to_i + b['silver'].to_i + b['bronze'].to_i

    result = b['overall'].to_i <=> a['overall'].to_i

    result != 0 ? result : result = b['gold'].to_i  <=> a['gold'].to_i

    result != 0 ? result : result = b['silver'].to_i <=> a['silver'].to_i

    result != 0 ? result : result = b['bronze'].to_i <=> a['bronze'].to_i

    result != 0 ? result : result = a['country'] <=> b['country']
  end #sort end

elsif($flag == "medalsByAthlete")
   $title = "Sorted by Medals per Athlete"
   #puts "medal by athlete sort"
  # sorted by overall -> gold -> silver -> bronze -> athlete
  # athlete ascending rest descending
  #uses $dataArray

  $dataArray.sort! do |a, b|
     a['overall'] = a['gold'].to_i + a['silver'].to_i + a['bronze'].to_i
     b['overall'] = b['gold'].to_i + b['silver'].to_i + b['bronze'].to_i

     result = b['overall'].to_i <=> a['overall'].to_i
     #puts result

     result != 0 ? result : result = b['gold'] <=> a['gold']

     result != 0 ? result : result = b['silver'] <=> a['silver']

     result != 0 ? result : result = b['bronze'] <=> a['bronze']

     result != 0 ? result : result = a['athlete'] <=> b['athlete']

  end

elsif($flag == "alphaByCountry")
   #puts "alpha by country sort"
  $title = "Sorted by Country"
  #ascedning by country
  #uses #countryArray
  $countryArray.sort! do |a,b|
    a['overall'] = a['gold'].to_i + a['silver'].to_i + a['bronze'].to_i
    b['overall'] = b['gold'].to_i + b['silver'].to_i + b['bronze'].to_i
    result = a['country'] <=> b['country']
  end
end

# $title is set
begin

  # creates new file html out
  $htmlOut = File.open(ARGV[1], 'w+')
  # going to half to back slach all the parenthesis
  #file.puts
  $htmlOut.puts "<!DOCTYPE html> 
<html lang=\"en\"> 
  <head> 
    <meta charset=\"utf-8\" /> 
    <title>#{$title}</title> 
    <style type=\"text/css\"> 
      body { 
        text-align: center; 
        font-family: Helvetica, Arial, sans-serif; 
      }
      table { 
        background-color: #fff; 
        border-collapse: collapse; 
        margin: 20px auto; 
        text-align: left; 
      } 
      table th { 
        border-bottom: 2px solid #666; 
        font-weight: normal; 
        padding: 10px 20px; 
      } 
      table td { 
        border-bottom: 1px solid #ccc; 
        padding: 6px 20px; 
      } 
      table td.number {
        text-align: right;
      }
      table tbody tr:hover td { 
        background-color: #eef; 
      } 
    </style> 
  </head>"


  if($flag == "medalsByAthlete")
    #need to add a column for athlete
    $htmlOut.puts "<body> 
    <h1>#{$title}</h1>
    <table> 
      <thead> 
        <tr>
	  <th>Athlete</th>
          <th>Country</th>
          <th>Gold</th>
          <th>Silver</th>
          <th>Bronze</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>"
  #puts $dataArray.inspect
   for item in $dataArray
     #puts "athlete: #{item['athlete']}"
     #puts "overall: #{item['overall']}"
    $htmlOut.puts "
            <tr>
	     <td>#{item['athlete']}</td>
	     <td>#{item['country']}</td>
	     <td class=\"number\">#{item['gold'].to_i}</td>
             <td class=\"number\">#{item['silver'].to_i}</td>
	     <td class=\"number\">#{item['bronze'].to_i}</td>
             <td class=\"number\">#{item['overall'].to_i}</td>
	   </tr>"
   end
   

  else
    #countries by medal. alpha by country. almost a copy paste of the example
  $htmlOut.puts "   
 <body> 
    <h1>#{$title}</h1>
    <table> 
      <thead> 
        <tr>
          <th>Country</th>
          <th>Gold</th>
          <th>Silver</th>
          <th>Bronze</th>
          <th>Total</th>
        </tr>
      </thead>
      <tbody>"

   for item in $countryArray
     $htmlOut.puts "
        <tr>
          <td>#{item['country']}</td>
          <td class=\"number\">#{item['gold'].to_i}</td>
          <td class=\"number\">#{item['silver'].to_i}</td>
          <td class=\"number\">#{item['bronze'].to_i}</td>
          <td class=\"number\">#{item['overall'].to_i}</td>
        </tr>"

   end

  end
  $htmlOut.puts "
</tbody>
    </table>
  </body>
</html>"


  $htmlOut.close
rescue => e
 
 puts "Rescue: #{e}"
 $htmlOut.close
end
#for item in $dataArray
#  puts item.inspect
#end