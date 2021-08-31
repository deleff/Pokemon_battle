import java.util.Scanner;



public class battle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String name;
		String type;
		int level;
		int damage;
		int turn = 1;
		String battleOption;
		boolean validOption = true;

		Scanner reader = new Scanner(System.in);  // Reading from System.in
		
		
		System.out.println("Trainer Gary has challenged you to a battle. Choose your Pokemon");
		
		//Get details to Create your Pokemon
		System.out.println("Pokemon's name: ");
		name = reader.next(); // Scans the next token of the input as a String.
		System.out.println(name +"'s type: ");
		type = reader.next().toLowerCase(); // Scans the next token of the input as a String.
		
		//Convert type to a string with no white space
				if (type.contains("fire")){
					type = "fire";
				} else if (type.contains("water")){
					type = "water";
				} else if (type.contains("grass")) {
					type = "grass";
				} else {
					type = "normal";
				}		
				
		System.out.println("Pokemon's level: ");
		level = reader.nextInt(); // Scans the next token of the input as an int.

		//Create an instance of your Pokemon
		Pokemon yours = new Pokemon(name, type, level);
		System.out.println(yours.name + " has " + yours.health + "HP.");
		
		//Create Gary's Pokemon
		name = "Slagasaur";
		
		//Gary chooses the opposite of your Pokemon's type because he's a dick
		if (type == "fire"){//{type.contains("fire")){
			type = "water";
		} else if (type == "water"){
			type = "grass";
		} else if (type == "grass") {
			type = "fire";
		} else {
			type = "normal";
		}
		
		//Gary's Pokemon is weaker than yours, but can't be level 0 or less
		if (level <= 2) {
			level = 1;
		} else {
			level = level - 2;
		}
		
		//Create an instance of Gary's Pokemon
		Pokemon garys = new Pokemon(name, type, level);
		
		//Announce Gary's Pokemon
		System.out.println("Gary sent out " + garys.name 
				+ ". it's a level " + garys.level
				+ " " + garys.type + " type with " 
				+ garys.health + "HP.");
	
		
		//A loop that won't end until someone has 0 health
		while (yours.health > 0 && garys.health > 0){
			
			//Which turn is it?
			System.out.println("Turn " + turn + ":");
			
			//Choose a move
			
			
			System.out.println("Your turn. Choose 'a' to attack, 's' for strong attack,"
					+ "or 'z' to use a potion.");
					battleOption = reader.next().toLowerCase(); 
					if (battleOption.contains("a")) {
						garys.takeDamage(yours.attack(garys.type));
						
					}else if (battleOption.contains("s") && yours.strongAttackPP > 0) {
						garys.takeDamage(yours.strongAttack(garys.type));
						
					} else if (battleOption.contains("z") && yours.numPotions > 0) { //Use potion, regain health
						yours.usePotion();
					} else { //invalid option
						if (battleOption.contains("z")) { //Notify out of potions
							System.out.print("Out of potions. ");
						}
						if (battleOption.contains("s")) { //Notify out of Strong Attack PP
							System.out.print("Out of PP for this move. ");
						}
						System.out.println("Invalid option. Turn over");
					}
					
					//Report Gary's Pokemon's health
					System.out.println(garys.name + " has " + garys.health + "HP left.");
					System.out.println("");
					
					//Gary attacks
					System.out.println("Gary's turn");
					//Gary attacks using that random number
					yours.takeDamage(garys.attack(yours.type));
					//Report how much health you have left
					System.out.println("Gary used 'Attack', " 
					+ yours.name + " has " + yours.health + "HP left.");
					System.out.println("");
					
					//Next turn
					turn = turn + 1;
		}//End while fighting loop
		
	//Close the scanner and end the class
	reader.close();
	}//End Main
	
	public static boolean battleOption(String chosenOption, Pokemon you, Pokemon enemy) {
		
		boolean validOption = true;
				
				if (chosenOption.contains("a")) {
					enemy.takeDamage(you.attack(enemy.type));
					
				}else if (chosenOption.contains("s") && you.strongAttackPP > 0) {
					enemy.takeDamage(you.strongAttack(enemy.type));
					
				} else if (chosenOption.contains("z") && you.numPotions > 0) { //Use potion, regain health
					you.usePotion();
				} else { //invalid option
					if (chosenOption.contains("z")) { //Notify out of potions
						System.out.print("Out of potions. ");
					}
					if (chosenOption.contains("s")) { //Notify out of Strong Attack PP
						System.out.print("Out of PP for this move. ");
					}
					System.out.println("Choose a valid option.");
					validOption = false;
				} // End if-else
				return validOption;
	}//End battleOption()
	
	
}//End Class
