#!/usr/bin/ruby
# typically returns...
#
# Author: Christopher Mai
#
# C:\Documents and Settings\username on MS Windows < Vista
# C:\Users\username on MS Windows >= Vista
#/home/username on Linux
#/Users/username on Mac OS X
# puts File.expand_path("~")
# Gracefully handle the case when "todo.txt" doesn't yet exist.

#if the file doesn't exist make one and put the default value of the static

require 'date'

#some global vars
$counter
$numElements
$dateObj
begin
  #checks for file existence
  $file = File.open("#{ENV['HOME']}/todo.txt", 'r')
  $exist = 1
rescue
  #file doesn't exist print message to suer
  puts "File not found. Creating new todo.txt in home directory"
  $exist = 0
  #set vars required for program execution
  $counter = 0
  $numElements = 0
end

#my task list
$todoList = Array.new()

def readArray
  #reads in hashs that were written out
  begin
   $file = File.open("#{ENV['HOME']}/todo.txt", 'r')
    #p#uts "this is done isn't it?"
    $counter = $file.gets
    $counter = $counter.to_i
    $numElements = $file.gets
    $numElements = $numElements.to_i
    
    #read in the array
    $numElements.times do
      #above call works 
      idTag = $file.gets
      #puts "idTag: #{idTag}"
      task = $file.gets
      #puts "task: #{task}"
      date = $file.gets
      #puts "date: #{date}"
      
      tempHash = Hash.new()
      tempHash['id'] = idTag
      tempHash['task'] = task
      tempHash['date'] = date

      $todoList.push tempHash
    end
      $file.close
    rescue
      puts "readArray: rescue"
      $file.close
    end
end

if $exist == 1
  readArray
end 

#set initial date to today
$date = "#{Time.now.month}-#{Time.now.day}-#{Time.now.year}" 
$today = Date.new(Time.now.year , Time.now.month, Time.now.day)

#prints info relevant to help method
def help_screen
 puts "-- Help --
	Add: add \"task in quotes\" [optional date string]
	 -- Adds a task to the todo list
	 -- no date. defaults to today
        Del: del <number> 
	 -- number > 0
	 -- deletes task with that number from the todo list
	List: list [optional date string]
	 -- displays tasks in the todo list.
         -- by default, if no date argument is specified, listing shows tasks
	    that are due within the next week.
	 -- date shown. all tasks up to specified ate shown. 
	 -- date in past throws an error"
end

# adds a task to the todo list
def add(todo, date = $date)
  tempTask = Hash.new()
  #check for identical hash before this happens
  found = 0

  for item in $todoList
     if (todo.chomp == (item['task'].chomp))
       #puts "equal!!!"
       found = 1
     end
  end
  
  if found == 1
   puts "Duplicate task found"
  elsif found == 0

    #where to check for date
    
    tempTask['id'] = $counter
    tempTask['task'] = todo
    tempTask['date'] = date
    tempTask['dateObj'] = $dateObj
    
    $todoList.push(tempTask)

    puts "ADDED ##{tempTask['id']} : #{tempTask['task']}"
    $counter = $counter + 1
    $numElements = $numElements + 1
  end 
end

# removes task from list
def del(taskNum)
  taskNum = taskNum.to_i
  #basically positive. check if num within array
  if(taskNum > -1)
    #it's positive check if a task has that number
    loopCount = 0
    found = 0
    for item in $todoList
	if(item['id'].to_i == taskNum.to_i)
	  $todoList.delete_at(loopCount) 
	  $numElements = $numElements - 1
          found = 1
	end
        loopCount = loopCount + 1
    end

    if(found == 1)
      puts "Task found and deleted"
    else 
      puts "Task Not Found"
    end
  else
    puts "number invalid"
  end  

end

# Always display tasks which are past due
# list dates a week after the current date
def list(date = $date)
  #list all tasks before today
  #todo list is sorted
  puts "Past Due Items:"
  loopCond = 1
  loopCount = 0
  while(loopCond == 1)
       	# set $dateObj to date of task
	if(loopCount <= $numElements - 1)

    	  tempTask = $todoList[loopCount]
	  dateCheck(tempTask['date'])
          #ensure dateObj has a date object
	  tempTask['dateObj'] = $dateObj	
	  #if(loopCount <= $numElements - 1)
          if(tempTask['dateObj'] < $today)
		#basically the past
	  	puts "#{tempTask['id'].chomp}: #{tempTask['task']}"
	  else
		loopCond = 0
	  end
        else
          loopCond = 0
        end
	loopCount = loopCount + 1
  end
  #corrective math
  loopCount = loopCount - 1
  #list all tasks 1 week into the future
  puts "Upcoming Items:"
  
  nextWeek = $today + 8
  #puts nextWeek  
  while(loopCond == 0)
    #loopCount shouldn't have to change
    if(loopCount < $numElements)
     #still elements in the list
     tempTask = $todoList[loopCount]
     dateCheck(tempTask['date'])
     #ensure dateObj has a date object
     tempTask['dateObj'] = $dateObj
     if(tempTask['dateObj'] < nextWeek)
       #the object is a week into the future
       puts "#{tempTask['id'].chomp}: #{tempTask['task']}"
     else
       loopCond = 1
     end
    else 
      loopCond = 1
    end
    loopCount = loopCount + 1
  end  
