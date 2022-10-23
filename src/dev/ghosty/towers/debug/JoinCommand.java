package dev.ghosty.towers.debug;

import static dev.ghosty.towers.TowersConfig.messages_joining;
import static dev.ghosty.towers.TowersConfig.messages_noGame;
import static dev.ghosty.towers.TowersConfig.messages_playersOnly;
import static dev.ghosty.towers.TowersConfig.messages_prefix;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ghosty.towers.Towers;
import dev.ghosty.towers.game.Instance;

public class JoinCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sd, Command cmd, String label, String[] args) {
		if(!(sd instanceof Player)) {
			sd.sendMessage(messages_prefix+messages_playersOnly);
			return true;
		}
		Player p = (Player) sd;
		for(Instance i : Towers.getInstance().getGames())
			if(i.getPlayers().size() < Towers.getInstance().getGameInfo().maxPlayers() && i.getState().canHavePlayers()) {
				p.sendMessage(messages_prefix+messages_joining);
				i.addPlayer(p);
				return true;
			}
		
		p.sendMessage(messages_prefix+messages_noGame);
		
		return true;
	}

}
