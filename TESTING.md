#Testings

Who:

	Camille Owens: caow7208
	Soo Park: soorin1993
	Jake Mitchell: jakeMitchell1419
	Alex Sheehan: ninjaalex888
	Qi Pei: qipe3398

Title: 
	
	Mafia Game

Vision: 
	
	Give users enjoyable and accurate experience of the Mafia Game

Automated Tests: 

	Android Studio, the program in which we are building our app has a built in testing library.  
	There is a specific directory in the path of the full application in which java classes can be placed.  
	These classes will be run using Junit testing.  For more information check out
	http://developer.android.com/tools/testing/index.html.
	
	In order to run the tests we have created, pull up the application in android studio.  Then go to the top bar 
	and where it says app in a drop down menu, change that to "all tests under mafiagame".  Or press the projects tab 
	in the top left of android studio, navigate to app/src/test/java/com/mafiagame/csci3308/mafiagame, 
	and right click, press run all tests in current directory.
	
	Screenshot of all tests passing
![ScreenShot1](https://github.com/soorin1993/mafiagame/blob/master/AutomatedTestsScreens/mafiaAutoTest1Pass.png)
	Screenshot of testing file 1
![ScreenShot2](https://github.com/soorin1993/mafiagame/blob/master/AutomatedTestsScreens/mafiaAutoTestCode1.png)
	Screenshot of testing file 2
![ScreenShot2](https://github.com/soorin1993/mafiagame/blob/master/AutomatedTestsScreens/mafiaAutoTestCode1.png)




User Acceptance Tests: 

	Use Case ID: UC-01
	Use Case Name: Test Settings
	Description: User can open app, navigate to settings page, change a setting
	------------------------------------------------------------------------------------------------------------------------------
	Users			|	Gamers  
	------------------------------------------------------------------------------------------------------------------------------
	Pre-conditions	|	App is loaded on phone, settings page acitivity is up and running along with associated .java class
	------------------------------------------------------------------------------------------------------------------------------
	Post-conditions	|	Gamer finds settings page and successfully changes setting
	------------------------------------------------------------------------------------------------------------------------------
	Frequency of Use|	Whenever a gamer would like to change a game or app setting
	------------------------------------------------------------------------------------------------------------------------------
	Flow of Events	|	Actor Action	|	System Response	|	Comments
	------------------------------------------------------------------------------------------------------------------------------
	                |                   |                   |
	                |   1. open app     |   app opens       |
	                |                   |                   |	                
	                |   2. navigate     |   setttings       |	
	                |   to settings     |  acvitvity opens  |	                
	                |   button          |                   |	                
	                |                   |                   |	                
	                |   3. change       |   music does NOT  |                      
	                |   mustic setting  |   begin playing   |   TODO	                
	                |   to on           |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	------------------------------------------------------------------------------------------------------------------------------
	Test Pass?		| 	FAIL
	------------------------------------------------------------------------------------------------------------------------------
	Notes and 		|	fix setting java class to correctly play music upon switch change
	Issues			|


	Use Case ID: UC-02
	Use Case Name: Start game with 10 players
	Description: User can open app, go to new game, and select 10 players as game option
	------------------------------------------------------------------------------------------------------------------------------
	Users			|	Gamers  
	------------------------------------------------------------------------------------------------------------------------------
	Pre-conditions	|	App is loaded on phone, settings page acitivity is up and running along with associated .java class
	------------------------------------------------------------------------------------------------------------------------------
	Post-conditions	|	Gamer finds settings page and successfully changes setting
	------------------------------------------------------------------------------------------------------------------------------
	Frequency of Use|	Whenever a gamer would like to change a game or app setting
	------------------------------------------------------------------------------------------------------------------------------
	Flow of Events	|	Actor Action	|	System Response	|	Comments
	------------------------------------------------------------------------------------------------------------------------------
	                |                   |                   |
	                |   1. open app     |   app opens       |
	                |                   |                   |	                
	                |   2. navigate     |   new game        |	slight delay on opening
	                |   to new game     |  begins	        |	 may just be emulator itself               
	                |   button          |                   |	                
	                |                   |                   |	                
	                |   3. change       | number of players |                      
	               	|   number of       |    is correct     |   	                
	                |   players to      |                   |	                
	                |   10 players      | 			|	                
	                |                   |		        |	 woohoo               
	                |                   |        		|	                
	                |                   |                   |	                
	------------------------------------------------------------------------------------------------------------------------------
	Test Pass?		| 	PASS
	------------------------------------------------------------------------------------------------------------------------------
	Notes and 		|	look into speeding up emulator
	Issues			|

	Use Case ID: UC-03
	Use Case Name: Test Settings
	Description: User can open app, navigate to settings page, change a setting
	------------------------------------------------------------------------------------------------------------------------------
	Users			|	Gamers  
	------------------------------------------------------------------------------------------------------------------------------
	Pre-conditions	|	App is loaded on phone, settings page acitivity is up and running along with associated .java class
	------------------------------------------------------------------------------------------------------------------------------
	Post-conditions	|	Gamer finds settings page and successfully changes setting
	------------------------------------------------------------------------------------------------------------------------------
	Frequency of Use|	Whenever a gamer would like to change a game or app setting
	------------------------------------------------------------------------------------------------------------------------------
	Flow of Events	|	Actor Action	|	System Response	|	Comments
	------------------------------------------------------------------------------------------------------------------------------
	                |                   |                   |
	                |   1. open app     |   app opens       |
	                |                   |                   |	                
	                |   2. navigate     |   setttings       |	
	                |   to settings     |  acvitvity opens  |	                
	                |   button          |                   |	                
	                |                   |                   |	                
	                |   3. change       |   music does NOT  |                      
	                |   mustic setting  |   begin playing   |   TODO	                
	                |   to on           |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	                |                   |                   |	                
	------------------------------------------------------------------------------------------------------------------------------
	Test Pass?		| 	FAIL
	------------------------------------------------------------------------------------------------------------------------------
	Notes and 		|	fix setting java class to correctly play music upon switch change
	Issues			|
