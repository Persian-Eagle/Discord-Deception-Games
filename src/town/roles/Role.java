package town.roles;

import java.util.List;

import javax.annotation.Nonnull;

import town.mafia.phases.Morning;
import town.persons.Attributes;
import town.persons.DiscordGamePerson;
import town.phases.Phase;

public interface Role
{
	Attributes getAttributes();

	List<DiscordGamePerson> getPossibleTargets(DiscordGamePerson user);

	String ability(DiscordGamePerson user, List<DiscordGamePerson> list);

	default boolean hasWon(@Nonnull DiscordGamePerson user)
	{
		if (user == null) throw new NullPointerException("User cannot be an exception");
		return user.getGame().hasTownFactionWon(getFaction());
	}

	default boolean canWin(@Nonnull DiscordGamePerson user)
	{
		if (user == null) throw new NullPointerException("User cannot be an exception");
		return getFaction().canWin(user.getGame());
	}

	default void win(@Nonnull DiscordGamePerson user)
	{
		if (user == null) throw new NullPointerException("User cannot be an exception");
		getFaction().win(user.getGame());
	}

	String getHelp();

	String getName();

	Faction getFaction();

	RoleData getInitialRoleData();

	int getPriority();

	/**
	 * Cancel the planned action.
	 * @return A string meant for the user to read.
	 */
	default String cancelAction(@Nonnull DiscordGamePerson user)
	{
		if (user == null) throw new NullPointerException("User cannot be an exception");
		if (user.getTownEvent() == null) return "There's no action to cancel";
		user.getGame().removeEvent(user.getTownEvent());
		return "Action canceled";
	}

	/**
	 * This method should be called when the phase changes.
	 * @param phase The new phase.
	 */
	default void onPhaseChange(@Nonnull DiscordGamePerson user, @Nonnull Phase phase)
	{
		if (user == null) throw new NullPointerException("User cannot be null");
		if (phase == null) throw new NullPointerException("Phase cannot be null");
		if (phase instanceof Morning)
		{
			user.clearTownEvent();
			user.clearTemporaryAttributes();
		}
	}
}
