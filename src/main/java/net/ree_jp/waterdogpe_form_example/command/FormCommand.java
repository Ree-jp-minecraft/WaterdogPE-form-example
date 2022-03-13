package net.ree_jp.waterdogpe_form_example.command;

import dev.waterdog.waterdogpe.command.Command;
import dev.waterdog.waterdogpe.command.CommandSender;
import dev.waterdog.waterdogpe.player.ProxiedPlayer;
import net.ree_jp.form.elements.*;
import net.ree_jp.form.type.CustomForm;
import net.ree_jp.form.type.ModalForm;
import net.ree_jp.form.type.SimpleForm;

import java.util.ArrayList;
import java.util.Arrays;

public class FormCommand extends Command {
    public FormCommand(String name) {
        super(name);
    }

    @Override
    public boolean onExecute(CommandSender sender, String alias, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer player = (ProxiedPlayer) sender;
            SimpleForm form = new SimpleForm("Form Example", "This is a form example.", () -> player.sendMessage("click close"));

            form.addElement(new Button("This is Button", () -> player.sendMessage("click button")));

            form.addElement(new Button("ModalForm", () -> sendModalForm(player)));

            form.addElement(new Button("CustomForm", () -> sendCustomForm(player)));

            form.sendForm(player);
        }
        return true;
    }

    private void sendModalForm(ProxiedPlayer player) {
        ModalForm form = new ModalForm("ModalForm", "This is a modal form example.",
                new Button("button1", () -> player.sendMessage("click button1")),
                new Button("button2", () -> player.sendMessage("click button2")));
        form.sendForm(player);
    }

    private void sendCustomForm(ProxiedPlayer player) {
        Label label = new Label("This is a label.");
        Input input = new Input("This is a input example.", "holderText", "defaultText");
        Dropdown dropdown = new Dropdown("This is a dropdown example.", new ArrayList<>(Arrays.asList("A", "B", "C")));
        Toggle toggle = new Toggle("This is a toggle example.", false);
        Slider slider = new Slider("This is a slider example.", 0, 100, 50);
        StepSlider stepSlider = new StepSlider("This is a step slider example.", new ArrayList<>(Arrays.asList("0", "50", "100")), 1);

        CustomForm form = new CustomForm("CustomForm", () -> {
            player.sendMessage("input: " + input.getResult());
            player.sendMessage("dropdown: " + dropdown.getResult() + "(value: " + dropdown.getResultValue() + ")");
            player.sendMessage("toggle: " + toggle.getResult());
            player.sendMessage("slider: " + slider.getResult());
            player.sendMessage("stepSlider: " + stepSlider.getResult() + "(value: " + stepSlider.getResultValue() + ")");
        }, () -> player.sendMessage("click close"));

        form.addElement(label, input, dropdown, toggle, slider, stepSlider);
        form.sendForm(player);
    }
}

