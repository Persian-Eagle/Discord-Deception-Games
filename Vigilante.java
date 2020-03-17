public class Vigilante implements Role{
	//tempStat starts out as null, is set to Powerful when a person is healed
	private DefenseStat tempStat;

	public String getRoleName(){
		return "Vigilante";
	}

	public int getPriority(){
		return 5;
	}

	public AttackStat getAttackStat(){
		return AttackStat.BASIC;
	}

	public DefenseStat getDefenseStat(){
		if (tempStat != null) return tempStat;
  		return DefenseStat.NONE;
	}

	public boolean execute(Player actor, Player target){
		if(target.canBeKilled(actor.getAttackStat())){
			target.die();
			actor.setRole(new SuicideVigilante());
		}
		else{
			//send message to target that they were attacked
			//send message to Vigilante their attack failed
		}
	}

	public boolean hasRBImunnity(){
		return false;
	}

	public boolean hasControlImmunity(){
		return false;
	}

	public void setDefenseStat(DefenseStat newStat){
		tempStat = newStat;
	}
}
