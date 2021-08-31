
public class Pokemon {


	  String name; 			// This Pokemon's name
	  String type; 			// fire, water, or grass
	  int level;	 	    // Higher level = more health
	  int health = 36;		// Default starting HP
	  int maxHealth = 36;	// Default maximum HP 
	  int numPotions = 1;	// Default number of potions
	  int strongAttackPP = 5; //Number of times you can attack with the strong move

	  //Default constructor, No info, but creates a new instance of a Pokemon
	  Pokemon(){
		  name = "Rattata";
		  level = 5;
		  type = "normal";
	  }

	  //Detailed constructor
	    Pokemon(String itsName, String itsType, int itsLevel){
	    name = itsName;    
	    type = itsType;
	    level = itsLevel;
	    health = health + (itsLevel * 4);
	    maxHealth = health;
	  }
	  
	  /*
	  Method to attack. Has unlimited uses.
	  Double damage against weaker types, like water vs. fire
	  */
	  public int attack(String enemyType){
		int damage = 0;
	    if (health == 0){ //Can't attack if health = 0
	      //damage = 0;
	      System.out.println(name + " has fainted, and cannot attack.");
	      } else {
	    	//Use a random number to generate the amount of damage
			damage = (int)(Math.random() * (7 - 1)); 
	    }
	    	if //Double the damage against weaker elements     
	      ( (type == "water" && enemyType == "fire")     ||
	    		 (type == "fire" && enemyType == "grass")    ||
	      	 (type == "grass" && enemyType == "water") ) {
	      	 		damage = damage * 2;  
	        } 
	    return damage;
	  }//End attack
	  
	  /*
	  Method to attack. Has limited uses (PP)
	  Double damage against weaker types, like water vs. fire
	  */
	  public int strongAttack(String enemyType) {
		  int damage = 7; //Attack damage		  
	    	
	    	if (strongAttackPP > 0 || health > 0){ //Can't attack if PP or HP = 0
	    		
	  		  //Make amount of damage proportional to your level
	  		  if (level > 15){ //Any lower level and damage would be < 7
	    		if (level % 2 == 1){
	  			  damage = (level - 1) / 2;  
	  		  } else {
	  			  damage = level / 2;
	  		  }//End else
	  		 }//End if (level > 15)
	  		    
	  		if //Double the damage against weaker elements      
	  	      ( (type == "water" && enemyType == "fire")     ||
	  	    		 (type == "fire" && enemyType == "grass")    ||
	  	      	 (type == "grass" && enemyType == "water") ) {
	  	      	 		damage = damage * 2;  
	  	        } //End elemental if
	  		  
	  		  strongAttackPP = strongAttackPP - 1; //Reduce PP for this move by 1
	  		  if (strongAttackPP == 0) {
	  			System.out.println("You're out of PP for this move");
	  		  }
	    	}// end if (strongAttackPP > 0 || health > 0) 
	    	else {
	    		damage = 0; //if pp or HP = 0, can't attack
	  	      System.out.println(name + " cannot use this attack.");
	    	} //End else
	    	return damage;
	    }
	  
	  /*
	  method to take damage, 
	  type advantage is calculated in the attack, not here
	  */
	  public void takeDamage(int damage){
	    health = health - damage;
	    
	    if (health <= 0) { //if health = 0, your Pokemon has fainted
	      health = 0; 
	      System.out.println(name + " has fainted!");
	    }//End if
	  } //End takeDamage

	  /*
	  Method to gain health
	  */
	  public void usePotion() {
		  int potionValue = 15; //Value in a variable in case to make it easy to change later
		  
		  if (numPotions >= 1){ //must have at least 1 potion to use
			  if (health + potionValue > maxHealth){ //Can't have more HP than the maximum amount
				  health = maxHealth;
				  System.out.println("Used potion, you now have " + health + "HP.");
			  } else {
				  health = health + potionValue; //Add the potion
				  System.out.println("Used potion, you now have " + health + "HP.");
			  } //End else
			  	  numPotions = numPotions - 1; //Now you have 1 less potion
		  } else {
			  System.out.println("Out of potions");
		  }//End else
	  }//End usePotion

	
}//End Pokemon
