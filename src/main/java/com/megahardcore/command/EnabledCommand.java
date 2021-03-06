package com.megahardcore.command;


import com.megahardcore.MegaHardCore;
import com.megahardcore.config.RootConfig;
import com.megahardcore.service.ICommand;
import com.megahardcore.service.PermissionNode;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/** @author Diemex */
public class EnabledCommand implements ICommand
{
    @Override
    public boolean execute (MegaHardCore plugin, CommandSender sender, Command command, String label, String[] args)
    {
        if (sender.hasPermission(PermissionNode.ADMIN.getNode()))
        {
            World world = null;
            if (args.length > 0)
                world = plugin.getServer().getWorld(args[0]);
            else if (sender instanceof Player)
                world = ((Player) sender).getWorld();
            if (world == null)
                sender.sendMessage(String.format("A world named %s doesn't exist", args[0]));
            else
            {
                RootConfig CFG = plugin.getModuleForClass(RootConfig.class);
                if (CFG.isEnabledForAll()) sender.sendMessage(ChatColor.GREEN + "MegaHardCore is enabled for all worlds");
                else
                {
                    boolean enabled = Arrays.asList(CFG.getEnabledWorlds()).contains(world.getName());
                    sender.sendMessage(
                            String.format("%s MegaHardCore is %s in world %s", enabled ? ChatColor.GREEN : ChatColor.RED, enabled ? "enabled" : "disabled", world.getName()));

                    if (CFG.getEnabledWorlds().length > 0) sender.sendMessage(String.format("%sEHM enabled for: %s", ChatColor.GREEN, Arrays.toString(CFG.getEnabledWorlds())));
                    List<String> enabledWorlds  = Arrays.asList(CFG.getEnabledWorlds());
                    List<String> disabledWorlds = plugin.getServer().getWorlds().stream().map(World::getName).collect(Collectors.toList());
                    disabledWorlds.removeAll(enabledWorlds);
                    if (disabledWorlds.size() > 0) sender.sendMessage(String.format("%sEHM disabled for: %s", ChatColor.RED, disabledWorlds));
                    sender.sendMessage(String.format("%sCurrent world: %s %s", enabled ? ChatColor.GREEN : ChatColor.RED, world.getName(), enabled ? "enabled" : "disabled"));

                }
            }
        } else
            sender.sendMessage(ChatColor.RED + plugin.getTag() + " Lack permission: " + PermissionNode.ADMIN.getNode());
        return true;
    }
}
