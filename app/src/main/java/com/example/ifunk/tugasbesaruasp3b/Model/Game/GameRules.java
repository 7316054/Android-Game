package com.example.ifunk.tugasbesaruasp3b.Model.Game;


public class GameRules {
    private RulesListener listener;
    private int score;
    public GameRules(RulesListener listener){
        this.listener=listener;
        this.score = 0;
    }


    public void cek(int i,float point) {
//        Log.d("cek: ", "index: "+i);
        int xBall = (int) this.listener.getXBall(i);
        int yBall = (int) this.listener.getYBall(i);
        int xHole = this.listener.getXHole();
        int yHole = this.listener.getYHole();

        int xb = xBall - 50;
        int yb = yBall - 50;
        int xh = xHole - 100;
        int yh = yHole - 100;

        int xb2 = xBall + 50;
        int yb2 = yBall + 50;
        int xh2 = xHole + 100;
        int yh2 = yHole + 100;

        if (xh < xb && xb2 < xh2 && yh < yb && yb2 < yh2) {
            listener.rand(i);
            score += point;
            listener.addScore(score);
            listener.timeUp();

        }
    }

}
