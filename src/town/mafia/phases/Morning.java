package town.mafia.phases;

import town.DiscordGame;
import town.persons.Person;
import town.phases.Phase;
import town.phases.PhaseManager;

public class Morning extends Phase
{
	Phase nextPhase = new Day(getGame(), getPhaseManager());

	public Morning(DiscordGame game, PhaseManager pm)
	{
		super(game, pm);
	}

	@Override
	public void start()
	{
		if (getGame().peekDeathForMorning() == null)
			return;
		Person person = getGame().getDeathForMorning();
		getGame().sendMessageToTextChannel("daytime_discussion", person.getCauseOfDeath() + "\nTheir role was: " + person.getType().getName())
		.queue();
		if (getGame().peekDeathForMorning() != null)
			nextPhase = new Morning(getGame(), getPhaseManager());
	}

	@Override
	public Phase getNextPhase()
	{
		return nextPhase;
	}

	@Override
	public int getDurationInSeconds()
	{
		return 4;
	}
}