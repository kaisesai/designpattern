package com.liukai.pattern.behavioral.command;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 命令模式之上帝创世纪系统
 * Created by Administrator on 2016/12/30 0030.
 */
public class TheWorld extends Frame implements ActionListener {

    //要有光命令
    private LetThereBeLightCommand letThereBeLightCommand;
    //要有大陆命令
    private LetThereBeLandCommand letThereBeLandCommand;
    //重置命令
    private ResetCommand resetCommand;
    //上帝要休息命令
    private GodRestsCommand godRestsCommand;
    //接收者对象
    private Panel panel;

    public TheWorld() throws HeadlessException {
        super("This is the world,and God says...");

        //创建面板对象
        panel = new Panel();
        panel.setBackground(Color.black);

        //将面板添加到Frame中
        add(panel);

        //创建按钮对象
        letThereBeLightCommand = new LetThereBeLightCommand("Let there be light", panel);
        letThereBeLandCommand = new LetThereBeLandCommand("Let there be land", panel);
        resetCommand = new ResetCommand("Rest", panel);
        godRestsCommand = new GodRestsCommand("God rests", panel);

        //将按钮对象添加到面板对象中
        panel.add(letThereBeLightCommand);
        panel.add(letThereBeLandCommand);
        panel.add(resetCommand);
        panel.add(godRestsCommand);

        //为各个按钮对象添加监听者对象
        letThereBeLightCommand.addActionListener(this);
        letThereBeLandCommand.addActionListener(this);
        resetCommand.addActionListener(this);
        godRestsCommand.addActionListener(this);

        setBounds(100, 100, 400, 200);
        setVisible(true);
    }

    /**
     * 实现了ActionListener监听者接口，表示此类是一个监听者，或者说是观察者。
     * 主题对象（被观察者）将持有此类的引用。当主题对象变化时将通知观察者，执行相应的动作。
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CommandFromGod command = (CommandFromGod) e.getSource();
        command.execute();
    }

    public static void main(String[] args) {
        new TheWorld();
    }
}

/**
 * 抽象命令类
 */
interface CommandFromGod {

    /**
     * 执行方法
     */
    void execute();
}

/**
 * 具体命令角色：要有光命令
 */
class LetThereBeLightCommand extends Button implements CommandFromGod {

    /**
     * 接收者对象
     */
    private Panel panel;

    public LetThereBeLightCommand(String caption, Panel panel) throws HeadlessException {
        super(caption);
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.setBackground(Color.WHITE);
    }

}

/**
 * 具体命令角色：要有大地命令
 */
class LetThereBeLandCommand extends Button implements CommandFromGod {

    /**
     * 接收者对象
     */
    private Panel panel;

    public LetThereBeLandCommand(String caption, Panel panel) throws HeadlessException {
        super(caption);
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.setBackground(Color.ORANGE);
    }

}

/**
 * 具体命令角色：复原命令
 */
class ResetCommand extends Button implements CommandFromGod {

    /**
     * 接收者对象
     */
    private Panel panel;

    public ResetCommand(String caption, Panel panel) throws HeadlessException {
        super(caption);
        this.panel = panel;
    }

    @Override
    public void execute() {
        panel.setBackground(Color.BLACK);
    }

}

/**
 * 具体命令角色：上帝休息命令
 */
class GodRestsCommand extends Button implements CommandFromGod {

    /**
     * 接收者对象
     */
    private Panel panel;

    public GodRestsCommand(String caption, Panel panel) throws HeadlessException {
        super(caption);
        this.panel = panel;
    }

    @Override
    public void execute() {
        System.exit(0);
    }

}