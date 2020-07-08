package town.persons;

import town.DiscordGame;
import town.GameFaction;
import town.GameRole;

// Civilian is NOT A REAL ROLE. This is a temporary useless town role to simulate games
public class Civilian extends Person
{
	public Civilian(DiscordGame game, int num, Long id)
	{
		super(game, num, id, GameRole.CIVILIAN);
	}

	@Override
	public boolean canWin()
	{
		// TODO: We can put commonly used victories in a static class
		return getGame().getPlayers().stream().filter((person) -> person.getType().getFaction() == GameFaction.TOWN
				&& person.alive).count() == getGame().getPlayers().stream().filter((person) -> person.alive).count();
	}

	@Override
	public boolean hasWon()
	{
		return getGame().hasTownFactionWon(getType().getFaction());
	}

	@Override
	public void win()
	{
		getGame().winTownFaction(getType().getFaction());
		getGame().sendMessageToTextChannel("daytime_discussion", "**Town has won!**").queue();
		getGame().endGame();
	}

	@Override
	public String getHelp()
	{
		return "Civilian is temporary. No commands, you just kind of die no matter what.";
	}
}
