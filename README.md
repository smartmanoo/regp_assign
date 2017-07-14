# regp_assign
Automatically assigns regp tickets
===================
Thursday, 6th July 2017

Authors : Manas Gupta, Shreesh Keskar


Min Version JSE 1.6


The source code of this program is provided with the application in the src folder. Run it with any editor that supports .java files. Otherwise, the jar file can be directly executed by double clicking.
------------------------
INSTRUCTIONS:
------------------------------------


SETTING UP THE SERVER:
------------------------------------
1)The program assumes that an sql server exists on the local host with a database called "tata".

2)To create the server, go to XAMPP control panel (installed on the machine) and start apache,MySql and Tomcat.

3)Go to any browser and type http://localhost/phpmyadmin/ in the address bar. You should be able to see the server created.

4)Check if any database called "tata" exists (Control pane on the left side of the window shows the current databases on the server).

5)If it doesn't, create it using the new button. You are now ready to execute the program.



EXECUTING THE PROGRAM:
------------------------------------
1)Copy all the active users from users_full to users.csv

2)Ensure the following files are in the REGP_Assignment folder: Call_Format.xlsm, Report.csv,Users.csv

3)Ensure Assign.csv, if exists, is closed.

4)Run REGP_Assign.

5)The assignment will be outputted in an assign.csv file. Run the format macro from Call_Format to proceed to mailing.


DEBUGGING:
------------------------------------

1)Error with server:
This mostly happens due to 2 reasons
I:Server not set up
Check if MySql and Apache are both started in the XAMPP control panel. If Apache isn't and it doesnt start, skip to the section     dealing with "Apache not working". If MySql does not start, you probably have an installation problem with XAMPP. Try installing it  again.
II:Database not set up
Go to a browser and type http://localhost/phpmyadmin/ in the address bar. Check if the tata database is created from the left window pane. If it isn't, create it using the new button in the same pane.

2)Apache not working:
This might happen with computers running Skype becasue Skype uses the default 80 port. To resolve this go through the followng steps:

1. Go in xampp/apache/conf/httpd.conf and open it. 
In the httpd.conf file at line 176 Replace

ServerName localhost:80 
with
ServerName localhost:81 
It will work.

2. Even if the above procedure doesn't work. Then in the same file (httpd.conf) at line 45 replace

   #Listen 0.0.0.0:80
   #Listen [::]:80
   Listen 80 
with

  #Listen 0.0.0.0:81
  #Listen [::]:81
  Listen 81
  
Now, the server can be accessed by typing http://localhost:81/phpmyadmin/ in the address bar. Keep in mind that after doing this step, http://localhost/phpmyadmin/ WILL NOT WORK.

3)Cannot find file:
Ensure that all the csv and excel files are in the Regp_Assignment/Regp_Assignment folder and they are all closed before the program is run, especially assign.csv.
If this still doesn't work, try deleting assign.csv and then running the program.

4)Too many active users in output file:
The program takes the active users from the file users.csv. Ensure that file is updated with the active users.

5)Keep getting deleting as the output:
This means that assign.csv is open. Close the file and then click on ok.
