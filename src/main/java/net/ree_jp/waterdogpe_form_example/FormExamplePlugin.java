package net.ree_jp.waterdogpe_form_example;


import dev.waterdog.waterdogpe.plugin.Plugin;
import net.ree_jp.waterdogpe_form_example.command.FormCommand;

public class FormExamplePlugin extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getCommandMap().registerCommand(new FormCommand("form"));
    }
}