end

#print all tasks up to the specified date
def listDateGiven(date)
# check if the date is at least today
  dateHold = $dateObj
  if($dateObj < $today)
    puts "Invalid date. Must be today or later."
  else 
    puts "Past Due Items:"
    loopCond = 1
    loopCount = 0
    while(loopCond == 1)
        # set $dateObj to date of task
        if(loopCount <= $numElements - 1)

          tempTask = $todoList[loopCount]
          dateCheck(tempTask['date'])
          #ensure dateObj has a date object
          tempTask['dateObj'] = $dateObj
          #if(loopCount <= $numElements - 1)
          if(tempTask['dateObj'] < $today)
                #basically the past
                puts "#{tempTask['id'].chomp}: #{tempTask['task']}"
          else
                loopCond = 0
          end
        else
          loopCond = 0
        end
        loopCount = loopCount + 1
    end
    #corrective math
    loopCount = loopCount - 1
    #list all tasks 1 week into the future
    puts "Upcoming Items:"
    #dateHold has the entered date
	while(loopCond == 0)
    #loopCount shouldn't have to change
    if(loopCount < $numElements)
     #still elements in the list
     tempTask = $todoList[loopCount]
     dateCheck(tempTask['date'])
     #ensure dateObj has a date object
     tempTask['dateObj'] = $dateObj
     if(tempTask['dateObj'] < dateHold)
       #the object is a week into the future
       puts "#{tempTask['id'].chomp}: #{tempTask['task']}"
     else
       loopCond = 1
     end
    else
      loopCond = 1
    end
    loopCount = loopCount + 1
  end

  end
end

#reads in date from user. and changes it as necessary
def dateCheck(date)
  if date =~ /^(\d{1,2})-(\d{1,2})-(\d{4})$/
      $dateObj = Date.new("#$3".to_i, "#$1".to_i, "#$2".to_i)
      dateSet
      #puts $dateObj
      return "yes"
  elsif date =~ /\+(\d*)d$/ 
     #puts "date check: #$1"
      $dateObj = (Date.new(Time.now.year, Time.now.month, Time.now.day) + 
			"#$1".to_i)
      #puts $dateObj
      dateSet 
     # capture buffer reset each time a match is made
     return "yes"
  elsif date =~ /\+(\d*)w$/
     $dateObj = (Date.new(Time.now.year, Time.now.month, Time.now.day) +
                        ("#$1".to_i * 7))
     dateSet
     #puts $dateObj
     return "yes"
  elsif date =~ /\+(\d*)m$/
     $dateObj = (Date.new(Time.now.year, Time.now.month, Time.now.day) >>
                        "#$1".to_i)
     dateSet
     #puts $dateObj
     return "yes"
  elsif date =~ /\+(\d*y)$/
     $dateObj = (Date.new(Time.now.year, Time.now.month, Time.now.day) >>
                        ("#$1".to_i * 12))
     dateSet
     #puts $dateObj
     return "yes"
  else 
     return "no"
  end
end

#converts from date class format to required format
def dateSet()
  # set $date in correct format of $dateObj
  $dateObj.to_s =~ /(\d*)-(\d*)-(\d*)/
  $date = "#$2-#$3-#$1"
  #puts "check: #{$date}"
end

## argv filtering ##
case ARGV[0]
when "help"
 if (ARGV.length != 1)
   puts "pointless parameters: help"
 else 
   help_screen
 end
when "add"
 if (ARGV.length < 2)
   puts "too few parameters: add \"task in quotes\" [optional date string] "
 elsif (ARGV.length == 2)
   add(ARGV[1])
 elsif (ARGV.length == 3)
   # dateCheck will change $date variable to correct value
   if (dateCheck(ARGV[2]) == "yes")
     #puts "match!"
     add(ARGV[1])
   else
     puts "Improper date format: xx-xx-xxxx, +nd, +nw, +nm, +ny"
   end
 else 
   puts "too many paramters: add \"task in quotes\" [optional date string]"
 end
when "del"
 if (ARGV.length != 2)
  puts "incorrect parameters: del <number>"
 else
   del(ARGV[1])
 end
when "list"
  if (ARGV.length == 2)
   if (dateCheck(ARGV[1]) == "yes")
     listDateGiven(ARGV[1])
   else
     puts "Improper date format: xx-xx-xxxx, +nd, +nw, +nm, +ny"
   end
  elsif (ARGV.length == 1)
   #no date given
   list()
  else
   puts "Incorrect parameters: list [optional date string]"
  end
 #./todo.rb list [optional date string]

 #list
end


#sort array
$todoList.sort! do |a,b|
  
  #compare dates
  dateCheck(a['date'])
  a['dateObj'] = $dateObj

  dateCheck(b['date'])
  b['dateObj'] = $dateObj

  #puts "date1: #{date1}"
  #puts "date2: #{date2}"

  result = a['dateObj'] <=> b['dateObj']
  result != 0 ? result : a['id'].to_i <=> b['id'].to_i
end

# write it out to file
$file = File.open("#{ENV['HOME']}/todo.txt", 'w')

$file.puts $counter
$file.puts $numElements
for item in $todoList
  $file.puts item['id']
  $file.puts item['task']
  $file.puts item['date']
end

$file.close