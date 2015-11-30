#!/usr/local/bin/python

# Christopher Mai
# CMSC 433
# chrmai1

import wx
import wx.html
import sys
import subprocess
import os.path

#for html window loop over the output and create a long string

class Frame(wx.Frame):
    
    def __init__(self):
        wx.Frame.__init__(self, None, -1, 'wx-diff', size=(900,500))

        icon = wx.Icon('files.ico', wx.BITMAP_TYPE_ANY, 16, 16)
        wx.Frame.SetIcon(self, icon)

        panel = wx.Panel(self)
        
        #flag set by normal/unified menu items
        self.format = "normal"
        #flag set by colorize check box
        self.colored = "false"

        self.diffOutput = "" #will hold the results of diff
        self.htmlOutput = "" #will hold the string that has html formatting

        #create frame objects and bind to methods
        #file menu items
        file = wx.Menu()
        txtFile = file.Append(-1, "Save as Txt...", "Save a txt file")
        self.Bind(wx.EVT_MENU, self.TxtFile, txtFile)
        htmlFile = file.Append(-1, "Save as HTML...", "Save a html file")
        self.Bind(wx.EVT_MENU, self.HTMLFile, htmlFile)
        quit = file.Append(-1, "Exit", "Quit the application")
        self.Bind(wx.EVT_MENU, sys.exit, quit)

        #edit menu items
        edit = wx.Menu()
        normal = edit.AppendRadioItem(-1, "Normal")
        self.Bind(wx.EVT_MENU, self.Normal, normal)
        unified = edit.AppendRadioItem(-1, "Unified")
        self.Bind(wx.EVT_MENU, self.Unified, unified)
        edit.AppendSeparator()
        color = edit.AppendCheckItem(-1, "Colorize")
        self.Bind(wx.EVT_MENU, self.Colorize, color)

        #hellp menu items
        help = wx.Menu()
        aboutItem = help.Append(-1, "About...", "Opens About Box")
        self.Bind(wx.EVT_MENU, self.AboutBox, aboutItem)
        




        menuBar = wx.MenuBar()
        menuBar.Append(file, "File")
        menuBar.Append(edit, "Edit")
        menuBar.Append(help, "Help")

        self.SetMenuBar(menuBar)
        self.CreateStatusBar()

        sizer = wx.GridBagSizer(hgap=1, vgap=1)

        imageFile = "folder.png"
        image = wx.Image(imageFile, wx.BITMAP_TYPE_ANY).ConvertToBitmap()
        self.file1 = wx.TextCtrl(panel,-1) #,size=(125, 25))
        folder1 = wx.BitmapButton(panel, id=-1, bitmap=image) #, size=(image.GetWidth()+15,image.GetHeight()+15))
        folder2 = wx.BitmapButton(panel, id=-1, bitmap=image) #, size=(image.GetWidth()+15,image.GetHeight()+15))
        self.file2 = wx.TextCtrl(panel,-1) #,size=(125, 25)) 
        diffButton = wx.Button(panel, id=-1, label="diff files")
        self.Bind(wx.EVT_BUTTON, self.Diff, diffButton)

        #s#elf.output = wx.TextCtrl(panel, -1, size=(200,200), style=wx.TE_MULTILINE|wx.TE_READONLY)

        self.htmlout = wx.html.HtmlWindow(panel, wx.ID_ANY, style=wx.NO_BORDER)
        

        sizer.Add(self.file1, pos=(0,0), flag=wx.EXPAND)
        sizer.Add(folder1, pos=(0,1), flag=wx.EXPAND)
        self.Bind(wx.EVT_BUTTON, self.SelFile1, folder1)
        sizer.Add(self.file2, pos=(0,2), flag=wx.EXPAND)
        sizer.Add(folder2, pos=(0,3), flag=wx.EXPAND)
        self.Bind(wx.EVT_BUTTON, self.SelFile2, folder2)
        sizer.Add(diffButton, pos=(0,4), flag=wx.EXPAND)
        #sizer.Add(s#elf.output,pos=(1,0), span=(2,5), flag=wx.EXPAND)
        sizer.Add(self.htmlout, pos=(1,0), span=(2,5), flag=wx.EXPAND)

        #self.htmlout.SetPage(html_code)

        sizer.AddGrowableCol(0,2)
        sizer.AddGrowableCol(1,1)
        sizer.AddGrowableCol(2,2)
        sizer.AddGrowableCol(3,1)
        sizer.AddGrowableCol(4,1)
        
        sizer.AddGrowableRow(1)
        sizer.AddGrowableRow(2)

        panel.SetSizer(sizer)
        
        #file1.SetValue('hello there')
        #textCtrl.SetValue(value)
        #textCtrl.GetValue()
        # wx.OPEN = wx.FD_OPEN
        self.fileChooser = wx.FileDialog(None, "Choose a File", style=wx.OPEN | wx.FILE_MUST_EXIST)
        #output is the multiline textCtrl

    def TxtFile(self, event):
        #check if something is in the diff area
        if self.diffOutput != "":
            filters = 'Text Files (*.txt)|*.txt'
            dialog = wx.FileDialog(None, wildcard = filters, style=wx.SAVE | wx.OVERWRITE_PROMPT)
            if dialog.ShowModal() == wx.ID_OK:
                
                print 'Selected: ', dialog.GetPath()
                
            
                fpath = dialog.GetPath()
                
                
                f = open(fpath, 'w')
                f.write(self.diffOutput)
            else:
                print 'Nothing was Selected.'
                
            dialog.Destroy()
        else:
            #nothing in diff area. no diff has been made
            wx.MessageDialog(None, "There is no diff to be saved", "", wx.OK).ShowModal()

    def HTMLFile(self, event):
        print "Creating HTML File"
         #check if something is in the diff area
        if self.diffOutput != "":
            filters = 'HTML Files (*.html)|*.html|All files (*.*)|*.*'
            dialog = wx.FileDialog(None, wildcard = filters, style=wx.SAVE | wx.OVERWRITE_PROMPT)
            if dialog.ShowModal() == wx.ID_OK:
                
                print 'Selected: ', dialog.GetPath()
                
                
                fpath = dialog.GetPath()
                
                   
                
                f = open(fpath, 'w')
                f.write('''<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"
<html>
  <head>
    <title>''' + self.file1.GetValue() + 'vs.' + self.file2.GetValue() + '''</title>
  </head>
  <body>''' + self.htmlOutput + '</body></html>')
            else:
                print "nothing was selected"
                
            dialog.Destroy()
        else:
            #nothing in diff area. no diff has been made
            wx.MessageDialog(None, "There is no diff to be saved", "", wx.OK).ShowModal()

        
    def Normal(self, event):
        print "Normal Format"
        self.format = "normal"
        #self.Diff()

    def Unified(self, event):
        print "unified output"
        self.format = "unified"
        #self.Diff()

    def Colorize(self, event):
        if self.colored == "false":
            self.colored = "true"
        else:
            self.colored = "false"

    def AboutBox(self, event):
        info = wx.AboutDialogInfo()

        info.SetIcon(wx.Icon('files.ico', wx.BITMAP_TYPE_ANY))
        info.SetName('wx-diff')
        info.SetVersion('1.0')
        info.SetDescription('a graphical front end for diff in wx-python')
        info.SetCopyright('(C) 2010 Christopher Mai')
        info.AddDeveloper('''Programming:
Christopher Mai
Icons:
http://www.iconfinder.com/''')

        wx.AboutBox(info)


        #print "color output"
        #costatus number: axs835-01
        #service ticket number:  038680927
    def SelFile1(self, event):
        if self.fileChooser.ShowModal() == wx.ID_OK:
            #print self.fileChooser.GetPath()
            self.file1.SetValue(self.fileChooser.GetPath())
        print "select file 1"

    def SelFile2(self, event):
        if self.fileChooser.ShowModal() == wx.ID_OK:
            #print self.fileChooser.GetPath()
            self.file2.SetValue(self.fileChooser.GetPath())
        print "select file 2"

    def Diff(self, event):
        if self.file1.GetValue() == "" or self.file2.GetValue() == "":
            #rawr
            print "rawr"
        else:
            
            if os.path.isfile(self.file1.GetValue()) and os.path.isfile(self.file2.GetValue()):
                print "do the diff"
                print self.format
                #check for unified
                if self.format == "unified":
                    self.diffOutput = subprocess.Popen(['diff', '-u', self.file1.GetValue(), self.file2.GetValue()], stdout=subprocess.PIPE).communicate()[0]
                    
                    #print self.diffOutput
                   
                else:
                    self.diffOutput = subprocess.Popen(['diff', self.file1.GetValue(), self.file2.GetValue()], stdout=subprocess.PIPE).communicate()[0]
                
                diffsplit = self.diffOutput.splitlines()

                    #print diffsplit #okay this splits correctly
                self.htmlOutput = ""
                print "begin html format"
                for line in diffsplit:
                    ###print line #this works
                    #each line is html formatted.
                    #if self.colored == "false":
                        #normal line with font line
                    if line[0] == '<':
                        #print "check rawr"
                        line = line.replace("<", "&lt;")
                        #print line

                    if line[0] == '>':
                        line = line.replace(">", "&gt;")
                        
                    if self.colored == "false":
                        self.htmlOutput = self.htmlOutput + '<div><small> ' + line + "</small></div>"
                    else:
                        #need to parse for coloring. these conditions work
                        print "colored"
                        if (line.startswith("-") and self.format == "unified") or (line.startswith("&lt;") and self.format == "normal"):
                            #print "yar"
                            self.htmlOutput = self.htmlOutput + '<div><font color="red"><small> ' + line + "</small></font></div>"

                        elif line.startswith("+") or line.startswith("&gt;"):
                            #print "rawrgle"
                            self.htmlOutput = self.htmlOutput + '<div><font color="blue"><small> ' + line + "</small></font></div>"
                        else:
                            #print "smargle"
                            self.htmlOutput = self.htmlOutput + '<div><small> ' + line + "</small></div>"

                if self.diffOutput == "":
                    wx.MessageDialog(None, "There was no difference Between the files...", "", wx.OK).ShowModal()           
                    
                self.htmlout.SetPage(self.htmlOutput)
                print self.htmlOutput
            else:
                            #print "goodbye"
                if not os.path.isfile(self.file1.GetValue()):
                    wx.MessageDialog(None, self.file1.GetValue() + " does not exist", "Error", wx.OK).ShowModal()    
                    
                if not os.path.isfile(self.file2.GetValue()):
                    wx.MessageDialog(None, self.file2.GetValue() + " does not exist", "Error", wx.OK).ShowModal()
                    
                    
        #set up the remaining fields and attach events to them

if __name__ == '__main__':
    app = wx.PySimpleApp()
    frame = Frame()
    frame.Show()
    app.MainLoop()


