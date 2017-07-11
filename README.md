# regp_assign
Automatically assigns regp tickets

Thursday, 6th July 2017

Authors : Manas Gupta, Shreesh Keskar

------------------------
The source code of this program is provided with the application in the main folder.
------------------------
INSTRUCTIONS:



a)SETTING UP THE SERVER:
------------------------------
1)The program assumes that an sql server exists on the local host with a database called "tata".

2)To create the server, go to XAMPP control panel (installed on the machine) and start apache,MySql and Tomcat.

3)Go to any browser and type http://localhost/phpmyadmin/ in the address bar. You should be able to see the server created.

4)Check if any database called "tata" exists (Control pane on the left side of the window shows the current databases on the server).

5)If it doesn't, create it using the new button. You are now ready to execute the program.



b)EXECUTING THE PROGRAM:
-------------------------------

1)Copy all the active users from users_full to users.csv

2)Ensure the following files are in the REGP_Assignment folder: Call_Format.xlsm, Report.csv,Users.csv

3)Ensure Assign.csv, if exists, is closed.

4)Run REGP_Assign.

5)The assignment will be outputted in an assign.csv file. Run the format macro from Call_Format to proceed to mailing.


DEBUGGING:
-----------------------------------

1)Error with server:
Check if server is up by going to a browser and typing http://localhost/phpmyadmin/ in the address bar. If it isn't restart XAMPP control panel and 
ensure apache,MYSql and tomcat are all started.

2)Cannot find file:
Ensure that all the csv and excel files are in the Regp_Assignment/Regp_Assignment folder and they are all closed before the program is run, especially assign.csv.
If this still doesn't work, try deleting assign.csv and then running the program.

3)Too many active users in output file:
The program takes the active users from the file users.csv. Ensure that file is updated with the active users.

------------------------------------
