package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener
{
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public UndoListener getUndoListener()
    {
        return undoListener;
    }

    public void resetUndo()
    {
        undoManager.discardAllEdits();
    }

    public void undo()
    {
       try
       {
           undoManager.undo();
       }
       catch (Exception e)
       {
           ExceptionHandler.log(e);
       }

    }

    public void redo()
    {
        try
        {
            undoManager.redo();
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }

    }

    public boolean isHtmlTabSelected()
    {
        if (tabbedPane.getSelectedIndex()==0) return true;
        else return false;
    }

    public Controller getController()
    {
        return controller;
    }

    public boolean canRedo()
    {
        return undoManager.canRedo();
    }

    public boolean canUndo()
    {
        return undoManager.canUndo();
    }

    public void setController(Controller controller)
    {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String name = e.getActionCommand();
        if (name.equals("Новый")) controller.createNewDocument();
        if (name.equals("Открыть")) controller.openDocument();
        if (name.equals("Сохранить")) controller.saveDocument();
        if (name.equals("Сохранить как...")) controller.saveDocumentAs();
        if (name.equals("Выход")) controller.exit();
        if (name.equals("О программе")) this.showAbout();
    }

    public void selectHtmlTab()
    {
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void init()
    {
        initGui();
        this.addWindowListener(new FrameListener(this));
        this.setVisible(true);

    }


    public void exit()
    {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        this.getContentPane().add(jMenuBar, BorderLayout.NORTH);
    }

    public void initEditor()
    {
        htmlTextPane.setContentType("text/html");
        JScrollPane jsp = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jsp);
        JScrollPane jsp2 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jsp2);
        tabbedPane.setPreferredSize(new Dimension(1000,500));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        this.getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public void initGui()
    {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged()
    {
        if (tabbedPane.getSelectedIndex()==0) controller.setPlainText(plainTextPane.getText());
        else plainTextPane.setText(controller.getPlainText());
        resetUndo();
    }

    public void update()
    {
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout()
    {
        JOptionPane.showMessageDialog(getContentPane(), "Version 1.0", "About", JOptionPane.INFORMATION_MESSAGE);
    }


    public View ()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e)
        {
            ExceptionHandler.log(e);
        }
    }
}
