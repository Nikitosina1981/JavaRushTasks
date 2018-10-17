package com.javarush.task.task35.task3513;
import java.util.*;

public class Model
{
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    public int score;
    public int maxTile;
    private Stack previousScores;
    private Stack previousStates;
    private boolean isSaveNeeded = true;



    public Tile[][] getGameTiles()
    {
        return gameTiles;
    }

    public void autoMove()
    {
        PriorityQueue<MoveEfficiency> priorityQueue = new PriorityQueue(4,Collections.reverseOrder());
        priorityQueue.add(getMoveEfficiency(this::left));
        priorityQueue.add(getMoveEfficiency(this::right));
        priorityQueue.add(getMoveEfficiency(this::up));
        priorityQueue.add(getMoveEfficiency(this::down));
        Move move = priorityQueue.peek().getMove();
        move.move();
    }

    public boolean hasBoardChanged()
    {
        Tile[][] tmp = (Tile[][])previousStates.peek();
        for (int i = 0; i < FIELD_WIDTH; i++)
        {
            for (int j = 0; j < FIELD_WIDTH; j++)
            {
                if (tmp[i][j].value!=gameTiles[i][j].value) return true;
            }
        }
        return false;
    }

    public MoveEfficiency getMoveEfficiency(Move move)
    {
        MoveEfficiency moveEfficiency;
        move.move();
        if (hasBoardChanged()) moveEfficiency = new MoveEfficiency(getEmptyTiles().size(), score, move);
        else moveEfficiency = new MoveEfficiency(-1, 0, move);
        rollback();

        return moveEfficiency;
    }

    public void randomMove()
    {
        int n = ((int) (Math.random() * 100)) % 4;
        if (n==0) left();
        if (n==1) right();
        if (n==2) up();
        if (n==3) down();
    }

    private void saveState (Tile[][] state)
    {
        int tmpScore = score;
        Tile[][] tmp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++)
        {
            for (int j = 0; j < FIELD_WIDTH; j++)
            {
                tmp[i][j] = new Tile(state[i][j].value);
            }
        }
        previousStates.push(tmp);
        previousScores.push(tmpScore);
        isSaveNeeded = false;
    }

    public void rollback()
    {
        if (!previousScores.empty()&&!previousStates.empty())
        {
            gameTiles = (Tile[][]) previousStates.pop();
            score = (int) previousScores.pop();
        }
    }

    public boolean canMove() {
        if(!getEmptyTiles().isEmpty()) return true;

        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[i][j].value == gameTiles[i][j-1].value)
                    return true;
            }
        }
        for (int i = 0; i < gameTiles.length; i++) {
            for (int j = 1; j < gameTiles.length; j++) {
                if (gameTiles[j][i].value == gameTiles[j-1][i]. value)return true;
            }
        }
        return false;
    }

    public Model()
    {
       resetGameTiles();
       previousScores = new Stack();
       previousStates = new Stack();
    }

    public void resetGameTiles()
    {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int n=0;n<FIELD_WIDTH;n++)
            for (int m=0; m<FIELD_WIDTH;m++)
                gameTiles[n][m] = new Tile();
        score = 0;
        maxTile = 0;

        addTile();
        addTile();

    }

    private void addTile()
    {
        List<Tile> b = getEmptyTiles();
        if (b.size()>0)
        {
            int target = (int) (b.size() * Math.random());
            int newValue = Math.random() < 0.9 ? 2 : 4;
            b.get(target).value = newValue;
        }
    }

    private List<Tile> getEmptyTiles()
    {
        List<Tile> result = new ArrayList<>();
        for (int n=0;n<FIELD_WIDTH;n++)
            for (int m=0; m<FIELD_WIDTH;m++)
                if (gameTiles[n][m].value==0) result.add(gameTiles[n][m]);
        return result;
    }

    private boolean compressTiles(Tile[] tiles)
    {
        boolean change=false;
        int a = 0;
        for (int i=0; i<tiles.length;i++)
        {
            if (tiles[i].value==0)
            {
                for (int o=i+1;o<tiles.length;o++)
                {
                    if (tiles[o].value>0)
                    {
                        tiles[i].value = tiles[o].value;
                        tiles[o].value = 0;
                        change = true;
                        o=tiles.length;
                    }
                }
            }
        }
        return change;
    }
    private boolean mergeTiles(Tile[] tiles)
    {
        boolean change = false;
            int x = 0;
            for (int i = 1; i < tiles.length; i++)
            {
                if (tiles[i].value == tiles[i - 1].value && tiles[i].value>0)
                {
                    change = true;
                    tiles[x].value = tiles[i].value * 2;
                    score+=tiles[x].value;
                    if (maxTile<tiles[x].value) maxTile = tiles[x].value;
                    tiles[i].value=0;
                    compressTiles(tiles);
                }
                x++;
            }
            return change;
    }

    public void left()
    {
       saveState(gameTiles);
        boolean check = false;
        for (int i=0; i<FIELD_WIDTH;i++)
        {
            if (compressTiles(gameTiles[i])) check=true;
            if (mergeTiles(gameTiles[i])) check = true;
        }
        if (check)
        {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void right()
    {
       saveState(gameTiles);
        boolean check = false;
        Tile[] tmpSet = new Tile[FIELD_WIDTH];
        int index;
        for (int m = 0; m<FIELD_WIDTH; m++)
        {
            index = 0;
            for (int i = FIELD_WIDTH - 1; i >= 0; i--)
            {
                tmpSet[index++] = gameTiles[m][i];
            }
            if (compressTiles(tmpSet)) check=true;
            if (mergeTiles(tmpSet)) check = true;
        }
        if (check)
        {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void up()
    {
       saveState(gameTiles);
        boolean check = false;
        Tile[] tmpSet = new Tile[FIELD_WIDTH];
        int index;
        for (int m = 0; m<FIELD_WIDTH; m++)
        {
            index = 0;
            for (int i = 0; i < FIELD_WIDTH; i++)
            {
                tmpSet[index++] = gameTiles[i][m];
            }
            if (compressTiles(tmpSet)) check=true;
            if (mergeTiles(tmpSet)) check = true;
        }
        if (check)
        {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void down()
    {
        saveState(gameTiles);
        boolean check = false;
        Tile[] tmpSet = new Tile[FIELD_WIDTH];
        int index;
        for (int m = 0; m < FIELD_WIDTH; m++)
        {
            index = 0;
            for (int i = FIELD_WIDTH - 1; i >= 0; i--)
            {
                tmpSet[index++] = gameTiles[i][m];
            }
            if (compressTiles(tmpSet)) check = true;
            if (mergeTiles(tmpSet)) check = true;
        }
        if (check)
        {
            addTile();
            isSaveNeeded = true;
        }
    }

    private void printTiles()
    {
        for (int n=0;n<FIELD_WIDTH;n++)
        {
            for (int m = 0; m < FIELD_WIDTH; m++)
            {
                System.out.print(gameTiles[n][m]+" ");
            }
            System.out.println("");
        }
        System.out.println("");
    }


}
