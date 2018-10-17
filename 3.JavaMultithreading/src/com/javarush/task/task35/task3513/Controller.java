package com.javarush.task.task35.task3513;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter
{
    private Model model;
    private View view;
    private final static int WINNING_TILE = 2048;

    public Tile[][] getGameTiles()
    {
       return model.getGameTiles();
    }

    public int getScore()
    {
        return model.score;
    }

    public Controller(Model model)
    {
        this.model = model;
        view = new View(this);
    }

    public void resetGame()
    {
        model.score=0;
        view.isGameLost = false;
        view.isGameWon = false;
        model.resetGameTiles();
    }


    public View getView()
    {
        return view;
    }



    public void keyPressed(KeyEvent a)
    {
        if (a.getKeyCode() == KeyEvent.VK_ESCAPE) resetGame();
        if (!model.canMove()) view.isGameLost = true;
        if (!view.isGameLost&&!view.isGameWon)
        {
            if(a.getKeyCode()==KeyEvent.VK_LEFT) model.left();
            if(a.getKeyCode()==KeyEvent.VK_RIGHT) model.right();
            if(a.getKeyCode()==KeyEvent.VK_UP) model.up();
            if(a.getKeyCode()==KeyEvent.VK_DOWN) model.down();
            if(a.getKeyCode()==KeyEvent.VK_Z) model.rollback();
            if(a.getKeyCode()==KeyEvent.VK_R) model.randomMove();
            if(a.getKeyCode()==KeyEvent.VK_A) model.autoMove();
        }
        if (model.maxTile==WINNING_TILE) view.isGameWon = true;

        view.repaint();
    }
}
