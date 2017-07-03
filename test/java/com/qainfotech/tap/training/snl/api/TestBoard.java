package com.qainfotech.tap.training.snl.api;


import static org.assertj.core.api.Assertions.assertThat;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.testng.annotations.Test;
import org.json.JSONObject;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class TestBoard {
	
	 
	 
	 @BeforeTest
	  public void newboard() throws FileNotFoundException, UnsupportedEncodingException, IOException{
		 Board testboard = new Board();
		
	  }
	 
	
	 @Test(expectedExceptions = PlayerExistsException.class)
	   public void test_registerPlayer_should_throw_PlayerExists_Exception() throws MaxPlayersReachedExeption, FileNotFoundException, UnsupportedEncodingException,   PlayerExistsException, GameInProgressException, IOException
	  { Board testboard = new Board();
		 
			testboard.registerPlayer("Ashika");
			testboard.registerPlayer("xyz");
			testboard.registerPlayer("Ashika");
			
		
	  }
	
	 
	 @Test(expectedExceptions = MaxPlayersReachedExeption.class)
	   public void test_registerPlayer_should_throw_MaxPlayerReached_Exception() throws MaxPlayersReachedExeption, FileNotFoundException, UnsupportedEncodingException,   PlayerExistsException, GameInProgressException, IOException
	  { Board testboard = new Board();
		 
			testboard.registerPlayer("Ashika");
			testboard.registerPlayer("Megha");
			testboard.registerPlayer("Gaurav");
			testboard.registerPlayer("Sam");
			testboard.registerPlayer("Sam1");
	  }
	
	
	 @Test(expectedExceptions = GameInProgressException.class)
	   public void test_registerPlayer_should_throw_GameInProgress_Exception() throws  FileNotFoundException, UnsupportedEncodingException,    GameInProgressException, IOException, PlayerExistsException, MaxPlayersReachedExeption
	  { Board testboard = new Board();
		
		 
	   testboard.registerPlayer("Ashika");
	   testboard.registerPlayer("Megha");
		
	   for(Object playerObject:testboard.data.getJSONArray("players")){
	  
	  
		
	            JSONObject player = (JSONObject)playerObject;
	            player.put("position", 1);
	            testboard.registerPlayer("sam");
	            	
		 }
	            testboard.registerPlayer("sam");
	  }
	 
	
	 @Test(expectedExceptions = NoUserWithSuchUUIDException.class)
	   public void test_deletePlayer_should_throw_No_User_With_Such_UUID_Exception() throws NoUserWithSuchUUIDException, IOException
	  { Board testboard = new Board();
		 
			
			testboard.deletePlayer(testboard.getUUID());
			
	  }
	
	
	 @Test(expectedExceptions = InvalidTurnException.class)
	   public void test_rollDice_should_throw_Invalid_Turn_Exception() throws InvalidTurnException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption 
	   { Board testboard = new Board();
	   
		 
	  testboard.registerPlayer("Ashika");
	  testboard.registerPlayer("Megha");
	 
	  UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(1)).get("uuid");
       testboard.rollDice(uuid1);
    
	  }
	 
	 
	/* @Test
	   public void test_rollDice_snake_steps_should_be_lower_than_target_position() throws FileNotFoundException, UnsupportedEncodingException, IOException, InvalidTurnException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption
	  { Board testboard = new Board();
	  Board aSpy = Mockito.spy(testboard);
	  
	  Integer dice;
      
	  testboard.registerPlayer("Ashika");
	  testboard.registerPlayer("ka");
       UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(0)).get("uuid");
     
       Integer turn = testboard.data.getInt("turn");
       JSONObject player = testboard.data.getJSONArray("players").getJSONObject(turn);
       Integer currentPosition = player.getInt("position");
       
       //dice=  Mockito.when(testboard.rollDice(uuid1)).thenReturn(setInt("dice"));
     //  Mockito.when(aSpy.rollDice(uuid1)).thenReturn(4);
      dice= testboard.rollDice(uuid1).getInt("dice");
    
       System.out.println(dice);
       
       Integer TargetPosition = player.getInt("position");
       
      Integer expected=currentPosition+dice;
      System.out.println(expected);
      System.out.println(TargetPosition);
     
      Assert.assertTrue(expected>TargetPosition);
       
	  }*/
	
	 
	 @Test
	   public void test_rollDice_ladders_steps_should_be_higher_than_target_position() throws FileNotFoundException, UnsupportedEncodingException, IOException, InvalidTurnException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption
	  { Board testboard = new Board();
	  
	  Integer dice;
	
	  testboard.registerPlayer("Ashika");
	  testboard.registerPlayer("Aska");
   
	  UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(0)).get("uuid");
   
     Integer turn = testboard.data.getInt("turn");
     //System.out.println(turn);
     JSONObject player = testboard.data.getJSONArray("players").getJSONObject(turn); 
     //System.out.println(turn);
     Integer currentPosition = player.getInt("position");
     
     
     
     
    dice= testboard.rollDice(uuid1).getInt("dice");
    
     //System.out.println(dice);
     
     Integer TargetPosition = player.getInt("position");
     
    Integer expected=currentPosition+dice;
   // System.out.println(expected);
    //System.out.println(TargetPosition);
    Assert.assertTrue(expected<TargetPosition);
     
	  }
	 
	 
	 @Test
	   public void test_Dice_value_should_not_be_more_than_six() throws FileNotFoundException, UnsupportedEncodingException, IOException, InvalidTurnException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption
	  { Board testboard = new Board();
	  
	  Integer dice;
	
	  testboard.registerPlayer("Ashika");
	  testboard.registerPlayer("Aska");
 
	  UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(0)).get("uuid");

  dice= testboard.rollDice(uuid1).getInt("dice");
  //System.out.println(dice);
  Assert.assertTrue(0<dice);
  Assert.assertTrue(dice<7);
   
   

   
	  }

	 
	 @Test(expectedExceptions= InvalidTurnException.class )
	   public void test_rollDice_for_position_more_than_100() throws InvalidTurnException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption 
	   { Board testboard = new Board();
		
		 
	   testboard.registerPlayer("Ashika");
	   testboard.registerPlayer("Megha");
	   UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(0)).get("uuid");

	   for(Object playerObject:testboard.data.getJSONArray("players")){
	  
	            JSONObject player = (JSONObject)playerObject;
	            player.put("position", 106);
	            testboard.rollDice(uuid1);
	            
		 }
	    
	   }
	 
	 
	 @Test
	   public void test_rollDice_for_position_near_100() throws InvalidTurnException, IOException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption 
	   { Board testboard = new Board();
	   
		Object ob;
		 
	   testboard.registerPlayer("Ashika");
	   testboard.registerPlayer("Megha");
	   UUID uuid1=(UUID) ((JSONObject) testboard.data.getJSONArray("players").get(0)).get("uuid");
     
	   for(Object playerObject:testboard.data.getJSONArray("players")){
	  
	            JSONObject player = (JSONObject)playerObject;
	            player.put("position", 95);
	            Integer previousPosition=player.getInt("position");
	          //  System.out.println("PreviousPosition"+previousPosition);
		            
	            ob=testboard.rollDice(uuid1);
	            Integer Position = player.getInt("position");
	           
	           // System.out.println("Position"+Position);
	           
		       // String msg=(((JSONObject) ob).getString("message"));

		        // System.out.println(msg);
		       // Integer type = player.getInt("type");
		     //   System.out.println("Type"+boardmodel..type);
		                
		         if(Position>100)
	            { 
	        	   
	        	  // System.out.println("messages"+(((JSONObject) ob).getString("message")));
	        	   
	           assertThat(((JSONObject) ob).getString("message")).isEqualTo("Incorrect roll of dice. Player did not move");
	            }
	            else
	            {
	            	// System.out.println("messages"+(((JSONObject) ob).getString("message")));
	  	           assertThat(((JSONObject) ob).getString("message"))
	                  .isEqualTo("Player moved to " + Position);
	            }
	   }
	    
	   }
	 
	  }









	
	
	
 
	